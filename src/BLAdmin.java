
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BLAdmin {
	public BLAdmin(){
        
    }

	public static void login(String email, String pw) throws DLException {
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
		DLAdmin.adminLogin(email,hashedPw);
	}
}
