package jp.gr.norinori.dom;

import java.io.File;
import java.util.List;

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
}
