package jp.gr.norinori.core.collection;

import java.util.HashMap;
import java.util.Map;

import jp.gr.norinori.core.collection.NumberingMapFrame;

/**
 * 番号つきハッシュマップ
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public class NumberingHashMap<Key, Value> extends NumberingMapFrame<Key, Value> {

    // コンストラクタ===========================================================
    /**
     * 番号付けマップのインスタンスを生成する
     */
    public NumberingHashMap() {
        super();
    }

    /**
     * 番号付けマップのインスタンスを生成する
     *
     * @param capacity 初期容量
     */
    public NumberingHashMap(int capacity) {
        super(capacity);
    }

    // オーバライドメソッド=====================================================
    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.core.collection.NumberingMapFrame#createMap()
     */
    @Override
    protected Map<Key, Value> createMap() {
        return new HashMap<Key, Value>();
    }

    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.core.collection.NumberingMapFrame#createMap()
     */
    @Override
    protected Map<Key, Value> createMap(int capacity) {
        return new HashMap<Key, Value>(capacity);
    }

}
