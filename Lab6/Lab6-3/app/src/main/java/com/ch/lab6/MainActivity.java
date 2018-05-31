package com.ch.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private EditText editName, editId;
    private ArrayList<String> mData;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize Views */
        editName = (EditText) findViewById(R.id.edit_name);
        editId = (EditText) findViewById(R.id.edit_id);
        ((Button) findViewById(R.id.btn_add)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_del)).setOnClickListener(this);

        /* Set ArrayAdapter for ListView using ArrayList */
        mData = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(mAdapter);

        helper = new DatabaseHelper(this);

        select();
    }

    private void reset() {
        editName.setText("");
        editId.setText("");
        editName.setFocusable(true);
    }

    private void insert() {
        /* Before Insertion, Searching Database to find existing name. */
        db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);

        while (c.moveToNext()) {
            if (c.getString(c.getColumnIndex("name")).equals(editName.getText().toString())) {
                Toast.makeText(getApplicationContext(), "데이터베이스에 이미 존재하는 이름입니다.", Toast.LENGTH_SHORT).show();
                c.close();
                reset();
                return;
            }
        }

        c.close();

        /* No existing name in database, Insert new data. */
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", editName.getText().toString());
        values.put("_id", editId.getText().toString());

        db.insert("student", null, values);
        Toast.makeText(getApplicationContext(), "데이터베이스에 저장되었습니다.", Toast.LENGTH_SHORT).show();

        select();
    }

    private void delete() {
        String toDelete = editName.getText().toString();

        /* Before Deletion, Searching Database to find existing name. */
        db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);

        while (c.moveToNext()) {
            if (c.getString(c.getColumnIndex("name")).equals(toDelete)) {
                /* Same name exists in Database, delete value */
                c.close();
                db = helper.getWritableDatabase();
                db.delete("student", "name=?", new String[]{toDelete});
                Toast.makeText(getApplicationContext(), "데이터베이스에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                select();
                return;
            }
        }

        Toast.makeText(getApplicationContext(), "데이터베이스에 존재하지 않는 이름입니다.", Toast.LENGTH_SHORT).show();
        reset();
    }

    private void select() {
        db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);

        mData.clear();

        while (c.moveToNext()) {
            String value = c.getString(c.getColumnIndex("name")) + "     " + c.getString(c.getColumnIndex("_id"));
            mData.add(value);
        }

        c.close();

        // Notify to adapter.
        mAdapter.notifyDataSetChanged();
        reset();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (!TextUtils.isEmpty(editId.getText().toString()) && !TextUtils.isEmpty(editName.getText().toString())) {
                    insert();
                } else {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_del:
                if (!TextUtils.isEmpty(editName.getText().toString())) {
                    delete();
                } else {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    class DatabaseHelper extends SQLiteOpenHelper {
        static final String KEY_NAME = "name";
        static final String KEY_ID = "_id";
        static final String DATABASE_NAME = "MyDB";
        static final String DATABASE_TABLE = "student";
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "(" + KEY_NAME + " text primary key not null, " + KEY_ID + " text not null)";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
}