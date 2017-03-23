package jp.gr.norinori.dom;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * HTMLエレメント Jsoup版
 *
 * @author inoue
 */
public class HtmlElement extends HtmlNode<HtmlElement> implements jp.gr.norinori.core.element.Element {

	// メンバ===================================================================
	private Element element;

	// コンストラクタ===========================================================
	/**
	 * HTMLエレメントのインスタンスを生成する
	 *
	 * @param element Jsoupエレメント
	 */
	public HtmlElement(Element element) {
		super(element);
		this.element = element;

		createElement();
		setValue(element.text());
	}

	// メソッド=================================================================
	/**
	 * 指定したタグのエレメントを取得する
	 *
	 * @param tagName タグ
	 * @return 指定したタグのエレメント
	 */
	public List<HtmlElement> getElementsByTag(String tagName) {
		return createElement(this.element.getElementsByTag(tagName));
	}

	/**
	 * 指定したタグの最初に見つかったエレメントを取得する
	 *
	 * @param tagName タグ
	 * @return 指定したタグのエレメント
	 */
	public HtmlElement getFirstElementByTag(String tagName) {
		List<HtmlElement> elementList = getElementsByTag(tagName);
		if (elementList.isEmpty()) {
			return null;
		}
		return elementList.get(0);
	}

	// Htmlエレメントを生成する
	private void createElement() {
		for (Element element : this.element.children()) {
			addNode(new HtmlElement(element));
		}
	}

	// Htmlエレメントを生成する
	private List<HtmlElement> createElement(Elements elements) {
		List<HtmlElement> elementList = new ArrayList<>();
		for (Element element : elements) {
			elementList.add(new HtmlElement(element));
		}
		return elementList;
	}
}
