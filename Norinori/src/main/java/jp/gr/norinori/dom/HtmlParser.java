package jp.gr.norinori.dom;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import jp.gr.norinori.define.Encoding;

/**
 * HTMLパーサ
 * Jsoup版
 *
 * @author inoue
 */
public class HtmlParser {

	/**
	 * HTML文を解析する
	 *
	 * @param file HTMLファイル
	 * @param encoding 文字コード
	 * @return 解析されたHTMLドキュメント
	 * @throws IOException
	 */
	public static HtmlDocument parse(File file, Encoding encoding) throws IOException {
		Document document = Jsoup.parse(file, encoding.toString());

		return new HtmlDocument(document);
	}
}
