package jp.gr.norinori.dom;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
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

	/**
	 * 認証を行い、認証情報を取得する
	 *
	 * @param url URL
	 * @param userAgent ユーザエージェント
	 * @param post POST情報
	 * @return 認証情報
	 * @throws IOException
	 */
	public static Authorization authoricate(URL url, String userAgent, Map<String, String> post) throws IOException {
		Connection connection = HttpConnection.connect(url).userAgent(userAgent);

		Response res = connection.data(post).method(Method.POST).execute();

		Authorization authorization = new Authorization();
		authorization.url = url;
		authorization.cookies = res.cookies();
		authorization.userAgent = userAgent;

		return authorization;
	}

	/**
	 * 指定したURLのHTML文を解析する
	 *
	 * @param url URL
	 * @param query パラメータ
	 * @param authorization 認証情報
	 * @return 解析されたHTMLドキュメント
	 * @throws IOException
	 */
	public static HtmlDocument parse(URL url, Map<String, String> query, Authorization authorization)
			throws IOException {
		Connection connection = HttpConnection.connect(url).userAgent(authorization.userAgent) .cookies(authorization.cookies);
		if (query != null) {
			connection = connection.data(query);
		}
		Response res = connection.execute();

		return new HtmlDocument(res.parse());
	}

}
