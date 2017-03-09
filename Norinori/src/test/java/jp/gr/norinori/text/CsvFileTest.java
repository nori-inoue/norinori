package jp.gr.norinori.text;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.text.CsvFile;

import org.junit.Test;

/**
 * CSVテキストファイル
 *
 * @author nori
 */
public class CsvFileTest extends NorinoriTestFrame {
    // 定数=====================================================================
    private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/text";

    // メソッド=================================================================
    /**
     * {@link jp.gr.norinori.text.TextFileFrame#getContents()} のためのテスト・メソッド。
     */
    @Test
    public void testOutputCsv() {
    	CsvFile textFile = getCsvFile();

        StringBuilder expected = new StringBuilder();
        expected.append("テスト").append("\n");
        expected.append("２行目").append("\n");

        try {
            textFile.open();

        	List<String> values = new ArrayList<String>();
        	values.add("テスト");
        	values.add("１行目");
        	values.add("３列目");
        	textFile.append(values);

        	values = new ArrayList<String>();
        	values.add("テスト");
        	values.add("２行目");
        	values.add("３列目");
        	textFile.append(values);

        	textFile.flush();
//            assertEquals(expected.toString(), textFile.getContents());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        } finally {
            try {
                textFile.close();
            } catch (Exception ignore) {
            }
        }
    }

    protected CsvFile getCsvFile() {
    	CsvFile csvFile = new CsvFile(RESOURCE_PATH + "/csv.txt", ",", "'");
    	csvFile.delete();

        return csvFile;
    }
}
