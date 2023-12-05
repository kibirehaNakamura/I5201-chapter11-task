import java.util.ArrayList;

/**
 * 文字列を加工するメソッドをまとめたクラスです。
 * 
 */
public class StringEditor {
	/**
	 * 文字列をカンマで分割した後、指定されたインデックスの場所の文字列をArrayListに格納して返すメソッドです。
	 * 
	 * @return 指定されたインデックスの個数分の文字列が格納されたArrayList
	 */
	public ArrayList<String> splitByComma(String str, int... index) {
		String[] splitStr = str.split(",");	// String.splitメソッド:文字列を、指定した文字で分割して配列に格納するメソッド。
		ArrayList<String> arraySplitData = new ArrayList<>();
		for(int i = 0; i < index.length; i++) {	// 引数indexで指定された場所の文字列をArrayListに格納していく
			arraySplitData.add(splitStr[index[i]]);
		}
		
		return arraySplitData;
	}
}