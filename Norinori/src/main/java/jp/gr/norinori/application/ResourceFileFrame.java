package jp.gr.norinori.application;

import java.io.InputStream;
import java.net.URL;

import jp.gr.norinori.core.file.ResourceFile;
import jp.gr.norinori.define.Encoding;

public class ResourceFileFrame implements ResourceFile {

	// メンバ===================================================================
	private Class<? extends Object> baseClass;
	private String path;
	private Encoding encoding;

	// コンストラクタ===========================================================

	/**
	 * リソースファイルのインスタンスを生成する
	 *
	 * @param baseClass 読み込み時に基盤となるクラス
	 * @param path リソースパス
	 */
	public ResourceFileFrame(Class<? extends Object> baseClass, String path) {
		this.baseClass = baseClass;
		this.path = path;
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ResourceFile#getEncoding()
	 */
	public Encoding getEncoding() {
		return this.encoding;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * jp.gr.norinori.application.ResourceFile#setEncoding(jp.gr.norinori.define
	 * .Encoding)
	 */
	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ResourceFile#getBaseClass()
	 */
	public Class<? extends Object> getBaseClass() {
		return this.baseClass;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ResourceFile#getName()
	 */
	public String getName() {
		return this.path;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ResourceFile#toURL()
	 */
	public URL toURL() {
		return this.baseClass.getResource("/" + this.path);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ResourceFile#createInputStream()
	 */
	public InputStream createInputStream() {
		return this.baseClass.getResourceAsStream(this.path);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ResourceFile#exists()
	 */
	public boolean exists() {
		if (this.baseClass != null
				&& this.baseClass.getResource(this.path) != null) {
			return true;
		}
		return false;
	}

}
