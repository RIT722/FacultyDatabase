
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;



public class BLFaculty extends DLFaculty {

   
   public BLFaculty(int ID){
        super(ID);
    }
   /*
   public boolean login(String email, String pw) {
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
		String storedPw = DLFaculty.login(email);
		return hashedPw.equals(storedPw);
	}
 */
   
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
}