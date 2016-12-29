package jp.gr.norinori.database.mysql;

import jp.gr.norinori.database.DatabaseColumn;

public class MysqlColumn implements DatabaseColumn {
	// メンバ===================================================================
	public String type;
	public String name;
	public String comment;
	public String key;
	public String extra;

	// メソッド=================================================================
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public boolean isAutoIncrement() {
		return (extra != null && extra.equals("auto_increment"));
	}

	public boolean isPrimaryKey() {
		return (key != null && key.equals("PRI"));
	}

}
