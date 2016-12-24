package jp.gr.norinori.core.element;

/**
 * 列・業ペア
 *
 * @author nori
 */
public class ColumnsRowPair implements Pair<Integer, Integer>, IdentifiableElement<String> {

	// メンバ===================================================================
	// 列番号
	private int _columnNumber;
	// 行番号
	private int _rowNUmber;

	// コンストラクタ===========================================================
	/**
	 * 開始・終了ペアのインスタンスを生成する
	 *
	 * @param columnNumber 列番号
	 * @param rowNUmber 行番号
	 */
	public ColumnsRowPair(int columnNumber, int rowNUmber) {
		this._columnNumber = columnNumber;
		this._rowNUmber = rowNUmber;
	}

	// メソッド=================================================================
	/**
	 * 列番号を取得する
	 *
	 * @return 列番号
	 */
	public Integer getFirst() {
		return this._columnNumber;
	}

	/**
	 * 列番号を取得する
	 *
	 * @return 列番号
	 */
	public int getColumnNumber() {
		return getFirst();
	}

	/**
	 * 行番号を取得する
	 *
	 * @return 行番号
	 */
	public Integer getSecond() {
		return this._rowNUmber;
	}

	/**
	 * 行番号を取得する
	 *
	 * @return 行番号
	 */
	public int getRowNumber() {
		return getSecond();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.core.element.IdentifiableElement#getIdentification()
	 */
	public String getIdentification() {
		String idKey = String.valueOf(this._columnNumber) + "," + String.valueOf(this._rowNUmber);

		return idKey;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if ((o instanceof Pair) && (((Pair) o).getFirst().equals(getFirst()))) {
			if ((o instanceof Pair) && (((Pair) o).getSecond().equals(getSecond()))) {
				return true;
			}
		}
		return false;

	}

	@Override
	public int hashCode() {
		return (getFirst().hashCode() * 17 + getSecond().hashCode() * 31);
	}
}
