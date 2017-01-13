package jp.gr.norinori.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jp.gr.norinori.core.element.Pair;
import jp.gr.norinori.core.file.ResourceFile;
import jp.gr.norinori.utility.Encryption;
import jp.gr.norinori.utility.StringUtil;

public abstract class AbstractDatabase implements Database {
	// メンバ===================================================================
	private DatabaseConfiguration configulation;

	// メソッド=================================================================
	/**
	 * 設定ファイルを読み取る
	 *
	 * @param filename 設定ファイル名
	 */
	public void configure(String filename) {
		this.configulation = new DatabaseConfiguration(filename);
	}

	/**
	 * 設定ファイルを読み取る
	 *
	 * @param resourceFile 設定ファイル名
	 */
	public void configure(ResourceFile resourceFile) {
		this.configulation = new DatabaseConfiguration(resourceFile);
	}

	/**
	 * データベースに接続する
	 *
	 * @return 接続情報
	 */
	public DatabaseConnection createConnection() {
		return createConnection("");
	}

	/**
	 * データベース設定管理を取得する。
	 *
	 * @return データベース設定管理
	 */
	public DatabaseConfiguration getDatabaseConfiguration() {
		return this.configulation;
	}

	/**
	 * データベースに接続する
	 *
	 * @param target 接続先
	 * @return 接続情報
	 */
	public DatabaseConnection createConnection(String target) {
		try {
			// JDBCドライバの登録
			String driver = getDriver();

			// データベースの指定
			String server = this.configulation.getServer(target);
			String dbname = this.configulation.getDatabaseName(target);
			String character = this.configulation.getEncode(target).getCode();
			String sslMode = this.configulation.getSsl(target);
			String ssl = "";
			if (sslMode != null && sslMode.equals("1")) {
				ssl = "&useSSL=true&requireSSL=true";
			}
			StringBuffer url = new StringBuffer("jdbc:");
			url.append(getDatabase() + "://");
			url.append(server + "/" + dbname);
			url.append("?useUnicode=true&characterEncoding=" + character);
			if (getDefaultOptions() != null) {
				for (Pair<String, String> keyValue : getDefaultOptions()) {
					url.append("&" + keyValue.getFirst() + "=" + keyValue.getSecond());
				}
			}
			url.append(ssl);

			String user = this.configulation.getUser(target);
			String password = this.configulation.getPassword(target);

			String encryption = this.configulation.getEncryption(target);
			if (!StringUtil.isEmpty(encryption) && encryption.equals("1")) {
				password = Encryption.decryptBlowfish("abc", password);
			}

			Class.forName(driver);

			// データベースとの接続
			Connection connection = DriverManager.getConnection(url.toString(), user, password);
			connection.setAutoCommit(false);
			DatabaseConnection databaseConnection = createConnection(this, target, connection);
			databaseConnection.setDatabaeName(dbname);

			return databaseConnection;
		} catch (SQLException e) {
			System.err.println("SQL failed.");
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 抽象メソッド=============================================================
	abstract protected String getDatabase();

	abstract protected String getDriver();

	abstract protected DatabaseConnection createConnection(Database database, String target, Connection connection);

	abstract protected List<Pair<String, String>> getDefaultOptions();

}
