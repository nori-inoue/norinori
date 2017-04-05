package jp.gr.norinori.application;

import java.util.Map;

import jp.gr.norinori.core.collection.NumberingHashMap;
import jp.gr.norinori.utility.ArrayUtil;
import jp.gr.norinori.utility.StringUtil;

/**
 * コマンドライン引数
 *
 * @author nori
 */
public class CommandLineArgument extends ArgumentsFrame {

	// コンストラクタ===========================================================
	/**
	 * コマンドライン引数のインスタンスを生成する
	 *
	 * @param args 引数
	 */
	public CommandLineArgument(String[] args) {
		super(createArgumentsMap(args));
	}

	/**
	 * 解析する
	 *
	 * @param args 引数
	 * @return 解析結果をMapで返す
	 */
	private final static Map<String, String[]> createArgumentsMap(String[] args) {
		Map<String, String[]> argsMap = new NumberingHashMap<String, String[]>();

		String key = "";
		for (String arg : args) {
			if (arg != null && arg.indexOf('-') == 0) {
				if (!StringUtil.isEmpty(key) && !argsMap.containsKey(key)) {
					argsMap.put(key, new String[] {});
				}
				key = arg;
			} else {
				String[] values = new String[0];
				if (argsMap.containsKey(key)) {
					values = argsMap.get(key);
				}
				values = ArrayUtil.add(values, arg);
				argsMap.put(key, values);
			}
		}
		if (!StringUtil.isEmpty(key) && !argsMap.containsKey(key)) {
			argsMap.put(key, new String[] {});
		}

		return argsMap;
	}

}
