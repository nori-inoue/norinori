package jp.gr.norinori.core.element;

/**
 * 識別可能要素
 *
 * @author nori
 * @param T 識別子
 */
public interface IdentifiableElement<T> extends Element {

	/**
	 * 識別子を取得する
	 *
	 * @return 識別子
	 */
	public T getIdentification();
}
