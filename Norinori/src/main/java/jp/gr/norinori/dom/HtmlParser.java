package jp.gr.norinori.dom;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import jp.gr.norinori.define.Encoding;

/**
 * HTMLパーサ Jsoup版
 *
 * @author inoue
 */
public class HtmlParser {

	/**
	 * 指定したファイルのHTML文を解析する
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

	/**
	 * 指定したURLのHTML文を解析する
	 *
	 * @param url URL
	 * @return 解析されたHTMLドキュメント
	 * @throws IOException
	 */
	public static HtmlDocument parse(URL url) throws IOException {
		return parse(url, null);
	}

	/**
	 * 指定したURLのHTML文を解析する
	 *
	 * @param url URL
	 * @param query パラメータ
	 * @return 解析されたHTMLドキュメント
	 * @throws IOException
	 */
	public static HtmlDocument parse(URL url, Map<String, String> query) throws IOException {
		Connection connection = HttpConnection.connect(url);
		if (query != null) {
			connection = connection.data(query);
		}

		return new HtmlDocument(connection.get());
	}
}
