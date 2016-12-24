package jp.gr.norinori.text.template.velocity;

import jp.gr.norinori.utility.FirstSkip;
import jp.gr.norinori.utility.StringUtil;

public class VelocityUtility {

	public FirstSkip createFirstSkip(String first) {
		return new FirstSkip(first);
	}

	public FirstSkip createFirstSkip(String first, String noFirst) {
		return new FirstSkip(first, noFirst);
	}

	public String pascalize(String str) {
		return StringUtil.pascalize(str);
	}

	public String camelize(String str) {
		return StringUtil.camelize(str);
	}
}
