package jp.gr.norinori.application;

/**
 * 引数
 *
 * @author inoue
 */
public interface Arguments {
	/**
	 * パラメータを取得する
	 *
	 * @param key パラメータキー
	 * @return 指定されたキーのパラメータ
	 */
	public String getOptionValue(String key);

	/**
	 * パラメータを取得する
	 *
	 * @param key パラメータキー
	 * @return 指定されたキーのパラメータの配列
	 */
	public String[] getOptionValues(String key);

	/**
	 * パラメータの存在を確認する
	 *
	 * @param key パラメータキー
	 * @return true:存在する false:存在しない
	 */
	public boolean hasOption(String key);

	/**
	 * パラメータを取得する
	 *
	 * @param index パラメータ番号
	 * @return 指定されたキーのパラメータ
	 */
	public String getOptionValue(int index);
}
