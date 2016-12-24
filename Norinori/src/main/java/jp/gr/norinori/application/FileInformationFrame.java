package jp.gr.norinori.application;

import java.io.File;
import java.util.Date;

import jp.gr.norinori.core.file.FileInformation;

/**
 * ファイル情報フレーム
 *
 * @author nori
 */
public abstract class FileInformationFrame implements FileInformation {

    // メンバ===================================================================
    private File file;

    // コンストラクタ===========================================================
    /**
     * ファイル情報のインスタンスを生成する
     *
     * @param file ファイル
     */
    public FileInformationFrame(File file) {
        this.file = file;
    }

    // メソッド=================================================================
    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.application.FileInformation#getLastModified()
     */
    public Date getLastModified() {
        return new Date(this.file.lastModified());
    }

    /*
     * (非 Javadoc)
     * @see jp.gr.norinori.application.FileInformation#setLastModified(java.util.Date)
     */
    public boolean setLastModified(Date date) {
        return this.file.setLastModified(date.getTime());
    }

}
