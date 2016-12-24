package jp.gr.norinori.application;

import java.io.File;

import jp.gr.norinori.application.FileInformationFrame;

/**
 * 標準ファイル情報
 *
 * @author nori
 */
public class SimpleFileInformation extends FileInformationFrame {

    // コンストラクタ===========================================================
    /**
     * 標準ファイル情報のインスタンスを生成する
     *
     * @param file ファイル
     */
    public SimpleFileInformation(File file) {
        super(file);
    }

}
