package jp.gr.norinori.database.mysql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.core.element.KeyValuePair;
import jp.gr.norinori.core.element.Pair;
import jp.gr.norinori.database.AbstractDatabase;
import jp.gr.norinori.database.Database;
import jp.gr.norinori.database.DatabaseConnection;

public class MySql extends AbstractDatabase {
	protected String getDatabase() {
		return "mysql";
	}

	protected String getDriver() {
		return "org.gjt.mm.mysql.Driver";
	}

	protected List<Pair<String, String>> getDefaultOptions() {
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		list.add(new KeyValuePair<String, String>("zeroDateTimeBehavior", "convertToNull"));
		return list;
	}

	protected DatabaseConnection createConnection(Database database, String target, Connection connection) {
		return new MysqlConnection(database, target, connection);
	}
}
