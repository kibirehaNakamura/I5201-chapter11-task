import java.util.ArrayList;

/**
 * 検索結果を表示するメソッドをまとめたクラスです。
 * 
 */
public class SearchResultViewer {
	/**
	 * 各種インスタンスを生成します。
	 * KeyIn キー入力を処理するクラス
	 * DateTime 日時に関する処理を行うクラス
	 * StringEdit 文字列の加工を行うクラス
	 */
	KeyIn ki = new KeyIn();
	DateTime dt = new DateTime();
	StringEditor se = new StringEditor();
	
	/**
	 * 出席番号での検索結果を表示するメソッドです。
	 * 
	 * @param arrayRawData  テキストから読み込んだ行ごとの未加工の文字列を格納したArrayList
	 * @param arrayHitRow   検索で合致した行番号を格納したArrayList
	 * @param searchContent 検索に使用した文字列
	 */
	public void showSearchResultByNumber(ArrayList<String> arrayRawData, ArrayList<Integer> arrayHitRow, String searchContent) {
		System.out.println();
		System.out.println("名簿データ検索表示 (" + dt.getCurrentDateTime("yyyy.MM.dd現在") + ")");
		System.out.println("(検索条件 = 出席番号:" + searchContent + "、結果件数 = " + arrayHitRow.size() + "件)");
		
		System.out.println("出席番号　氏名　　　　　　　生年月日　　住所　");
		System.out.println("--------　----------------　----------　------");
		if(arrayHitRow.size() <= 0) {	// 検索結果の件数が0なら
			System.out.println("　　　　　　　　　該当者なし　　　　　　　　　");
		} else {
			for(int i = 0; i < arrayHitRow.size(); i++) {
				ArrayList<String> arrayResult = se.splitByComma(arrayRawData.get(arrayHitRow.get(i)), 0, 1, 2, 4, 5, 6, 7);
				String buf = arrayResult.get(0) + "        ";	// 出席番号(半角8桁)
				System.out.print(buf.substring(0,8) + "　");
				buf = arrayResult.get(1) + arrayResult.get(2) + "　　　　　　　　";	// 苗字と名前(全角8文字)
				System.out.print(buf.substring(0,8) + "　");
				System.out.print(arrayResult.get(3) + ".");	// 生年(半角4桁)
				buf = arrayResult.get(4);	// 月(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf + ".");
				buf = arrayResult.get(5);	// 日(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf + "　");
				System.out.println(arrayResult.get(6));	// 住所(全角2～3文字)
			}
		}
		System.out.println("----------------------------------------------");
		ki.readString("[Enter]キーを押してください");
		System.out.println();
	}
	
	/**
	 * 名前での検索結果を表示するメソッドです。
	 * 
	 * @param arrayRawData  テキストから読み込んだ行ごとの未加工の文字列を格納したArrayList
	 * @param arrayHitRow   検索で合致した行番号を格納したArrayList
	 * @param searchContent 検索に使用した文字列
	 */
	public void showSearchResultByName(ArrayList<String> arrayRawData, ArrayList<Integer> arrayHitRow, String searchContent) {
		System.out.println();
		System.out.println("名簿データ検索表示 (" + dt.getCurrentDateTime("yyyy.MM.dd現在") + ")");
		System.out.println("(検索条件 = 名前:" + searchContent + "、結果件数 = " + arrayHitRow.size() + "件)");
		
		System.out.println("出席番号　氏名　　　　　　　生年月日　　住所　");
		System.out.println("--------　----------------　----------　------");
		if(arrayHitRow.size() <= 0) {	// 検索結果の件数が0なら
			System.out.println("　　　　　　　　　該当者なし　　　　　　　　　");
		} else {
			for(int i = 0; i < arrayHitRow.size(); i++) {
				ArrayList<String> arrayResult = se.splitByComma(arrayRawData.get(arrayHitRow.get(i)), 0, 1, 2, 4, 5, 6, 7);
				String buf = arrayResult.get(0) + "        ";	// 出席番号(半角8桁)
				System.out.print(buf.substring(0,8) + "　");
				buf = arrayResult.get(1) + arrayResult.get(2) + "　　　　　　　　";	// 苗字と名前(全角8文字)
				System.out.print(buf.substring(0,8) + "　");
				System.out.print(arrayResult.get(3) + ".");	// 生年(半角4桁)
				buf = arrayResult.get(4);	// 月(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf + ".");
				buf = arrayResult.get(5);	// 日(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf + "　");
				System.out.println(arrayResult.get(6));	// 住所(全角2～3文字)
			}
		}
		System.out.println("----------------------------------------------");
		ki.readString("[Enter]キーを押してください");
		System.out.println();
	}
	
	/**
	 * 生年月日での検索結果を表示するメソッドです。
	 * 
	 * @param arrayRawData  テキストから読み込んだ行ごとの未加工の文字列を格納したArrayList
	 * @param arrayHitRow   検索で合致した行番号を格納したArrayList
	 * @param searchContent 検索に使用した文字列
	 */
	public void showSearchResultByBirthday(ArrayList<String> arrayRawData, ArrayList<Integer> arrayHitRow, String searchContent) {
		StringBuilder sb = new StringBuilder(searchContent);	// 文字列に文字を挿入するためにStringBuilderを使用
		sb.insert(4, ".");	// 年と月の間
		sb.insert(7, ".");	// 月と日の間に"."を挿入
		System.out.println();
		System.out.println("名簿データ検索表示 (" + dt.getCurrentDateTime("yyyy.MM.dd現在") + ")");
		System.out.println("(検索条件 = 生年月日:" + sb.toString() + "、結果件数 = " + arrayHitRow.size() + "件)");
		
		System.out.println("出席番号　氏名　　　　　　　生年月日　　住所　");
		System.out.println("--------　----------------　----------　------");
		if(arrayHitRow.size() <= 0) {	// 検索結果の件数が0なら
			System.out.println("　　　　　　　　　該当者なし　　　　　　　　　");
		} else {
			for(int i = 0; i < arrayHitRow.size(); i++) {
				ArrayList<String> arrayResult = se.splitByComma(arrayRawData.get(arrayHitRow.get(i)), 0, 1, 2, 4, 5, 6, 7);
				String buf = arrayResult.get(0) + "        ";	// 出席番号(半角8桁)
				System.out.print(buf.substring(0,8) + "　");
				buf = arrayResult.get(1) + arrayResult.get(2) + "　　　　　　　　";	// 苗字と名前(全角8文字)
				System.out.print(buf.substring(0,8) + "　");
				System.out.print(arrayResult.get(3) + ".");	// 生年(半角4桁)
				buf = arrayResult.get(4);	// 月(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf + ".");
				buf = arrayResult.get(5);	// 日(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf + "　");
				System.out.println(arrayResult.get(6));	// 住所(全角2～3文字)
			}
		}
		System.out.println("----------------------------------------------");
		ki.readString("[Enter]キーを押してください");
		System.out.println();
	}
}