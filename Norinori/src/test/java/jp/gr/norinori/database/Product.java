package jp.gr.norinori.database;

import java.sql.Timestamp;

import org.junit.Ignore;

/**
 * 商品（テスト用）
 *
 * @author inoue
 *
 */
@Ignore
public class Product {
	/** 商品番号 */
	public int productid;

	/** 商品名 */
	public String name;

	/** カテゴリ番号 */
	public int categoryid;

	/** 画像URL */
	public String imageurl;

	/** ポイント */
	public int point;

	/** 仕様 */
	public String features;

	/** 備考 */
	public String description;

	/** 作成日時 */
	public Timestamp createtime;

	/** 作成ユーザ */
	public int createuser;

	/** 作成IP */
	public String createip;

	/** 作成アプリ */
	public String createapplication;

	/** 更新日時 */
	public Timestamp modifytime;

	/** 更新ユーザ */
	public int modifyuser;

	/** 更新IP */
	public String modifyip;

	/** 更新アプリ */
	public String modifyapplication;

}
