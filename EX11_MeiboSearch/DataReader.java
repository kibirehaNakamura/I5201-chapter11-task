import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * データの読み取りを行うメソッドをまとめたクラスです。
 * 
 */
public class DataReader {
	/**
	 * ファイル読み込みのためのFileInインスタンスを生成します。
	 * 
	 */
	FileIn fi = new FileIn();
	
	/**
	 * ファイルのテキストを1行ずつArrayListに格納するメソッドです。
	 * 
	 * @param fileName 読み込むファイルの名前
	 * @return 読み込んだデータを格納したArrayList
	 */
	public ArrayList<String> copyFromFileToArrayList(String fileName) {
		/* ファイルの存在確認 */
		if(!Files.exists(Paths.get(fileName))) {
			System.out.println("指定されたファイルが見つかりません");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		/* ファイルオープン */
		boolean fileOperationFlag = fi.open(fileName);
		if(fileOperationFlag == false) {
			System.out.println("ファイルオープンエラー");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		/* 1行ずつ読み込んでArrayListに格納 */
		ArrayList<String> arrayRawData = new ArrayList<>();
		while(true) {
			String buf = fi.readLine();
			if(buf == null) {
				break;
			}
			arrayRawData.add(buf);
		}
		
		/* ファイルクローズ */
		fileOperationFlag = fi.close();
		if(fileOperationFlag == false) {
			System.out.println("ファイルクローズエラー");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		return arrayRawData;
	}
}