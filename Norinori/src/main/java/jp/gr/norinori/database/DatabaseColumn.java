package jp.gr.norinori.database;

/**
 * データベースカラムインタフェース
 *
 * @author inoue
 */
public interface DatabaseColumn {
	/**
	 * タイプを取得する
	 *
	 * @return タイプ
	 */
	public String getType();

	/**
	 * カラム名を取得する
	 *
	 * @return カラム名
	 */
	public String getName();

	/**
	 * サイズを取得する。
	 *
	 * @return サイズ
	 */
	public int getSize();

	/**
	 * コメントを取得する。
	 *
	 * @return コメント
	 */
	public String getComment();

	/**
	 * オートインクリメントカラムかを判定する
	 *
	 * @return true:オートインクリメント / false:通常カラム
	 */
	public boolean isAutoIncrement();

	/**
	 * プライマリーキーかを判定する
	 *
	 * @return true:プライマリーキー / false:非プライマリーキー
	 */
	public boolean isPrimaryKey();
}
