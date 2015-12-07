/**
 *
 * @author Group 2: Chris Penepent, Katherine Shaw, Fahad Alotaibi, Nazar Al-Wattar
 */
/* This class contains the methods for connecting to the database, getting data,
updating the database, preparing statements, executing prepared statements, 
starting transactions, committing transactions, and rolling back transactions
*/

import java.lang.*;
import java.sql.*;
import java.util.*;

public class MySQLDatabase { //Database connector class
    private static final MySQLDatabase INSTANCE = new MySQLDatabase();
            
    static Connection conn = null; //Connection object
    private String userName = "root"; //Database user name
    private String password;        //Database password variable
    private String url = "jdbc:mysql://127.0.0.1/722Project"; //Database url
    public boolean connection = false;
    public boolean closeVar = false;
    HashMap<String, PreparedStatement> prepString = new HashMap(); //HashMap for saving prepared Statements

 
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
            conn = DriverManager.getConnection(url, userName, password); //creates Connection object
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
    
    //Gets data from database with or without attribute names, depending on boolean
    public ArrayList<ArrayList<String>> getData(String sql, boolean b) throws DLException{
        
        ArrayList<ArrayList<String>> aList = getData(sql);
        
        if(!b){ //if boolean is false, return without attribute names
            return aList;
        }
        
        else //if boolean is true, return with attribute names
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
    
    //Gets data from database using prepared statements
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
    
    //Gets list of BLPaper objects using prepared statements
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
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getPapers() of MySQLDatabase", "SQL = "+ sql);
        }
    }
    
    //Gets list of BLPaper objects (not using prepared statements)
    public ArrayList<BLPaper> getPapers(String sql) throws DLException{
        try{
            PreparedStatement stmt = prepare(sql);
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
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getPapers() of MySQLDatabase", "SQL = "+ sql);
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
    
    //Executes updates, deletes, and inserts into the database using prepared statements
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
    
    //creates PreparedStatements using a set of given values
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
    
    //Creates PreparedStatements
    public PreparedStatement prepare(String sql) throws DLException{
        PreparedStatement stmt;
        try{
            if(prepString.containsKey(sql))
                {
                    stmt = prepString.get(sql);
                }
            else{
                    stmt = conn.prepareStatement(sql);
                    prepString.put(sql, stmt);
                }
            return stmt;
        }
        
        catch(SQLException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase prepare()");
        }
        
    }
    
    //Executes prepared statements
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
    
    //Starts a transaction by turning off autocommit
    public void startTrans() throws DLException{
        try{
            conn.setAutoCommit(false);
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase startTrans()");
        }
    }
    
    //Ends a transaction by committing the changes and turning autocommit on
    public void endTrans() throws DLException{
       try{
           conn.commit();
           conn.setAutoCommit(true);
        }
        catch(SQLException se){
            throw new DLException(se, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in MySQLDatabase endTrans()");
        } 
    }
    
    //Rolls back changes and turns autocommit back on
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
