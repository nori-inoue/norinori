package jp.gr.norinori.database.postgresql;

import jp.gr.norinori.database.DatabaseColumn;

/**
 * Postgresqlカラム
 *
 * @author inoue
 */
public class PostgresqlColumn implements DatabaseColumn {
	// メンバ===================================================================
	public String type;
	public String name;
	public int size;
	public String comment;
	public boolean isAutoIncrement = false;
	public boolean isPrimaryKey = false;

	// メソッド=================================================================
	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public int getSize() {
		return this.size;
	}

	public String getComment() {
		return this.comment;
	}

	public boolean isAutoIncrement() {
		return this.isAutoIncrement;
	}

	public boolean isPrimaryKey() {
		return this.isPrimaryKey;
	}
}
