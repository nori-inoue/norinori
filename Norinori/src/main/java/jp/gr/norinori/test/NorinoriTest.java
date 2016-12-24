package jp.gr.norinori.test;

import jp.gr.norinori.text.TextFile;

import org.junit.Assert;

/**
 * @author nori
 */
public class NorinoriTest extends Assert {

	/**
	 * 指定したテキストファイルを比較する
	 *
	 * @param expected 予想ファイル
	 * @param actual 結果ファイル
	 */
	public static void assertEquals(TextFile expected, TextFile actual) {
		assertEquals("", expected, actual);
	}

	/**
	 * 指定したテキストファイルを比較する<br/>
	 * エラーの場合、指定したメッセージを表示する
	 *
	 * @param expected 予想ファイル
	 * @param actual 結果ファイル
	 */
	public static void assertEquals(String message, TextFile expected,
			TextFile actual) {
		if (expected == null && actual == null) {
			return;
		}
		if (expected != null && actual == null) {
			failNotEquals(message, expected, actual);
			return;
		}

		boolean isExpectedLiving = false;
		boolean isActualLiving = false;

		try {
			isExpectedLiving = expected.isLiving();
			if (!isExpectedLiving) {
				expected.create();
			}
			String expectedContents = expected.getContents();

			try {
				isActualLiving = actual.isLiving();
				if (!isActualLiving) {
					actual.create();
				}
				String actualContents = actual.getContents();

				if (expectedContents != null
						&& expectedContents.equals(actualContents)) {
					return;
				}

				failNotEquals(message, expectedContents, actualContents);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		} catch (Exception e) {
			fail(e.getMessage());

		} finally {
			if (!isExpectedLiving) {
				try {
					expected.destroy();
				} catch (Exception ignore) {
				}
			}
			if (!isActualLiving) {
				try {
					actual.destroy();
				} catch (Exception ignore) {
				}
			}
		}

	}

	private static void failNotEquals(String message, Object expected,
			Object actual) {
		fail(format(message, expected, actual));
	}

	private static String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null && !message.equals("")) {
			formatted = message + " ";
		}
		String expectedString = String.valueOf(expected);
		String actualString = String.valueOf(actual);
		if (expectedString.equals(actualString)) {
			return formatted + "expected: "
					+ formatClassAndValue(expected, expectedString)
					+ " but was: " + formatClassAndValue(actual, actualString);
		} else {
			return formatted + "expected:<" + expectedString + "> but was:<"
					+ actualString + ">";
		}
	}

	private static String formatClassAndValue(Object value, String valueString) {
		String className = value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}

}
