package jp.gr.norinori.core.flow;

/**
 * ライフサイクル
 *
 * @author nori
 */
public interface LifeCycle extends AutoCloseable {

	/**
	 * 処理、構成物を開始・生成する.
	 *
	 * @throws Exception
	 */
	public void open() throws Exception;

	/**
	 * 指定した処理、構成物の情報をもとに開始・生成する.
	 *
	 * @param target 処理・構成物の情報
	 * @throws Exception
	 */
	public <T> void open(T target) throws Exception;

	/**
	 * 処理、構成物が存在するかを判定する.
	 *
	 * @return true:存在する、false:存在しない
	 * @throws Exception
	 */
	public boolean isLiving() throws Exception;

	/**
	 * 処理、構成物を終了・破壊する.
	 *
	 * @throws Exception
	 */
	// public void close() throws Exception;
}
