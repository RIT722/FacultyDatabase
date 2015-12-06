

import java.lang.*;
import java.sql.*;
import java.util.*;

public class MySQLDatabase { //Database connector class
    private static final MySQLDatabase INSTANCE = new MySQLDatabase();
            
    static Connection conn = null; //Connection object
    private String userName = "root";
    private String password;
    private String url = "jdbc:mysql://127.0.0.1/722Project";
    public boolean connection = false;
    public boolean closeVar = false;
    HashMap<String, PreparedStatement> prepString = new HashMap();

 
    private MySQLDatabase(){ 
	}
    
    public static MySQLDatabase getInstance(){
        return INSTANCE;
        }
    
	public void setPassword(String pw) {
		password = pw;
	}
	
    public boolean connect() throws DLException{ //makes connection to database
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); //sets driver 
        }
        catch(ClassNotFoundException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Driver error in connect()");
        }
        
        try{
            conn = DriverManager.getConnection(url, userName, password);
            connection = true;
        }
        catch(SQLException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "URL=" + url, "Connection error in MySQLDatabase");
        }
        return connection;
    }
    public boolean close()throws DLException { //closes connection to database
        try{
            if(conn != null)   
               conn.close(); 
            closeVar = true;
         }
         catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in close() of MySQLDatabase");
        }
        return closeVar;
    }
    
    //gets query results from database
    public ArrayList<ArrayList<String>> getData(String sql) throws DLException{
        try{
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            
            int row = 0;
            ArrayList<ArrayList<String>> arry = new ArrayList<ArrayList<String>>();
            
            while(rs.next()){
                ArrayList<String> temp = new ArrayList(numCols);
                for(int i = 1; i <=numCols; i++)
                    temp.add(rs.getString(i));
                arry.add(temp);
            }
            return arry;
        }
        catch(SQLException|RuntimeException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getData() of MySQLDatabase", "SQL = "+ sql);
        }

    }
    
    public ArrayList<ArrayList<String>> getData(String sql, boolean b) throws DLException{
        
        ArrayList<ArrayList<String>> aList = getData(sql);
        
        if(!b){
            return aList;
        }
        
        else
        {
            aList.add(0, new ArrayList());
            try{
                Statement stmnt = MySQLDatabase.conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i = 1; i <= rsmd.getColumnCount(); i++){
                    String colName = rsmd.getColumnName(i);
                    aList.get(0).add(colName);
                }
            }
            catch(SQLException e){
                throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase boolean getData()");
            }
                  
            return aList;
        }
        
    }
    
    public ArrayList<ArrayList<String>> getData(String sql, ArrayList values) throws DLException{
        try{
            PreparedStatement stmt = prepare(sql, values);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            
            ArrayList<ArrayList<String>> arry = new ArrayList<ArrayList<String>>();
            
            while(rs.next()){
                ArrayList<String> temp = new ArrayList(numCols);
                for(int i = 1; i <=numCols; i++)
                    temp.add(rs.getString(i));
                arry.add(temp);
            }
                   
            return arry;
        }
        catch(SQLException|RuntimeException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getData() of MySQLDatabase", "SQL = "+ sql);
        }
    }
    
    public ArrayList<BLPaper> getPapers(String sql, ArrayList values) throws DLException{
        try{
            PreparedStatement stmt = prepare(sql, values);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            
            ArrayList<BLPaper> arry = new ArrayList<BLPaper>();
            
            while(rs.next()){
               BLPaper temp = new BLPaper(rs.getInt("id"), rs.getString("title"), rs.getString("abstract"), rs.getString("citation"));
        
                arry.add(temp);
            }
                   
            return arry;
        }
        catch(SQLException|RuntimeException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getData() of MySQLDatabase", "SQL = "+ sql);
        }
    }
    
    public ArrayList<BLPaper> getPapers(String sql) throws DLException{
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            
            ArrayList<BLPaper> arry = new ArrayList<BLPaper>();
            
            while(rs.next()){
               BLPaper temp = new BLPaper(rs.getInt("id"), rs.getString("title"), rs.getString("abstract"), rs.getString("citation"));
        
                arry.add(temp);
            }
                   
            return arry;
        }
        catch(SQLException|RuntimeException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getData() of MySQLDatabase", "SQL = "+ sql);
        }
    }
    
    //Executes updates, deletes, and inserts into database
    public boolean setData(String sql) throws DLException{
        try{
            Statement stmnt = conn.createStatement();
            boolean rc = stmnt.execute(sql);     
            return rc;
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in setData() of MySQLDatabase", "SQL = " + sql);
        }
    }
    
    public boolean setData(String sql, ArrayList values) throws DLException{
        boolean success = false;
        try{
            PreparedStatement stmt = prepare(sql, values);
            boolean rc = stmt.execute();
            success = true;
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in setData() of MySQLDatabase", "SQL = " + sql);
        }
        return success;
    }
    
    public PreparedStatement prepare(String sql, ArrayList values) throws DLException{
        PreparedStatement stmt;
        try{
            if(prepString.containsKey(sql))
                {
                    stmt = prepString.get(sql);
                    for(int i = 0; i < values.size(); i++)
                        stmt.setObject(i+1, values.get(i));
                }
            else{
                    stmt = conn.prepareStatement(sql);
                    for(int i = 0; i < values.size(); i++){
                        stmt.setObject(i+1, values.get(i));
                    prepString.put(sql, stmt);
                    }
                }
            return stmt;
        }
        
        catch(SQLException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase prepare()");
        }
        
    }
    

    
    public int executeStmt(String sql, ArrayList values) throws DLException{
        try{
            PreparedStatement stmt = prepare(sql, values);
            int result = stmt.executeUpdate();
            return result;
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase executeStmt()");
        }
        
    }
    
    public void startTrans() throws DLException{
        try{
            conn.setAutoCommit(false);
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase startTrans()");
        }
    }
    
    public void endTrans() throws DLException{
       try{
           conn.commit();
           conn.setAutoCommit(true);
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase endTrans()");
        } 
    }
    
    public void rollbackTrans() throws DLException{
        try{
            conn.rollback();
            conn.setAutoCommit(true);
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase rollbackTrans()");
        }
    }
}
