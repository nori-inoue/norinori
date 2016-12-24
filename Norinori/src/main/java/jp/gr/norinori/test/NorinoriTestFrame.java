package jp.gr.norinori.test;

import java.io.File;
import java.io.IOException;

/**
 * テストフレーム
 *
 * @author nori
 */
public abstract class NorinoriTestFrame {

    // 一時ディレクトリ作成
    protected void createDirectory(String directoryName) throws IOException {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
