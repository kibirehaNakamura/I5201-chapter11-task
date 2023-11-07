import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyIn {
	String buf = null;
	
	public String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"Shift_JIS"));
			buf = br.readLine();
		} catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		return buf;
	}
	
	
	public String readString(String msg) {
		System.out.print(msg + ">");
		return readString();
	}
}