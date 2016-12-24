package jp.gr.norinori.application;

import java.io.File;
import java.net.URI;

import jp.gr.norinori.application.ApplicationFile;
import jp.gr.norinori.core.file.FileInformation;
import jp.gr.norinori.define.Encoding;

/**
 * アプリケーションファイルフレーム
 *
 * @author nori
 */
public abstract class ApplicationFileFrame implements ApplicationFile {

	// メンバ===================================================================
	private File file;
	private Encoding encoding;
	private FileInformation fileInformation;

	// コンストラクタ===========================================================
	/**
	 * アプリケーションファイルのインスタンスを生成する
	 *
	 * @param fileName ファイル名
	 */
	public ApplicationFileFrame(String fileName) {
		this(new File(fileName));
	}

	/**
	 * アプリケーションファイルのインスタンスを生成する
	 *
	 * @param file ファイル
	 */
	public ApplicationFileFrame(File file) {
		this.file = file;
		this.fileInformation = createFileInformation();
	}

	// 抽象メソッド=============================================================
	/**
	 * ファイル情報を生成する
	 *
	 * @return ファイル情報
	 */
	protected abstract FileInformation createFileInformation();

	// メソッド=================================================================
	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#getFile()
	 */
	public File getFile() {
		return this.file;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#getFileInformation()
	 */
	public FileInformation getFileInformation() {
		return this.fileInformation;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#getName()
	 */
	public String getName() {
		return this.file.getName();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#getParent()
	 */
	public String getParent() {
		return this.file.getParent();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#getPath()
	 */
	public String getPath() {
		return this.file.getPath();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#toURI()
	 */
	public URI toURI() {
		return this.file.toURI();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#setCanRead(boolean)
	 */
	public void setReadable(boolean canRead) {
		this.file.setReadable(canRead);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#canRead()
	 */
	public boolean canRead() {
		return this.file.canRead();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#setCanWrite(boolean)
	 */
	public void setWritable(boolean canWrite) {
		this.file.setWritable(canWrite);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#canWrite()
	 */
	public boolean canWrite() {
		return this.file.canWrite();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#setExecutable(boolean)
	 */
	public boolean setExecutable(boolean canExecute) {
		return this.file.setExecutable(canExecute);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#canExecute()
	 */
	public boolean canExecute() {
		return this.file.canExecute();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#exists()
	 */
	public boolean exists() {
		return this.file.exists();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#delete()
	 */
	public boolean delete() {
		return this.file.delete();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * jp.gr.norinori.application.ApplicationFile#renameTo(java.lang.String)
	 */
	public boolean renameTo(String fileName) {
		return this.file.renameTo(new File(fileName));
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * jp.gr.norinori.application.ApplicationFile#setEncoding(jp.gr.norinori
	 * .define.Encoding)
	 */
	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see jp.gr.norinori.application.ApplicationFile#getEncoding()
	 */
	public Encoding getEncoding() {
		return this.encoding;
	}
}
