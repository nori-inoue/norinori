package jp.gr.norinori.application.spreadsheets;

import java.io.OutputStream;
import java.util.List;

import jp.gr.norinori.application.ApplicationFile;

/**
 * 表計算インタフェース
 *
 * @author nori
 */
public interface Spreadsheets extends ApplicationFile {

	/**
	 * 指定した番号のシートを取得する
	 *
	 * @param index 番号
	 * @return シート
	 */
	public Sheet getSheetAt(int index);

	/**
	 * 指定したシート名のシートを取得する
	 *
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet getSheet(String sheetName);

	/**
	 * シートのリストを取得する
	 *
	 * @return シートのリスト
	 */
	public List<Sheet> getSheetList();

	/**
	 * シートの配列を取得する
	 *
	 * @return シートの配列
	 */
	public Sheet[] getSheets();

	/**
	 * 指定した番号のシートをアクティブにする
	 *
	 * @param index 番号
	 */
	public void setActiveSheet(int index);

	/**
	 * 指定したシート名でシートを作成する
	 *
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet addSheet(String sheetName);

	/**
	 * 対象のシートの前に指定したシート名でシートを作成する
	 *
	 * @param targetSheetName 対象のシート名
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet addBeforeSheet(String targetSheetName, String sheetName);

	/**
	 * 対象のシートの後に指定したシート名でシートを作成する
	 *
	 * @param targetSheetName 対象のシート名
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet addAfterSheet(String targetSheetName, String sheetName);

	/**
	 * 指定したシート名でシートを複製する
	 *
	 * @param originalSheetName クローン元シート名
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet cloneSheet(String originalSheetName, String sheetName);

	/**
	 * 対象のシートの前に指定したシート名でシートを複製する
	 *
	 * @param targetSheetName 対象のシート名
	 * @param originalSheetName クローン元シート名
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet cloneBeforeSheet(String targetSheetName, String originalSheetName, String sheetName);

	/**
	 * 対象のシートの後に指定したシート名でシートを複製する
	 *
	 * @param targetSheetName 対象のシート名
	 * @param originalSheetName クローン元シート名
	 * @param sheetName シート名
	 * @return シート
	 */
	public Sheet cloneAfterSheet(String targetSheetName, String originalSheetName, String sheetName);

	/**
	 * ファイルを保存する
	 *
	 * @return true:保存に成功、false：保存に失敗
	 */
	public boolean write();

	/**
	 * 指定したファイルとして保存する
	 *
	 * @param fileName ファイル名
	 * @return true:保存に成功、false：保存に失敗
	 */
	public boolean write(String fileName);

	/**
	 * 指定した出力ストリームに出力する
	 *
	 * @param out 出力ストリーム
	 * @return true:保存に成功、false：保存に失敗
	 */
	public boolean write(OutputStream out);

	// TODO POIのWorkbook.class参照
}
