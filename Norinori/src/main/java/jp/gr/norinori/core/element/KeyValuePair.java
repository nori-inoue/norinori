package jp.gr.norinori.core.element;

import jp.gr.norinori.core.element.Pair;

/**
 * キー・値ペア
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public class KeyValuePair<Key, Value> implements Pair<Key, Value> {
	// メンバ===================================================================
	private Key key;
	private Value value;

	// コンストラクタ===========================================================
	/**
	 * キー・値ペアのインスタンスを生成する
	 *
	 * @param key キー
	 * @param value 値
	 */
	public KeyValuePair(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	// メソッド=================================================================
	/**
	 * キーを取得する
	 *
	 * @return キー
	 */
	public Key getFirst() {
		return this.key;
	}

	/**
	 * キーを取得する
	 *
	 * @return キー
	 */
	public Key getKey() {
		return this.key;
	}

	/**
	 * 値を取得する
	 *
	 * @return 値
	 */
	public Value getSecond() {
		return this.value;
	}

	/**
	 * 値を取得する
	 *
	 * @return 値
	 */
	public Value getValue() {
		return this.value;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if ((o instanceof Pair) && (((Pair) o).getFirst().equals(getFirst()))) {
			if ((o instanceof Pair) && (((Pair) o).getSecond().equals(getSecond()))) {
				return true;
			}
		}
		return false;

	}

	@Override
	public int hashCode() {
		return (getFirst().hashCode() * 17 + getSecond().hashCode() * 31);
	}
}
