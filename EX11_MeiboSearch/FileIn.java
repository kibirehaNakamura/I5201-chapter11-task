import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileIn {
	BufferedReader br = null;
	
	public boolean open(String fname) {
		boolean sts = true;
		try {
			br = new BufferedReader(new FileReader(fname));
		} catch (IOException e) {
			System.out.println("ファイル名に誤りがあります");
			System.out.println(e);
			sts = false;
		}
		return sts;
	}
	
	
	public String readLine() {
		String buff;
		try {
			buff = br.readLine();
		} catch(IOException e) {
			System.out.println("読み込みエラー");
			System.out.println(e);
			buff = null;
		}
		return buff;
	}
	
	
	public boolean close() {
		boolean sts = true;
		try {
			br.close();
		} catch(IOException e) {
			System.out.println("ファイルクローズエラー");
			System.out.println(e);
			sts = false;
		}
		return sts;
	}
}