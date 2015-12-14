import java.util.ArrayList;

/**
 *
 * @author Group 2: Nazar Al-Wattar, Fahad Alotaibi, Katherine Shaw, Chris Penepent
 */
//Data layer class for Faculty entity
public class DLFaculty {
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected String askHelp;
    protected int ID;
    
    public DLFaculty(){
        
    }
    
    //Faculty constructor
    public DLFaculty(int ID){
        this.ID = ID;
    }
    
    /* Mutators */
    public void setFN(String fn){
        firstName = fn;
    }
   
    public void setLN(String ln){
        lastName = ln;
    }
    
    public void setEmail(String Email){
        email = Email;
    }
    
    public void setPass(String pass){
        password = pass;
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
    
    public String getHelp(){
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
            askHelp = (rs.get(0)).get(5);            
            return true;
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "SQL string = " + sql, "Error in fetch() of Faculty");
        }
    }
    
    /*Made changes*/
    //Updates the database using values from a faculty object
    public void put() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(firstName);
            values.add(lastName);
            values.add(email);
            values.add(ID);

            msd.setData("UPDATE Faculty SET fName = ?, lName = ?,"
                    + "email = ? WHERE id = ?;", values);
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in put() of Faculty");
        }
    }
    
    /*Modified method*/
    //inserts values into the faculty table from a faculty object
    public int post() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(firstName);
            values.add(lastName);
            values.add(password);
            values.add(email);
            
            msd.setData("INSERT INTO Faculty(fname, lname, password,email) VALUES(?, ?, ?, ?);", values);
            ArrayList<ArrayList<String>> rs = msd.getData("SELECT LAST_INSERT_ID()");
            ID = Integer.parseInt(rs.get(0).get(0));
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in post() of Faculty");
        }
        return ID;
    }
    
    /* Modified method */
    //deletes entries from the faculty table using a faculty object
    public void delete() throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{   
            ArrayList values = new ArrayList();
            values.add(ID);
            msd.startTrans();
            msd.setData("DELETE papers FROM (faculty INNER JOIN authorship ON faculty.id = facultyId) JOIN papers ON papers.id = paperId WHERE facultyId = ?;", values);
            msd.setData("DELETE FROM faculty WHERE id = ?", values);
            msd.endTrans();
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in delete() of Faculty");
        }
    }
    
    //attempt to select facultyid from the Faculty table with the given credentials
	public static int facultyLogin(String email, String password) throws DLException{
	   int id;
	   ArrayList<ArrayList<String>> result;
	   MySQLDatabase db = MySQLDatabase.getInstance();
	   try {
		   ArrayList<String> values = new ArrayList();
		   values.add(email);
		   values.add(password);
		   result = db.getData("SELECT id FROM Faculty WHERE email=? AND password=?", values);
		   id = Integer.parseInt(result.get(0).get(0));
		   return id;
	   }
	   catch(DLException e) {
		   throw e;
	   }
	   catch(IndexOutOfBoundsException e) {
		   throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in facultyLogin() of Faculty");
	   }
    }
    
        
    //Inserts a student into the student table using a faculty object
    public boolean addStudent(String studentName) throws DLException{
        MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            ArrayList values = new ArrayList();
            values.add(studentName);
            values.add(ID);
            
            ArrayList name = new ArrayList();
            name.add(studentName);
            ArrayList<ArrayList<String>> studentID = msd.getData("SELECT ID from student WHERE name = ?", name);
            if(studentID.size() > 0)
            {
                return false;
            }
            else 
                msd.setData("INSERT INTO student(name, facultyID) VALUES(?, ?)", values);
                return true;
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in addStudent() of Faculty");
        }
    }
    
    //Updates the needHelp boolean in the faculty table
    public void needHelp(String help) throws DLException{
         MySQLDatabase msd = MySQLDatabase.getInstance();        
        try{
            if(help.isEmpty())
            {
                ArrayList nullValues = new ArrayList();
                nullValues.add(ID);
                msd.setData("UPDATE Faculty SET askHelp = NULL WHERE id = ?;", nullValues);
            }
            else
            {
                ArrayList values = new ArrayList();
                values.add(help);
                values.add(ID);

                msd.setData("UPDATE Faculty SET askHelp = ? WHERE id = ?;", values);
            }
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in needHelp() of Faculty");
        }
    }
    
      
    //get student (s) name for this faculty with this ID
    public ArrayList<ArrayList<String>> getAllProfCurrentStudentName() throws DLException{
		ArrayList<ArrayList<String>> studentList;

        try{
            MySQLDatabase msd = MySQLDatabase.getInstance();  
            studentList = msd.getData("select student.name from student join faculty on"+
                " ((faculty.id = student.facultyID) AND faculty.id ="+  ID +")group by student.id;");
			
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getAllProfCurrentStudentName Method");
        }
        return studentList;
	}
    
   
    //get All Papers for this faculty with this ID 
    public ArrayList<ArrayList<String>> getAllProfPapers() throws DLException{
		ArrayList<ArrayList<String>> profPapersList;

        try{
            MySQLDatabase msd = MySQLDatabase.getInstance();  
            profPapersList = msd.getData("Select papers.id, papers.title from papers join"+
                    " faculty join authorship on "+
            "((papers.id = authorship.paperId) AND (faculty.id = authorship.facultyId))AND "+
                    "faculty.id ="+ ID + " group by papers.id;");
			
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getAllProfPapers Method");
        }
       
        return profPapersList;
	} 
    
    /*getAllProfs Method to return all the professor names */
	public static ArrayList<ArrayList<String>> getAllProfs() throws DLException{
		ArrayList<ArrayList<String>> profList;

        try{
            MySQLDatabase msd = MySQLDatabase.getInstance();  
            profList = msd.getData("SELECT Id, fname, lname  FROM faculty");
			
            
        }
        catch(RuntimeException e){
            throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in getAllProfs Method");
        }
        return profList;
	}
}