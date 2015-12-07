
import java.util.ArrayList;

public class DLAdmin {

	public DLAdmin() {}
	
	public static void adminLogin(String email,String password) throws DLException{
		ArrayList<ArrayList<String>> result;
		MySQLDatabase db = MySQLDatabase.getInstance();
		try {
			ArrayList<String> values = new ArrayList();
			values.add(email);
			values.add(password);
			result = db.getData("SELECT True FROM Admin WHERE email=? AND password=?", values);
			result.get(0).get(0); //this will throw a DLException if RS is empty, i.e. if login fails
	   }
	   catch(DLException e) {
		   throw e;
	   }
	   catch(IndexOutOfBoundsException e) {
		   throw new DLException(e, "Unix time: " + String.valueOf(System.currentTimeMillis()/1000), "Error in adminLogin() of DLAdmin");
	   }
	}
}