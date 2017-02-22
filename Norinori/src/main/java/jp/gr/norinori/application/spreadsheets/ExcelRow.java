package jp.gr.norinori.application.spreadsheets;

import java.util.LinkedList;
import java.util.List;

import jp.gr.norinori.application.spreadsheets.Cell;
import jp.gr.norinori.application.spreadsheets.Row;
import jp.gr.norinori.utility.ArrayUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 行
 *
 * @author nori
 */
public class ExcelRow implements Row {

	// メンバ===================================================================
	private HSSFRow hssfRow;
	private HSSFSheet hssfSheet;
	private List<Cell> cellList;

	// コンストラクタ===========================================================
	/**
	 * 行のインスタンスを生成する
	 *
	 * @param hssfSheet シート
	 * @param hssfRow 行
	 */
	public ExcelRow(HSSFSheet hssfSheet, HSSFRow hssfRow) {
		this.hssfRow = hssfRow;
		this.hssfSheet = hssfSheet;
		this.cellList = new LinkedList<Cell>();

		createExcelObject();
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Row#getCellList()
	 */
	public List<Cell> getCellList() {
		return this.cellList;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Row#getCells()
	 */
	public Cell[] getCells() {
		return ArrayUtil.toArray(this.cellList);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Row#getCellAt(int)
	 */
	public Cell getCellAt(int columnnumber) {
		if (this.cellList.size() <= columnnumber) {
			for (int c = this.cellList.size(); c <= columnnumber; c++) {
				this.cellList.add(createExcelCell(this.hssfRow.createCell(c), c));
			}
		}
		return this.cellList.get(columnnumber);
	}

	/*
	 * /(非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Row#getRowNumber()
	 */
	public int getRowNumber() {
		return this.hssfRow.getRowNum();
	}

	/**
	 * HSSFSheetを取得する
	 *
	 * @return HSSFSheet
	 */
	protected HSSFSheet getHSSFSheet() {
		return this.hssfSheet;
	}

	// ローカルメソッド=========================================================
	/**
	 * 初期オブジェクトを作成する
	 */
	private void createExcelObject() {
		if (this.hssfRow == null) {
			return;
		}

		int cellCount = this.hssfRow.getLastCellNum();
		for (int i = 0; i < cellCount; i++) {
			HSSFCell hssfCell = this.hssfRow.getCell(i);
			this.cellList.add(createExcelCell(hssfCell, i));
		}
	}

	/**
	 * ExcelCellを作成する
	 *
	 * @param hssfCell HSSFCell
	 * @param cellNumber セル番号
	 * @return ExcelCell
	 */
	private Cell createExcelCell(HSSFCell hssfCell, int cellNumber) {
		return new ExcelCell(this.hssfRow, hssfCell, cellNumber);
	}
}
