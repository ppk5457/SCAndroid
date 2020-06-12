package cn.edu.sc.train;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity2 extends AppCompatActivity {
    private Button btnRegister;
    private EditText edtUsername,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnRegister=findViewById(R.id.btnRegister);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=edtUsername.getText().toString();
                String password=edtPassword.getText().toString();

                SharedPreferences sp=getSharedPreferences("message",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit().putString("username",username);
                editor.putString("password",password);
                editor.commit();
                try {
                    FileOutputStream os=openFileOutput("msg.txt",MODE_APPEND);
                    os.write(username.getBytes());
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File path=Environment.getRootDirectory();
                File output=new File(path,"output.txt");
                FileOutputStream os= null;
                try {
                    os = new FileOutputStream(output);
                    os.write(username.getBytes());
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                Intent intent=getIntent();
//                intent.putExtra("username",username);
//                intent.putExtra("password",password);
//                setResult(0x200,intent);
//                finish();
            }
        });


    }
}