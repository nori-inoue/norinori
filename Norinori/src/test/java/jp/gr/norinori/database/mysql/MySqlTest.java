package jp.gr.norinori.database.mysql;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jp.gr.norinori.database.DatabaseConnection;
import jp.gr.norinori.database.mysql.MySql;
import jp.gr.norinori.test.NorinoriTestFrame;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MySqlTest extends NorinoriTestFrame {

	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/database";

	// メソッド=================================================================
	@Test
	public void dummy() {

	}

	@Ignore
	public void testRead() {
		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection();

		// テーブル照会実行
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = stmt.executeQuery(sql);
			// テーブル照会結果を出力
			while (rs.next()) {
				int id = rs.getInt("productid");
				String name = rs.getString("name");
				System.out.println("ID：" + id);
				System.out.println("名前：" + name);
			}
			// データベースのクローズ
			rs.close();
		} catch (SQLException e) {
			fail();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}

	@Ignore
	public void testLocalRead() {
		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection("local");

		// テーブル照会実行
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = stmt.executeQuery(sql);
			// テーブル照会結果を出力
			while (rs.next()) {
				int id = rs.getInt("productid");
				String name = rs.getString("name");
				System.out.println("ID：" + id);
				System.out.println("名前：" + name);
			}
			// データベースのクローズ
			rs.close();
		} catch (SQLException e) {
			fail();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}

	@Ignore
	public void testRemoteRead() {
		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection("remote");

		// テーブル照会実行
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = stmt.executeQuery(sql);
			// テーブル照会結果を出力
			while (rs.next()) {
				int id = rs.getInt("productid");
				String name = rs.getString("name");
				System.out.println("ID：" + id);
				System.out.println("名前：" + name);
			}
			// データベースのクローズ
			rs.close();
		} catch (SQLException e) {
			fail();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}

	@Ignore
	public void testSslRead() {
		System.setProperty("javax.net.ssl.keyStore", RESOURCE_PATH + "\\keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "hankan");
		System.setProperty("javax.net.ssl.trustStore", RESOURCE_PATH + "\\truststore");
		System.setProperty("javax.net.ssl.trustStorePassword", "hankan");
		System.setProperty("javax.net.debug", "all");

		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection("ssl");

		// テーブル照会実行
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = stmt.executeQuery(sql);
			// テーブル照会結果を出力
			while (rs.next()) {
				int id = rs.getInt("productid");
				String name = rs.getString("name");
				System.out.println("ID：" + id);
				System.out.println("名前：" + name);
			}
			// データベースのクローズ
			rs.close();
		} catch (SQLException e) {
			fail();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}

	@Ignore
	public void testEncryptionRead() {
		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection("enc");

		// テーブル照会実行
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String name = rs.getString("name");
				System.out.println("->" + name);
			} else {
				fail();
			}
			// データベースのクローズ
			rs.close();
		} catch (SQLException e) {
			fail();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}
}
