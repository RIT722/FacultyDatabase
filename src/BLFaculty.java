import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 * @author Group 2: Fahad Alotaibi, Katherine Shaw, Nazar Al-Wattar, Chris Penepent
 */

//Business layer class for Faculty entity
public class BLFaculty extends DLFaculty {

    /*Constructor*/
    public BLFaculty(){
        
    }
    /*Parameterized Constructor*/
   public BLFaculty(int ID){
        super(ID);
    }
   
   //Hash user-entered password, send down to data layer facultyLogin() method
   public static int login(String email, String pw) throws DLException {
		MessageDigest md;
		byte[] hashedBytes = new byte[0];
		try {
			md = MessageDigest.getInstance("MD5");
			hashedBytes = md.digest(pw.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			 throw new DLException(ex, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Driver error in connect()");
		}
		
		StringBuilder sb = new StringBuilder();
		for (byte b : hashedBytes) {
			sb.append(String.format("%02X", b));
		}
		String hashedPw = sb.toString();
		return DLFaculty.facultyLogin(email,hashedPw);
	}
   
   /* profList() Method to return professor names*/
   public static ArrayList<ArrayList<String>> profList() throws DLException{
	   ArrayList<ArrayList<String>> returnList = getAllProfs();
	   ArrayList<ArrayList<String>> outputList = new ArrayList();
	   for(int i = 0; i < returnList.size(); i++)
	   {
			   String name = returnList.get(i).get(1) + " " + returnList.get(i).get(2);
			   String profID = returnList.get(i).get(0);
			   ArrayList<String> temp = new ArrayList();
			   temp.add(profID);
			   temp.add(name);
			   outputList.add(temp);
	   }
	   return outputList;
   }
   
   //get Student(s) name depends on the faculty ID ()
      public ArrayList<ArrayList<String>> profCurrentStudentList() throws DLException{
	   ArrayList<ArrayList<String>> returnList = getAllProfCurrentStudentName();
	   ArrayList<ArrayList<String>> outputList = new ArrayList();
	   for(int i = 0; i < returnList.size(); i++)
	   {
			   String name = returnList.get(i).get(0);
			   ArrayList<String> temp = new ArrayList();
			   temp.add(name);
			   outputList.add(temp);
	   }
	   return outputList;
   }
   
   //get All papers depends on the facultyID ()
        public ArrayList<ArrayList<String>> profPapersList() throws DLException{
	   ArrayList<ArrayList<String>> returnPapersList = getAllProfPapers();
	   ArrayList<ArrayList<String>> outputList = new ArrayList();
            
	   for(int i = 0; i < returnPapersList.size(); i++)
	   {
		String Title;	 
			   String paperID = returnPapersList.get(i).get(0);
                            
                            if((returnPapersList.get(i).get(1)).length()> 43)
                                Title = (returnPapersList.get(i).get(1)).substring(0, 43) + "...";
                           else
                                Title = (returnPapersList.get(i).get(1));
                           ArrayList<String> temp = new ArrayList();
			   temp.add(paperID);
			   temp.add(Title);
                                                   
			   outputList.add(temp);
	   }
	   return outputList;
   }
        /* To update the full faculty name and email */
        public void setUpdateDetails(String fn, String ln, String newEmail){
            firstName = fn;
            lastName = ln;
            int atIndx = newEmail.indexOf("@");
            email = newEmail;
            askHelp = null;
        }
         /* To insert the full faculty name and email */
        public int setInsertDetails(String fn, String ln, String newEmail) throws DLException{
            firstName = fn;
            lastName = ln;
            int atIndx = newEmail.indexOf("@");
            String pw = newEmail.substring(0, atIndx);
            email = newEmail;
            int newID;
            
            MessageDigest md;
		byte[] hashedBytes = new byte[0];
		try {
			md = MessageDigest.getInstance("MD5");
			hashedBytes = md.digest(pw.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException|UnsupportedEncodingException ex) {
			 throw new DLException(ex, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error In the Method setInsertDetails");
		}
		
		StringBuilder sb = new StringBuilder();
		for (byte b : hashedBytes) {
			sb.append(String.format("%02X", b));
		}
		String hashedPw = sb.toString();
            password = hashedPw;
            
            try{
                newID = this.post();
            }
            catch(DLException e){
                throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in setInsertDetails() of BLFaculty");
            }
            return newID;
        }

}