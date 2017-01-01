package jp.gr.norinori.core.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import jp.gr.norinori.test.NorinoriTestFrame;

/**
 * 番号付けマップテストフレーム
 *
 * @author nori
 */
public abstract class NumberingMapTestFrame extends NorinoriTestFrame {

	protected NumberingMap<String, String> map;

	protected abstract NumberingMap<String, String> createMap();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.map = createMap();

		this.map.put("1", "100");
		this.map.put("2", "200");
		this.map.put("3", "300");
		this.map.put("4", "400");
		this.map.put("5", "500");
		this.map.put("6", "600");
		this.map.put("7", "700");
		this.map.put("8", "800");
		this.map.put("9", "900");
		this.map.put("10", "1000");
		this.map.put("0", "000");
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#size()}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testSize() {
		assertEquals(11, this.map.size());
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#clear()}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testClear() {
		this.map.clear();

		assertEquals(0, this.map.size());
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#get(int)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testGetInt() {
		assertEquals("200", this.map.get(1));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#getKey(int)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testGetKey() {
		assertEquals("2", this.map.getKey(1));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#indexOf(java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testIndexOf() {
		assertEquals(1, this.map.indexOf("2"));
		assertEquals(-1, this.map.indexOf("99"));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#put(java.lang.Object, java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testPutKeyValue() {
		this.map.put("2", "2000");
		assertEquals("2000", this.map.get("2"));
		assertEquals(1, this.map.indexOf("2"));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#get(java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testGetObject() {
		assertEquals("200", this.map.get("2"));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#containsKey(java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testContainsKeyObject() {
		assertTrue(this.map.containsKey("2"));
		assertFalse(this.map.containsKey("99"));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#containsValue(java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testContainsValueObject() {
		assertTrue(this.map.containsValue("200"));
		assertFalse(this.map.containsValue("00"));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#remove(java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testRemoveObject() {
		assertTrue(this.map.containsKey("2"));
		assertTrue(this.map.containsValue("200"));
		assertEquals(2, this.map.indexOf("3"));

		assertEquals("200", this.map.remove("2"));

		assertFalse(this.map.containsKey("2"));
		assertFalse(this.map.containsValue("200"));
		assertEquals(1, this.map.indexOf("3"));
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#keySet()}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testKeySet() {
		String[] keys = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0" };
		Set<String> keySet = this.map.keySet();

		int count = 0;
		for (String key : keySet) {
			for (int i = 0; i < keys.length; i++) {
				if (keys[i].equals(key)) {
					count++;
					keys[i] = "";
					break;
				}
			}
		}

		assertEquals(11, count);
	}

	/**
	 * {@link jp.gr.norinori.core.collection.NumberingMapFrame#entrySet()}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testEntrySet() {
		String[] keys = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0" };
		String[] values = { "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "000" };
		Set<Entry<String, String>> entrySet = this.map.entrySet();

		int count = 0;
		for (Entry<String, String> entry : entrySet) {
			for (int i = 0; i < keys.length; i++) {
				if (keys[i].equals(entry.getKey())) {
					keys[i] = "";

					if (values[i].equals(entry.getValue())) {
						count++;
						values[i] = "";
						break;
					}
				}
			}
		}

		assertEquals(11, count);
	}

	/**
	 * {@link java.util.AbstractMap#isEmpty()} のためのテスト・メソッド。
	 */
	@Test
	public void testIsEmpty() {
		this.map.clear();
		assertTrue(this.map.isEmpty());
	}

	/**
	 * {@link java.util.AbstractMap#putAll(java.util.Map)} のためのテスト・メソッド。
	 */
	@Test
	public void testPutAll() {
		Map<String, String> addtionalMap = new HashMap<String, String>();
		addtionalMap.put("100", "100000");
		addtionalMap.put("200", "200000");
		this.map.putAll(addtionalMap);

		assertEquals(13, this.map.size());
	}

	/**
	 * {@link java.util.AbstractMap#values()} のためのテスト・メソッド。
	 */
	@Test
	public void testValues() {
		String[] values = { "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "000" };
		Collection<String> vals = this.map.values();

		int count = 0;
		for (String val : vals) {
			for (int i = 0; i < values.length; i++) {
				if (values[i].equals(val)) {
					values[i] = "";

					count++;
				}
			}
		}

		assertEquals(11, count);
	}

	/**
	 * valueList
	 */
	@Test
	public void testValueList() {
		String[] values = { "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "000" };
		List<String> valueList = this.map.valueList();

		int i = 0;
		for (String val : valueList) {
				assertEquals(values[i], val);
				i++;
		}

		assertEquals(11, valueList.size());
	}

	/**
	 * keyList
	 */
	@Test
	public void testKeyList() {
		String[] keys = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0" };
		List<String> keyList = this.map.keyList();

		int i = 0;
		for (String key : keyList) {
			assertEquals(keys[i], key);
			i++;
		}

		assertEquals(11, keyList.size());
	}

	/**
	 * relocation
	 */
	@Test
	public void testRelocation() {
		List<String> keyList = new ArrayList<>();
		keyList.add("0");
		keyList.add("10");
		keyList.add("9");
		keyList.add("8");
		keyList.add("7");
		keyList.add("6");
		keyList.add("5");
		keyList.add("X");

		this.map.relocation(keyList);

		int i = 0;
		for (String key : this.map.keySet()) {
			assertEquals(keyList.get(i), key);
			i++;
		}

		assertFalse(this.map.containsKey("2"));
		assertTrue(this.map.containsKey("X"));
	}

}
