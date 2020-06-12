package cn.edu.sc.scu_contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class WorldListActivity extends AppCompatActivity {
    private Button btnAdd,btnSearch;
    private EditText edtWord,edtMean;
    private ListView listView;

    private ContentResolver contentResolver;
    private SimpleCursorAdapter adapter;


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_list);
        btnAdd=findViewById(R.id.btnAdd);
        btnSearch=findViewById(R.id.btnSearch);
        edtWord=findViewById(R.id.edtWord);
        edtMean=findViewById(R.id.edtMean);
        listView=findViewById(R.id.listWords);
        contentResolver=getContentResolver();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=edtWord.getText().toString();
                Uri uri=null;
                Cursor cursor=null;
                if(TextUtils.isEmpty(word)){
                    uri=Uri.parse("content://cn.edu.sc.scu.wordsprovider/words");
                    cursor=contentResolver.query(uri,new String[]{"_id","word","mean"},null,null,null);
                }else{
                    uri=Uri.withAppendedPath(Uri.parse("content://cn.edu.sc.scu.wordsprovider"),"/word/"+word);
                    cursor=contentResolver.query(uri,new String[]{"_id","word","mean"},null,null,null);
                }
                adapter=new SimpleCursorAdapter(WorldListActivity.this,R.layout.item_word,cursor,new String[]{"word","mean"},new int[]{R.id.txtWord,R.id.txtMean});
                listView.setAdapter(adapter);

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=edtWord.getText().toString();
                String mean=edtMean.getText().toString();
                if(TextUtils.isEmpty(word)){
                    edtWord.setError("单词不能为空！");
                }else{
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("word",word);
                    contentValues.put("mean",mean);
                    Uri uri=Uri.parse("content://cn.edu.sc.scu.wordsprovider/words");
                    Uri newwordUri=contentResolver.insert(uri,contentValues);
                    Log.i("uri",newwordUri.toString());
                }

            }
        });
    }
}