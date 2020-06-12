package cn.edu.sc.scu_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private TextView txtProgress;
    private ProgressBar progressBar;
    private Button btnStart;
    private int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        txtProgress=findViewById(R.id.txtProgress);
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtProgress.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                Thread loadThread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                       while(progress<100){
                           progress=progress+10;
                           progressBar.post(new Runnable() {
                               @Override
                               public void run() {
                                   progressBar.setProgress(progress);
                                   txtProgress.setText("当前现在进度："+progress+"%");
                               }
                           });
                           try {
                               Thread.sleep(1000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                });
                loadThread.start();
            }
        });


    }
}
