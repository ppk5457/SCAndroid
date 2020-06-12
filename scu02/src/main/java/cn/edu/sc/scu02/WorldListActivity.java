package cn.edu.sc.scu02;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class WorldListActivity extends AppCompatActivity {
    private Button btnAdd,btnSearch;
    private EditText edtWord,edtMean;
    private ListView listView;
    private WordObsever wordObserver;

    private ContentResolver contentResolver;
    private SimpleCursorAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Uri uri=Uri.parse("content://cn.edu.sc.scu.wordsprovider/words");
                Cursor cursor=contentResolver.query(uri,new String[]{"_id","word","mean"},null,null,null);
                adapter=new SimpleCursorAdapter(WorldListActivity.this,R.layout.item_word,cursor,new String[]{"word","mean"},new int[]{R.id.txtWord,R.id.txtMean});
                listView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        //contentResolver.unregisterContentObserver(wordObserver);
    }

    @Override
    protected void onDestroy() {
        contentResolver.unregisterContentObserver(wordObserver);
        super.onDestroy();
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String word=((TextView)view.findViewById(R.id.txtWord)).getText().toString();
                Toast.makeText(WorldListActivity.this,"word:"+word,Toast.LENGTH_LONG).show();
                Uri uri1=Uri.parse("content://cn.edu.sc.scu.wordsprovider/word/"+word);
                contentResolver.delete(uri1,null,null);
                Uri uri2=Uri.parse("content://cn.edu.sc.scu.wordsprovider/words");
                Cursor cursor=contentResolver.query(uri2,new String[]{"_id","word","mean"},null,null,null);
                adapter=new SimpleCursorAdapter(WorldListActivity.this,R.layout.item_word,cursor,new String[]{"word","mean"},new int[]{R.id.txtWord,R.id.txtMean});
                listView.setAdapter(adapter);
                return false;
            }
        });
        contentResolver=getContentResolver();
        wordObserver=new WordObsever(handler,getApplicationContext());
        Uri uri1=Uri.parse("content://cn.edu.sc.scu.wordsprovider/words");
        contentResolver.registerContentObserver(uri1,false,wordObserver);
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

                }

            }
        });
    }
}