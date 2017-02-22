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

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * シート
 *
 * @author nori
 */
public class OOXMLExcelSheet implements Sheet {

	// メンバ===================================================================
	private XSSFWorkbook xssfWorkbook;
	private XSSFSheet xssfSheet;
	private List<Row> rowList;

	// コンストラクタ===========================================================
	/**
	 * XSSFSheetを元にインスタンスを生成する
	 *
	 * @param xssfSheet XSSFSheet
	 */
	public OOXMLExcelSheet(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet) {
		this.xssfWorkbook = xssfWorkbook;
		this.xssfSheet = xssfSheet;
		this.rowList = new LinkedList<Row>();

		createExcelObject();
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#getName()
	 */
	public String getName() {
		return this.xssfSheet.getSheetName();
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
		return this.rowList.get(rownumber);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Sheet#setSelected(boolean)
	 */
	public void setSelected(boolean isSelect) {
		this.xssfSheet.setSelected(isSelect);
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
		XSSFRow xssfRow = this.xssfSheet.createRow(rowCount);
		row = createExcelRow(xssfRow);
		this.rowList.add(row);

		return row;
	}

	/**
	 * XSSFWorkbookを取得する
	 *
	 * @return XSSFWorkbook
	 */
	protected XSSFWorkbook getXSSFWorkbook() {
		return this.xssfWorkbook;
	}

	// ローカルメソッド=========================================================
	/**
	 * 初期オブジェクトを作成する
	 */
	private void createExcelObject() {
		int rowCount = this.xssfSheet.getLastRowNum() + 1;
		for (int i = 0; i < rowCount; i++) {
			XSSFRow xssfRow = this.xssfSheet.getRow(i);
			if (rowCount == 1 && xssfRow == null) {
				return;
			}
			this.rowList.add(createExcelRow(xssfRow));
		}
	}

	/**
	 * ExcelRowを作成する
	 *
	 * @param xssfRow XSSFRow
	 * @return ExcelRow
	 */
	private Row createExcelRow(XSSFRow xssfRow) {
		return new OOXMLExcelRow(this.xssfSheet, xssfRow);
	}

}
