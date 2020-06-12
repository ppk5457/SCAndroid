package cn.edu.sc.scu02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button,button2,button3,button4;
    private static final int OPEN_REQUEST_CODE=0x100;
    private static final int CREATE_REQUEST_CODE=0x200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.txtView);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.btnOldeFileOperate);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUtil dbUtil=new DBUtil(getApplicationContext(),"news.db",null,1);
                SQLiteDatabase db=dbUtil.getWritableDatabase();
                String insertStr="insert into news(title,content) values(?,?)";
                db.execSQL(insertStr,new String[]{"川大头条","四川大学春季实训于6月2日全面开始"});
                db.close();
                Intent intent=new Intent(MainActivity.this,NewsActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=getExternalFilesDir("");
                try {
                    FileOutputStream fileOutputStream=new FileOutputStream(new File(file,"external.txt"));
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    bw.write("Hello SCU!");
                    bw.newLine();
                    bw.write("Hi Young Man!");
                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/plain");
                //intent.putExtra(Intent.EXTRA_TITLE,"good.txt");
                startActivityForResult(intent,OPEN_REQUEST_CODE);

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE,"good.txt");
                startActivityForResult(intent,CREATE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("uri",""+resultCode);
        if(CREATE_REQUEST_CODE==requestCode&&resultCode== Activity.RESULT_OK){
            Uri uri=null;
            uri=data.getData();
            try {
                OutputStream fos=getContentResolver().openOutputStream(uri);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                bw.write("好好学习天天向上！22");
                bw.newLine();
                bw.write("Hello world  hellolll  how time fley!");
                bw.close();
                Log.i("uri","bw.close()");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(OPEN_REQUEST_CODE==requestCode&&resultCode== Activity.RESULT_OK){
            Uri uri=null;
            uri=data.getData();
            try {
                String msg=readTextFromUri(uri);
                textView.setText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream =
                getContentResolver().openInputStream(uri);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}
