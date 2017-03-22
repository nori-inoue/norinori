package jp.gr.norinori.database.postgresql;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Ignore;
import org.junit.Test;

import jp.gr.norinori.database.DatabaseColumn;
import jp.gr.norinori.database.DatabaseConnection;
import jp.gr.norinori.database.DatabaseTableInformation;
import jp.gr.norinori.test.NorinoriTestFrame;

@Ignore
public class PostgreSqlTest extends NorinoriTestFrame {

	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/database";

	// メソッド=================================================================
	@Test
	public void dummy() {

	}

	@Ignore
	public void testRead() {
		PostgreSql me = new PostgreSql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection("postgres");

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
	public void testTableInformation() {
		PostgreSql me = new PostgreSql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection("postgres");

		// テーブル照会実行
		try {
			DatabaseTableInformation tableInfo = con.createTableInformation("product");

			for (DatabaseColumn column : tableInfo.getColumns()) {
				System.out.print("NAME：" + column.getName());
				System.out.print(" TYPE：" + column.getType());
				System.out.println(" SIZE：" + column.getSize());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			try {
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}
}
