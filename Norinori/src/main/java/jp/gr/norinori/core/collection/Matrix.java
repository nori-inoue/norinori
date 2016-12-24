package jp.gr.norinori.core.collection;

import java.util.List;

/**
 * 行列
 *
 * @author nori
 * @param Value 値のタイプ
 */
public interface Matrix<Value> {

    /**
     * 行を取得する
     *
     * @param rowNumber 行番号
     * @return 行
     */
    public List<Value> getRows(int rowNumber);

    /**
     * 列を取得する
     *
     * @param columnNumber 列番号
     * @return　列
     */
    public List<Value> getColumns(int columnNumber);

    /**
     * 値を取得する
     *
     * @param rowNumber 行番号
     * @param columnNumber 列番号
     * @return　値
     */
    public Value getValue(int rowNumber, int columnNumber);

    /**
     * 値を設定する
     *
     * @param rowNumber 行番号
     * @param columnNumber 列番号
     * @param value 値
     */
    public void setValue(int rowNumber, int columnNumber, Value value);

}
