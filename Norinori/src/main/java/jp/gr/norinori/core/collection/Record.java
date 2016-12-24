package jp.gr.norinori.core.collection;

import java.util.Map.Entry;

/**
 * レコード
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public interface Record<Key, Value> extends Iterable<Entry<Key, Value>> {

	/**
	 * 値を取得する
	 *
	 * @param key キー
	 * @return 値
	 */
	public Value getValue(Key key);

	/**
	 * 値を設定する
	 *
	 * @param key キー
	 * @param value 値
	 */
	public void setValue(Key key, Value value);
}
