package jp.gr.norinori.product.web.model;

import jp.gr.norinori.utility.StringUtil;

public class Action {

	enum Type {
		URL, LINK
	}

	private Type type = null;
	private Element action = null;

	public Action(String actionText) {
		if (!StringUtil.isEmpty(actionText)) {
			String[] actions = actionText.split(":", 0);
			setAction(new Element(actions[1]));

			if (actions[0] == "url") {
				setType(Type.URL);
			} else if (actions[0] == "link") {
				setType(Type.LINK);
			}
		}
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return this.type;
	}

	public void setAction(Element action) {
		this.action = action;
	}

	public Element getAction() {
		return this.action;
	}

	public boolean isUrlType() {
		return (this.type == Type.URL);
	}

	public boolean isLinkType() {
		return (this.type == Type.LINK);
	}
}
