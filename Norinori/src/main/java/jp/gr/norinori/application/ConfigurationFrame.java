package jp.gr.norinori.application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import jp.gr.norinori.application.ApplicationFile;
import jp.gr.norinori.application.Configuration;
import jp.gr.norinori.core.file.ResourceFile;
import jp.gr.norinori.define.Encoding;

/**
 * 設定管理フレーム
 *
 * @author nori
 */
public abstract class ConfigurationFrame implements Configuration {

	// メンバ===================================================================
	private ApplicationFile file;
	private ResourceFile resourceFile;
	private Properties configuration;

	// コンストラクタ===========================================================
	/**
	 * 設定管理のインスタンスを生成する
	 *
	 * @param filename ファイル名
	 */
	public ConfigurationFrame(String filename) {
		this.file = createApplicationFile(filename);
		load();
	}

	/**
	 * 設定管理のインスタンスを生成する
	 *
	 * @param file ファイル
	 */
	public ConfigurationFrame(ApplicationFile file) {
		this.file = file;
		load();
	}

	/**
	 * 設定管理のインスタンスを生成する
	 *
	 * @param resourceFile リソースファイル
	 */
	public ConfigurationFrame(ResourceFile resourceFile) {
		this.resourceFile = resourceFile;
		loadResource();
	}

	// 抽象メソッド=============================================================
	/**
	 * ファイル情報を生成する
	 *
	 * @return ファイル情報
	 */
	protected abstract ApplicationFile createApplicationFile(String filename);

	// メソッド=================================================================
	/**
	 * 読み込んだプロパティ値を取得する
	 *
	 * @param path 設定のキーとなるパス
	 * @return プロパティ値
	 */
	protected String getProperty(String path) {
		return this.configuration.getProperty(path);
	}

	/**
	 * プロパティを読み込む
	 */
	@SuppressWarnings("resource")
	private void load() {
		try {
			this.configuration = new Properties();
			InputStream inputStream = new FileInputStream(this.file.getFile());
			Encoding encoding = Encoding.UTF8;
			if (this.file.getEncoding() != null) {
				encoding = this.file.getEncoding();
			}
			if(encoding == Encoding.UTF8) {
				inputStream = skipBOM(inputStream);
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding.toString());
			this.configuration.load(inputStreamReader);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * プロパティを読み込む
	 */
	private void loadResource() {
		if (!this.resourceFile.exists()) {
			throw new IllegalArgumentException();
		}
		try {
			this.configuration = new Properties();
			InputStream inputStream = this.resourceFile.createInputStream();
			Encoding encoding = Encoding.UTF8;
			if (this.resourceFile.getEncoding() != null) {
				encoding = this.resourceFile.getEncoding();
			}
			if(encoding == Encoding.UTF8) {
				inputStream = skipBOM(inputStream);
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding.toString());
			this.configuration.load(inputStreamReader);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// UTF8 BOMを飛ばす
	private InputStream skipBOM(InputStream inputStream) throws IOException {
		if (!inputStream.markSupported()) {
			// マーク機能が無い場合BufferedInputStreamを被せる
			inputStream = new BufferedInputStream(inputStream);
		}
		inputStream.mark(3); // 先頭にマークを付ける
		if (inputStream.available() >= 3) {
			byte b[] = { 0, 0, 0 };
			inputStream.read(b, 0, 3);
			if (b[0] != (byte) 0xEF || b[1] != (byte) 0xBB || b[2] != (byte) 0xBF) {
				inputStream.reset();// BOMでない場合は先頭まで巻き戻す
			}
		}
		return inputStream;
	}
}
