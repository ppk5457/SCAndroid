package cn.edu.sc.scu_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DatabaseActivity extends AppCompatActivity {
    private Button btnSave,btnGet;
    private EditText edtMessage;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet=findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper=new DBHelper(DatabaseActivity.this,"news.db",null,1);
                SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
                String selectSQL="select * from news";
                Cursor cursor=sqLiteDatabase.rawQuery(selectSQL,null);
                while(cursor.moveToNext()){
                    String title =cursor.getString(cursor.getColumnIndex("title"));
                    String detail=cursor.getString(cursor.getColumnIndex("detail"));
                    edtMessage.setText(title);
                }
                sqLiteDatabase.close();
            }
        });

        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dbHelper=new DBHelper(DatabaseActivity.this,"news.db",null,1);
                SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
                String insertStr="insert into news(title,detail) values(?,?)";
                String title=edtMessage.getText().toString();
                String detail=edtMessage.getText().toString();
                sqLiteDatabase.execSQL(insertStr,new String[]{title,detail});
                sqLiteDatabase.close();
                edtMessage.setText("");
            }
        });
        edtMessage=findViewById(R.id.edtMessage);

    }
}