package com.example.johnny.databasetest.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.johnny.databasetest.R;
import com.example.johnny.databasetest.adapter.BookAdapter;
import com.example.johnny.databasetest.db.MyDatabaseHelper;
import com.example.johnny.databasetest.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2016/8/15.
 */

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private Button btnCreateDatabase,btnAddData,btnUpdateData,btnDeleteData,btnQueryData;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(MainActivity.this,"BookStore.db",null,1);

        //创建表
        btnCreateDatabase = (Button) findViewById(R.id.btn_create_database);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });

        //添加数据
        btnAddData = (Button) findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                //第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
                values.clear();
                Toast.makeText(MainActivity.this,"insert succeed",Toast.LENGTH_SHORT).show();
            }
        });

        //修改数据
        btnUpdateData = (Button) findViewById(R.id.btn_update_data);
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"});
                Toast.makeText(MainActivity.this,"update succeed",Toast.LENGTH_SHORT).show();
            }
        });

        //删除数据
        btnDeleteData = (Button) findViewById(R.id.btn_delete_data);
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","pages > ?",new String[]{"500"});
                Toast.makeText(MainActivity.this,"delete succeed",Toast.LENGTH_SHORT).show();
            }
        });

        //查询数据
        btnQueryData = (Button) findViewById(R.id.btn_query_data);
        btnQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookList.clear();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Book表中的所有数据
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        //遍历cursor对象，取出数据并打印
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Book book = new Book(id,name,author,pages,price);
                        bookList.add(book);
                    }while (cursor.moveToNext());
                }else {
                    Toast.makeText(MainActivity.this,"暂无数据",Toast.LENGTH_SHORT).show();
                }
                BookAdapter adapter = new BookAdapter(MainActivity.this,R.layout.book_item,bookList);
                ListView lvBook = (ListView) findViewById(R.id.lv_results);
                lvBook.setAdapter(adapter);
                cursor.close();
            }
        });
    }
}
