

import java.sql.*;
import java.util.*;

public class MainTest {

    public static void main(String[] args) throws DLException{
        
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


    }
}    