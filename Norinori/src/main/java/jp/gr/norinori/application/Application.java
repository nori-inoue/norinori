package jp.gr.norinori.application;

/**
 * アプリケーション
 *
 * @author inoue
 */
public interface Application {

	/**
	 *
	 * @param arguments
	 */
	public void run(Arguments arguments);

	/**
	 * アプリケーションを終了する。
	 */
	public void destroy();
}
