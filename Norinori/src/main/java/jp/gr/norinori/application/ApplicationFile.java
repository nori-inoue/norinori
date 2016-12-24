package jp.gr.norinori.application;

import jp.gr.norinori.core.file.DataFile;
import jp.gr.norinori.define.Encoding;

/**
 * アプリケーションファイル
 *
 * @author nori
 */
public interface ApplicationFile extends DataFile {

	/**
	 * 文字コードを取得する
	 *
	 * @return 文字コード
	 */
	public Encoding getEncoding();

	/**
	 * 文字コードを取得する
	 *
	 * @param encode 文字コード
	 */
	public void setEncoding(Encoding encoding);

}
