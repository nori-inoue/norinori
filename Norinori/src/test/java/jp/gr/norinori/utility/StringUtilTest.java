package jp.gr.norinori.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import jp.gr.norinori.utility.StringUtil;

import org.junit.Test;

/**
 * 文字列ユーティリティテスト
 *
 * @author inoue
 */
public class StringUtilTest {

	@Test
	public void testIsNumeric() {
		assertFalse(StringUtil.isNumeric(""));
		assertFalse(StringUtil.isNumeric("a"));
		assertFalse(StringUtil.isNumeric("1a"));
		assertFalse(StringUtil.isNumeric("a1"));
		assertFalse(StringUtil.isNumeric("-"));

		assertTrue(StringUtil.isNumeric("10"));
		assertTrue(StringUtil.isNumeric("-1"));
		assertTrue(StringUtil.isNumeric("2.90"));
	}

	@Test
	public void testFormatNumber() {
		assertEquals("0100", StringUtil.formatNumber(100, "0000"));
		assertEquals("100", StringUtil.formatNumber(100, "00"));
		assertEquals("100", StringUtil.formatNumber(100, "####"));
		assertEquals("0100.00", StringUtil.formatNumber(100, "0000.00"));
		assertEquals("100", StringUtil.formatNumber(100, "####.##"));
		assertEquals("1,000", StringUtil.formatNumber(1000, "#,###"));

		assertEquals("1,000.05", StringUtil.formatNumber(1000.05, "#,###.##"));

		assertEquals("1,000.05", StringUtil.formatNumber("1000.05", "#,###.##"));
	}

	@Test
	public void testToHankakuKana() {
		assertEquals("我思ｳﾕｴﾆ我ｱﾘ｡ﾅｰｾﾞﾅｾﾞ？", StringUtil.toHankaku("我思ウゆえに我アリ。ナーゼなぜ？"));
	}

	@Test
	public void testLength() {
		assertEquals(3, StringUtil.length("abc"));
		assertEquals(5, StringUtil.length("我思ｳ"));
		assertEquals(8, StringUtil.length("我ABC思ｳ"));

		assertEquals(0, StringUtil.length(null));
	}

	@Test
	public void testPad() {
		assertEquals("abcabcabc", StringUtil.pad("abc", 3));
		assertEquals("", StringUtil.pad("abc", -1));

		assertNull(StringUtil.pad(null, 3));
		assertNull(StringUtil.pad(null, -1));
	}

	@Test
	public void testCenter() {
		assertEquals("   a  ", StringUtil.center("a", 6, ' '));
		assertEquals("-abc-", StringUtil.center("abc", 5, '-'));

		assertNull(StringUtil.center(null, 6, ' '));
	}

	@Test
	public void testReplace() {
		assertEquals("xbcxbc", StringUtil.replace("abcabc", "a", "x"));
		assertEquals("xcxc", StringUtil.replace("abcabc", "ab", "x"));

		assertNull(StringUtil.replace(null, "a", "x"));
	}

	@Test
	public void testPascalize() {
		assertEquals("Abcabc", StringUtil.pascalize("abcabc"));
		assertEquals("AbcAbc", StringUtil.pascalize("abc_abc"));
		assertEquals("AbcAbc", StringUtil.pascalize("Abc_abc"));
		assertEquals("AbcAbc", StringUtil.pascalize("abc_Abc"));

		assertNull(StringUtil.pascalize(null));
	}

	@Test
	public void testCamelize() {
		assertEquals("abcabc", StringUtil.camelize("abcabc"));
		assertEquals("abcAbc", StringUtil.camelize("abc_abc"));
		assertEquals("abcAbc", StringUtil.camelize("Abc_abc"));
		assertEquals("abcAbc", StringUtil.camelize("abc_Abc"));

		assertNull(StringUtil.pascalize(null));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(StringUtil.isEmpty("abcabc"));
		assertTrue(StringUtil.isEmpty(""));
		assertFalse(StringUtil.isEmpty(" "));
		assertTrue(StringUtil.isEmpty(null));

	}
}