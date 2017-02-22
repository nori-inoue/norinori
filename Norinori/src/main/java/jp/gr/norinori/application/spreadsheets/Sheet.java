package jp.gr.norinori.application.spreadsheets;

import java.util.List;

import jp.gr.norinori.core.collection.Table;

/**
 * シートインタフェース
 *
 * @author nori
 */
public interface Sheet {

	/**
	 * シート名を取得する
	 *
	 * @return シート名
	 */
	public String getName();

	/**
	 * 行リストを取得する
	 *
	 * @return 行リスト
	 */
	public List<Row> getRowList();

	/**
	 * 行配列を取得する
	 *
	 * @return 行配列
	 */
	public Row[] getRows();

	/**
	 * 指定した行番号の行を取得する
	 *
	 * @param rownumber 行番号
	 * @return 行
	 */
	public Row getRowAt(int rownumber);

	/**
	 * 行を追加する
	 *
	 * @return 行
	 */
	public Row addRow();

	/**
	 * シートを選択する
	 *
	 * @param isSelect true:選択する / flase:選択解除する
	 */
	public void setSelected(boolean isSelect);

	/**
	 * テーブルからシートにデータを読み込む
	 *
	 * @param tableテーブル
	 */
	public void loadTable(Table<Integer, Object> table);
}
