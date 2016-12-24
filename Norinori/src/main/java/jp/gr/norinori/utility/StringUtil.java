package jp.gr.norinori.utility;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.gr.norinori.define.Encoding;

/**
 * 文字列ユーティリティ
 *
 * @author inoue
 */
public class StringUtil {

	/**
	 * 文字列を置換する
	 *
	 * @param original 変換元の文字列
	 * @param target 検索対象の文字列
	 * @param replacement 置き換える文字列
	 * @return 置換後の文字列
	 */
	public static String replace(String original, String target, String replacement) {
		if (original == null) {
			return null;
		}
		return original.replace(target, replacement);
	}

	/**
	 * パスカライズ
	 *
	 * @param str 文字
	 * @return パスカライズした文字
	 */
	public static String pascalize(String str) {
		return camelize(str, true);
	}

	/**
	 * キャメライズ
	 *
	 * @param str 文字
	 * @return キャメライズした文字
	 */
	public static String camelize(String str) {
		return camelize(str, false);
	}

	/**
	 * 空文字を判定する。
	 *
	 * @param str 文字
	 * @return true:空文字 / false:空文字でない
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 区切り文字で結合する。
	 *
	 * @param list 文字のリスト
	 * @param separator 区切り文字
	 * @return リストの文字を区切り文字で結合した文字
	 */
	public static String join(List<? extends Object> list, String separator) {
		FirstSkip firstSkip = new FirstSkip(separator);
		StringBuffer sb = new StringBuffer();
		for (Object obj : list) {
			sb.append(firstSkip.toString()).append(obj);
		}
		return sb.toString();
	}

	/**
	 * 数字判定を行う。
	 *
	 * @param numberString
	 * @return true:数字 / false:数字でない
	 */
	public static boolean isNumeric(String numberString) {
		if (StringUtil.isEmpty(numberString)) {
			return false;
		}
		return numberString.matches("-?\\d+(.\\d+)?");
	}

	/**
	 * 数字をフォーマットする。
	 *
	 * @param number 数字
	 * @param format フォーマット (#,###.## 、00,000.0)
	 * @return フォーマットした数字
	 */
	public static String formatNumber(String number, String format) {
		return formatNumber(Double.parseDouble(number), format);
	}

