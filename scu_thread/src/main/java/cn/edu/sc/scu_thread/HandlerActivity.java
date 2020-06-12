package cn.edu.sc.scu_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
    private TextView txtProgress;
    private ProgressBar progressBar;
    private Button btnStart;
    private int progress=0;
    private static final int LOAD_OPERATE=0x101;

    private Handler myHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==LOAD_OPERATE){
                progressBar.setProgress(msg.arg1);
                txtProgress.setText(msg.obj.toString());
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtProgress=findViewById(R.id.txtProgress);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setProgress(progress);
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtProgress.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        while (progress<100){
                            progress=progress+10;
                            Message message=Message.obtain();
                            message.what=LOAD_OPERATE;
                            message.arg1=progress;
                            message.obj="当前下载进度为："+progress+"%";
                            myHandler.sendMessage(message);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

    }
}