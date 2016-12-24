package jp.gr.norinori.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;

import jp.gr.norinori.utility.DateUtil;

import org.junit.Test;

/**
 * 日付ユーティリティテスト
 *
 * @author inoue
 */
public class DateUtilTest {
	@Test
	public void testGetNowString() {
		try {
			String result = DateUtil.getNowString("yyyyMMdd");
			System.out.println(result);

			assertEquals(8, result.length());
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddDateString() {
		try {
			String now = DateUtil.getNowString("yyyyMMdd");
			System.out.println(now);

			String result =DateUtil.addDay(now, 1, "yyyyMMdd");
			System.out.println(result);

		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
}
