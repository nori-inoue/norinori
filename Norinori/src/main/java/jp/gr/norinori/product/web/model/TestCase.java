package jp.gr.norinori.product.web.model;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
	private String name = null;
	private String description = null;
	private Action action = null;
	private List<Input> inputs = new ArrayList<>();
	private List<Result> results = new ArrayList<>();
	private String importData = null;
	private TestCase chain = null;
	private boolean existsChain = false;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return this.action;
	}

	public void addInput(Input input) {
		this.inputs.add(input);
	}

	public List<Input> getInputs() {
		return this.inputs;
	}

	public void addResult(Result result) {
		this.results.add(result);
	}

	public List<Result> getResults() {
		return this.results;
	}

	public void setChain(TestCase chain) {
		this.chain = chain;
	}

	public TestCase getChain() {
		return this.chain;
	}

	public boolean existsChain() {
		return this.existsChain;
	}

	public void setExistsChain(boolean existsChain) {
		this.existsChain = existsChain;
	}

	public void setImportData(String importData) {
		this.importData = importData;
	}

	public String getImportData() {
		return this.importData;
	}
}
