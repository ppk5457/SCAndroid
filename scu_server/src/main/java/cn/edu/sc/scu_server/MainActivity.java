package cn.edu.sc.scu_server;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.FileUtils;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //private MediaPlayer mediaPlayer=new MediaPlayer();
    private MediaPlayer mediaPlayer;
    private static final int OPEN_REQUEST_CODE=0x100;

    private Button btnPlay,btnPause,btnStop,btnLoop,btnStart,btnBind,btnStopService,btnUnBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnLoop = findViewById(R.id.btnLoop);

        btnBind=findViewById(R.id.btnBind);
        btnStart=findViewById(R.id.btnStart);
        btnStopService=findViewById(R.id.btnStopService);
        btnUnBind=findViewById(R.id.btnUnBind);
        btnStart.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnUnBind.setOnClickListener(this);

        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);

        btnPlay.setOnClickListener(this);
        btnLoop.setOnClickListener(this);
        initMediaPlayer();

    }
    @Override
    public void onClick(View view){
            switch (view.getId()) {
                case R.id.btnPlay:
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                    break;
                case R.id.btnPause:
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                    break;
                case R.id.btnStop:
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        initMediaPlayer();
                    }
                    break;

                case R.id.btnLoop:
                    if (mediaPlayer.isLooping()) {
                        mediaPlayer.setLooping(false);
                        btnLoop.setText("单次播放");
                    } else {
                        mediaPlayer.setLooping(true);
                        btnLoop.setText("循环播放");
                    }
                    break;
                case R.id.btnStart:
                    Intent intent=new Intent(MainActivity.this,MyService.class);
                    startService(intent);
                    break;
                case R.id.btnStopService:
                    Intent intent1=new Intent(MainActivity.this,MyService.class);
                    stopService(intent1);
                    break;
                case R.id.btnBind:
                    Intent intent2=new Intent(MainActivity.this,MyService.class);
                    bindService(intent2,myConn,BIND_AUTO_CREATE);
                    break;
                case R.id.btnUnBind:
                    Intent intent3=new Intent(MainActivity.this,MyService.class);
                    unbindService(myConn);
                    break;
            }

    }
    ServiceConnection myConn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder binder=(MyService.MyBinder)iBinder;
            MyService myService=binder.getService();
            //

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
   private void initMediaPlayer(){
        mediaPlayer=MediaPlayer.create(this,R.raw.miles);//音频文件在Raw文件夹下；

//        try {
//            AssetFileDescriptor afd=getAssets().openFd("成都.mp3");
//            mediaPlayer=new MediaPlayer();
//            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(), afd.getLength());
//            mediaPlayer.prepare();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }


}
