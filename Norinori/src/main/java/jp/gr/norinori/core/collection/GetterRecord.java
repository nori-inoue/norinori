package jp.gr.norinori.core.collection;

import java.sql.Timestamp;
import java.text.ParseException;

import jp.gr.norinori.core.collection.Record;
import jp.gr.norinori.utility.DateUtil;
import jp.gr.norinori.utility.StringUtil;

public class GetterRecord<Key, Value> {
	private Record<Key, Value> record;

	public GetterRecord(Record<Key, Value> record) {
		this.record = record;
	}

	public Object getObject(Key key) {
		return this.record.getValue(key);
	}

	public String getString(Key key) {
		Object value = getObject(key);
		if (value == null) {
			return null;
		}

		return String.valueOf(value);
	}

	public boolean getBoolean(Key key) {
		Object value = getObject(key);
		if (value == null) {
			return false;
		}

		return Boolean.parseBoolean(String.valueOf(value));
	}

	public int getInt(Key key) {
		String value = getString(key);
		if (value == null) {
			return 0;
		}

		return Integer.parseInt(value);
	}

	public String getFormattedString(Key key, String format) {
		Object value = getObject(key);
		if (value == null) {
			return null;
		}
		if (value instanceof Timestamp) {
			return DateUtil.formattedTimestamp((Timestamp) value, format);
		}

		if (value instanceof Integer) {
			return StringUtil.formatNumber((Integer) value, format);
		}
		return String.valueOf(value);
	}

	public Timestamp getTimestamp(Key key, String format) throws ParseException {
		String value = getString(key);
		if (value == null) {
			return null;
		}

		return DateUtil.toTimestamp(value, format);
	}

	public boolean isEmpty(Key key) {
		String value = getString(key);
		if (value == null || value.equals("")) {
			return true;
		}
		return false;
	}
}
