package jp.gr.norinori.product.web.model;

import java.util.ArrayList;
import java.util.List;

public class TestInfo {

	private Outline outline = null;
	private Environment environment = null;
	private List<TestCase> testCases = new ArrayList<>();

	public void setOutline(Outline outline) {
		this.outline = outline;
	}

	public Outline getOutline() {
		return this.outline;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Environment getEnvironment() {
		return this.environment;
	}

	public void addTestCase(TestCase testCase) {
		this.testCases.add(testCase);
	}

	public List<TestCase> getTestCases() {
		return this.testCases;
	}
}
