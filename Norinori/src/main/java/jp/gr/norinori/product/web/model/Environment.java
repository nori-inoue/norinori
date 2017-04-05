package jp.gr.norinori.product.web.model;

import java.util.ArrayList;
import java.util.List;

public class Environment {
	private List<String> browsers = new ArrayList<>();

	public void addBrowser(String browser) {
		this.browsers.add(browser);
	}

	public List<String> getBrowsers() {
		return this.browsers;
	}
}
