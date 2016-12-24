package jp.gr.norinori.utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	/**
	 * Timestampに変換する。
	 *
	 * @param time 日時文字列
	 * @param inputFormat 文字フォーマット ex.yyyyMMdd
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp toTimestamp(String time, String inputFormat) throws ParseException {
		if (time == null) {
			return null;
		}
		return new Timestamp(new SimpleDateFormat(inputFormat).parse(time).getTime());
	}

	/**
	 * 現在の日付を取得する。
	 *
	 * @return 現在のTimestamp
	 * @throws ParseException
	 */
	public static Timestamp getNowTimestamp() throws ParseException {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 現在の日付を取得する。
	 *
	 * @param diff 移動する日付
	 * @return 指定した日付分移動したTimestamp
	 * @throws ParseException
	 */
	public static Timestamp getNowTimestamp(int diff) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, diff);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * 現在の日付を取得する。
	 *
	 * @param outputFormat 文字フォーマット ex.yyyyMMdd
	 * @return 現在の日付
	 * @throws ParseException
	 */
	public static String getNowString(String outputFormat) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * 現在から指定した日付分移動した日付文字列を取得する。
	 *
	 * @param diff 移動する日付
	 * @param outputFormat 文字フォーマット ex.yyyyMMdd
	 * @return 指定した日付分移動した日付文字列
	 * @throws ParseException
	 */
	public static String getNowString(int diff, String outputFormat) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, diff);
		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * 現在から指定した月付分移動した日付文字列を取得する。
	 *
	 * @param diff 移動する月
	 * @param outputFormat 文字フォーマット ex.yyyyMMdd
	 * @return 指定した月分移動した日付文字列
	 * @throws ParseException
	 */
	public static String getNowStringDiffMonth(int diff, String outputFormat) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, diff);
		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * Timestampから日付文字列を取得する。
	 *
	 * @param timestamp
	 * @param outputFormat 文字フォーマット ex.yyyyMMdd
	 * @return 日付文字列
	 */
	public static String formattedTimestamp(Timestamp timestamp, String outputFormat) {
		if (timestamp == null) {
			return null;
		}
		return new SimpleDateFormat(outputFormat).format(timestamp);
	}

	/**
	 * Timestampから日付文字列を取得する。
	 *
	 * @param timestamp
	 * @param format 文字フォーマット ex.yyyyMMdd
	 * @return 日付文字列
	 * @throws ParseException
	 */
	public static String formattedString(String time, String inputFormat, String outputFormat) throws ParseException {
		if (time == null) {
			return null;
		}
		Timestamp timestamp = new Timestamp(new SimpleDateFormat(inputFormat).parse(time).getTime());

		return new SimpleDateFormat(outputFormat).format(timestamp);
	}

	/**
	 * 日付文字列に指定した日付分移動した日付文字列を取得する。
	 *
	 * @param time
	 * @param diff
	 * @param inputFormat 文字フォーマット ex.yyyyMMdd
	 * @return 指定した日付分移動した日付文字列
	 * @throws ParseException
	 */
	public static String addDay(String time, int diff, String inputFormat) throws ParseException {
		return addDay(time, diff, inputFormat, inputFormat);
	}

	/**
	 * 日付文字列に指定した日付分移動した日付文字列を取得する。
	 *
	 * @param time
	 * @param diff
	 * @param inputFormat 入力文字フォーマット ex.yyyyMMdd
	 * @param outputFormat 出力文字フォーマット ex.yyyyMMdd
	 * @return 指定した日付分移動した日付文字列
	 * @throws ParseException
	 */
	public static String addDay(String time, int diff, String inputFormat, String outputFormat) throws ParseException {
		if (time == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new SimpleDateFormat(inputFormat).parse(time).getTime());
		cal.add(Calendar.DATE, diff);
		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * 日付文字列に指定した月分移動した日付文字列を取得する。
	 *
	 * @param time
	 * @param diff
	 * @param inputFormat 文字フォーマット ex.yyyyMMdd
	 * @return 指定した月分移動した日付文字列
	 * @throws ParseException
	 */
	public static String addMonth(String time, int diff, String inputFormat) throws ParseException {
		return addMonth(time, diff, inputFormat, inputFormat);
	}

	/**
	 * 日付文字列に指定した月分移動した日付文字列を取得する。
	 *
	 * @param time
	 * @param diff
	 * @param inputFormat 入力文字フォーマット ex.yyyyMMdd
	 * @param outputFormat 出力文字フォーマット ex.yyyyMMdd
	 * @return 指定した月分移動した日付文字列
	 * @throws ParseException
	 */
	public static String addMonth(String time, int diff, String inputFormat, String outputFormat) throws ParseException {
		if (time == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new SimpleDateFormat(inputFormat).parse(time).getTime());
		cal.add(Calendar.MONTH, diff);
		SimpleDateFormat sdf = new SimpleDateFormat(outputFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * 指定した日付の末日を取得する。
	 *
	 * @param time
	 * @param format 文字フォーマット ex.yyyyMMdd
	 * @return 指定した年月の末日 (末日のみ）
	 * @throws ParseException
	 */
	public static int getLastDay(String time, String format) throws ParseException {
		// Calendarクラスのオブジェクトcal1を生成しています。
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new SimpleDateFormat(format).parse(time).getTime());

		return cal.getActualMaximum(Calendar.DATE);
	}

}
