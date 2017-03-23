package jp.gr.norinori.dom;

import org.jsoup.nodes.Attributes;

/**
 * HTMLノード属性
 * Jsoup版
 *
 * @author inoue
 */
public class HtmlAttributes implements jp.gr.norinori.core.element.Attributes {
	// メンバ===================================================================
	private Attributes attributes;

	// コンストラクタ===========================================================
	/**
	 * HTMLノード属性のインスタンスを生成する
	 *
	 * @param attributes Jsoup版属性
	 */
	public HtmlAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	// メソッド=================================================================
	@Override
	public String get(String key) {
		return this.attributes.get(key);
	}

	@Override
	public void set(String key, String value) {
		this.attributes.put(key, value);
	}

	@Override
	public boolean hasKey(String key) {
		return this.attributes.hasKey(key);
	}

}
