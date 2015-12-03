

import java.io.*;
import java.util.*;

public class DLException extends Exception{
    Exception e;
    String[] msgs;
    
    public DLException(Exception e) {
        this.e = e;
        log();
    }
    
    public DLException(Exception e, String...msgs) {
        this.e = e;
        this.msgs = msgs;
        log();
    }
    
    public void log() {
        try{
        File logfile = new File("LogFile.txt");
        if(!logfile.exists())
            logfile.createNewFile();
        
        FileWriter FW = new FileWriter(logfile, true);
        Date date = new Date();
        FW.write(date.toString() + " " + e.toString() + " ");
                
        for(int i = 0; i < msgs.length; i++)
        {    
            FW.write("\n");
            FW.write(msgs[i]);
        }
        
        FW.write("\n");
 
        FW.write("Stack Trace: ");
        PrintWriter PW = new PrintWriter(FW);
        e.printStackTrace(PW);

        FW.write("\n");
        
        FW.flush();
        FW.close();
        }
        catch(IOException IE){
            IE.printStackTrace();
        }
    }
}
