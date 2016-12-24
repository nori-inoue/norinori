package jp.gr.norinori.core.element;

/**
 * ペア
 *
 * @author nori
 * @param T1 第一引数のタイプ
 * @param T2 第二引数のタイプ
 */
public interface Pair<T1, T2> {

	/**
	 * 第一引数を取得する
	 *
	 * @return 第一引数
	 */
	public T1 getFirst();

	/**
	 * 第二引数を取得する
	 *
	 * @return 第二引数
	 */
	public T2 getSecond();
}
