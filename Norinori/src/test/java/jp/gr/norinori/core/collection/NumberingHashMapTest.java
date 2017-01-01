package jp.gr.norinori.core.collection;

/**
 * 番号付けマップテスト
 *
 * @author nori
 */
public class NumberingHashMapTest extends NumberingMapTestFrame {

	protected NumberingMap<String, String> createMap() {
		return new NumberingHashMap<String, String>();
	}

	protected NumberingMap<String, String> createMap(NumberingMap<String, String> map) {
		return new NumberingHashMap<String, String>(map);
	}
}
