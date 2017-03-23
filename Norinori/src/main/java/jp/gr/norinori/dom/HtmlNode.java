package jp.gr.norinori.dom;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Node;

/**
 * HTMLノード
 * Jsoup版
 *
 * @author inoue
 */
@SuppressWarnings("rawtypes")
public class HtmlNode<T extends HtmlNode> extends jp.gr.norinori.core.collection.Node<T, String> {

	// メンバ===================================================================
	private Node node;

	// コンストラクタ===========================================================
	/**
	 * HTMLノードのインスタンスを生成する
	 *
	 * @param node Jsoupノード
	 */
	public HtmlNode(Node node) {
		this.node = node;
	}

	// メソッド=================================================================
	/**
	 * ノード名を取得する
	 *
	 * @return ノード名
	 */
	public String getName() {
		return this.node.nodeName();
	}

	/**
	 * ノードの属性を取得する
	 *
	 * @return ノードの属性
	 */
	public HtmlAttributes getAttribute() {
		Attributes attributes = this.node.attributes();
		return new HtmlAttributes(attributes);
	}
}
