package jp.gr.norinori.application;

import java.io.File;

import jp.gr.norinori.application.ApplicationFileFrame;
import jp.gr.norinori.core.file.FileInformation;

public class SimpleApplicationFile extends ApplicationFileFrame {
	// メンバ===================================================================
	private boolean isLiving = false;

	// コンストラクタ===========================================================
	public SimpleApplicationFile(String fileName) {
		super(fileName);
	}

	public SimpleApplicationFile(File file) {
		super(file);
	}

	// メソッド=================================================================
	public void create() throws Exception {
		this.isLiving = true;
	}

	public <T> void create(T target) throws Exception {
		this.isLiving = true;
	}

	public boolean isLiving() throws Exception {
		return this.isLiving;
	}

	public void destroy() throws Exception {
		this.isLiving = false;
	}

	protected FileInformation createFileInformation() {
		return new SimpleFileInformation(getFile());
	}

}
