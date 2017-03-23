package jp.gr.norinori.core.collection;

/**
 * ツリーノード
 *
 * @author nori
 *
 * @param <E> Tree Node Element
 */
public class TreeNode<E> extends Node<TreeNode<E>, E> {
	/**
	 * ツリーノードのインスタンスを生成する
	 */
	public TreeNode() {
		super();
	}

	/**
	 * ツリーノードのインスタンスを生成する
	 *
	 * @param value 値
	 */
	public TreeNode(E value) {
		super(value);
	}
}
