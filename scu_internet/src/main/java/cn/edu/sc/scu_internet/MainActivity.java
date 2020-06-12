package cn.edu.sc.scu_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editURL;
    private Button btnExplore;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editURL=findViewById(R.id.editURL);
        btnExplore=findViewById(R.id.btnExplore);
        webView=findViewById(R.id.webView);
        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String netUrl=editURL.getText().toString();
                if(TextUtils.isEmpty(netUrl)){
                    editURL.setError("网址不能为空");
                }else{
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl("http://www.baidu.com");
                }
            }
        });

    }
}
