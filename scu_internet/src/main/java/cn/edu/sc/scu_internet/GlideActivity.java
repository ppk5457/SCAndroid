package cn.edu.sc.scu_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageView=findViewById(R.id.imageView2);
        Glide.with(GlideActivity.this).load("http://img.jj20.com/up/allimg/tx20/580411014410670.jpg").into(imageView);
    }
}