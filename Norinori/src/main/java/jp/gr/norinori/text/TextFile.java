package jp.gr.norinori.text;

import jp.gr.norinori.core.file.DataFile;
import jp.gr.norinori.define.Encoding;

/**
 * テキストファイルインタフェース
 *
 * @author nori
 */
public interface TextFile extends DataFile {

	/**
	 * エンコーディングを取得する
	 *
	 * @param encoding エンコーディング
	 */
	public void setEncoding(Encoding encoding);

	/**
	 * エンコーディングを取得する
	 *
	 * @return エンコーディング
	 */
	public Encoding getEncoding();

	/**
	 * 内容を取得する
	 *
	 * @return テキストファイルの内容
	 */
	public String getContents();

	/**
	 * 内容に追加する
	 *
	 * @param text 追加するテキスト
	 */
	public void append(String text);

	/**
	 * 内容を反映する
	 */
	public void flush();
}
