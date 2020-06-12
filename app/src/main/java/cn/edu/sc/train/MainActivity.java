package cn.edu.sc.train;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity{
    private static final String TAG="MainActivity";
    private EditText edtUsername,edtPassword;
    private Button btnRegister,btnLogin;
    private TextView textView;
    private static final int CREATE_FILE=1;
    private void createFile(Uri pickerInitialUri) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_TITLE, "invoice.pdf");

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when your app creates the document.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPassword=findViewById(R.id.edtPassword);
        edtUsername=findViewById(R.id.edtUsername);
        btnRegister=findViewById(R.id.btnRegister);
        textView=findViewById(R.id.textView);
      

        btnRegister.setText(android.R.string.paste);

        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtUsername.getText().toString();
                String password=edtPassword.getText().toString();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
//                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
//                    SharedPreferences.Editor editor=preferences.edit();
//                    editor.putString("username",name);
//                    editor.putString("password",password);
//                    editor.commit();
//
//                    SharedPreferences sp1=getPreferences(MODE_PRIVATE);
//                    SharedPreferences.Editor editor1=sp1.edit();
//                    editor1.putBoolean("flag",true);
//                    editor1.commit();
//                    try {
//                        FileOutputStream fos=openFileOutput("login.txt",MODE_APPEND);
//                        fos.write(name.getBytes());
//                        fos.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }


                       createFile(Uri.fromFile(new File("good.txt")));



                    Intent intent=new Intent(MainActivity.this,IndexActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent,0x100);

            }
        });

        Log.i(TAG,"----onCreate() is running----");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
        String username=data.getStringExtra("username");
        String password=data.getStringExtra("password");
        edtUsername.setText(username);
        edtPassword.setText(""+2);}

    }

}