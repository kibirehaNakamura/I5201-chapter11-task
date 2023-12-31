import java.util.ArrayList;

/**
 * 検索を行うメソッドをまとめたクラスです。
 * 
 */
public class Search {
	/**
	 * 文字列の加工を行うStringEditクラスを読み込みます。
	 * 
	 */
	StringEdit se = new StringEdit();
	
	/**
	 * テキストを書き出したArrayListと検索する出席番号の文字列を受け取り、合致する行番号を格納したArrayListを返すメソッドです。
	 * 
	 * @param arrayRawData  テキストを読み出したデータの入ったArrayList
	 * @param searchContent 検索に使用する出席番号の文字列
	 * @return 検索に合致する行番号を格納したArrayList
	 */
	public ArrayList<Integer> searchByNumber(ArrayList<String> arrayRawData, String searchContent) {
		ArrayList<Integer> arrayHitRow = new ArrayList<>();
		for(int i = 0; i < arrayRawData.size(); i++) {
			ArrayList<String> buf = se.splitByComma(arrayRawData.get(i), 0);
			String compareContent = buf.get(0);
			if(searchContent.equals(compareContent)) {
				arrayHitRow.add(i);
			}
		}
		
		return arrayHitRow;
	}
	
	/**
	 * テキストを書き出したArrayListと検索する名前の文字列を受け取り、合致する行番号を格納したArrayListを返すメソッドです。
	 * 
	 * @param arrayRawData  テキストを読み出したデータの入ったArrayList
	 * @param searchContent 検索に使用する名前の文字列
	 * @return 検索に合致する行番号を格納したArrayList
	 */
	public ArrayList<Integer> searchByName(ArrayList<String> arrayRawData, String searchContent) {
		ArrayList<Integer> arrayHitRow = new ArrayList<>();
		for(int i = 0; i < arrayRawData.size(); i++) {
			ArrayList<String> buf = se.splitByComma(arrayRawData.get(i), 1, 2);
			String compareContent = buf.get(0) + buf.get(1);
			if(searchContent.equals(compareContent)) {
				arrayHitRow.add(i);
			}
		}
		
		return arrayHitRow;
	}
	
	/**
	 * テキストを書き出したArrayListと検索する生年月日の文字列を受け取り、合致する行番号を格納したArrayListを返すメソッドです。
	 * 
	 * @param arrayRawData  テキストを読み出したデータの入ったArrayList
	 * @param searchContent 検索に使用する生年月日の文字列
	 * @return 検索に合致する行番号を格納したArrayList
	 */
	public ArrayList<Integer> searchByBirthday(ArrayList<String> arrayRawData, String searchContent) {
		ArrayList<Integer> arrayHitRow = new ArrayList<>();
		for(int i = 0; i < arrayRawData.size(); i++) {
			ArrayList<String> buf = se.splitByComma(arrayRawData.get(i), 4, 5, 6);
			String compareContent = buf.get(0);
			if(buf.get(1).length() < 2) {
				compareContent = compareContent + " " + buf.get(1);
			} else {
				compareContent = compareContent + buf.get(1);
			}
			if(buf.get(2).length() < 2) {
				compareContent = compareContent + " " + buf.get(2);
			} else {
				compareContent = compareContent + buf.get(2);
			}
			if(searchContent.equals(compareContent)) {
				arrayHitRow.add(i);
			}
		}
		
		return arrayHitRow;
	}
}