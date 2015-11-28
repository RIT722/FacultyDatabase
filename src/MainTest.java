
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {

    
     public ArrayList<ArrayList<String>> currentStudent;
    public ArrayList<ArrayList<String>> profPapers;
    
    
    //Constractor
    public MainTest()
    {}
    
    private static void initCom()
    {
    
          boolean connection;
        boolean closeVar;
        
        MySQLDatabase MySQLDB = MySQLDatabase.getInstance();
            
            try{
                connection = MySQLDB.connect();
                if(connection)
                System.out.println("connection successful"); //Will print if connection is successful
            } //attempt connection to database
            catch(DLException DLE){
                System.out.println("Error: cannot complete operation");
            }
                    
            
        int ID = 1;
        BLFaculty bL = new BLFaculty(ID);
       
        try{
            bL.fetch();
                System.out.println("Here is the full information for this faculty Member\n\n");       
              System.out.println(bL.firstName + "" + bL.lastName);
              System.out.println(bL.email);
                System.out.println(bL.askHelp);
              
        }catch(DLException e)
        {
        //Do nothing
        }
          MainTest myTest = new MainTest();  
        
        try {
            myTest.profPapers = bL.profPapersList();
        }
        catch(DLException e){
            //Do nothing
        }
        
        
        
        
        String[] profPapersTitle = new String[myTest.profPapers.size()+1];
        profPapersTitle[0] = "";  
  for (int i=1;i<profPapersTitle.length;i++) {
            profPapersTitle[i] = myTest.profPapers.get(i-1).get(1);
            System.out.println("This is the papers ID depends on the facultyID \n"+i +" "+myTest.profPapers.get(i-1).get(0));
            System.out.println("This is the papers title depends on the facultyID \n" +i+" "+ profPapersTitle[i]);
        }   
            
            
          try {
            myTest.currentStudent = bL.profCurrentStudentList();
        }
        catch(DLException e){
            //Do nothing
        }
          
         
        String[] profStudentNames = new String[myTest.currentStudent.size()+1];
        profStudentNames[0] = "";  
        for (int i=1;i<profStudentNames.length;i++) {
            profStudentNames[i] = myTest.currentStudent.get(i-1).get(0);
            System.out.println("This is the Student(s) Name depends on the facultyID \n"+i+" "+profStudentNames[i]);
            
        }  
          int paperID =1;  
       BLPaper myPaper = new BLPaper(paperID);
         try {
             myPaper.fetch();
         } catch (DLException ex) {
             Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
         }
          System.out.println("My paper Information");
          System.out.println("--------------------");
        System.out.println("Paper ID: " + myPaper.ID);
         System.out.println("Paper Title: " + myPaper.title);
          System.out.println("Paper Abstract: " + myPaper.pAbstract);
           System.out.println("Paper Citation: " + myPaper.citation);
        
               
          
    
    }
    
    public static void main(String[] args) throws DLException{
        
    
            initCom();
            
            
	/*  
   try{
   // Get All Ids Students Depend On Faculty Ids 
          BLFaculty faculty = new BLFaculty(1);
          faculty.getAllStudentNames();
   
   
   // Get Paper Information Depend On Paper Id
        BLPaper x = new BLPaper();
        x.SetPaperId(5);
        x.fetch();
   
    System.out.printf("Title : %s\nAbstract : %s\nCitation : %s\n", x.title,
                x.pAbstract, x.citation);
                
   // Get Paper Titles Depend On Paper Titles
   x.SearchByTitle("Sanity Students");
   }
   catch(DLException e){
       System.out.println("Error: cannot complete operation");
   }
   
   try{
            closeVar = MySQLDB.close(); //attempt to close database connection
            if(closeVar)
                System.out.println("database closed");  //Will print if connection closes successfully         
            else
                System.out.println("close failed");
            }
            catch(DLException DLE){
                System.out.println("Error: cannot complete operation");
            }
*/

    }
}