	/**
	 * 数値をフォーマットする。
	 *
	 * @param number 数値
	 * @param format フォーマット (#,###.## 、00,000.0)
	 * @return フォーマットした数字
	 */
	public static String formatNumber(int number, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	/**
	 * 数値をフォーマットする。
	 *
	 * @param number 数値
	 * @param format フォーマット (#,###.## 、00,000.0)
	 * @return フォーマットした数字
	 */
	public static String formatNumber(float number, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	/**
	 * 数値をフォーマットする。
	 *
	 * @param number 数値
	 * @param format フォーマット (#,###.## 、00,000.0)
	 * @return フォーマットした数字
	 */
	public static String formatNumber(double number, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	/**
	 * nullを空文字に変換する
	 *
	 * @param str 文字列
	 * @return nullを空文字に変換。それ以外は未変換
	 */
	public static String nullToString(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 全角半角混在桁数
	 *
	 * @param str 文字列
	 * @return 全角：2 半角：1とした桁数
	 */
	public static int length(String str) {
		if (str == null) {
			return 0;
		}
		try {
			return str.getBytes(Encoding.SHIFT_JIS.getCode()).length;
		} catch (UnsupportedEncodingException ignore) {
		}
		return 0;
	}

	/**
	 * 指定した文字列を指定数分繰り返し結合する
	 *
	 * @param str 文字列
	 * @param size 生成数
	 * @return 指定した文字列を指定数分生成した文字列
	 */
	public static String pad(String str, int size) {
		if (str == null) {
			return null;
		}
		if (size <= 0) {
			return "";
		}

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append(str);
		}
		return s.toString();
	}

	/**
	 * 文字を中央寄せにする
	 *
	 * @param str 文字列
	 * @param size 幅
	 * @param padChar 埋める文字
	 * @return 指定した文字列を指定幅の中央に位置した文字列。 center("a", 6, ' ') = "   a  "
	 */
	public static String center(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		if (size <= 0) {
			return str;
		}

		int strLength = length(str);
		if (size <= strLength) {
			return str;
		}

		int rightSize = (size - strLength) / 2;
		int leftSize = size - strLength - rightSize;
		String padStr = String.valueOf(padChar);

		StringBuilder s = new StringBuilder();
		s.append(pad(padStr, leftSize));
		s.append(str);
		s.append(pad(padStr, rightSize));

		return s.toString();
	}

	// キャメライズ
	private static String camelize(String str, boolean upperFirstLetter) {
		if (StringUtil.isEmpty(str)) {
			return str;
		}

		Pattern pattern = Pattern.compile("[^_]+");
		Matcher matcher = pattern.matcher(str);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			buffer.append(matcher.group(0).substring(0, 1).toUpperCase());
			buffer.append(matcher.group(0).substring(1));
		}
		String result = buffer.toString();
		if (upperFirstLetter) {
			result = result.substring(0, 1).toUpperCase() + result.substring(1);
		} else {
			result = result.substring(0, 1).toLowerCase() + result.substring(1);
		}
		return result;
	}

	private static final char[] ZENKAKU_KATAKANA = { 'ァ', 'ア', 'ィ', 'イ', 'ゥ', 'ウ', 'ェ', 'エ', 'ォ', 'オ', 'カ', 'ガ', 'キ',
			'ギ', 'ク', 'グ', 'ケ', 'ゲ', 'コ', 'ゴ', 'サ', 'ザ', 'シ', 'ジ', 'ス', 'ズ', 'セ', 'ゼ', 'ソ', 'ゾ', 'タ', 'ダ', 'チ', 'ヂ',
			'ッ', 'ツ', 'ヅ', 'テ', 'デ', 'ト', 'ド', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'バ', 'パ', 'ヒ', 'ビ', 'ピ', 'フ', 'ブ', 'プ',
			'ヘ', 'ベ', 'ペ', 'ホ', 'ボ', 'ポ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ャ', 'ヤ', 'ュ', 'ユ', 'ョ', 'ヨ', 'ラ', 'リ', 'ル', 'レ',
			'ロ', 'ヮ', 'ワ', 'ヰ', 'ヱ', 'ヲ', 'ン', 'ヴ', 'ヵ', 'ヶ' };

	private static final char[] ZENKAKU_HIRAGANA = { 'ぁ', 'あ', 'ぃ', 'い', 'ぅ', 'う', 'ぇ', 'え', 'ぉ', 'お', 'か', 'が', 'き',
			'ぎ', 'く', 'ぐ', 'け', 'げ', 'こ', 'ご', 'さ', 'ざ', 'し', 'じ', 'す', 'ず', 'せ', 'ぜ', 'そ', 'ぞ', 'た', 'だ', 'ち', 'ぢ',
			'っ', 'つ', 'づ', 'て', 'で', 'と', 'ど', 'な', 'に', 'ぬ', 'ね', 'の', 'は', 'ば', 'ぱ', 'ひ', 'び', 'ぴ', 'ふ', 'ぶ', 'ぷ',
			'へ', 'べ', 'ぺ', 'ほ', 'ぼ', 'ぽ', 'ま', 'み', 'む', 'め', 'も', 'ゃ', 'や', 'ゅ', 'ゆ', 'ょ', 'よ', 'ら', 'り', 'る', 'れ',
			'ろ', 'ゎ', 'わ', 'ゐ', 'ゑ', 'を', 'ん', 'ゔ', 'ゕ', 'ゖ' };

	private static final char[] ZENKAKU_MARKS = { '、', '。', 'ー' };

	private static final String[] HANKAKU_KATAKANA = { "ｧ", "ｱ", "ｨ", "ｲ", "ｩ", "ｳ", "ｪ", "ｴ", "ｫ", "ｵ", "ｶ", "ｶﾞ",
			"ｷ", "ｷﾞ", "ｸ", "ｸﾞ", "ｹ", "ｹﾞ", "ｺ", "ｺﾞ", "ｻ", "ｻﾞ", "ｼ", "ｼﾞ", "ｽ", "ｽﾞ", "ｾ", "ｾﾞ", "ｿ", "ｿﾞ", "ﾀ",
			"ﾀﾞ", "ﾁ", "ﾁﾞ", "ｯ", "ﾂ", "ﾂﾞ", "ﾃ", "ﾃﾞ", "ﾄ", "ﾄﾞ", "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾊﾞ", "ﾊﾟ", "ﾋ", "ﾋﾞ",
			"ﾋﾟ", "ﾌ", "ﾌﾞ", "ﾌﾟ", "ﾍ", "ﾍﾞ", "ﾍﾟ", "ﾎ", "ﾎﾞ", "ﾎﾟ", "ﾏ", "ﾐ", "ﾑ", "ﾒ", "ﾓ", "ｬ", "ﾔ", "ｭ", "ﾕ", "ｮ",
			"ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ﾜ", "ﾜ", "ｲ", "ｴ", "ｦ", "ﾝ", "ｳﾞ", "ｶ", "ｹ" };

	private static final String[] HANKAKU_MARKS = { "､", "｡", "ｰ" };

	private static final char ZENKAKU_KATAKANA_FIRST_CHAR = ZENKAKU_KATAKANA[0];

	private static final char ZENKAKU_KATAKANA_LAST_CHAR = ZENKAKU_KATAKANA[ZENKAKU_KATAKANA.length - 1];

	private static final char ZENKAKU_HIRAGANA_FIRST_CHAR = ZENKAKU_HIRAGANA[0];

	private static final char ZENKAKU_HIRAGANA_LAST_CHAR = ZENKAKU_HIRAGANA[ZENKAKU_HIRAGANA.length - 1];

	private static final char ZENKAKU_MARKS_FIRST_CHAR = ZENKAKU_MARKS[0];

	private static final char ZENKAKU_MARKS_LAST_CHAR = ZENKAKU_MARKS[ZENKAKU_MARKS.length - 1];

	/**
	 * 全角カナ文字列を半角カナに変換する
	 *
	 * @param str 文字列
	 * @return 半角カナに変換した文字列
	 */
	public static String toHankaku(String str) {
		StringBuffer sb = new StringBuffer();
		int l = str.length();
		for (int i = 0; i < l; i++) {
			char c = str.charAt(i);
			sb.append(toHankakuKana(c));
		}
		return sb.toString();
	}

	// 全角カナを半角カナに変換する
	private static String toHankakuKana(char c) {
		if (c >= ZENKAKU_KATAKANA_FIRST_CHAR && c <= ZENKAKU_KATAKANA_LAST_CHAR) {
			return HANKAKU_KATAKANA[c - ZENKAKU_KATAKANA_FIRST_CHAR];
		} else if (c >= ZENKAKU_HIRAGANA_FIRST_CHAR && c <= ZENKAKU_HIRAGANA_LAST_CHAR) {
			return HANKAKU_KATAKANA[c - ZENKAKU_HIRAGANA_FIRST_CHAR];
		} else if (c >= ZENKAKU_MARKS_FIRST_CHAR && c <= ZENKAKU_MARKS_LAST_CHAR) {
			for (int i = 0; i < ZENKAKU_MARKS.length; i++) {
				if (c == ZENKAKU_MARKS[i]) {
					return HANKAKU_MARKS[i];
				}
			}
		}
		return String.valueOf(c);
	}

}
