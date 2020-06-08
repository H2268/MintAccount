package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    List<Map<String,String>> users=new ArrayList<Map<String,String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


    }
    public void login(View view) {
        EditText t1 = findViewById(R.id.name);
        EditText t2 = findViewById(R.id.passWord);
        String name = t1.getText().toString();
        String password = t2.getText().toString();

        SqliteDbManager sqliteDbManager = SqliteDbManager.getInstance();
        sqliteDbManager.setSqliteDbOpen(this);
        Cursor rawQuery2 = sqliteDbManager.query2();
        if (null != rawQuery2) {
            while (rawQuery2.moveToNext()) {
                String username = rawQuery2.getString(rawQuery2.getColumnIndex("name"));
                String userpass = rawQuery2.getString(rawQuery2.getColumnIndex("password"));
                Map<String, String> map = new HashMap<String, String>();
                map.put(username, userpass);
                users.add(map);
            }
        }


        boolean flg = false;
        for (Map m : users) {
            if (m.containsKey(name)) {
                flg = true;
                if (m.get(name).equals(password)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("id",users.indexOf(m)+1+"");
                    intent.putExtra("name",name);
                    intent.putExtra("pass",password);

                    startActivity(intent);
                } else {
                    Display display = getWindowManager().getDefaultDisplay();
                    // 获取屏幕高度
                    int height = display.getHeight();
                    Toast toast = Toast.makeText(this, "密码错误", Toast.LENGTH_LONG);
                    // 这里给了一个1/4屏幕高度的y轴偏移量
                    toast.setGravity(Gravity.TOP, 0, height / 4);
                    toast.show();
                }
            }
        }
            if (!flg) {
                Display display = getWindowManager().getDefaultDisplay();
                // 获取屏幕高度
                int height = display.getHeight();
                Toast toast = Toast.makeText(this, "用户名不存在", Toast.LENGTH_LONG);
                //这里给了一个1/4屏幕高度的y轴偏移量
                toast.setGravity(Gravity.TOP, 0, height / 4);
                toast.show();

            }

        }



        public void cancel(View view){
        EditText t1=findViewById(R.id.name);
        EditText t2=findViewById(R.id.passWord);
        t1.setText("");
        t2.setText("");
           }
    public void register(View view){
        Intent intent=new Intent(this, registerActivity.class);
        startActivity(intent);
    }

}
