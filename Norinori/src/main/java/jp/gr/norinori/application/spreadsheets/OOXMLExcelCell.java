package jp.gr.norinori.application.spreadsheets;

import java.util.Date;

import jp.gr.norinori.application.spreadsheets.Cell;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * セル
 *
 * @author nori
 */
public class OOXMLExcelCell implements Cell {

	// メンバ===================================================================
	private XSSFRow xssfRow;
	private XSSFCell xssfCell;
	private int cellNumber;

	// コンストラクタ===========================================================
	/**
	 * 行のインスタンスを生成する
	 *
	 * @param xssfRow 行
	 * @param xssfCell セル
	 * @param cellNumber セル番号
	 */
	public OOXMLExcelCell(XSSFRow xssfRow, XSSFCell xssfCell, int cellNumber) {
		this.xssfRow = xssfRow;
		this.xssfCell = xssfCell;
		this.cellNumber = cellNumber;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Cell#getValue()
	 */
	public String getValue() {
		if (this.xssfCell == null) {
			return null;
		}
		return this.xssfCell.getStringCellValue();
	}

	/**
	 * 内部保持している値を取得する
	 *
	 * @return 内部保持している値
	 */
	public Object getRawValue() {
		if (this.xssfCell == null) {
			return null;
		}
		switch (this.xssfCell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
		case XSSFCell.CELL_TYPE_BLANK:
			return this.xssfCell.getStringCellValue();
		case XSSFCell.CELL_TYPE_BOOLEAN:
			return this.xssfCell.getBooleanCellValue();
		case XSSFCell.CELL_TYPE_NUMERIC:
			return this.xssfCell.getNumericCellValue();
		case XSSFCell.CELL_TYPE_FORMULA:
			return this.xssfCell.getCellFormula();
		case XSSFCell.CELL_TYPE_ERROR:
			return this.xssfCell.getErrorCellValue();

		}
		return this.xssfCell.getStringCellValue();
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Cell#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		if (this.xssfCell == null) {
			this.xssfCell = this.xssfRow.createCell(this.cellNumber);
		}
		this.xssfCell.setCellValue(value);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Cell#getType()
	 */
	public CellType getType() {
		if (this.xssfCell == null) {
			return CellType.STRING;
		}
		switch (this.xssfCell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
		case XSSFCell.CELL_TYPE_BLANK:
		case XSSFCell.CELL_TYPE_ERROR:
			return CellType.STRING;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			return CellType.BOOLEAN;
		case XSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(this.xssfCell)) {
				return CellType.DATE;
			} else {
				return CellType.NUMERIC;
			}
		case XSSFCell.CELL_TYPE_FORMULA:
			return CellType.FUNCTION;
		}
		return CellType.OTHER;
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Cell#setRawValue(java.lang.Object
	 * , jp.gr.norinori.application.spreadsheets.Cell.Type)
	 */
	public void setRawValue(Object value, CellType type) {
		String rawValue = (value == null ? null : String.valueOf(value));

		switch (getType()) {
		case STRING:
		case OTHER:
			this.xssfCell.setCellValue((String)null);
			break;
		case BOOLEAN:
			this.xssfCell.setCellValue(false);
			break;
		case NUMERIC:
		case DECIMAL:
		case DATE:
			this.xssfCell.setCellValue(0);
			break;
		case FUNCTION:
			this.xssfCell.setCellFormula(null);
			break;
		}

		switch (type) {
		case STRING:
		case OTHER:
			this.xssfCell.setCellType(XSSFCell.CELL_TYPE_STRING);
			this.xssfCell.setCellValue(rawValue);
			break;
		case BOOLEAN:
			this.xssfCell.setCellType(XSSFCell.CELL_TYPE_BOOLEAN);
			this.xssfCell.setCellValue(Boolean.parseBoolean(rawValue));
			break;
		case NUMERIC:
		case DECIMAL:
			this.xssfCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			this.xssfCell.setCellValue(Long.parseLong(rawValue));
			break;
		case DATE:
			this.xssfCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			this.xssfCell.setCellValue(((Date) value).getTime());
			break;
		case FUNCTION:
			this.xssfCell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			this.xssfCell.setCellValue(rawValue);
			break;
		}
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Cell#setType(jp.gr.norinori.
	 * application.spreadsheets.Cell.Type)
	 */
	public void setType(CellType type) {
		Object value = getRawValue();
		setRawValue(value, type);
	}


	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Cell#getColumnNumber()
	 */
	public int getColumnNumber() {
		return this.cellNumber;
	}
}
