package jp.gr.norinori.core.collection;

import java.util.List;
import java.util.Map;

/**
 * 番号付けマップ
 *
 * @author norinori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public interface NumberingMap<Key, Value> extends Map<Key, Value> {

    /**
     * 指定された番号から値を取得する
     *
     * @param index 番号
     * @return 値
     */
    public Value getOf(int index);

    /**
     * 指定された番号からキーを取得する
     *
     * @param index 番号
     * @return キー
     */
    public Key getOfKey(int index);

    /**
     * 指定されたキーから番号を取得する
     *
     * @param key キー
     * @return 番号
     */
    public int indexOf(Key key);

	/**
	 * キーのリストを取得する
	 *
	 * @return キーのリスト
	 */
	public List<Key> keyList();

	/**
	 * 値のリストを取得する
	 *
	 * @return 値のリスト
	 */
	public List<Value> valueList();

	/**
	 * キーの順番を再配置する
	 *
	 * @param キーのリスト
	 */
	public void relocation(List<Key> keyList);
}
