package jp.gr.norinori.application.spreadsheets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.gr.norinori.application.spreadsheets.Cell;
import jp.gr.norinori.application.spreadsheets.Row;
import jp.gr.norinori.application.spreadsheets.Sheet;
import jp.gr.norinori.application.spreadsheets.Spreadsheets;
import jp.gr.norinori.application.spreadsheets.Workbook;
import jp.gr.norinori.core.collection.Record;
import jp.gr.norinori.core.collection.SimpleRecord;
import jp.gr.norinori.core.collection.SimpleTable;
import jp.gr.norinori.core.collection.Table;
import jp.gr.norinori.test.NorinoriTestFrame;

import org.junit.Test;

/**
 * @author nori
 */
public class WorkbookTest extends NorinoriTestFrame {
	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/application/spreadsheets/";

	private final static String TEMP_PATH = RESOURCE_PATH + "temp";

	// メンバ===================================================================
	@Test
	public void testGetCellValue() {
		try {
			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			Sheet sheet = spreadsheets.getSheetAt(0);
			Row row = sheet.getRowAt(0);
			Cell cell = row.getCellAt(0);

			assertEquals("てきすと", cell.getValue());

			cell = row.getCellAt(9);
			assertEquals("１０列", cell.getValue());

			row = sheet.getRowAt(9);
			cell = row.getCellAt(1);
			assertEquals("１０行", cell.getValue());

			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testWriteAs() {
		try {
			createDirectory(TEMP_PATH);

			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			Sheet sheet = spreadsheets.getSheetAt(0);
			Row row = sheet.getRowAt(0);
			Cell cell = row.getCellAt(1);
			cell.setValue("ほげほげ");
			spreadsheets.write(TEMP_PATH + "/test1.xls");
			spreadsheets.destroy();

			spreadsheets = new Workbook(TEMP_PATH + "/test1.xls");
			spreadsheets.create();

			sheet = spreadsheets.getSheetAt(0);
			row = sheet.getRowAt(0);
			cell = row.getCellAt(1);
			assertEquals("ほげほげ", cell.getValue());

			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddSBeforeheet() {
		try {
			createDirectory(TEMP_PATH);

			String[] expectedSheet = new String[] { "Sheet1", "Sheet4", "Sheet2", "Sheet3" };
			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			spreadsheets.addBeforeSheet("Sheet2", "Sheet4");

			int index = 0;
			for (Sheet s : spreadsheets.getSheetList()) {
				assertEquals(expectedSheet[index], s.getName());
				index++;
			}

			spreadsheets.write(TEMP_PATH + "/test1.xls");
			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddAfterSheet() {
		try {
			createDirectory(TEMP_PATH);

			String[] expectedSheet = new String[] { "Sheet1", "Sheet2", "Sheet4", "Sheet3" };
			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			spreadsheets.addAfterSheet("Sheet2", "Sheet4");

			int index = 0;
			for (Sheet s : spreadsheets.getSheetList()) {
				assertEquals(expectedSheet[index], s.getName());
				index++;
			}

			spreadsheets.write(TEMP_PATH + "/test1.xls");
			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCloneBeforeSheet() {
		try {
			createDirectory(TEMP_PATH);

			String[] expectedSheet = new String[] { "Sheet1", "Sheet4", "Sheet2", "Sheet3" };
			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			spreadsheets.cloneBeforeSheet("Sheet2", "Sheet1", "Sheet4");

			int index = 0;
			for (Sheet s : spreadsheets.getSheetList()) {
				assertEquals(expectedSheet[index], s.getName());
				index++;
			}

			spreadsheets.write(TEMP_PATH + "/test1.xls");
			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCloneAfterSheet() {
		try {
			createDirectory(TEMP_PATH);

			String[] expectedSheet = new String[] { "Sheet1", "Sheet2", "Sheet4", "Sheet3" };
			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			spreadsheets.cloneAfterSheet("Sheet2", "Sheet1", "Sheet4");

			int index = 0;
			for (Sheet s : spreadsheets.getSheetList()) {
				assertEquals(expectedSheet[index], s.getName());
				index++;
			}

			spreadsheets.write(TEMP_PATH + "/test1.xls");
			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testLoadTable() {
		try {
			createDirectory(TEMP_PATH);

			Table<Integer ,Object> table = new SimpleTable<Integer ,Object>();
			Record<Integer ,Object> record = new SimpleRecord<Integer ,Object>();
			record.setValue(0, "0,0");
			record.setValue(1, "1,0");
			record.setValue(2, "2,0");
			table.addRecord(record);

			record = new SimpleRecord<Integer ,Object>();
			record.setValue(0, "0,1");
			record.setValue(1, "1,1");
			record.setValue(2, "2,1");
			table.addRecord(record);

			Spreadsheets spreadsheets = new Workbook(RESOURCE_PATH + "/test1.xls");
			spreadsheets.create();

			Sheet sheet = spreadsheets.getSheetAt(1);
			sheet.loadTable(table);

			spreadsheets.write(TEMP_PATH + "/test1.xls");
			spreadsheets.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
