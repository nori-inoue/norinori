package jp.gr.norinori.database.postgresql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.core.element.Pair;
import jp.gr.norinori.database.AbstractDatabase;
import jp.gr.norinori.database.Database;
import jp.gr.norinori.database.DatabaseConnection;

public class PostgreSql extends AbstractDatabase {
	protected String getDatabase() {
		return "postgresql";
	}

	protected String getDriver() {
		return "org.postgresql.Driver";
	}

	protected List<Pair<String, String>> getDefaultOptions() {
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		return list;
	}

	protected DatabaseConnection createConnection(Database database, String target, Connection connection) {
		return new PostgresqlConnection(database, target, connection);
	}
}
