package jp.gr.norinori.database.postgresql;

import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.database.DatabaseColumn;
import jp.gr.norinori.database.DatabaseTableInformation;

/**
 * Postgresqlテーブル情報
 *
 * @author inoue
 */
public class PostgresqlTableInformation implements DatabaseTableInformation {
	private boolean hasAutoIncrement;
	private boolean hasPrimaryKey;
	private List<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();
	private String comment;

	/**
	 * オートインクリメントテーブルかを判定する
	 *
	 * @return true:オートインクリメントテーブル / false:通常テーブル
	 */
	public boolean hasAutoIncrement() {
		return this.hasAutoIncrement;
	}

	/**
	 * プライマリーキーを保持しているかを判定する
	 *
	 * @return true:プライマリーキーを保持している / false:プライマリーキーを保持していない
	 */
	public boolean hasPrimaryKey() {
		return this.hasPrimaryKey;
	}

	/**
	 * カラムリストを取得する
	 *
	 * @return カラムリスト
	 */
	public List<DatabaseColumn> getColumns() {
		return this.columns;
	}

	/**
	 * テーブルコメントを取得する
	 *
	 * @return テーブルコメント
	 */
	public String getComment() {
		return this.comment;
	}

	public void setHasAutoIncrement(boolean hasAutoIncrement) {
		this.hasAutoIncrement = hasAutoIncrement;
	}

	public void setHasPrimaryKey(boolean hasPrimaryKey) {
		this.hasPrimaryKey = hasPrimaryKey;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void addColumn(DatabaseColumn column) {
		this.columns.add(column);
	}
}
