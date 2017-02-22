package jp.gr.norinori.application.spreadsheets;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import jp.gr.norinori.application.spreadsheets.Cell;
import jp.gr.norinori.application.spreadsheets.Row;
import jp.gr.norinori.application.spreadsheets.Sheet;
import jp.gr.norinori.application.spreadsheets.Cell.CellType;
import jp.gr.norinori.core.collection.Record;
import jp.gr.norinori.core.collection.Table;
import jp.gr.norinori.utility.ArrayUtil;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * シート
 *
 * @author nori
 */
public class ExcelSheet implements Sheet {

	// メンバ===================================================================
	private HSSFWorkbook hssfWorkbook;
	private HSSFSheet hssfSheet;
	private List<Row> rowList;

	// コンストラクタ===========================================================
	/**
	 * HSSFSheetを元にインスタンスを生成する
	 *
	 * @param hssfSheet HSSFSheet
	 */
	public ExcelSheet(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet) {
		this.hssfWorkbook = hssfWorkbook;
		this.hssfSheet = hssfSheet;
		this.rowList = new LinkedList<Row>();

		createExcelObject();
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#getName()
	 */
	public String getName() {
		return this.hssfSheet.getSheetName();
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#getRecordList()
	 */
	public List<Row> getRowList() {
		return this.rowList;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#getRecords()
	 */
	public Row[] getRows() {
		return ArrayUtil.toArray(this.rowList);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#getRecordAt(int)
	 */
	public Row getRowAt(int rownumber) {
		if (this.rowList.size() <= rownumber) {
			return null;
		}
		return this.rowList.get(rownumber);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#setSelected(boolean)
	 */
	public void setSelected(boolean isSelect) {
		this.hssfSheet.setSelected(isSelect);
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Sheet#loadTable(jp.gr.norinori
	 * .core.collection.Table)
	 */
	public void loadTable(Table<Integer, Object> table) {
		int rownumber = 0;
		for (Record<Integer, Object> record : table) {
			Row row = null;
			if (this.rowList.size() >= rownumber) {
				row = addRow();
			} else {
				row = getRowAt(rownumber);
			}

			for (Entry<Integer, Object> en : record) {
				int columnnumber = en.getKey();
				Cell cell = row.getCellAt(columnnumber);
				cell.setRawValue(en.getValue(), CellType.STRING);
			}
			rownumber++;
		}
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#addRow()
	 */
	public Row addRow() {
		Row row = null;

		int rowCount = this.rowList.size();
		HSSFRow hssfRow = this.hssfSheet.createRow(rowCount);
		row = createExcelRow(hssfRow);
		this.rowList.add(row);

		return row;
	}

	/**
	 * HSSFWorkbookを取得する
	 *
	 * @return HSSFWorkbook
	 */
	protected HSSFWorkbook getHSSFWorkbook() {
		return this.hssfWorkbook;
	}

	// ローカルメソッド=========================================================
	/**
	 * 初期オブジェクトを作成する
	 */
	private void createExcelObject() {
		int rowCount = this.hssfSheet.getLastRowNum() + 1;
		for (int i = 0; i < rowCount; i++) {
			HSSFRow hssfRow = this.hssfSheet.getRow(i);
			if (rowCount == 1 && hssfRow == null) {
				return;
			}
			this.rowList.add(createExcelRow(hssfRow));
		}
	}

	/**
	 * ExcelRowを作成する
	 *
	 * @param hssfRow HSSFRow
	 * @return ExcelRow
	 */
	private Row createExcelRow(HSSFRow hssfRow) {
		return new ExcelRow(this.hssfSheet, hssfRow);
	}

}
