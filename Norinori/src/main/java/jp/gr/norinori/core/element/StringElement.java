package jp.gr.norinori.core.element;

import jp.gr.norinori.core.element.Element;

/**
 * 文字列要素
 *
 * @author nori
 */
public class StringElement implements Element {
	// メンバ===================================================================
	private String value;

	// コンストラクタ===========================================================
	/**
	 * 文字列要素のインスタンスを生成する
	 *
	 * @param value 文字列
	 */
	public StringElement(String value) {
		this.value = value;
	}

	// メソッド=================================================================
	/**
	 * 文字列を取得する
	 *
	 * @return 文字列
	 */
	public String toString() {
		return this.value;
	}
}
