package jp.gr.norinori.product.web.model;

import jp.gr.norinori.utility.StringUtil;

public class Result {

	private Element keyElemenrt = null;
	private String value = null;

	public Result(String elementKeyValueText) {
		if (!StringUtil.isEmpty(elementKeyValueText)) {
			String[] keyValues = elementKeyValueText.split(":", 0);

			setKeyElement(new Element(keyValues[0]));
			setValue(keyValues[1]);
		}
	}

	public void setKeyElement(Element keyElemenrt) {
		this.keyElemenrt = keyElemenrt;
	}

	public Element getKeyElement() {
		return this.keyElemenrt;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
