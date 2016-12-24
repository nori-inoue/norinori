package jp.gr.norinori.text.template;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import jp.gr.norinori.text.TextFile;
import jp.gr.norinori.text.template.TextTemplate;

/**
 * テキストテンプレートフレーム
 *
 * @author nori
 */
public abstract class TextTemplateFrame implements TextTemplate {

    // メンバ===================================================================
    private Map<String, Object> variableMap;
    private TextFile templateFile;
    private Writer writer;

    // コンストラクタ===========================================================
    /**
     * テキストテンプレートのインスタンスを生成する
     */
    public TextTemplateFrame() {
        this.variableMap = new HashMap<String, Object>();
    }

    // メソッド=================================================================
    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.text.template.TextTemplate#setWriter(java.io.Writer)
     */
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.text.template.TextTemplate#setTemplate(jp.gr.norinori.text.TextFile)
     */
    public void setTemplate(TextFile templateFile) {
        this.templateFile = templateFile;
    }

    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.text.template.TextTemplate#addVariable(java.lang.String, java.lang.String)
     */
    public void putVariable(String key, Object value) {
        this.variableMap.put(key, value);
    }

    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.text.template.TextTemplate#clearVariable()
     */
    public void clearVariable() {
        this.variableMap.clear();
    }

    // 系列メソッド=============================================================
    // テンプレートファイルを取得する
    protected TextFile getTemplate() {
        return this.templateFile;
    }

    // ライタを取得する
    protected Writer getWriter() {
        return this.writer;
    }

    // 対応キーのマップを取得
    protected Map<String, Object> getVariableMap() {
        return this.variableMap;
    }
}
