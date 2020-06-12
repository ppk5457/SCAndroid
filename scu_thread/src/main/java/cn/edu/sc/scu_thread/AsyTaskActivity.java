package cn.edu.sc.scu_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyTaskActivity extends AppCompatActivity {
    private TextView txtProgress;
    private Button btnStart;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtProgress=findViewById(R.id.txtProgress);
        btnStart=findViewById(R.id.btnStart);
        progressBar=findViewById(R.id.progressBar);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLoadTask myLoadTask=new MyLoadTask();
                myLoadTask.execute();
            }
        });

    }
    class MyLoadTask extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            int progress=0;
            while(progress<100){
                progress=progress+10;
                publishProgress(progress);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            return true;
        }

        public MyLoadTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            txtProgress.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            txtProgress.setText("开始下载！");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
               txtProgress.setText("下载完成！");
            }else{
                txtProgress.setText("下载失败");
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            txtProgress.setText("当前下载进度："+values[0]+"%");

        }
    }
}