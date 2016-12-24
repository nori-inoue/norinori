package jp.gr.norinori.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jp.gr.norinori.application.ApplicationFileFrame;
import jp.gr.norinori.define.Encoding;
import jp.gr.norinori.text.TextFile;

/**
 * テキストファイルフレーム
 *
 * @author nori
 */
public abstract class TextFileFrame extends ApplicationFileFrame implements TextFile {

	// メンバ===================================================================
	private BufferedReader reader = null;
	private BufferedWriter writer = null;

	// コンストラクタ===========================================================
	/**
	 * テキストファイルのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 */
	public TextFileFrame(String fileName) {
		this(new File(fileName));
	}

	/**
	 * テキストファイルのインスタンスを生成する
	 *
	 * @param textFile テキストファイル
	 */
	public TextFileFrame(File textFile) {
		super(textFile);
		setEncoding(Encoding.UTF8);
	}

	// メソッド=================================================================

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#create()
	 */
	public void create() throws Exception {
		create(getFile());
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#create(java.lang.Object)
	 */
	public <T> void create(T target) throws Exception {
		File file = null;
		if (target instanceof File) {
			file = (File) target;
		} else if (target instanceof String) {
			file = new File((String) target);
		} else {
			throw new IllegalArgumentException();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		FileInputStream inputStream = new FileInputStream(file);
		FileOutputStream outputStream = new FileOutputStream(file, true);

		this.reader = new BufferedReader(new InputStreamReader(inputStream, getEncoding().getCode()));

		this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, getEncoding().getCode()));
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#isLiving()
	 */
	public boolean isLiving() throws Exception {
		return (this.reader != null ? true : false);
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.core.flow.LifeCycle#destroy()
	 */
	public void destroy() throws Exception {
		this.reader.close();
		this.writer.close();
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.text.TextFile#getContents()
	 */
	public String getContents() {
		StringBuilder sb = new StringBuilder();
		try {
			String contents;
			while ((contents = this.reader.readLine()) != null) {
				sb.append(contents).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.text.TextFile#append()
	 */
	public void append(String text) {
		try {
			PrintWriter pw = new PrintWriter(this.writer);
			pw.print(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (非 Javadoc)
	 * @see jp.gr.norinori.text.TextFile#flush()
	 */
	public void flush() {
		try {
			this.writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
