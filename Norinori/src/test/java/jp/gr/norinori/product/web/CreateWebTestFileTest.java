package jp.gr.norinori.product.web;

import static jp.gr.norinori.test.NorinoriTest.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jp.gr.norinori.application.Arguments;
import jp.gr.norinori.application.SimpleArgument;
import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.text.SimpleTextFile;
import jp.gr.norinori.text.TextFile;

public class CreateWebTestFileTest extends NorinoriTestFrame {
	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/test/resources/jp/gr/norinori/product/web";
	private final static String TEMP_PATH = RESOURCE_PATH + "/temp";

	@Test
	public void testFileOutput() {
		try {
			createDirectory(TEMP_PATH);

			String inputFile = RESOURCE_PATH + "/WebTest1.xlsx";
			String packageName = "jp.gr.norinori.product.web";

			Map<String, String[]> argumentsMap = new HashMap<>();
			argumentsMap.put("-input", new String[] { inputFile });
			argumentsMap.put("-output", new String[] { TEMP_PATH });
			argumentsMap.put("-package", new String[] { packageName });

			Arguments args = new SimpleArgument(argumentsMap);

			CreateWebTestFile createWebTestFile = new CreateWebTestFile();
			createWebTestFile.create(args);

			TextFile expected = new SimpleTextFile(RESOURCE_PATH + "/WebTest1IE11.java");
			TextFile actual = new SimpleTextFile(TEMP_PATH + "/WebTest1IE11.java");

			assertEquals(expected, actual);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
