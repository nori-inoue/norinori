package jp.gr.norinori.application.spreadsheets;

import jp.gr.norinori.core.element.Element;

/**
 * セルインタフェース
 *
 * @author nori
 */
public interface Cell extends Element {

	/**
	 * タイプ
	 *
	 * @author nori
	 */
	public enum CellType {
		STRING, NUMERIC, DECIMAL, BOOLEAN, DATE, FUNCTION, OTHER
	};

	/**
	 * 値を取得する
	 *
	 * @return 値
	 */
	public String getValue();

	/**
	 * 内部保持している値を取得する
	 *
	 * @return 内部保持している値
	 */
	public Object getRawValue();

	/**
	 * セルのタイプを取得する
	 *
	 * @return セルのタイプ
	 */
	public CellType getType();

	/**
	 * 値を設定する
	 *
	 * @param value 値
	 */
	public void setValue(String value);

	/**
	 * 内部保持している値を設定する
	 *
	 * @param value 内部保持している値
	 * @param type 保持するタイプ
	 */
	public void setRawValue(Object value, CellType type);

	/**
	 * セルのタイプを設定する
	 *
	 * @param type 保持するタイプ
	 */
	public void setType(CellType type);

	/**
	 * 列番号を取得する
	 *
	 * @return 列番号
	 */
	public int getColumnNumber();
}
