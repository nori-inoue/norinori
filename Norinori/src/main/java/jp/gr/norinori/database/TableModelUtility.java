package jp.gr.norinori.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Database Table to Model
 *
 * @author nori
 *
 */
public class TableModelUtility {

	/**
	 * モデルリスト変換
	 *
	 * @param modelClass
	 * @param rs
	 * @return モデル
	 * @throws Exception
	 */
	public static <T> List<T> toModels(Class<T> modelClass, ResultSet rs) throws Exception {
		return toModels(modelClass, rs, null);
	}

	/**
	 * モデルリスト変換
	 *
	 * @param modelClass
	 * @param rs
	 * @param connection
	 * @return モデルリスト
	 * @throws Exception
	 */
	public static <T> List<T> toModels(Class<T> modelClass, ResultSet rs, DatabaseConnection connection) throws Exception {
		List<T> list = new ArrayList<T>();

		while (rs.next()) {
			T model = null;
			if (connection == null) {
				model = modelClass.newInstance();
			} else {
				Constructor<T> constructor = modelClass.getConstructor(new Class[] { DatabaseConnection.class });
				model = constructor.newInstance(new Object[] { connection });
			}
			ResultSetMetaData meta = rs.getMetaData();

			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String column = meta.getColumnLabel(i);
				Field field;
				try {
					field = modelClass.getField(column);

					if (field.getType().getName().indexOf("String") >= 0) {
						field.set(model, rs.getString(column));
					}
					if (field.getType().getName().indexOf("int") >= 0) {
						field.set(model, rs.getInt(column));
					}
					if (field.getType().getName().indexOf("Timestamp") >= 0) {
						field.set(model, rs.getTimestamp(column));
					}
				} catch (NoSuchFieldException ignore) {
				}
			}

			list.add(model);
		}
		return list;
	}

	/**
	 * モデル変換
	 *
	 * @param model
	 * @param rs
	 * @throws Exception
	 */
	public static <T> void loadModel(T model, ResultSet rs) throws Exception {
		while (rs.next()) {
			Class<? extends Object> modelClass = model.getClass();
			ResultSetMetaData meta = rs.getMetaData();

			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String column = meta.getColumnLabel(i);
				Field field;
				try {
					field = modelClass.getField(column);

					if (field.getType().getName().indexOf("String") >= 0) {
						field.set(model, rs.getString(column));
					}
					if (field.getType().getName().indexOf("int") >= 0) {
						field.set(model, rs.getInt(column));
					}
					if (field.getType().getName().indexOf("Timestamp") >= 0) {
						field.set(model, rs.getTimestamp(column));
					}
				} catch (NoSuchFieldException ignore) {
				}
			}
		}
	}
}
