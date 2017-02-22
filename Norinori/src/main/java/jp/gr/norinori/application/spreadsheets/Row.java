package jp.gr.norinori.application.spreadsheets;

import java.util.List;

/**
 * シート行インタフェース
 *
 * @author nori
 */
public interface Row {

	/**
	 * セルリストを取得する
	 *
	 * @return セルリスト
	 */
	public List<Cell> getCellList();

	/**
	 * セル配列を取得する
	 *
	 * @return セル配列
	 */
	public Cell[] getCells();

	/**
	 * 指定した番号のセルを取得する
	 *
	 * @param columnnumber 列番号
	 * @return セル
	 */
	public Cell getCellAt(int columnnumber);

	/**
	 * 行番号を取得する
	 *
	 * @return 行番号
	 */
	public int getRowNumber();
}
