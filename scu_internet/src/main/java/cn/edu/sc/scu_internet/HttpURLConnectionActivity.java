package cn.edu.sc.scu_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionActivity extends AppCompatActivity {
    private Button btnload;
    private EditText edtUrl;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_u_r_l_connection);
        btnload=findViewById(R.id.btnLoad);
        edtUrl=findViewById(R.id.editURL);
        imageView=findViewById(R.id.imageView);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final String urlStr=edtUrl.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url=new URL("http://img.jj20.com/up/allimg/tx20/580411014410670.jpg");
                                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                                connection.setConnectTimeout(50000);
                                InputStream inputStream=connection.getInputStream();
                                final Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                                imageView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageView.setImageBitmap(bitmap);
                                    }
                                });
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

        });
    }

}