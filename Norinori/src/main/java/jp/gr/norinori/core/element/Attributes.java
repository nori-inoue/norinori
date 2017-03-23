package jp.gr.norinori.core.element;

/**
 * 属性インタフェース
 *
 * @author inoue
 */
public interface Attributes {

	/**
	 * 属性を取得する
	 *
	 * @param key 属性キー
	 * @return 属性キーの値
	 */
	public String get(String key);

	/**
	 * 属性を設定する
	 *
	 * @param key 属性キー
	 * @param value 属性値
	 */
	public void set(String key, String value);

	/**
	 * 属性の存在をチェックする
	 *
	 * @param key 属性キー
	 * @return true:属性が存在する false:属性が存在しない
	 */
	public boolean hasKey(String key);
}
