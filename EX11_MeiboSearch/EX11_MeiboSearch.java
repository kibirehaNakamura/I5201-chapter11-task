import java.util.ArrayList;

/**
 * 名簿をテキストファイルから読み込み、入力された出席番号、名前、生年月日で検索した結果を表示するクラスです。
 * 
 */
public class EX11_MeiboSearch {
	/**
	 * 各種インスタンスを生成します。
	 * 
	 * DataRead テキストファイルを読み込むクラス
	 * Search 検索処理を行うクラス
	 * DateTime 日時に関する処理を行うクラス
	 * SearchResultViewer 検索結果の表示を行うクラス
	 * KeyIn キー入力処理を行うクラス
	 */
	static DataReader dr = new DataReader();
	static Searcher sh = new Searcher();
	static DateTime dt = new DateTime();
	static SearchResultViewer srv = new SearchResultViewer();
	static KeyIn ki = new KeyIn();
	
	/**
	 * プログラムのエントリーポイントです。
	 * 
	 */
	public static void main(String[] args) {
		/* ファイルを読み込む */
		ArrayList<String> arrayRawData = null;
		try {
			arrayRawData = dr.copyFromFileToArrayList(args[0]);
		}catch(ArrayIndexOutOfBoundsException e) {	// 引数がない場合
			System.out.println("引数にファイル名が入力されていません");
			System.out.println("プログラムを終了します");
			System.exit(1);
		}
		
		/* メニューと検索処理を終了するまでループ */
		SearchLoop:
		while(true) {
			String select = selectMenu();
			
			String searchContent;
			ArrayList<Integer> arrayHitRow = null;
			switch(select) {
			case "1":	// 出席番号で検索
				System.out.println("出席番号で検索します");
				searchContent = ki.readString("出席番号");
				arrayHitRow = sh.searchByNumber(arrayRawData, searchContent);
				srv.showSearchResultByNumber(arrayRawData, arrayHitRow, searchContent);
				break;
				
			case "2":	// 名前で検索
				System.out.println("名前で検索します");
				searchContent = ki.readString("苗字");
				searchContent = searchContent + (ki.readString("名前"));
				arrayHitRow = sh.searchByName(arrayRawData, searchContent);
				srv.showSearchResultByName(arrayRawData, arrayHitRow, searchContent);
				break;
				
			case "3":	// 生年月日で検索
				System.out.println("生年月日で検索します");
				searchContent = ki.readString("生年月日(年)");
				String buf = ki.readString("生年月日(月)");
				if(buf.length() < 2) {	// 桁数が1なら
					buf = " " + buf;	// 桁揃えにスペースを追加
				}
				searchContent = searchContent + buf;
				buf = ki.readString("生年月日(日)");
				if(buf.length() < 2) {	// 桁数が1なら
					buf = " " + buf;	// 桁揃えにスペースを追加
				}
				searchContent = searchContent + buf;
				arrayHitRow = sh.searchByBirthday(arrayRawData, searchContent);
				srv.showSearchResultByBirthday(arrayRawData, arrayHitRow, searchContent);
				break;
				
			case "Q":	// 終了
				System.out.println("********** 名簿照会処理を終了しました **********");
				break SearchLoop;
				
			default:	// 指定外
				System.out.println("指定外のコマンドが入力されました");
				System.out.println("メニューに戻ります");
				break;
			}
		}
	}
	
	/**
	 * メニューを表示し、メニュー選択の入力を受け付けるメソッドです。
	 * 
	 * @return 「"1", "2", "3", "Q"」のいずれかの文字列
	 */
	private static String selectMenu() {
		System.out.print("       ********** 名簿照会処理 **********       　");
		System.out.println(dt.getCurrentDateTime("yyyy年MM月dd日HH時mm分ss秒"));
		System.out.println("――――――――――――――――――――――――");
		System.out.println("メニュー　1)出席番号　2)名前　3)生年月日　Q)終了");
		System.out.println("――――――――――――――――――――――――");
		
		String select;
		SelectLoop:
		while(true) {
			select = ki.readString("メニューNo.:");
			switch(select) {
			case "1":	// breakなし
			case "2":	// breakなし
			case "3":	// breakなし
			case "Q":
				break SelectLoop;
				
			default:
				System.out.println("メニューNo.エラー！");
				break;
			}
		}
		
		return select;
	}
}