package jp.gr.norinori.core.collection;

import java.util.Map;

import jp.gr.norinori.core.collection.RecordFrame;
import jp.gr.norinori.utility.FirstSkip;

/**
 * CSVレコード
 *
 * @author inoue
 *
 * @param Key 項目となるキーのタイプ
 * @param Value 値のタイプ
 */
public class CsvRecord extends RecordFrame<String, String> {
	// メンバ===================================================================
	private String delimiter;
	private String quotation;

	// コンストラクタ===========================================================
	/**
	 * CSVファイルのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 * @param delimiter 区切り文字
	 * @param quotation 引用符
	 */
	public CsvRecord(String delimiter, String quotation) {
		this.delimiter = delimiter;
		this.quotation = quotation;
	}

	// メソッド=================================================================
	/**
	 * 文字列に結合する
	 */
	public String concatenate() {
		StringBuilder s = new StringBuilder();
		FirstSkip delimiter = new FirstSkip(this.delimiter);
		for (Map.Entry<String, String> en : this) {
			s.append(delimiter).append(this.quotation).append(en.getValue()).append(this.quotation);
		}
		return s.toString();
	}

	/**
	 * 指定したエントリ順で文字列に結合する
	 *
	 * @param iterable エントリ順
	 */
	public String concatenate(Iterable<Map.Entry<String, String>> iterable) {
		StringBuilder s = new StringBuilder();
		FirstSkip delimiter = new FirstSkip(this.delimiter);
		for (Map.Entry<String, String> en : iterable) {
			s.append(delimiter).append(this.quotation).append(getValue(en.getKey())).append(this.quotation);
		}
		return s.toString();
	}

	/**
	 * マップを生成する
	 *
	 * @return マップ
	 */
	protected Map<String, String> createMap() {
		return new NumberingHashMap<String, String>();
	}
}
