/**
 *
 * @author Group 2: Chris Penepent, Katherine Shaw, Fahad Alotaibi, Nazar Al-Wattar
 */
/*This class creates a custom exception. Details from exceptions are written
to a log file.
*/
import java.io.*;
import java.util.*;

public class DLException extends Exception{
    Exception e;
    String[] msgs;
    
    //Single argument constructor
    public DLException(Exception e) {
        this.e = e;
        log();
    }
    
    //Multiple argument constructor
    public DLException(Exception e, String...msgs) {
        this.e = e;
        this.msgs = msgs;
        log();
    }
    
    //Writes the exception information to a log file
    public void log() {
        try{
        File logfile = new File("LogFile.txt"); //Create file
        if(!logfile.exists())
            logfile.createNewFile();
        
        FileWriter FW = new FileWriter(logfile, true); //Create file writer
        Date date = new Date();
        FW.write(date.toString() + " " + e.toString() + " "); //Write date and exception to file
                
        for(int i = 0; i < msgs.length; i++) //Write additional info to file
        {    
            FW.write("\n");
            FW.write(msgs[i]);
        }
        
        FW.write("\n");
 
        FW.write("Stack Trace: "); //Write stack trace to file
        PrintWriter PW = new PrintWriter(FW);
        e.printStackTrace(PW);

        FW.write("\n");
        
        FW.flush();
        FW.close(); //Close the file writer
        }
        catch(IOException IE){
            IE.printStackTrace();
        }
    }
}
