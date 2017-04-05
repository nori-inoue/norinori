package jp.gr.norinori.product.web;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import jp.gr.norinori.application.Arguments;
import jp.gr.norinori.application.spreadsheets.OOXMLWorkbook;
import jp.gr.norinori.application.spreadsheets.Row;
import jp.gr.norinori.application.spreadsheets.Sheet;
import jp.gr.norinori.application.spreadsheets.Spreadsheets;
import jp.gr.norinori.define.Encoding;
import jp.gr.norinori.product.web.model.Action;
import jp.gr.norinori.product.web.model.Browser;
import jp.gr.norinori.product.web.model.Environment;
import jp.gr.norinori.product.web.model.Input;
import jp.gr.norinori.product.web.model.Outline;
import jp.gr.norinori.product.web.model.Result;
import jp.gr.norinori.product.web.model.TestCase;
import jp.gr.norinori.product.web.model.TestInfo;
import jp.gr.norinori.text.SimpleTextFile;
import jp.gr.norinori.text.template.TextTemplate;
import jp.gr.norinori.text.template.velocity.VelocityTemplate;
import jp.gr.norinori.utility.StringUtil;

public class CreateWebTestFile {
	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/main/resources/jp/gr/norinori/product/web";


	public void create(Arguments args) {

		if (!args.hasOption("-input")) {
			throw new IllegalArgumentException("No arugments [-input]");
		}
		if (!args.hasOption("-output")) {
			throw new IllegalArgumentException("No arugments [-output]");
		}
		if (!args.hasOption("-package")) {
			throw new IllegalArgumentException("No arugments [-package]");
		}
		String packageName = args.getOptionValue("-package");
		String outputDirectory = args.getOptionValue("-output");

		try (Spreadsheets spreadsheets = new OOXMLWorkbook(args.getOptionValue("-input"))) {
			spreadsheets.open();

			TestInfo testInfo = new TestInfo();

			// 概要シート
			Sheet outlineSheet = spreadsheets.getSheet("概要");
			Outline outline = new Outline();
			outline.setTitle(outlineSheet.getRowAt(0).getCellAt(1).getValue());
			outline.setFilename(outlineSheet.getRowAt(1).getCellAt(1).getValue());

			// 環境シート
			Sheet environmentSheet = spreadsheets.getSheet("環境");
			Environment environment = new Environment();

			String[] browsers = environmentSheet.getRowAt(0).getCellAt(1).getValue().split(",", 0);
			for (String browser : browsers) {
				environment.addBrowser(browser);
			}

			// 項目シート
			Sheet itemSheet = spreadsheets.getSheet("項目");

			Map<String, TestCase> testCases = new HashMap<>(); // チェイン用
			Map<String, String> chains = new HashMap<>(); // チェイン用
			for (Row row : itemSheet.getRowList()) {
				if (StringUtil.isEmpty(row.getCellAt(0).getValue())) {
					continue;
				}
				if (row.getRowNumber() == 0)
					continue;

				TestCase testCase = new TestCase();
				testInfo.addTestCase(testCase);

				testCase.setName(row.getCellAt(0).getValue());
				testCase.setDescription(row.getCellAt(1).getValue());
				testCase.setAction(new Action(row.getCellAt(2).getValue()));

				String[] inputs = row.getCellAt(3).getValue().split("\n");
				for (String input : inputs) {
					if (StringUtil.isEmpty(input))
						continue;
					testCase.addInput(new Input(input));
				}

				String[] results = row.getCellAt(4).getValue().split("\n");
				for (String result : results) {
					if (StringUtil.isEmpty(result))
						continue;
					testCase.addResult(new Result(result));
				}

				chains.put(testCase.getName(), row.getCellAt(5).getValue());
				testCases.put(testCase.getName(), testCase);
			}


		    // チェイン
			for (TestCase testCase : testInfo.getTestCases()) {
				String chain = chains.get(testCase.getName());
				if (!StringUtil.isEmpty(chain)) {
					TestCase chainTestCase = testCases.get(chain);
		            if (chainTestCase != null) {
		            	testCase.setChain(chainTestCase);

		            	chainTestCase.setExistsChain(true);
		            }
		        }
		    }

			for (String browserName : environment.getBrowsers()) {
				Browser browser = new Browser(browserName);
				String className = outline.getFilename() + browserName;

				TextTemplate textTemplate = new VelocityTemplate();
				try (OutputStreamWriter out = new OutputStreamWriter(
						new FileOutputStream(outputDirectory + "/" + className + ".java"), Encoding.UTF8.name())) {

					textTemplate.setTemplate(new SimpleTextFile(RESOURCE_PATH + "/createWebTest.vm"));
					textTemplate.setWriter(out);

					textTemplate.putVariable("outline", outline);
					textTemplate.putVariable("testCases", testCases);

					textTemplate.putVariable("browser", browser);
					textTemplate.putVariable("package", packageName);
					textTemplate.putVariable("className", className);

					textTemplate.output();
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
