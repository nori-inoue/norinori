package jp.gr.norinori.text;

import java.io.File;

import jp.gr.norinori.application.SimpleFileInformation;
import jp.gr.norinori.core.file.FileInformation;
import jp.gr.norinori.text.TextFileFrame;

/**
 * シンプルテキストファイル
 *
 * @author nori
 */
public class SimpleTextFile extends TextFileFrame {

    // コンストラクタ===========================================================
    /**
     * テキストファイルのインスタンスを生成する
     *
     * @param fileName ファイル名
     */
    public SimpleTextFile(String fileName) {
        this(new File(fileName));
    }

    /**
     * テキストファイルのインスタンスを生成する
     *
     * @param textFile テキストファイル
     */
    public SimpleTextFile(File textFile) {
        super(textFile);
    }

    // オーバライドメソッド=====================================================
    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.application.ApplicationFileFrame#createFileInformation()
     */
    @Override
    protected FileInformation createFileInformation() {
        return new SimpleFileInformation(getFile());
    }

}
