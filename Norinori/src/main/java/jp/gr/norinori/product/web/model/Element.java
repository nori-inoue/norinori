package jp.gr.norinori.product.web.model;

import jp.gr.norinori.utility.StringUtil;

public class Element {

	enum Type {
		NAME, PATH
	}

	private String name = null;
	private Type type = null;

	public Element(String elementKeyText) {
		if (!StringUtil.isEmpty(elementKeyText)) {
			if (elementKeyText.startsWith("NAME<")) {
				String[] keyValues2 = elementKeyText.split("<", 0);
				String[] keyValues3 = keyValues2[1].split(">", 0);
				setName(keyValues3[0].replace("\"", "\\\""));

				setType(Type.NAME);
			} else if (elementKeyText.startsWith("PATH<")) {
				String[] keyValues2 = elementKeyText.split("<", 0);
				String[] keyValues3 = keyValues2[1].split(">", 0);
				setName(keyValues3[0].replace("\"", "\\\""));

				setType(Type.PATH);
			} else {
				setName(elementKeyText);
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

	public boolean isNameType() {
		return (this.type == Type.NAME);
	}

	public boolean isPathType() {
		return (this.type == Type.PATH);
	}
}
