package cn.edu.sc.scu_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {
    private TextView txtProgress;
    private ProgressBar progressBar;
    private Button btnStart;

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
                MyDownloadTask task=new MyDownloadTask();
                task.execute();
            }
        });

    }

class MyDownloadTask extends AsyncTask<Void,Integer,Boolean>{
    private int progress;
    public MyDownloadTask() {
        super();
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        while (progress<100){
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
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress=0;
        progressBar.setVisibility(View.VISIBLE);
        txtProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean){
            txtProgress.setText("下载完成");
        }else{
            txtProgress.setText("下载失败");
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        txtProgress.setText("下载进度："+values[0]+"%");
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}
}