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
import android.widget.TableLayout;
import android.widget.Toast;

public class noteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TableLayout tableLayout=(TableLayout) getLayoutInflater().inflate(R.layout.note,null);
        final EditText e1=tableLayout.findViewById(R.id.name);
        final EditText e2=tableLayout.findViewById(R.id.oldpass);
        final EditText e3=tableLayout.findViewById(R.id.newpass);
        final EditText e4=tableLayout.findViewById(R.id.conpass);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");
        new AlertDialog.Builder(this)
                .setTitle("修改信息")
                .setView(tableLayout)
                .setPositiveButton("确认",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog,int which) {
                //传值到setFragment
                SqliteDbManager sqliteDbManager=SqliteDbManager.getInstance();
                sqliteDbManager.setSqliteDbOpen(getApplicationContext());
                Cursor rawQuery2 = sqliteDbManager.selectpass(id);
                if (null != rawQuery2) {
                    while (rawQuery2.moveToNext()) {
                        String oldname = rawQuery2.getString(rawQuery2.getColumnIndex("name"));
                        String password = rawQuery2.getString(rawQuery2.getColumnIndex("password"));
                       // System.out.println("*************"+"e2:"+e2.getText().toString()+"e3:"+e3.getText().toString()+"e4:"+e4.getText().toString()+"pass:"+password);

                        if(e2.getText().toString().equals(password)){
                            if(e3.getText().toString().equals(e4.getText().toString())) {
                                //此处password已经改变，再次对比则会失败


                                sqliteDbManager.updateTb(oldname, e1.getText().toString(), e3.getText().toString());


                                Display display = getWindowManager().getDefaultDisplay();
                                // 获取屏幕高度
                                int height = display.getHeight();
                                Toast toast = Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG);
                                //这里给了一个1/4屏幕高度的y轴偏移量
                                toast.setGravity(Gravity.TOP, 0, height / 4);
                                toast.show();
                            }
                            else {
                                Display display = getWindowManager().getDefaultDisplay();
                                // 获取屏幕高度
                                int height = display.getHeight();
                                Toast toast = Toast.makeText(getApplicationContext(), "两次输入密码不一致，修改失败", Toast.LENGTH_LONG);
                                //这里给了一个1/4屏幕高度的y轴偏移量
                                toast.setGravity(Gravity.TOP, 0, height / 4);
                                toast.show();
                            }
                        }

                        else  {
                            Display display = getWindowManager().getDefaultDisplay();
                            // 获取屏幕高度
                            int height = display.getHeight();
                            Toast toast = Toast.makeText(getApplicationContext(), "原密码错误，修改失败", Toast.LENGTH_LONG);
                            //这里给了一个1/4屏幕高度的y轴偏移量
                            toast.setGravity(Gravity.TOP, 0, height / 4);
                            toast.show();
                        }



                    }
                }

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                String name=e1.getText().toString();
                intent.putExtra("name",name);
                String number=e2.getText().toString();
                intent.putExtra("number",number);
                intent.putExtra("phone",e3.getText().toString());
                intent.putExtra("email",e4.getText().toString());
                setResult(RESULT_OK, intent);
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
