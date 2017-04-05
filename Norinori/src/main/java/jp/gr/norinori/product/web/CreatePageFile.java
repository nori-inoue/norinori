package jp.gr.norinori.product.web;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.application.Arguments;
import jp.gr.norinori.define.Encoding;
import jp.gr.norinori.dom.HtmlAttributes;
import jp.gr.norinori.dom.HtmlDocument;
import jp.gr.norinori.dom.HtmlElement;
import jp.gr.norinori.dom.HtmlParser;
import jp.gr.norinori.text.SimpleTextFile;
import jp.gr.norinori.text.template.TextTemplate;
import jp.gr.norinori.text.template.velocity.VelocityTemplate;
import jp.gr.norinori.text.template.velocity.VelocityUtility;
import jp.gr.norinori.utility.StringUtil;

public class CreatePageFile {
	// 定数=====================================================================
	private final static String RESOURCE_PATH = "./src/main/resources/jp/gr/norinori/product/web";

	public void create(Arguments args) throws Exception {

		if (!args.hasOption("-url")) {
			throw new IllegalArgumentException("No arugments [-url]");
		}
		if (!args.hasOption("-class")) {
			throw new IllegalArgumentException("No arugments [-class]");
		}
		if (!args.hasOption("-output")) {
			throw new IllegalArgumentException("No arugments [-output]");
		}
		if (!args.hasOption("-package")) {
			throw new IllegalArgumentException("No arugments [-package]");
		}

		URL url = new URL(args.getOptionValue("-url"));
		HtmlDocument document = HtmlParser.parse(url);

		createFile(args.getOptionValue("-package"), args.getOptionValue("-class"), document, url,
				args.getOptionValue("-output"));

	}

	private void createFile(String packageName, String className, HtmlDocument document, URL url,
			String outputDirectory) {

		TextTemplate textTemplate = new VelocityTemplate();
		try (OutputStreamWriter out = new OutputStreamWriter(
				new FileOutputStream(outputDirectory + "/" + className + ".java"), Encoding.UTF8.name())) {

			textTemplate.setTemplate(new SimpleTextFile(RESOURCE_PATH + "/createPage.vm"));
			textTemplate.setWriter(out);

			textTemplate.putVariable("util", new VelocityUtility());
			textTemplate.putVariable("className", className);
			textTemplate.putVariable("package", packageName);
			textTemplate.putVariable("baseUrl", url.toString());

			List<String> inputElements = new ArrayList<>();
			for (HtmlElement element : document.getElementsByTag("input")) {
				HtmlAttributes attributes = element.getAttribute();
				if (!StringUtil.isEmpty(attributes.get("id"))) {
					if (attributes.get("type").equals("text") || attributes.get("type").equals("password")) {
						inputElements.add(element.getAttribute().get("id"));
					}
				}
			}
			textTemplate.putVariable("inputElements", inputElements);

			List<String> formElements = new ArrayList<>();
			for (HtmlElement element : document.getElementsByTag("form")) {
				HtmlAttributes attributes = element.getAttribute();

				if (!StringUtil.isEmpty(attributes.get("name"))) {
					formElements.add(element.getAttribute().get("name"));
				}
			}
			textTemplate.putVariable("formElements", formElements);

			textTemplate.output();
		} catch (Exception e) {
		}
	}
}
