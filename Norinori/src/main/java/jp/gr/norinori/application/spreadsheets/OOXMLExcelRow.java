package jp.gr.norinori.application.spreadsheets;

import java.util.LinkedList;
import java.util.List;

import jp.gr.norinori.application.spreadsheets.Cell;
import jp.gr.norinori.application.spreadsheets.Row;
import jp.gr.norinori.utility.ArrayUtil;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * 行
 *
 * @author nori
 */
public class OOXMLExcelRow implements Row {

    // メンバ===================================================================
    private XSSFRow xssfRow;
    private XSSFSheet xssfSheet;
    private List<Cell> cellList;

    // コンストラクタ===========================================================
    /**
     * 行のインスタンスを生成する
     *
     * @param xssfSheet シート
     * @param xssfRow 行
     */
    public OOXMLExcelRow(XSSFSheet xssfSheet, XSSFRow xssfRow) {
        this.xssfRow = xssfRow;
        this.xssfSheet = xssfSheet;
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
            this.cellList.add(createExcelCell(this.xssfRow.createCell(columnnumber), columnnumber));
        }
        return this.cellList.get(columnnumber);
    }

	/*
	 * /(非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Row#getRowNumber()
	 */
	public int getRowNumber() {
		return this.xssfRow.getRowNum();
	}

    /**
     * XSSFSheetを取得する
     *
     * @return XSSFSheet
     */
    protected XSSFSheet getXSSFSheet() {
        return this.xssfSheet;
    }

    // ローカルメソッド=========================================================
    /**
     * 初期オブジェクトを作成する
     */
    private void createExcelObject() {
        if (this.xssfRow == null) {
            return;
        }

        int cellCount = this.xssfRow.getLastCellNum();
        for (int i = 0; i < cellCount; i++) {
            XSSFCell xssfCell = this.xssfRow.getCell(i);
            this.cellList.add(createExcelCell(xssfCell, i));
        }
    }

    /**
     * ExcelCellを作成する
     *
     * @param xssfCell XSSFCell
     * @param cellNumber セル番号
     * @return ExcelCell
     */
	private Cell createExcelCell(XSSFCell xssfCell, int cellNumber) {
        return new OOXMLExcelCell(this.xssfRow, xssfCell, cellNumber);
    }
}
