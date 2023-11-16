import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日時の処理を行うメソッドをまとめたクラスです。
 * 
 */
public class DateTime {
	/**
	 * システム日時を取得して、引数で指定したフォーマットの文字列に変換するメソッドです。
	 * 
	 * @param format 適用するフォーマットの文字列
	 * @return フォーマットされた現在の日時の文字列
	 */
	public String getCurrentDateTime(String format) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		return currentDateTime.format(DateTimeFormatter.ofPattern(format));
	}
}