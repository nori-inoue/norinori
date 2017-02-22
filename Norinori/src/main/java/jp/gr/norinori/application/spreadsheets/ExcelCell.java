package jp.gr.norinori.application.spreadsheets;

import java.util.Date;

import jp.gr.norinori.application.spreadsheets.Cell;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * セル
 *
 * @author nori
 */
public class ExcelCell implements Cell {

	// メンバ===================================================================
	private HSSFRow hssfRow;
	private HSSFCell hssfCell;
	private int cellNumber;

	// コンストラクタ===========================================================
	/**
	 * 行のインスタンスを生成する
	 *
	 * @param hssfRow 行
	 * @param hssfCell セル
	 * @param cellNumber セル番号
	 */
	public ExcelCell(HSSFRow hssfRow, HSSFCell hssfCell, int cellNumber) {
		this.hssfRow = hssfRow;
		this.hssfCell = hssfCell;
		this.cellNumber = cellNumber;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Cell#getValue()
	 */
	public String getValue() {
		if (this.hssfCell == null) {
			return null;
		}
		return this.hssfCell.getStringCellValue();
	}

	/**
	 * 内部保持している値を取得する
	 *
	 * @return 内部保持している値
	 */
	public Object getRawValue() {
		if (this.hssfCell == null) {
			return null;
		}
		switch (this.hssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
		case HSSFCell.CELL_TYPE_BLANK:
			return this.hssfCell.getStringCellValue();
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return this.hssfCell.getBooleanCellValue();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return this.hssfCell.getNumericCellValue();
		case HSSFCell.CELL_TYPE_FORMULA:
			return this.hssfCell.getCellFormula();
		case HSSFCell.CELL_TYPE_ERROR:
			return this.hssfCell.getErrorCellValue();

		}
		return this.hssfCell.getStringCellValue();
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Cell#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		if (this.hssfCell == null) {
			this.hssfCell = this.hssfRow.createCell(this.cellNumber);
		}
		this.hssfCell.setCellValue(value);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Cell#getType()
	 */
	public CellType getType() {
		if (this.hssfCell == null) {
			return CellType.STRING;
		}
		switch (this.hssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
		case HSSFCell.CELL_TYPE_BLANK:
		case HSSFCell.CELL_TYPE_ERROR:
			return CellType.STRING;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return CellType.BOOLEAN;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(this.hssfCell)) {
				return CellType.DATE;
			} else {
				return CellType.NUMERIC;
			}
		case HSSFCell.CELL_TYPE_FORMULA:
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
			this.hssfCell.setCellValue((String) null);
			break;
		case BOOLEAN:
			this.hssfCell.setCellValue(false);
			break;
		case NUMERIC:
		case DECIMAL:
		case DATE:
			this.hssfCell.setCellValue(0);
			break;
		case FUNCTION:
			this.hssfCell.setCellFormula(null);
			break;
		}

		switch (type) {
		case STRING:
		case OTHER:
			this.hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			this.hssfCell.setCellValue(rawValue);
			break;
		case BOOLEAN:
			this.hssfCell.setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
			this.hssfCell.setCellValue(Boolean.parseBoolean(rawValue));
			break;
		case NUMERIC:
		case DECIMAL:
			this.hssfCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			this.hssfCell.setCellValue(Long.parseLong(rawValue));
			break;
		case DATE:
			this.hssfCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			this.hssfCell.setCellValue(((Date) value).getTime());
			break;
		case FUNCTION:
			this.hssfCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			this.hssfCell.setCellValue(rawValue);
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
