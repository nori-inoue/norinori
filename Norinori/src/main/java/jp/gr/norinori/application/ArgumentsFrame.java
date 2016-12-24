package jp.gr.norinori.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jp.gr.norinori.application.Arguments;

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
	 * ファイル情報のインスタンスを生成する
	 *
	 * @param file ファイル
	 */
	public ArgumentsFrame(String[] args) {
		this.arguments = parase(args);
		this.argumentList = new ArrayList<String>(Arrays.asList(args));
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

	/**
	 * 解析する
	 *
	 * @param args 引数
	 * @return 解析結果をMapで返す
	 */
	abstract protected Map<String, String[]> parase(String[] args);

}
