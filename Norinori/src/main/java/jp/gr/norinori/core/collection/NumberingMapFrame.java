package jp.gr.norinori.core.collection;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import jp.gr.norinori.core.collection.NumberingMap;

/**
 * 番号付けマップフレーム
 *
 * @author nori
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public abstract class NumberingMapFrame<Key, Value> extends AbstractMap<Key, Value>
		implements NumberingMap<Key, Value> {

	// メンバ===================================================================
	private List<Key> keyList;
	private Map<Key, Value> data;

	// コンストラクタ===========================================================
	/**
	 * 番号付けマップのインスタンスを生成する
	 */
	public NumberingMapFrame() {
		super();
		this.keyList = new ArrayList<Key>();
		this.data = createMap();
	}

	/**
	 * 番号付けマップのインスタンスを生成する
	 *
	 * @param capacity 初期容量
	 */
	public NumberingMapFrame(int capacity) {
		super();
		this.keyList = new ArrayList<Key>();
		this.data = createMap(capacity);
	}

	// 抽象メソッド=============================================================
	/**
	 * マップを生成する
	 *
	 * @return マップ
	 */
	protected abstract Map<Key, Value> createMap();

	/**
	 * マップを生成する
	 *
	 * @param capacity 初期容量
	 * @return マップ
	 */
	protected abstract Map<Key, Value> createMap(int capacity);

	// メソッド=============================================================
	/**
	 * 指定された番号から値を取得する
	 *
	 * @param index 番号
	 * @return 値
	 */
	public Value get(int index) {
		return get(getKey(index));
	}

	/**
	 * 指定された番号からキーを取得する
	 *
	 * @param index 番号
	 * @return キー
	 */
	public Key getKey(int index) {
		return this.keyList.get(index);
	}

	/**
	 * 指定されたキーから番号を取得する
	 *
	 * @param key キー
	 * @return 番号
	 */
	public int indexOf(Key key) {
		return this.keyList.indexOf(key);
	}

	/**
	 * クリアする
	 */
	public void clear() {
		this.data.clear();
		this.keyList.clear();
	}

	/**
	 * 指定された値と指定されたキー関連付ける
	 *
	 * @param key キー
	 * @param value 値
	 * @return 元の関連付けされていた値
	 */
	public Value put(Key key, Value value) {
		Value originalValue = this.data.put(key, value);
		if (originalValue == null) {
			this.keyList.add(key);
		}

		return originalValue;
	}

	/**
	 * 指定されたキー関連付いた値を取得する
	 *
	 * @param key キー
	 * @return 関連付けされていた値
	 */
	public Value get(Object key) {
		return this.data.get(key);
	}

	/**
	 * 関連付けされた数を取得する
	 *
	 * @return 関連付けされた数
	 */
	public int size() {
		return this.keyList.size();
	}

	/**
	 * 指定されたキーが関連付けされているかどうかを判定する
	 *
	 * @param key キー
	 * @return 関連付けされている場合はtrue、されていない場合はfalse
	 */
	public boolean containsKey(Object key) {
		return this.data.containsKey(key);
	}

	/**
	 * 指定された値が存在するかどうかを判定する
	 *
	 * @param value 値
	 * @return 存在する場合はtrue、存在しない場合はfalse
	 */
	public boolean containsValue(Object value) {
		return this.data.containsValue(value);
	}

	/**
	 * キーに対する関連付けがあれば、そのキーを関連付けから削除
	 *
	 * @param key キー
	 * @return 関連付けされていた値、存在しなった場合はnull
	 */
	public Value remove(Object key) {
		Value originalValue = this.data.remove(key);

		@SuppressWarnings("unchecked")
		int index = indexOf((Key) key);
		this.keyList.remove(index);

		return originalValue;
	}

	/**
	 * 値のコレクションを取得する
	 *
	 * @return 値のコレクション
	 */
	public Collection<Value> values() {
		List<Value> values = new ArrayList<Value>();
		for (Key key : this.keyList) {
			values.add(this.data.get(key));
		}
		return values;
	}

	/**
	 * キーセットを取得する
	 *
	 * @return キーセット
	 */
	public Set<Key> keySet() {
		return new KeySet(this.keyList);
	}

	/**
	 * エントリセットを取得する
	 *
	 * @return エントリセット
	 */
	public Set<Entry<Key, Value>> entrySet() {
		return new EntrySet(this.keyList, this.data);
	}

	/**
	 * キーの順番を再配置する
	 *
	 * @param キーのリスト
	 */
	public void relocation(List<Key> keyList) {
		Map<Key, Value> exists = createMap(keyList.size());
		for(Key key : keyList) {
			if(!this.data.containsKey(key)) {
				this.data.put(key, null);
			}
			exists.put(key, null);
		}
		for(Key key : this.keyList) {
			if(!exists.containsKey(key)) {
				this.data.remove(key);
			}
		}
		this.keyList = keyList;
	}

	/*
	 *
	 */
	private final class EntrySet extends AbstractSet<Map.Entry<Key, Value>> {
		private List<Key> keyList;
		private Map<Key, Value> data;

		public EntrySet(List<Key> keyList, Map<Key, Value> data) {
			this.keyList = keyList;
			this.data = data;
		}

		public Iterator<Map.Entry<Key, Value>> iterator() {
			return new EntryIterator(this.keyList, this.data);
		}

		public boolean contains(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			return this.data.containsKey(o);
		}

		public boolean remove(Object o) {
			this.keyList.remove(o);
			return this.data.remove(o) != null;
		}

		public int size() {
			return this.data.size();
		}

		public void clear() {
			this.data.clear();
			this.keyList.clear();
		}

	}

	/*
	 *
	 */
	private class EntryIterator implements Iterator<Map.Entry<Key, Value>> {
		private List<Key> keyList;
		private Map<Key, Value> data;

		int current = 0;
		int dataSize = -1;

		public EntryIterator(List<Key> keyList, Map<Key, Value> data) {
			this.keyList = keyList;
			this.data = data;
			this.dataSize = this.keyList.size();
		}

		public boolean hasNext() {
			if (this.current < this.dataSize) {
				return true;
			}
			return false;
		}

		public java.util.Map.Entry<Key, Value> next() {
			Key key = this.keyList.get(this.current);
			Value value = this.data.get(key);
			this.current++;
			return new MapEntry(key, value);
		}

		public void remove() {
			Key key = this.keyList.get(this.current);
			this.keyList.remove(this.current);
			this.data.remove(key);
		}
	}

	/**
	 *
	 * Map.Entry implement
	 *
	 * @author nori
	 */
	public class MapEntry implements Map.Entry<Key, Value> {
		private Key key;
		private Value value;

		/**
		 *
		 * @param key
		 * @param value
		 */
		public MapEntry(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * @return
		 * @see java.util.Map.Entry#getKey()
		 */
		public Key getKey() {
			return this.key;
		}

		/**
		 * @return
		 * @see java.util.Map.Entry#getValue()
		 */
		public Value getValue() {
			return this.value;
		}

		/**
		 * @return
		 * @see java.util.Map.Entry#setValue(java.lang.Object)
		 */
		public Value setValue(Value value) {
			Value orginalValue = this.value;
			this.value = value;
			return orginalValue;
		}

	}

	public class KeySet implements Set<Key> {
		private List<Key> list;

		/**
		 *
		 * @param list
		 */
		public KeySet(List<Key> list) {
			this.list = list;
		}
		/**
		 * @return
		 * @see java.util.List#size()
		 */
		public int size() {
			return list.size();
		}

		/**
		 * @return
		 * @see java.util.List#isEmpty()
		 */
		public boolean isEmpty() {
			return list.isEmpty();
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#contains(java.lang.Object)
		 */
		public boolean contains(Object arg0) {
			return list.contains(arg0);
		}

		/**
		 * @return
		 * @see java.util.List#iterator()
		 */
		public Iterator<Key> iterator() {
			return list.iterator();
		}

		/**
		 * @return
		 * @see java.util.List#toArray()
		 */
		public Object[] toArray() {
			return list.toArray();
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		public <T> T[] toArray(T[] arg0) {
			return list.toArray(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#add(java.lang.Object)
		 */
		public boolean add(Key arg0) {
			return list.add(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#remove(java.lang.Object)
		 */
		public boolean remove(Object arg0) {
			return list.remove(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		public boolean containsAll(Collection<?> arg0) {
			return list.containsAll(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		public boolean addAll(Collection<? extends Key> arg0) {
			return list.addAll(arg0);
		}

		/**
		 * @param arg0
		 * @param arg1
		 * @return
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		public boolean addAll(int arg0, Collection<? extends Key> arg1) {
			return list.addAll(arg0, arg1);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		public boolean removeAll(Collection<?> arg0) {
			return list.removeAll(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		public boolean retainAll(Collection<?> arg0) {
			return list.retainAll(arg0);
		}

		/**
		 *
		 * @see java.util.List#clear()
		 */
		public void clear() {
			list.clear();
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#equals(java.lang.Object)
		 */
		public boolean equals(Object arg0) {
			return list.equals(arg0);
		}

		/**
		 * @return
		 * @see java.util.List#hashCode()
		 */
		public int hashCode() {
			return list.hashCode();
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#get(int)
		 */
		public Key get(int arg0) {
			return list.get(arg0);
		}

		/**
		 * @param arg0
		 * @param arg1
		 * @return
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		public Key set(int arg0, Key arg1) {
			return list.set(arg0, arg1);
		}

		/**
		 * @param arg0
		 * @param arg1
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		public void add(int arg0, Key arg1) {
			list.add(arg0, arg1);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#remove(int)
		 */
		public Key remove(int arg0) {
			return list.remove(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#indexOf(java.lang.Object)
		 */
		public int indexOf(Object arg0) {
			return list.indexOf(arg0);
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#lastIndexOf(java.lang.Object)
		 */
		public int lastIndexOf(Object arg0) {
			return list.lastIndexOf(arg0);
		}

		/**
		 * @return
		 * @see java.util.List#listIterator()
		 */
		public ListIterator<Key> listIterator() {
			return list.listIterator();
		}

		/**
		 * @param arg0
		 * @return
		 * @see java.util.List#listIterator(int)
		 */
		public ListIterator<Key> listIterator(int arg0) {
			return list.listIterator(arg0);
		}

		/**
		 * @param arg0
		 * @param arg1
		 * @return
		 * @see java.util.List#subList(int, int)
		 */
		public List<Key> subList(int arg0, int arg1) {
			return list.subList(arg0, arg1);
		}
	}
}
