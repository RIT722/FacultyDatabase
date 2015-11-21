
import java.util.ArrayList;

public class DLFaculty {
    String firstName;
    String lastName;
    String password;
    String email;
    boolean askHelp;
    int ID;
    
    //Faculty constructor
    public DLFaculty(int ID){
        this.ID = ID;
    }
    
    //Accessor methods
    public String getFN(){
        return firstName;
    }
    
    public String getLN(){
        return lastName;
    }
    
    public String getPass(){
        return password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public boolean getHelp(){
        return askHelp;
    }
    
    //Gets values from database and loads them into a faculty object
        public boolean fetch() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        String sql = "SELECT * FROM Faculty WHERE ID = ?;";
        ArrayList<String> facID = new ArrayList();
        facID.add(Integer.toString(ID));
        try{
            ArrayList<ArrayList<String>> rs = msd.getData(sql, facID);
            firstName = (rs.get(0)).get(1);
            lastName = (rs.get(0)).get(2);
            password = (rs.get(0)).get(3);
            email = (rs.get(0)).get(4);
            askHelp = Boolean.parseBoolean((rs.get(0)).get(5));            
            return true;
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "SQL string = " + sql, "Error in fetch() of Faculty");
        }
    }
    
    //Updates the database using values from a faculty object
    public void put() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(firstName);
            values.add(lastName);
            values.add(password);
            values.add(email);
            values.add(askHelp);
            values.add(ID);

            msd.setData("UPDATE Faculty SET fName = ?, lName = ?,"
                    + "password = ?, email = ?, askHelp = ? WHERE id = ?;", values);
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in put() of Faculty");
        }
    }
    
    //inserts values into the faculty table from a faculty object
    public void post() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(ID);
            values.add(firstName);
            values.add(lastName);
            values.add(password);
            values.add(email);
            values.add(askHelp);
            
            msd.setData("INSERT INTO Faculty VALUES(?, ?, ?, ?, ?, ?);", values);
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in post() of Faculty");
        }
    }
    
    //deletes entries from the faculty table using a faculty object
    public void delete() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{   
            ArrayList values = new ArrayList();
            values.add(ID);
            msd.setData("DELETE FROM Faculty WHERE id = ?;", values);
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in delete() of Faculty");
        }
    }
    
    
    //faculty login
    //Consider adding unique username to password
   public static boolean facultyLogin(String email, String password) throws DLException{
        // Nazar 
    }
    
    
        
    //Inserts a student into the student table using a faculty object
    public void addStudent(String studentName) throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(studentName);
            values.add(ID);
            
            msd.setData("INSERT INTO student(name, facultyID) VALUES(?, ?)", values);
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in addStudent() of Faculty");
        }
    }
    
    //Updates the needHelp boolean in the faculty table
    //Change to use text box and return string value
    public void needHelp(boolean help) throws DLException{
         MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(help);
            values.add(ID);

            msd.setData("UPDATE Faculty SET askHelp = ? WHERE id = ?;", values);
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in needHelp() of Faculty");
        }
    }
    
       
    
    
    public void getAllProfessorInfo(String ProfName) throws SQLException {
    
    // Nazar
    }
    
    // Will return the faculty name, email, and list of papers
    public void getAllProfessorInfo(String ProfName) throws SQLException {
    
    // 
    }
    
    public void getAllStudentNames(String ProfName) throws SQLException{
    
    
    // Nazar
    }

}