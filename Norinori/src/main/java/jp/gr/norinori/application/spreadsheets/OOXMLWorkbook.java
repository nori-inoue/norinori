package jp.gr.norinori.application.spreadsheets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import jp.gr.norinori.application.ApplicationFileFrame;
import jp.gr.norinori.application.SimpleFileInformation;
import jp.gr.norinori.application.spreadsheets.Sheet;
import jp.gr.norinori.application.spreadsheets.Spreadsheets;
import jp.gr.norinori.core.file.FileInformation;
import jp.gr.norinori.utility.ArrayUtil;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author nori
 */
public class OOXMLWorkbook extends ApplicationFileFrame implements Spreadsheets {

	// メンバ===================================================================
	private XSSFWorkbook xssfWorkbook;
	private List<Sheet> sheetList;

	private InputStream inputStream;

	// コンストラクタ===========================================================
	/**
	 * 指定したファイル名でWorkbookのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 * @throws Exception
	 */
	public OOXMLWorkbook(String fileName) throws Exception {
		this(fileName, null);
	}

	/**
	 * 指定した入力ストリームのWorkbookのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 * @param inputStream 入力ストリーム
	 * @throws Exception
	 */
	public OOXMLWorkbook(String fileName, InputStream inputStream) throws Exception {
		super(fileName);
		if (inputStream == null) {
			if (getFile().exists()) {
				this.inputStream = new FileInputStream(getFile());
			}
		} else {
			this.inputStream = inputStream;
		}
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

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Spreadsheets#getSheet(int)
	 */
	public Sheet getSheetAt(int index) {
		return this.sheetList.get(index);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Spreadsheets#getSheet(java.lang.String)
	 */
	public Sheet getSheet(String sheetName) {
		if (sheetName == null) {
			return null;
		}

		for (Sheet sheet : this.sheetList) {
			if (sheetName.equals(sheet.getName())) {
				return sheet;
			}
		}
		return null;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Spreadsheets#getSheetList()
	 */
	public List<Sheet> getSheetList() {
		return this.sheetList;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Spreadsheets#getSheets()
	 */
	public Sheet[] getSheets() {
		return ArrayUtil.toArray(this.sheetList);
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#setActiveSheet(int)
	 */
	public void setActiveSheet(int index) {
		for (Sheet sheet : getSheetList()) {
			sheet.setSelected(false);
		}
		getSheetAt(index).setSelected(true);
		this.xssfWorkbook.setActiveSheet(index);
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#addSheet(java.lang
	 * .String)
	 */
	public Sheet addSheet(String sheetName) {
		XSSFSheet sheet = this.xssfWorkbook.createSheet(sheetName);
		sheet.setForceFormulaRecalculation(true);
		Sheet newSheet = createExcelSheet(sheet);
		this.sheetList.add(newSheet);
		return newSheet;
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#addBeforeSheet(java
	 * .lang.String, java.lang.String)
	 */
	public Sheet addBeforeSheet(String targetSheetName, String sheetName) {
		Sheet newSheet = addSheet(sheetName);
		int targetSheetIndex = this.xssfWorkbook.getSheetIndex(targetSheetName);
		this.xssfWorkbook.setSheetOrder(sheetName, targetSheetIndex);

		this.sheetList.remove(this.sheetList.size() - 1);
		this.sheetList.add(targetSheetIndex, newSheet);

		return newSheet;
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#addAfterSheet(java
	 * .lang.String, java.lang.String)
	 */
	public Sheet addAfterSheet(String targetSheetName, String sheetName) {
		Sheet newSheet = addSheet(sheetName);
		int targetSheetIndex = this.xssfWorkbook.getSheetIndex(targetSheetName);
		this.xssfWorkbook.setSheetOrder(sheetName, targetSheetIndex + 1);

		this.sheetList.remove(this.sheetList.size() - 1);
		this.sheetList.add(targetSheetIndex + 1, newSheet);

		return newSheet;
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#cloneSheet(java.
	 * lang.String, java.lang.String)
	 */
	public Sheet cloneSheet(String originalSheetName, String sheetName) {
		int sheetIndex = this.xssfWorkbook.getSheetIndex(originalSheetName);
		XSSFSheet sheet = this.xssfWorkbook.cloneSheet(sheetIndex);
		int cloneSheetIndex = this.xssfWorkbook.getSheetIndex(sheet);
		this.xssfWorkbook.setSheetName(cloneSheetIndex, sheetName);

		Sheet newSheet = createExcelSheet(sheet);
		this.sheetList.add(newSheet);
		return newSheet;
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#cloneBeforeSheet
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public Sheet cloneBeforeSheet(String targetSheetName, String originalSheetName, String sheetName) {
		Sheet newSheet = cloneSheet(originalSheetName, sheetName);

		int targetSheetIndex = this.xssfWorkbook.getSheetIndex(targetSheetName);
		this.xssfWorkbook.setSheetOrder(sheetName, targetSheetIndex);

		this.sheetList.remove(this.sheetList.size() - 1);
		this.sheetList.add(targetSheetIndex, newSheet);

		return newSheet;
	}

	/*
	 * (非 Javadoc)
	 * @see
	 * jp.gr.norinori.application.spreadsheets.Spreadsheets#cloneAfterSheet(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public Sheet cloneAfterSheet(String targetSheetName, String originalSheetName, String sheetName) {
		Sheet newSheet = cloneSheet(originalSheetName, sheetName);

		int targetSheetIndex = this.xssfWorkbook.getSheetIndex(targetSheetName);
		this.xssfWorkbook.setSheetOrder(sheetName, targetSheetIndex + 1);

		this.sheetList.remove(this.sheetList.size() - 1);
		this.sheetList.add(targetSheetIndex + 1, newSheet);

		return newSheet;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.ApplicationData#write()
	 */
	public boolean write() {
		return write(getPath());
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.ApplicationData#write(java.lang.String)
	 */
	public boolean write(String fileName) {
		try {
			return write(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.application.spreadsheets.Spreadsheets#write(java.io.
	 * OutputStream)
	 */
	public boolean write(OutputStream output) {
		try {
			this.xssfWorkbook.write(output);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#create()
	 */
	public void create() throws Exception {
		create(this.inputStream);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#create(java.lang.Object)
	 */
	public <T> void create(T inputStream) throws Exception {
		if (inputStream != null) {
			this.xssfWorkbook = new XSSFWorkbook((InputStream) inputStream);
		} else {
			this.xssfWorkbook = new XSSFWorkbook();
		}
		this.sheetList = new LinkedList<Sheet>();

		createExcelObject();
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#isLiving()
	 */
	public boolean isLiving() throws Exception {
		return this.xssfWorkbook != null;
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#destroy()
	 */
	public void destroy() throws Exception {
		this.xssfWorkbook = null;
		this.sheetList = null;
	}

	// ローカルメソッド=========================================================

	/**
	 * 初期オブジェクトを作成する
	 */
	private void createExcelObject() {
		int sheetCount = this.xssfWorkbook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			XSSFSheet sheet = this.xssfWorkbook.getSheetAt(i);
			sheet.setForceFormulaRecalculation(true);
			this.sheetList.add(createExcelSheet(sheet));
		}
	}

	/**
	 * ExcelSheetを作成する
	 *
	 * @param sheet XSSFSheet
	 * @return ExcelSheet
	 */
	private Sheet createExcelSheet(XSSFSheet sheet) {
		return new OOXMLExcelSheet(this.xssfWorkbook, sheet);
	}
}
