
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;



public class BLFaculty extends DLFaculty {

    /*New method*/
    public BLFaculty(){
        
    }
   
   public BLFaculty(int ID){
        super(ID);
    }
   public static int login(String email, String pw) throws DLException {
		MessageDigest md;
		byte[] hashedBytes = new byte[0];
		try {
			md = MessageDigest.getInstance("MD5");
			hashedBytes = md.digest(pw.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			System.out.println("OOPSIE");
		}
		
		StringBuilder sb = new StringBuilder();
		for (byte b : hashedBytes) {
			sb.append(String.format("%02X", b));
		}
		String hashedPw = sb.toString();
		return DLFaculty.facultyLogin(email,hashedPw);
	}
   
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
   
   //get Student (s) name for this faculty ID (Nazar)
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
   
   //get All papers depends on the facultyID (Nazar)
        public ArrayList<ArrayList<String>> profPapersList() throws DLException{
	   ArrayList<ArrayList<String>> returnPapersList = getAllProfPapers();
	   ArrayList<ArrayList<String>> outputList = new ArrayList();
            
	   for(int i = 0; i < returnPapersList.size(); i++)
	   {
		String Title;	 
			   String paperID = returnPapersList.get(i).get(0);
                            //to disply only 21 character of the papertitle
                            if((returnPapersList.get(i).get(1)).length()> 43)
                                Title = (returnPapersList.get(i).get(1)).substring(0, 43) + "...";
                           else
                                Title = (returnPapersList.get(i).get(1));
                           ArrayList<String> temp = new ArrayList();
			   temp.add(paperID);
			   temp.add(Title);
                           
                         //  System.out.println("Paper ID: " + paperID);
                         //  System.out.println("Paper Title: " + Title);
			   outputList.add(temp);
	   }
	   return outputList;
   }
        /* New Method */
        public void setUpdateDetails(String fn, String ln, String newEmail){
            firstName = fn;
            lastName = ln;
            int atIndx = newEmail.indexOf("@");
            email = newEmail;
            askHelp = null;
        }
        
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
			System.out.println("OOPSIE");
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