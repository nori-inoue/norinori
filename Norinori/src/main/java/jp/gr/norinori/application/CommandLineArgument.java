package jp.gr.norinori.application;

import java.util.HashMap;
import java.util.Map;

import jp.gr.norinori.application.ArgumentsFrame;
import jp.gr.norinori.utility.ArrayUtil;

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
		super(args);
	}

	@Override
	protected Map<String, String[]> parase(String[] args) {
		Map<String, String[]> argsMap = new HashMap<String, String[]>();

		String key = "";
		for (String arg : args) {
			if (arg != null && arg.indexOf('-') == 0) {
				if (!argsMap.containsKey(key)) {
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
		if (!argsMap.containsKey(key)) {
			argsMap.put(key, new String[] {});
		}

		return argsMap;
	}

}
