package jp.gr.norinori.product.web;

import static org.junit.Assert.fail;

import static jp.gr.norinori.test.NorinoriTest.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jp.gr.norinori.application.Arguments;
import jp.gr.norinori.application.SimpleArgument;
import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.text.SimpleTextFile;
import jp.gr.norinori.text.TextFile;

public class CreatePageFileTest extends NorinoriTestFrame {
	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/product/web";
	private final static String TEMP_PATH = RESOURCE_PATH + "/temp";

	@Test
	public void testFileOutput() {
		try {
			createDirectory(TEMP_PATH);

			String url = "http://testsrver/inoue/login.php";
			String className = "LoginPage";
			String packageName = "jp.gr.norinori.product.web";

			Map<String, String[]> argumentsMap = new HashMap<>();
			argumentsMap.put("-url", new String[] { url });
			argumentsMap.put("-class", new String[] { className });
			argumentsMap.put("-output", new String[] { TEMP_PATH });
			argumentsMap.put("-package", new String[] { packageName });

			Arguments args = new SimpleArgument(argumentsMap);

			CreatePageFile createPageFile = new CreatePageFile();
			createPageFile.create(args);

			TextFile expected = new SimpleTextFile(RESOURCE_PATH + "/LoginPage.java");
			TextFile actual = new SimpleTextFile(TEMP_PATH + "/LoginPage.java");

			assertEquals(expected, actual);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
