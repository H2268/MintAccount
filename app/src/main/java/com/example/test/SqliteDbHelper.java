package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteDbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "count.db";
    public static final String TABLE_NAME = "record";
    public static final String TABLE_NAME2 = "users";



    public SqliteDbHelper(Context context)
    {
        super(context,DB_NAME, null, DB_VERSION);
        //System.out.println("在构造方法中");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //当数据库第一次被建立的时候被执行，一般把创建表,初始化数据等操作放在这里
        String createTbStr="create table if not exists "+TABLE_NAME+"( _id integer primary key, state varchar,type varchar, date integer,fee integer, remark varchar)";
        db.execSQL(createTbStr);
        System.out.println("数据库被创建了,此时还不可写");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //可重新建表
        //也可在表中新增列元素， 使用 SQL的ALTER语句

    }
}
