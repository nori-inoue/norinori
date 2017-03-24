package jp.gr.norinori.dom;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import jp.gr.norinori.define.Encoding;
import jp.gr.norinori.test.NorinoriTest;

public class HtmlParserTest extends NorinoriTest {

	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/dom";

	// メソッド=================================================================
	@Test
	public void testParse() {

		try {
			HtmlDocument document = HtmlParser.parse(new File(RESOURCE_PATH + "/test.html"), Encoding.UTF8);

			List<HtmlElement> htmlElements = document.children();

			HtmlElement htmlElement = htmlElements.get(0);

			assertEquals("html", htmlElement.getName());

			HtmlElement titleElement = htmlElement.getElementsByTag("title").get(0);
			assertEquals("タイトル", titleElement.getValue());
			assertEquals("タイトル", htmlElement.getFirstElementByTag("title").getValue());
			assertEquals("タイトル", document.title());

			HtmlElement divElement = htmlElement.getFirstElementByTag("div");
			HtmlAttributes divAttributes = divElement.getAttribute();

			assertEquals("wrapper", divAttributes.get("id"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUrlParse() {

		try {
			URL url = new URL("https://www.google.co.jp/");
			HtmlDocument document = HtmlParser.parse(url);

			assertEquals("Google", document.title());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUrlParseQuery() {

		try {
			URL url = new URL("https://www.google.co.jp/search");
			Map<String, String> query = new HashMap<>();
			query.put("q", "桜");

			HtmlDocument document = HtmlParser.parse(url, query);

			HtmlElement h3Element = document.getFirstElementByTag("h3");
			assertThat(h3Element.getValue(), containsString("サクラ"));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Ignore
	public void testParseWithLogin() {

		try {
			String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36 ";

			URL loginUrl = new URL("http://localhost:8090//development/session/login");
			Map<String, String> post = new HashMap<>();
			post.put("email", "inoue");
			post.put("password", "inoue");

			Authorization authorization = HtmlParser.authoricate(loginUrl, ua, post);

			URL topUrl = new URL("http://localhost:8090/development/");
			HtmlDocument document = HtmlParser.parse(topUrl, null, authorization);

			HtmlElement h2Element = document.getFirstElementByTag("h2");
			assertNull(h2Element);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
