package jp.gr.norinori.core.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jp.gr.norinori.core.collection.Record;

/**
 * レコードフレーム
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public abstract class RecordFrame<Key, Value> implements Record<Key, Value> {

	// メンバ===================================================================
	private Map<Key, Value> data;

	// コンストラクタ===========================================================
	/**
	 * レコードのインスタンスを生成する
	 */
	public RecordFrame() {
		this.data = createMap();
	}

	// 抽象メソッド=============================================================
	/**
	 * マップを生成する
	 *
	 * @return マップ
	 */
	protected abstract Map<Key, Value> createMap();

	// メソッド=================================================================
	/**
	 * 値を取得する
	 *
	 * @param key キー
	 * @return 値
	 */
	public Value getValue(Key key) {
		return this.data.get(key);
	}

	/**
	 * 値を設定する
	 *
	 * @param key キー
	 * @param value 値
	 */
	public void setValue(Key key, Value value) {
		this.data.put(key, value);
	}

	/**
	 * イテレータ
	 */
	public Iterator<Entry<Key,Value>> iterator() {
		return this.data.entrySet().iterator();
	}
}
