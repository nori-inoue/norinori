package jp.gr.norinori.core.file;

import java.io.InputStream;
import java.net.URL;

import jp.gr.norinori.define.Encoding;

/**
 * リソースファイル
 *
 * @author nori
 */
public interface ResourceFile {

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

	/**
	 * 読み込みの基盤となるクラスを取得する
	 *
	 * @return 読み込みの基盤となるクラス
	 */
	public Class<? extends Object> getBaseClass();

	/**
	 * ファイル名を取得する
	 *
	 * @return ファイル名
	 */
	public String getName();

	/**
	 * URLを取得する
	 *
	 * @return URL
	 */
	public URL toURL();

	/**
	 * 入力ストリームを取得する
	 *
	 * @return 入力ストリーム
	 */
	public InputStream createInputStream();

	/**
	 * ファイルの存在を判定する
	 *
	 * @return true:存在する、false:存在しない
	 */
	public boolean exists();

}
