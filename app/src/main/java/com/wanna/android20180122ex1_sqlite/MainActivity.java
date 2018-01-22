package com.wanna.android20180122ex1_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //將db搬進android裡
    //需先於res下新建一個raw資料夾android resource directory
    public void click1(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        InputStream is = getResources().openRawResource(R.raw.school);
        try {
            OutputStream os = new FileOutputStream(dbFile);
            int r;
            while((r = is.read()) != -1)
            {
                os.write(r);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click2(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        //Cursor游標
        Cursor c = db.rawQuery("Select * from students",null);
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getInt(2));
        while(c.moveToNext())
        {
            Log.d("DB",c.getString(1)+","+c.getInt(2));
        }

    }
    public void click3(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        String strSql ="Select * from students where _id=?";
        Cursor c = db.rawQuery(strSql,new String[]{"2"});
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getInt(2));
    }
    //抓取全部資訊
    public void click4(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        Cursor c = db.query("students",new String[]{"_id","name","score"},null,null,null,null,null);
        c.moveToFirst();//第一筆
        Log.d("DB",c.getString(1)+","+c.getInt(2));
    }
    public void click5(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        Cursor c = db.query("students",new String[]{"_id","name","score"},"_id=?",new String[]{"2"},null,null,null);
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getInt(2));
    }

    public void click6(View v)
    {
        File dbFile = new File(getFilesDir(), "school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("Insert into students (_id, name, score) values (3, 'Ruby', 95)");
        db.close();
    }
    public void click7(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        ContentValues cv = new ContentValues();
        cv.put("_id",4);
        cv.put("name","Kay");
        cv.put("score",95);
        db.insert("students",null,cv);
        db.close();
    }
    public void click8(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        ContentValues cv = new ContentValues();
        cv.put("score",50);
        db.update("students",cv,"_id=?",new String[]{"2"});
        db.close();
    }
    public void click9(View v)
    {
        File dbFile = new File(getFilesDir(),"school.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);//OPEN_READWRITE讀跟寫
        db.delete("students","_id=?",new String[]{"4"});
        db.close();
    }

}
