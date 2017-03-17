package jp.gr.norinori.database.postgresql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jp.gr.norinori.database.AbstractDatabaseConnection;
import jp.gr.norinori.database.Database;
import jp.gr.norinori.database.DatabaseTableInformation;

/**
 * Postgresqlコネクション
 *
 * @author inoue
 */
public class PostgresqlConnection extends AbstractDatabaseConnection {
	// コンストラクタ===========================================================
	/**
	 * データベース連結モデルのインスタンスを生成する。
	 *
	 * @param connection
	 */
	public PostgresqlConnection(Database database, String target, Connection connection) {
		super(database, target, connection);
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * jp.gr.norinori.database.DatabaseConnection#createTableInformation(java
	 * .lang.String)
	 *
	 * http://qiita.com/aiyu427/items/26d82811fc6c8e301b94
	 */
	@Override
	public DatabaseTableInformation createTableInformation(String tableName) throws Exception {
		PostgresqlTableInformation tableInformation = new PostgresqlTableInformation();

		Statement stmt = null;
		try {
			stmt = getConnection().createStatement();
			String sql = "SELECT tbl.colname, tbl.description, tbl.typname, tbl.adsrc, tbl.size";
			sql += " FROM";
			sql += "     (";
			sql += "         SELECT";
			sql += "             cls.oid,";
			sql += "             attr.attnum AS idx,";
			sql += "             attr.attname AS colname,";
			sql += "             attr.atttypmod - 4 AS size,";
			sql += "             typ.typname,";
			sql += "             adef.adsrc,";
			sql += "             pd.description";
			sql += "         FROM pg_class cls";
			sql += "         INNER JOIN pg_attribute attr ON (cls.oid = attr.attrelid)";
			sql += "         INNER JOIN pg_type typ ON (attr.atttypid = typ.oid)";
			sql += "         LEFT JOIN pg_description pd ON (pd.objoid = cls.oid ";
			sql += "                AND pd.objoid = attr.attrelid";
			sql += "                AND pd.objsubid = attr.attnum";
			sql += "         )";
			sql += "         LEFT OUTER JOIN pg_attrdef adef ON (cls.oid = adef.adrelid AND attr.attnum = adef.adnum)";
			sql += "         INNER JOIN pg_namespace nsp ON (cls.relnamespace = nsp.oid AND nsp.nspname = 'public')";
			sql += "         INNER JOIN pg_user usr ON (cls.relowner = usr.usesysid)";
			sql += "         WHERE";
			sql += "             cls.relkind = 'r'";
			sql += "             AND attr.attnum >= 0";
			sql += "             AND attr.attisdropped IS NOT TRUE";
			sql += "             AND typ.typisdefined";
			sql += "             AND cls.relname = '" + tableName + "'";
			sql += "     ) AS tbl";
			sql += " LEFT JOIN pg_description";
			sql += " ON tbl.oid = pg_description.objoid ";
			sql += " AND pg_description.objsubid=0";
			sql += " ORDER BY oid, idx";

			ResultSet rs = stmt.executeQuery(sql);

			// テーブル照会結果を出力
			tableInformation.setHasAutoIncrement(false);
			tableInformation.setHasPrimaryKey(false);

			while (rs.next()) {
				PostgresqlColumn column = new PostgresqlColumn();
				column.name = rs.getString("colname");
				column.comment = rs.getString("description");
				if (rs.getInt("size") > 0) {
					column.size = rs.getInt("size");
				}

				String type = rs.getString("typname");
				if (type.indexOf("int") == 0) {
					column.type = "int";
				} else if (type.indexOf("time") == 0) {
					column.type = "Timestamp";
				} else {
					column.type = "String";
				}

				String def = rs.getString("adsrc");
				if (def != null && def.indexOf("nextval") == 0) {
					column.isAutoIncrement = true;
				}

				if (column.isAutoIncrement()) {
					tableInformation.setHasAutoIncrement(true);
				}

				tableInformation.addColumn(column);
			}
			// データベースのクローズ
			rs.close();
			stmt.close();

			stmt = getConnection().createStatement();
			sql = "SELECT pg_description.description FROM pg_description";
			sql += " INNER JOIN pg_stat_user_tables ON pg_stat_user_tables.relid=pg_description.objoid";
			sql += " WHERE pg_stat_user_tables.relname = '" + tableName + "' AND pg_description.objsubid=0";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tableInformation.setComment(rs.getString("description"));
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
