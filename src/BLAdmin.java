
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BLAdmin {
	public BLAdmin(){
        
    }

	//Hash user-entered password, send down to data layer adminLogin() method
	public static void login(String email, String pw) throws DLException {
		MessageDigest md;
		byte[] hashedBytes = new byte[0];
		try { //MD5 hash the user-entered password
			md = MessageDigest.getInstance("MD5");
			hashedBytes = md.digest(pw.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new DLException(ex,"Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in login() of BLAdmin");
		}
		
		//convert the hashed password to a String
		StringBuilder sb = new StringBuilder();
		for (byte b : hashedBytes) {
			sb.append(String.format("%02X", b));
		}
		String hashedPw = sb.toString();
		DLAdmin.adminLogin(email,hashedPw); //pass email and hashed pw down to data layer
	}
}
