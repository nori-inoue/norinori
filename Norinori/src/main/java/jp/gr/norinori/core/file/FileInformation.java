package jp.gr.norinori.core.file;

import java.util.Date;

/**
 * ファイル情報
 *
 * @author nori
 */
public interface FileInformation {

    /**
     * 最終更新日時を取得する
     *
     * @return 最終更新日時
     */
    public Date getLastModified();

    /**
     * 最終更新日時を設定する
     *
     * @param date 最終更新日時
     * @return true:設定に成功、false:失敗
     */
    public boolean setLastModified(Date date);
}
