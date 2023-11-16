import java.util.ArrayList;

/**
 * 文字列を加工するメソッドをまとめたクラスです。
 * 
 */
public class StringEdit {
	/**
	 * 文字列と複数のインデックスの数値を受け取り、文字列をカンマで分割した後指定されたインデックスの場所の文字列をArrayListに格納して返します。
	 * 
	 * @return 指定されたインデックスの個数分の文字列が格納されたArrayList
	 */
	public ArrayList<String> splitByComma(String str, int... index) {
		String[] splitStr = str.split(",");
		ArrayList<String> arraySplitData = new ArrayList<>();
		for(int i = 0; i < index.length; i++) {
			arraySplitData.add(splitStr[index[i]]);
		}
		
		return arraySplitData;
	}
}