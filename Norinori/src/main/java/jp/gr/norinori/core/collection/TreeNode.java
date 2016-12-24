package jp.gr.norinori.core.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * ツリーノード
 *
 * @author nori
 *
 * @param <E> Tree Node Element
 */
public class TreeNode<E> {
	private List<TreeNode<E>> children;
	private E value;

	/**
	 * ツリーノードのインスタンスを生成する
	 */
	public TreeNode() {
		this.children = new ArrayList<>();
	}

	/**
	 * ツリーノードのインスタンスを生成する
	 *
	 * @param value 値
	 */
	public TreeNode(E value) {
		this();
		this.value = value;
	}

	/**
	 * 子ノードを取得する
	 *
	 * @return 子ノード
	 */
	public List<TreeNode<E>> children() {
		return children;
	}

	/**
	 * 子ノードに追加する
	 *
	 * @param node ノード
	 */
	public void addNode(TreeNode<E> node) {
		this.children.add(node);
	}

	/**
	 * 子ノードから削除する
	 *
	 * @param node ノード
	 */
	public void removeNode(TreeNode<E> node) {
		this.children.remove(node);
	}

	/**
	 * 値を取得する
	 *
	 * @return 値
	 */
	public E getValue() {
		return this.value;
	}

	/**
	 * 値を設定する
	 *
	 * @param value 値
	 */
	public void setValue(E value) {
		this.value = value;
	}
}
