package jp.gr.norinori.core.collection;

import java.util.Map;

import jp.gr.norinori.core.collection.RecordFrame;

/**
 * シンプルレコード
 *
 * @author inoue
 *
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public class SimpleRecord<Key, Value> extends RecordFrame<Key, Value> {

	/**
	 * マップを生成する
	 *
	 * @return マップ
	 */
	protected Map<Key, Value> createMap() {
		return new NumberingHashMap<Key, Value>();
	}
}
