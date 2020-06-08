package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.activity_register, null);
        final EditText e1 = tableLayout.findViewById(R.id.name);
        final EditText e2 = tableLayout.findViewById(R.id.pass);
        new AlertDialog.Builder(this)
                .setTitle("注册账号")
                .setView(tableLayout)
                .setPositiveButton("确认",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        //保存数据到数据库
                        //System.out.println("我在保存方法中"+e1.getText().toString());
                        SqliteDbManager sqliteDbManager = SqliteDbManager.getInstance();
                        sqliteDbManager.setSqliteDbOpen(getApplicationContext());
                        List<String> list=new ArrayList<String>();
                        Cursor rawQuery2 = sqliteDbManager.query2();
                        if (null != rawQuery2) {
                            while (rawQuery2.moveToNext()) {
                                String username = rawQuery2.getString(rawQuery2.getColumnIndex("name"));
                                list.add(username);
                            }
                        }
                        if(list.contains(e1.getText().toString())){
                            Display display = getWindowManager().getDefaultDisplay();
                            // 获取屏幕高度
                            int height = display.getHeight();
                            Toast toast = Toast.makeText(getApplication(), "用户名已存在", Toast.LENGTH_LONG);
                            //这里给了一个1/4屏幕高度的y轴偏移量
                            toast.setGravity(Gravity.TOP, 0, height / 4);
                            toast.show();
                        }
                        else
                            sqliteDbManager.insert2(e1.getText().toString(),e2.getText().toString());
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create()
                .show();

    }
}