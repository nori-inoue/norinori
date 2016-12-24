package jp.gr.norinori.application;

import jp.gr.norinori.core.element.Element;

/**
 * 設定管理
 *
 * @author nori
 */
public interface Configuration {

    /**
     * 指定された設定のキーとなるパスから設定値を取得する
     *
     * @param path 設定のキーとなるパス
     * @return 値
     */
    public Element getParameter(String path);
}
