package jp.gr.norinori.text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.text.SimpleTextFile;
import jp.gr.norinori.text.TextFile;

import org.junit.Test;

/**
 * シンプルテキストファイル
 *
 * @author nori
 */
public class SimpleTextFileTest extends NorinoriTestFrame {
    // 定数=====================================================================
    private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/text";

    // メソッド=================================================================
    /**
     * {@link jp.gr.norinori.text.TextFileFrame#getContents()} のためのテスト・メソッド。
     */
    @Test
    public void testGetContents() {
        TextFile textFile = getTextFile();

        StringBuilder expected = new StringBuilder();
        expected.append("テスト").append("\n");
        expected.append("２行目").append("\n");

        try {
            textFile.open();
            assertEquals(expected.toString(), textFile.getContents());
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

    protected TextFile getTextFile() {
        TextFile textFile = new SimpleTextFile(RESOURCE_PATH + "/test.txt");

        return textFile;
    }
}
