package jp.gr.norinori.text.template.velocity;

import static jp.gr.norinori.test.NorinoriTest.assertEquals;
import static jp.gr.norinori.test.NorinoriTest.fail;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import jp.gr.norinori.define.Encoding;
import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.text.SimpleTextFile;
import jp.gr.norinori.text.TextFile;
import jp.gr.norinori.text.template.TextTemplate;
import jp.gr.norinori.text.template.velocity.VelocityTemplate;

import org.junit.Test;

/**
 * Velocityテンプレートテスト
 *
 * @author nori
 */
public class VelocityTemplateTest extends NorinoriTestFrame {
	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/text/template/velocity/";

	private final static String TEMP_PATH = RESOURCE_PATH + "temp";

	// メソッド=================================================================
	@Test
	public void testTemplate() {
		try {
			TextTemplate textTemplate = new VelocityTemplate();
			StringWriter sw = new StringWriter();

			textTemplate.setTemplate(new SimpleTextFile(RESOURCE_PATH + "/test.vm"));
			textTemplate.setWriter(sw);

			textTemplate.putVariable("text", "てきすと");
			textTemplate.putVariable("text1", "てきすと２");

			textTemplate.output();

			assertEquals("てきすと\r\nてきすと２\r\n", sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFileOutput() {
		try {
			createDirectory(TEMP_PATH);

			TextTemplate textTemplate = new VelocityTemplate();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(TEMP_PATH + "/test.txt"), Encoding.UTF8.name());

			textTemplate.setTemplate(new SimpleTextFile(RESOURCE_PATH + "/test.vm"));
			textTemplate.setWriter(out);

			textTemplate.putVariable("text", "てきすと");
			textTemplate.putVariable("text1", "てきすと２");

			textTemplate.output();

			TextFile expected = new SimpleTextFile(RESOURCE_PATH + "/testExpect.txt");
			TextFile actual = new SimpleTextFile(TEMP_PATH + "/test.txt");

			assertEquals(expected, actual);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAbsolutePath() {
		try {
			createDirectory(TEMP_PATH);

			TextTemplate textTemplate = new VelocityTemplate();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(TEMP_PATH + "/test.txt"), Encoding.UTF8.name());

			File absoluteFile = new File(RESOURCE_PATH + "/test.vm");
			textTemplate.setTemplate(new SimpleTextFile(absoluteFile.getAbsolutePath()));
			textTemplate.setWriter(out);

			textTemplate.putVariable("text", "てきすと");
			textTemplate.putVariable("text1", "てきすと２");

			textTemplate.output();

			TextFile expected = new SimpleTextFile(RESOURCE_PATH + "/testExpect.txt");
			TextFile actual = new SimpleTextFile(TEMP_PATH + "/test.txt");

			assertEquals(expected, actual);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
