package jp.gr.norinori.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 引数フレーム
 *
 * @author inoue
 */
public abstract class ArgumentsFrame implements Arguments {

	// メンバ===================================================================
	/**
	 * 引数マップ
	 */
	private Map<String, String[]> arguments;

	/**
	 * 引数リスト
	 */
	private List<String> argumentList;

	// コンストラクタ===========================================================
	/**
	 * 引数のインスタンスを生成する
	 *
	 * @param argumentsMap 引数マップ
	 */
	public ArgumentsFrame(Map<String, String[]> argumentsMap) {
		this.arguments = argumentsMap;
		this.argumentList = new ArrayList<String>();
		for (Entry<String, String[]> en : this.arguments.entrySet()) {
			this.argumentList.add(en.getKey());
			this.argumentList.addAll(Arrays.asList(en.getValue()));
		}
	}

	/**
	 * パラメータを取得する
	 *
	 * @param key パラメータキー
	 * @return 指定されたキーのパラメータ
	 */
	public String getOptionValue(String key) {
		if (hasOption(key)) {
			String[] values = this.arguments.get(key);
			if (values.length == 0) {
				return null;
			}
			return values[0];
		}
		return null;
	}

	/**
	 * パラメータを取得する
	 *
	 * @param key パラメータキー
	 * @return 指定されたキーのパラメータの配列
	 */
	public String[] getOptionValues(String key) {
		if (hasOption(key)) {
			return this.arguments.get(key);
		}
		return null;
	}

	/**
	 * パラメータの存在を確認する
	 *
	 * @param key パラメータキー
	 * @return true:存在する false:存在しない
	 */
	public boolean hasOption(String key) {
		return this.arguments.containsKey(key);
	}

	/**
	 * パラメータを取得する
	 *
	 * @param index パラメータ番号
	 * @return 指定されたキーのパラメータ
	 */
	public String getOptionValue(int index) {
		if (this.argumentList.size() > index) {
			return this.argumentList.get(index);
		}
		return null;
	}

}
