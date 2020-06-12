package cn.edu.sc.scu_force_offline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private TextView txtOffline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOffline=findViewById(R.id.txt_offline);
        txtOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("cn.edu.sc.scu.FORCE_OFFLINE");
               // sendBroadcast(intent);
                sendOrderedBroadcast(intent,null);
            }
        });
    }
}
