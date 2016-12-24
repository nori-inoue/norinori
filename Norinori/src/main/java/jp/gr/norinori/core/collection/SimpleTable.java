package jp.gr.norinori.core.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.gr.norinori.core.collection.Record;
import jp.gr.norinori.core.collection.Table;

/**
 * シンプル表
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public class SimpleTable<Key, Value> implements Table<Key, Value> {
	// メンバ===================================================================
	/* データ */
	private List<Record<Key, Value>> data;

	// コンストラクタ===========================================================
	/**
	 * シンプル表のインスタンスを生成する。
	 *
	 * @param table テーブル
	 * @param columnNumber 列番号
	 */
	public SimpleTable() {
		this.data = new ArrayList<Record<Key, Value>>();
	}

	// メソッド=============================================================
	/*
	 * (非 Javadoc)
	 *
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<Record<Key, Value>> iterator() {
		return this.data.iterator();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.collection.Table#getValue(java.lang.Object, int)
	 */
	public Value getValue(Key key, int rowNumber) {
		Record<Key, Value> record = getRecord(rowNumber);

		return record.getValue(key);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.collection.Table#setValue(java.lang.Object, int,
	 * java.lang.Object)
	 */
	public void setValue(Key key, int rowNumber, Value value) {
		Record<Key, Value> record = getRecord(rowNumber);
		record.setValue(key, value);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.collection.Table#getRecord(int)
	 */
	public Record<Key, Value> getRecord(int rowNumber) {
		Record<Key, Value> record = this.data.get(rowNumber);
		return record;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * jp.gr.norinori.core.collection.Table#addRecord(jp.gr.norinori.core.collection
	 * .Record)
	 */
	public void addRecord(Record<Key, Value> record) {
		this.data.add(record);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.collection.Table#removeRecord(int)
	 */
	public Record<Key, Value> removeRecord(int rowNumber) {
		return this.data.remove(rowNumber);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.collection.Table#clear()
	 */
	public void clear() {
		this.data.clear();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.collection.Table#getRecordCount()
	 */
	public int getRecordCount() {
		return this.data.size();
	}

}
