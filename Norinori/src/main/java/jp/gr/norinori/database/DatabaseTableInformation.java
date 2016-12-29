package jp.gr.norinori.database;

import java.util.List;

/**
 * テーブル情報インタフェース
 *
 * @author inoue
 */
public interface DatabaseTableInformation {

	/**
	 * オートインクリメントテーブルかを判定する
	 *
	 * @return true:オートインクリメントテーブル / false:通常テーブル
	 */
	public boolean hasAutoIncrement();

	/**
	 * プライマリーキーを保持しているかを判定する
	 *
	 * @return true:プライマリーキーを保持している / false:プライマリーキーを保持していない
	 */
	public boolean hasPrimaryKey();

	/**
	 * カラムリストを取得する
	 *
	 * @return カラムリスト
	 */
	public List<DatabaseColumn> getColumns();

	/**
	 * テーブルコメントを取得する
	 *
	 * @return テーブルコメント
	 */
	public String getComment();
}
