package jp.gr.norinori.text.template;

import java.io.Writer;

import jp.gr.norinori.text.TextFile;

/**
 * テキストテンプレート
 *
 * @author nori
 */
public interface TextTemplate {

    /**
     * 出力先を設定する
     *
     * @param writer 出力先
     */
    public void setWriter(Writer writer);

    /**
     * テンプレートファイルを設定する
     *
     * @param templateFile
     */
    public void setTemplate(TextFile templateFile);

    /**
     * テンプレート変数を設定する
     *
     * @param key テンプレート変数名
     * @param value 変数の値
     */
    public void putVariable(String key, Object value);

    /**
     * 設定したテンプレート変数をクリアする
     */
    public void clearVariable();

    /**
     * テンプレートを元に出力する.
     */
    public void output() throws Exception;
}
