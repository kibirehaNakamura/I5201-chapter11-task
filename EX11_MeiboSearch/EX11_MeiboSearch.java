import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Console;

/**
 * 名簿をテキストファイルから読み込み、入力された出席番号、名前、生年月日で検索した結果を表示するクラスです。
 * 
 */
public class EX11_MeiboSearch {
	/**
	 * キーボード入力のためのKeyInインスタンスと、ファイル読み込みのためのFileInインスタンスを生成します。
	 * 
	 */
	public static KeyIn ki = new KeyIn();
	public static FileIn fi = new FileIn();
	
	/**
	 * プログラムのエントリーポイントです。
	 * 
	 */
	public static void main(String[] args) {
		/* 名簿データ読み込み・配列セット */
		ArrayList<ArrayList<String>> arrayData = null;
		try {
			arrayData = dataSet(args[0]);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("ファイル名が入力されていません");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		/* メニューと検索処理を終了するまでループ */
		SearchLoop:
		while(true) {
			/* メニューの表示、項目の選択 */
			String select = menu();
			
			/* 選択された項目に応じて検索OR終了 */
			ArrayList<String> arraySearchContent = null;
			ArrayList<Integer> arrayHitRow = null;
			switch(select) {
			case "1":	// 出席番号で検索
				System.out.println("出席番号で検索します");
				arraySearchContent = inputSearchContent("出席番号");
				arrayHitRow = getSearchResult(arrayData, arraySearchContent, 0);
				showSearchResultHeader(arraySearchContent, arrayHitRow, "出席番号", "");
				showSearchResult(arrayData, arrayHitRow);
				break;
				
			case "2":	// 名前で検索
				System.out.println("名前で検索します");
				arraySearchContent = inputSearchContent("苗字", "名前");
				arrayHitRow = getSearchResult(arrayData, arraySearchContent, 1, 2);
				showSearchResultHeader(arraySearchContent, arrayHitRow, "名前", "");
				showSearchResult(arrayData, arrayHitRow);
				break;
				
			case "3":	// 生年月日で検索
				System.out.println("生年月日で検索します");
				arraySearchContent = inputSearchContent("生年月日(年)", "生年月日(月)", "生年月日(日)");
				arrayHitRow = getSearchResult(arrayData, arraySearchContent, 4, 5, 6);
				showSearchResultHeader(arraySearchContent, arrayHitRow, "生年月日", ".");
				showSearchResult(arrayData, arrayHitRow);
				break;
				
			case "Q":	// 終了
				System.out.println("********** 名簿照会処理を終了しました **********");
				break SearchLoop;
				
			default:	// 通常ありえないはずだが一応書いておく
				System.out.println("引数エラー : Menuメソッドから指定以外の値が渡された可能性があります");
				System.out.println("メニューに戻ります");
				break;
			}
		}
	}
	
	/**
	 * メニューを表示し、入力された値がメニューと合致すればその入力された値を返すメソッドです。
	 * 
	 * @return 「"1", "2", "3", "Q"」のいずれかの文字列
	 */
	private static String menu() {
		System.out.print("       ********** 名簿照会処理 **********       　");
		System.out.println(getCurrentDateTime("yyyy年MM月dd日HH時mm分ss秒"));
		System.out.println("――――――――――――――――――――――――");
		System.out.println("メニュー　1)出席番号　2)名前　3)生年月日　Q)終了");
		System.out.println("――――――――――――――――――――――――");
		
		String select;
		while(true) {
			select = ki.readString("メニューNo.:");
			String[] menuContent = { "1", "2", "3", "Q" };
			if(Arrays.asList(menuContent).contains(select)) {
				break;
			} else {
				System.out.println("メニューNo.エラー！");
			}
		}
		
		return select;
	}
	
	/**
	 * 引数として受け取ったファイル名のファイルからデータを読み込み、ArrayListに格納するメソッドです。
	 * 
	 * @return データを格納したArrayListの参照
	 */
	private static ArrayList<ArrayList<String>> dataSet(String address) {
		/* ファイルの存在確認 */
		if(!Files.exists(Paths.get(address))) {
			System.out.println("指定されたファイルが見つかりません");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		/* ファイルオープン */
		boolean flag = fi.open(address);
		if(flag == false) {
			System.out.println("ファイルオープンエラー");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		/* ArrayListを入れるArrayListを宣言 */
		ArrayList<ArrayList<String>> arrayData = new ArrayList<>();
		/* テキスト終端までループ */
		while(true) {
			/* 1行読み込んで */
			String buf = fi.readLine();
			/* nullなら終了 */
			if(buf == null) {
				break;
			}
			/* splitで分割した配列をasListでリストにしたものをバッファに移して大元のArrayListに追加 */
			/* asListでリストに変換したものの扱いがよくわからないのでバッファにコピーしている */
			ArrayList<String> bufAL = new ArrayList<>(Arrays.asList(buf.split(",")));
			arrayData.add(bufAL);
		}
		
		/* ファイルクローズ */
		flag = fi.close();
		if(flag == false) {
			System.out.println("ファイルクローズエラー");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		return arrayData;
	}
	
	/**
	 * システム日時を取得して、引数で指定したフォーマットの文字列に変換するメソッドです。
	 * 
	 * @return フォーマットされた現在の日時の文字列
	 */
	private static String getCurrentDateTime(String format) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		return currentDateTime.format(DateTimeFormatter.ofPattern(format));
	}
	
	/**
	 * 任意の個数の引数で指定された文字列を順番に表示して、それぞれに対して入力された内容をArrayListで返すメソッドです。
	 * 
	 * @return 渡された引数と同じ個数の入力内容が格納されたArrayListの参照
	 */
	private static ArrayList<String> inputSearchContent(String... question) {
		if(question.length < 1) {
			System.out.println("引数エラー : 質問文をひとつ以上引数に入力してください");
		}
		
		ArrayList<String> arraySearchContent = new ArrayList<>();
		for(int i = 0; i < question.length; i++) {
			arraySearchContent.add(ki.readString(question[i] + ":"));
		}
		
		return arraySearchContent;
	}
	
	/**
	 * データの格納場所、検索内容、検索する列番号を受け取り、データを検索し一致した行番号をArrayListで返すメソッドです。
	 * 
	 * @return 検索結果の行番号が格納されたArrayListの参照
	 */
	private static ArrayList<Integer> getSearchResult(ArrayList<ArrayList<String>> arrayData, ArrayList<String> arraySearchContent, int... column) {
		ArrayList<Integer> arrayHitRow = new ArrayList<>();
		for(int i = 0; i < arrayData.size(); i++) {
			int count = 0;
			for(int j = 0; j < arraySearchContent.size(); j++) {
				if(arrayData.get(i).get(column[j]).equals(arraySearchContent.get(j))) {
					count += 1;
				}
			}
			if(arraySearchContent.size() == count) {
				arrayHitRow.add(i);
			}
		}
		
		return arrayHitRow;
	}
	
	/**
	 * データの格納場所、検索該当行のArrayListを受け取り、検索結果を表示するメソッドです。
	 * 
	 */
	private static void showSearchResult(ArrayList<ArrayList<String>> arrayData, ArrayList<Integer> arrayHitRow) {
		System.out.println("出席番号　氏名　　　　　　　性別　生年月日　　住所　");
		System.out.println("----------------------------------------------------");
		if(arrayHitRow.size() <= 0) {
			System.out.println("　　　　　　　　　　 該当者なし 　　　　　　　　　　");
		} else {
			for(int i = 0; i < arrayHitRow.size(); i++) {
				String buf = arrayData.get(arrayHitRow.get(i)).get(0) + "        ";	// 出席番号(半角8桁)
				System.out.print(buf.substring(0,8));
				System.out.print("　");
				buf = arrayData.get(arrayHitRow.get(i)).get(1) + arrayData.get(arrayHitRow.get(i)).get(2) + "　　　　　　　　";	//苗字と名前(全角8文字)
				System.out.print(buf.substring(0,8));
				System.out.print("　");
				if(arrayData.get(arrayHitRow.get(i)).get(3).equals("1")) {	// 性別(1:男/2:女/それ以外:？)(全角1文字)
					System.out.print("男");
				} else if(arrayData.get(arrayHitRow.get(i)).get(3).equals("2")) {
					System.out.print("女");
				} else {
					System.out.print("？");
				}
				System.out.print("　　");
				System.out.print(arrayData.get(arrayHitRow.get(i)).get(4));	// 生年(半角4桁)
				System.out.print(".");
				buf = arrayData.get(arrayHitRow.get(i)).get(5);	// 月(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf);
				System.out.print(".");
				buf = arrayData.get(arrayHitRow.get(i)).get(6);	// 日(半角2桁)
				if(buf.length() < 2) {
					buf = " " + buf;
				}
				System.out.print(buf);
				System.out.print("　");
				System.out.print(arrayData.get(arrayHitRow.get(i)).get(7));	// 住所(全角2～3文字/最後なので文字数合わせ不要)
				System.out.println();
			}
		}
		System.out.println("----------------------------------------------------");
		ki.readString("[Enter]キーを押してください");
		System.out.println();
	}
	
	/**
	 * 検索内容と該当行番号のArrayListと、検索条件として表示する文字列と、区切りに使用する文字列を受け取り、検索結果のヘッダーを表示するメソッドです。
	 * 
	 */
	private static void showSearchResultHeader(ArrayList<String> arraySearchContent, ArrayList<Integer> arrayHitRow, String searchGenre, String delimiter) {
		System.out.println();
		System.out.println("名簿データ検索表示 (" + getCurrentDateTime("yyyy.MM.dd現在") + ")");
		System.out.print("(検索条件 = " + searchGenre + ":");
		for(int i = 0; i < arraySearchContent.size(); i++) {
			System.out.print(arraySearchContent.get(i));
			if(i < (arraySearchContent.size() - 1)) {
				System.out.print(delimiter);
			}
		}
		System.out.println("、結果件数 = " + arrayHitRow.size() + "件)");
	}
}