package cn.edu.sc.scu_storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editMessage;
    private Button btnSave,btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMessage=findViewById(R.id.edtMessage);
        btnGet=findViewById(R.id.btnGet);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=23){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x100);
                    }else{
                       saveDate("file.txt");
                    }
                }else{
                    saveDate("file.txt");
                }

            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=23){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x200);
                    }else{
                        getDate("file.txt");
                    }
                }else{
                    getDate("file.txt");
                }
            }
        });



    }

    private void getDate(String fileName) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File dir=Environment.getExternalStorageDirectory();
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File newfile=new File(dir+File.separator+fileName);
            try {
                FileInputStream fis=new FileInputStream(newfile);
                BufferedReader br=new BufferedReader(new InputStreamReader(fis));
                editMessage.setText(br.readLine());
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            switch (requestCode){
                case 0x100:
                    saveDate("file.txt");
                    break;
                case 0x200:
                    getDate("file.txt");
                    break;
            }

        }
     }

    public void saveDate(String fileName){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File dir=Environment.getExternalStorageDirectory();
            File newfile=new File(dir+File.separator+fileName);
            try {
                FileOutputStream fos=new FileOutputStream(newfile);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(editMessage.getText().toString());
                bw.close();
                editMessage.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
