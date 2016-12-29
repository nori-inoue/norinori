package jp.gr.norinori.database.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jp.gr.norinori.database.DatabaseConnection;
import jp.gr.norinori.database.Product;
import jp.gr.norinori.database.TableModelUtility;
import jp.gr.norinori.database.mysql.MySql;
import jp.gr.norinori.test.NorinoriTestFrame;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TableModelUtilityTest extends NorinoriTestFrame {

	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/database";

	// メソッド=================================================================
	@Test
	public void dummy() {

	}

	@Ignore
	public void testToModels() {
		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection();
		Statement stmt = null;
		try {

			stmt = con.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = stmt.executeQuery(sql);

			List<Product> list = TableModelUtility.toModels(Product.class, rs);

			for (Product product : list) {
				System.out.println(product.productid + "," + product.name);
			}

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}

	@Ignore
	public void testLoadModel() {
		MySql me = new MySql();
		me.configure(RESOURCE_PATH + "/db.properties");

		DatabaseConnection con = me.createConnection();
		Statement stmt = null;
		try {

			stmt = con.createStatement();
			String sql = "SELECT * FROM product limit 1";
			ResultSet rs = stmt.executeQuery(sql);

			Product product = new Product();

			TableModelUtility.loadModel(product, rs);

			System.out.println("-----");
			System.out.println(product.productid + "," + product.name);

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException ignore) {
			}
		}
	}
}
