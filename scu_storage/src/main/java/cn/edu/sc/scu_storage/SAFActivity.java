package cn.edu.sc.scu_storage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SAFActivity extends AppCompatActivity {
    private Button btnGet,btnSave;
    private EditText editMessage;
    private final int  READ_REQUEST_CODE=0x100;
    private final int CREATE_REQUEST_CODE=0x200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMessage=findViewById(R.id.edtMessage);
        btnGet=findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                //MIME
                intent.setType("text/plain");
                startActivityForResult(intent,READ_REQUEST_CODE);
            }
        });
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE,"scu.txt");
                startActivityForResult(intent,CREATE_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(READ_REQUEST_CODE==requestCode&&resultCode== Activity.RESULT_OK){
            Uri uri=null;
            uri=data.getData();
            //ContentResolver
            try {
                InputStream is=getContentResolver().openInputStream(uri);
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                StringBuilder stringBuilder=new StringBuilder();
                while ((line=br.readLine())!=null){
                    stringBuilder.append(line);
                }
                Log.i("SAF","----SAF----"+stringBuilder.toString());
                editMessage.setText(stringBuilder.toString());
                br.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(CREATE_REQUEST_CODE==requestCode&&resultCode== Activity.RESULT_OK){
            Uri uri=data.getData();
            try {
                OutputStream os=getContentResolver().openOutputStream(uri);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
                bw.write("Hi SCU Youn Man");
                bw.newLine();
                bw.write("How time fly");
                bw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}