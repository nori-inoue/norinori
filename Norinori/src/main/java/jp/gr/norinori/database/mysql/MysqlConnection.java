package jp.gr.norinori.database.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import jp.gr.norinori.database.AbstractDatabaseConnection;
import jp.gr.norinori.database.Database;
import jp.gr.norinori.database.DatabaseTableInformation;
import jp.gr.norinori.utility.StringUtil;

/**
 * MySQLコネクション
 *
 * @author inoue
 */
public class MysqlConnection extends AbstractDatabaseConnection {
	// コンストラクタ===========================================================
	/**
	 * データベース連結モデルのインスタンスを生成する。
	 *
	 * @param connection
	 */
	public MysqlConnection(Database database, String target, Connection connection) {
		super(database, target, connection);
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * jp.gr.norinori.database.DatabaseConnection#createTableInformation(java
	 * .lang.String)
	 */
	@Override
	public DatabaseTableInformation createTableInformation(String tableName) throws Exception {
		MysqlTableInformation tableInformation = new MysqlTableInformation();

		Statement stmt = null;
		try {
			stmt = getConnection().createStatement();
			String sql = "SHOW FULL COLUMNS FROM `" + tableName + "`";
			ResultSet rs = stmt.executeQuery(sql);

			// テーブル照会結果を出力
			tableInformation.setHasAutoIncrement(false);
			tableInformation.setHasPrimaryKey(false);

			while (rs.next()) {
				MysqlColumn column = new MysqlColumn();
				column.name = rs.getString("Field");
				column.comment = rs.getString("Comment");
				String extra = rs.getString("Extra");
				String key = rs.getString("Key");

				if (extra != null && extra.equals("auto_increment")) {
					column.isAutoIncrement = true;
				}

				if (key != null && key.equals("auto_increment")) {
					column.isPrimaryKey = true;
				}

				String type = rs.getString("Type");
				column.type = type;
				if (type.indexOf("int") == 0) {
					column.javaType = int.class;
				} else if (type.indexOf("tinyint") == 0) {
					column.javaType = int.class;
				} else if (type.indexOf("date") == 0) {
					column.javaType = Timestamp.class;
				} else {
					column.javaType = String.class;
				}

				if (!StringUtil.isEmpty(type)) {
					String[] types = type.split("\\(");
					if (types != null && types.length > 1) {
						String[] types2 = types[1].split("\\)");
						if (StringUtil.isNumeric(types2[0])) {
							column.size = Integer.valueOf(types2[0]);
						}
					}
				}
				if (column.isAutoIncrement()) {
					tableInformation.setHasAutoIncrement(true);
				}
				if (column.isPrimaryKey()) {
					tableInformation.setHasPrimaryKey(true);
				}
				tableInformation.addColumn(column);
			}
			// データベースのクローズ
			rs.close();
			stmt.close();

			stmt = getConnection().createStatement();
			sql = "SHOW TABLE STATUS FROM " + getDatabaseName() + " where name = '" + tableName + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tableInformation.setComment(rs.getString("Comment"));
			}

			return tableInformation;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException ignore) {
			}
		}
	}
}
