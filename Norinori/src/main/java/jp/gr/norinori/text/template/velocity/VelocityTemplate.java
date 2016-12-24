package jp.gr.norinori.text.template.velocity;

import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import jp.gr.norinori.text.TextFile;
import jp.gr.norinori.text.template.TextTemplateFrame;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Velocityテンプレート
 *
 * @author nori
 */
public class VelocityTemplate extends TextTemplateFrame {

	// メンバ===================================================================
	private VelocityContext context;

	// コンストラクタ===========================================================
	/**
	 * Velocityテンプレートのインスタンスを生成する
	 */
	public VelocityTemplate() {
		super();
		this.context = new VelocityContext();
	}

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.text.template.TextTemplate#output()
	 */
	public void output() throws Exception {
		TextFile templateFile = getTemplate();

		VelocityEngine engine = new VelocityEngine();
		Properties p = new Properties();
		p.setProperty("file.resource.loader.path", templateFile.getParent());
		engine.init(p);

		Map<String, Object> variableMap = getVariableMap();
		Writer writer = getWriter();

		for (Entry<String, Object> en : variableMap.entrySet()) {
			this.context.put(en.getKey(), en.getValue());
		}
		Template template = engine.getTemplate(templateFile.getName(), templateFile.getEncoding().getCode());
		template.merge(this.context, writer);

		writer.flush();
	}
}
