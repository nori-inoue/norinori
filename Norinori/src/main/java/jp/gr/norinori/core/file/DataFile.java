package jp.gr.norinori.core.file;

import java.io.File;
import java.net.URI;

import jp.gr.norinori.core.flow.LifeCycle;

public interface DataFile extends LifeCycle {

	/**
	 * ファイルを取得する
	 *
	 * @return ファイル
	 */
	public File getFile();

	/**
	 * ファイル情報を取得する
	 *
	 * @return ファイル情報
	 */
	public FileInformation getFileInformation();

	/**
	 * ファイル名を取得する
	 *
	 * @return ファイル名
	 */
	public String getName();

	/**
	 * ファイルの親パスを取得する
	 *
	 * @return ファイルの親パス
	 */
	public String getParent();

	/**
	 * ファイルフルパスを取得する
	 *
	 * @return ファイルフルパス
	 */
	public String getPath();

	/**
	 * URIを取得する
	 *
	 * @return URI
	 */
	public URI toURI();

	/**
	 * 読み取り可・不可を設定する
	 *
	 * @param canRead true:読み取り可、false:読み取り不可
	 */
	public void setReadable(boolean canRead);

	/**
	 * 読み取り可・不可を判定する
	 *
	 * @return true:読み取り可、false:読み取り不可
	 */
	public boolean canRead();

	/**
	 * 書き込み可・不可を設定する
	 *
	 * @param canRead true:書き込み可、false:書き込み不可
	 */
	public void setWritable(boolean canWrite);

	/**
	 * 書き込み可・不可を判定する
	 *
	 * @return true:書き込み可、false:書き込み不可
	 */
	public boolean canWrite();

	/**
	 * 実行可・不可を設定する
	 *
	 * @param canRead true:実行可、false:実行不可
	 */
	public boolean setExecutable(boolean canExecute);

	/**
	 * 実行可・不可を判定する
	 *
	 * @return true:実行可、false:実行不可
	 */
	public boolean canExecute();

	/**
	 * ファイルの存在を判定する
	 *
	 * @return true:存在する、false:存在しない
	 */
	public boolean exists();

	/**
	 * ファイルを削除する
	 *
	 * @return true:削除に成功、false：削除に失敗
	 */
	public boolean delete();

	/**
	 * ファイル名称／パスを変更する
	 *
	 * @param fileName ファイル名
	 * @return true:変更に成功、false：変更に失敗
	 */
	public boolean renameTo(String fileName);

}
