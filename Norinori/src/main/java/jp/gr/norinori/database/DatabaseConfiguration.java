package jp.gr.norinori.database;

import jp.gr.norinori.application.ApplicationFile;
import jp.gr.norinori.application.ConfigurationFrame;
import jp.gr.norinori.application.SimpleApplicationFile;
import jp.gr.norinori.core.element.StringElement;
import jp.gr.norinori.core.file.ResourceFile;
import jp.gr.norinori.define.Encoding;

/**
 * データベース設定管理
 *
 * @author nori
 */
public class DatabaseConfiguration extends ConfigurationFrame {

	// コンストラクタ===========================================================
	/**
	 * データベース設定管理のインスタンスを生成する
	 *
	 * @param filename ファイル名
	 */
	public DatabaseConfiguration(String filename) {
		super(filename);
	}

	/**
	 * データベース設定管理のインスタンスを生成する
	 *
	 * @param file ファイル
	 */
	public DatabaseConfiguration(ApplicationFile file) {
		super(file);
	}

	/**
	 * データベース設定管理のインスタンスを生成する
	 *
	 * @param resourceFile リソースファイル
	 */
	public DatabaseConfiguration(ResourceFile resourceFile) {
		super(resourceFile);
	}

	@Override
	public StringElement getParameter(String path) {
		return new StringElement(getProperty(path));
	}

	// メソッド=================================================================
	/**
	 * サーバ名を取得する
	 *
	 * @param target 接続先
	 * @return サーバ名
	 */
	public String getServer(String target) {
		return getParameterWithTarget(target, "server");
	}

	/**
	 * データベース名を取得する
	 *
	 * @param target 接続先
	 * @return データベース名
	 */
	public String getDatabaseName(String target) {
		return getParameterWithTarget(target, "dbname");
	}

	/**
	 * 文字コードを取得する
	 *
	 * @param target 接続先
	 * @return 文字コード
	 */
	public Encoding getEncode(String target) {
		return Encoding.valueOf(getParameterWithTarget(target, "character"));
	}

	/**
	 * ユーザ名を取得する
	 *
	 * @param target 接続先
	 * @return ユーザ名
	 */
	public String getUser(String target) {
		return getParameterWithTarget(target, "user");
	}

	/**
	 * パスワードを取得する
	 *
	 * @param target 接続先
	 * @return パスワード
	 */
	public String getPassword(String target) {
		return getParameterWithTarget(target, "password");
	}

	/**
	 * SSLモードを取得する
	 *
	 * @param target 接続先
	 * @return SSLモード
	 */
	public String getSsl(String target) {
		return getParameterWithTarget(target, "ssl");
	}

	/**
	 * 暗号化モードを取得する
	 *
	 * @param target 接続先
	 * @return 暗号化モード
	 */
	public String getEncryption(String target) {
		return getParameterWithTarget(target, "encryption");
	}

	/**
	 * 接続先パラメータを取得する
	 *
	 * @param target 接続先
	 * @param parameter パラメータ
	 * @return パラメータ値
	 */
	private String getParameterWithTarget(String target, String parameter) {
		if (target != null && !target.equals("")) {
			parameter = target + "." + parameter;
		}
		return getParameter(parameter).toString();
	}

	@Override
	protected ApplicationFile createApplicationFile(String filename) {
		return new SimpleApplicationFile(filename);
	}
}
