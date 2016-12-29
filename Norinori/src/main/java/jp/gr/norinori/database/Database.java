package jp.gr.norinori.database;

import jp.gr.norinori.core.file.ResourceFile;

/**
 * データベースインタフェース
 *
 * @author inoue
 */
public interface Database {

	/**
	 * 設定ファイルを読み取る
	 *
	 * @param filename 設定ファイル名
	 */
	public void configure(String filename);

	/**
	 * 設定ファイルを読み取る
	 *
	 * @param resourceFile 設定ファイル名
	 */
	public void configure(ResourceFile resourceFile);

	/**
	 * データベースに接続する
	 *
	 * @param target 接続先
	 * @return 接続情報
	 */
	public DatabaseConnection createConnection(String target);

	/**
	 * データベースに接続する
	 *
	 * @return 接続情報
	 */
	public DatabaseConnection createConnection();

	/**
	 * データベース設定管理を取得する。
	 *
	 * @return データベース設定管理
	 */
	public DatabaseConfiguration getDatabaseConfiguration();
}
