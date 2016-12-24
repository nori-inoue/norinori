package jp.gr.norinori.core.collection;

import jp.gr.norinori.core.collection.NumberingHashMap;
import jp.gr.norinori.core.collection.NumberingMap;
import jp.gr.norinori.core.collection.NumberingMapTestFrame;

/**
 * 番号付けマップテスト
 *
 * @author nori
 */
public class NumberingHashMapTest extends NumberingMapTestFrame {

    protected NumberingMap<String, String> createMap() {
        return new NumberingHashMap<String, String>();
    }
}
