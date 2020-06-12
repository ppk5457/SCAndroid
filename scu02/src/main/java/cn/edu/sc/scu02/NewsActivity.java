package cn.edu.sc.scu02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {
    private TextView txtTitle,txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        txtDetail=findViewById(R.id.txtDetail);
        txtTitle=findViewById(R.id.txtTitle);
        DBUtil dbUtil=new DBUtil(getApplicationContext(),"news.db",null,1);
        SQLiteDatabase db=dbUtil.getWritableDatabase();
        String query="select * from news";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToNext()){
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String detail=cursor.getString(cursor.getColumnIndex("content"));
            txtTitle.setText(title);;
            txtDetail.setText(detail);
        }
    }
}