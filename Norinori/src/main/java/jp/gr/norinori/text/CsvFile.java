package jp.gr.norinori.text;

import java.io.File;
import java.util.List;

import jp.gr.norinori.application.SimpleFileInformation;
import jp.gr.norinori.core.collection.CsvRecord;
import jp.gr.norinori.core.file.FileInformation;
import jp.gr.norinori.text.TextFileFrame;
import jp.gr.norinori.utility.FirstSkip;

/**
 * CSVファイル
 *
 * @author inoue
 */
public class CsvFile extends TextFileFrame {
	// メンバ===================================================================
	private String delimiter;
	private String quotation;

	private CsvRecord headeRecord;

	// コンストラクタ===========================================================
	/**
	 * CSVファイルのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 */
	public CsvFile(String fileName) {
		this(new File(fileName));
	}

	/**
	 * CSVファイルのインスタンスを生成する
	 *
	 * @param textFile テキストファイル
	 */
	public CsvFile(File textFile) {
		this(textFile, ",", "");
	}

	/**
	 * CSVファイルのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 * @param delimiter 区切り文字
	 * @param quotation 引用符
	 */
	public CsvFile(String fileName, String delimiter, String quotation) {
		this(new File(fileName), delimiter, quotation);
	}

	/**
	 * CSVファイルのインスタンスを生成する
	 *
	 * @param textFile テキストファイル
	 * @param delimiter 区切り文字
	 * @param quotation 引用符
	 */
	public CsvFile(File textFile, String delimiter, String quotation) {
		super(textFile);
		this.delimiter = delimiter;
		this.quotation = quotation;
		this.headeRecord = new CsvRecord(delimiter, quotation);
	}

	// メソッド=================================================================
	/**
	 * ヘッダを設定し、出力する
	 *
	 * @param headeRecord ヘッダ
	 */
	public void setHeader(CsvRecord headeRecord) {
		setHeader(headeRecord, true);
	}

	/**
	 * ヘッダを設定する
	 *
	 * @param headeRecord ヘッダ
	 * @param doOutput 出力有無 true:出力する / false:出力しない
	 */
	public void setHeader(CsvRecord headeRecord, boolean doOutput) {
		this.headeRecord = headeRecord;

		if (doOutput) {
			append(headeRecord.concatenate());
			append(System.lineSeparator());
		}
	}

	/**
	 * 文字列リスト形式で追加する
	 *
	 * @param values 文字列リスト
	 */
	public void append(List<String> values) {
		StringBuilder s = new StringBuilder();
		FirstSkip delimiter = new FirstSkip(this.delimiter);
		for (String value : values) {
			s.append(delimiter).append(this.quotation).append(value).append(this.quotation);
		}
		append(s.toString());
		append(System.lineSeparator());
	}

	/**
	 * CSVレコード形式で追加する（要ヘッダ）
	 *
	 * @param record CSVレコード
	 */
	public void append(CsvRecord record) {
		append(record.concatenate(this.headeRecord));
		append(System.lineSeparator());
	}

	// オーバライドメソッド=====================================================
	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.ApplicationFileFrame#createFileInformation()
	 */
	@Override
	protected FileInformation createFileInformation() {
		return new SimpleFileInformation(getFile());
	}

}
