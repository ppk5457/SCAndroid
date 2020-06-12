package cn.edu.sc.train;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndexActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        textView=findViewById(R.id.txtMsg);
        try {
            FileInputStream fis=openFileInput("login.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String username=br.readLine();
            fis.close();
            textView.setText("欢迎您："+username);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}