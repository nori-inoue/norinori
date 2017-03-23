package jp.gr.norinori.dom;

import org.jsoup.nodes.Document;

/**
 * HTMLドキュメント
 * Jsoup版
 *
 * @author inoue
 */
public class HtmlDocument extends HtmlElement {

	// メンバ===================================================================
	private Document document;

	// コンストラクタ===========================================================
	/**
	 * HTMLドキュメントのインスタンスを生成する
	 *
	 * @param document Jsoupドキュメント
	 */
	public HtmlDocument(Document document) {
		super(document);
		this.document = document;
	}

	// メソッド=================================================================
	/**
	 * タイトルを取得する
	 *
	 * @return タイトル
	 */
	public String title() {
		return this.document.title();
	}
}
