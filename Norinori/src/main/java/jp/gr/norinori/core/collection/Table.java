package jp.gr.norinori.core.collection;

/**
 * 表
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public interface Table<Key, Value> extends Iterable<Record<Key, Value>> {

	/**
	 * 値を取得する
	 *
	 * @param key キー
	 * @param rowNumber 行番号
	 * @return 値
	 */
	public Value getValue(Key key, int rowNumber);

	/**
	 * 値を設定する
	 *
	 * @param key キー
	 * @param rowNumber 行番号
	 * @param value 値
	 */
	public void setValue(Key key, int rowNumber, Value value);

	/**
	 * レコードを取得する
	 *
	 * @param rowNumber 行番号
	 * @return レコード
	 */
	public Record<Key, Value> getRecord(int rowNumber);

	/**
	 * レコードを追加する
	 *
	 * @param record レコード
	 */
	public void addRecord(Record<Key, Value> record);

	/**
	 * レコードを削除する
	 *
	 * @param rowNumber 行番号
	 * @return 削除したレコード
	 */
	public Record<Key, Value> removeRecord(int rowNumber);

	/**
	 * クリアする
	 */
	public void clear();

	/**
	 * レコード件数を取得する
	 *
	 * @return レコード件数
	 */
	public int getRecordCount();
}
