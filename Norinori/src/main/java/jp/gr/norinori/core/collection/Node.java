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
@SuppressWarnings("rawtypes")
public class Node<T extends Node, E> {
	private List<T> children;
	private E value;

	/**
	 * ツリーノードのインスタンスを生成する
	 */
	public Node() {
		this.children = new ArrayList<>();
	}

	/**
	 * ツリーノードのインスタンスを生成する
	 *
	 * @param value 値
	 */
	public Node(E value) {
		this();
		this.value = value;
	}

	/**
	 * 子ノードを取得する
	 *
	 * @return 子ノード
	 */
	public List<T> children() {
		return children;
	}

	/**
	 * 子ノードに追加する
	 *
	 * @param node ノード
	 */
	public void addNode(T node) {
		this.children.add(node);
	}

	/**
	 * 子ノードから削除する
	 *
	 * @param node ノード
	 */
	public void removeNode(T node) {
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
