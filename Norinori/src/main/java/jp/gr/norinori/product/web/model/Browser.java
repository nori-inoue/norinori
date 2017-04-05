package jp.gr.norinori.product.web.model;

import jp.gr.norinori.utility.StringUtil;

public class Browser {

	enum Type {
		CHROME, IE
	}

	private String name = null;
	private Type type = null;

	public Browser(String browserName) {
		if (!StringUtil.isEmpty(browserName)) {
			setName(browserName);

			if (browserName.startsWith("Chrome")) {
				setType(Type.CHROME);
			}
			if (browserName.startsWith("IE")) {
				setType(Type.IE);
			}
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return this.type;
	}

	public boolean isChrome() {
		if (this.type == null)
			return false;

		return (this.type == Type.CHROME);
	}

	public boolean isIE() {
		if (this.type == null)
			return false;

		return (this.type == Type.IE);
	}
}
