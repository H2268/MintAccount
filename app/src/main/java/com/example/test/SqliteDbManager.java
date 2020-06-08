package com.example.test;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SqliteDbManager {
    private static SqliteDbManager mInstance = null;
    private SQLiteDatabase mDb = null;
    private SqliteDbHelper mDbHelper = null;

    public static SqliteDbManager getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new SqliteDbManager();
        }
        return mInstance;
    }
    public SQLiteDatabase setSqliteDbOpen(Context context)
    {
        mDbHelper = new SqliteDbHelper(context.getApplicationContext());
        mDb = mDbHelper.getWritableDatabase();
        System.out.println("可写的数据库打开成功");
        return mDb;
    }
    public void insert2(String name,String password){
        openDb();

        String sql = "insert into users(name,password) values(?,?)";
        SQLiteStatement stat = mDb.compileStatement(sql);
        mDb.beginTransaction();
        stat.bindString(1, name);
        stat.bindString(2, password);
        stat.executeInsert();

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        closeDb();
    }


    public void insert(String state,String type,String date,String fee,String remark){
        openDb();

        String sql = "insert into record(state,type,date,fee,remark) values(?,?,?,?,?)";
        SQLiteStatement stat = mDb.compileStatement(sql);

        mDb.beginTransaction();

        stat.bindString(1, state);
        stat.bindString(2, type);
        stat.bindString(3, date);
        stat.bindString(4, fee);
        stat.bindString(5, remark);
        stat.executeInsert();

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        closeDb();
        System.out.println("数据写入成功");
    }
        public void update(){
            System.out.println("update方法执行成功");
            openDb();
            String createTbStr2="create table if not exists "+"users"+"( _id integer primary key, name varchar,password varchar)";
            mDb.execSQL(createTbStr2);
           // System.out.println("user表创建成功");
            closeDb();
        }

    public void deleteTb(String tbName)
    {
        openDb();
        //方法一：
        //mDb.delete(tbName,"name=?",new String[]{"zhangsan"});
        //方法二：
        mDb.execSQL("delete from "+tbName+" where name = '李四'");
        //closeDb();
    }

    public void updateTb(String oldName,String newName,String password)
    {
        openDb();
        //方法一：
        System.out.println("修改方法正在执行");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",newName);
        contentValues.put("password",password);
        mDb.update("users",contentValues,"name=?",new String[]{oldName});
        //方法二：

        //String sql="UPDATE CUSTOMER SET name = '%s',password='123' WHERE name= %s" %();

        //mDb.execSQL("update users"+" set name = ?,password = ? where name = ?");
        closeDb();
    }



    public Cursor query(){
        openDb();
        Cursor rawQuery = mDb.rawQuery("select state,type,date,fee,remark from record",null);
        //System.out.println("查询成功");
        return rawQuery;
    }
    public Cursor query2(){
        openDb();
        Cursor rawQuery = mDb.rawQuery("select name,password from users",null);
        System.out.println("查询users成功");
        return rawQuery;
    }
    public Cursor selectpass(String id) {
        openDb();
        Cursor rawQuery = mDb.rawQuery("select name,password from users where _id="+ id, null);

            return rawQuery;
    }





    private void openDb() {
        if (this.mDbHelper != null) {
            try {
                mDb = mDbHelper.getWritableDatabase();
            } catch (Exception e) {
                mDb = mDbHelper.getReadableDatabase();
                e.printStackTrace();
            }
        }
    }

    /**
     *  关闭数据库
     */
    private void closeDb() {
        try {
            if (mDb != null) {
                mDb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





















}
