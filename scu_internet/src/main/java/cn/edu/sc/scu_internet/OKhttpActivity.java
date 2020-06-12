package cn.edu.sc.scu_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKhttpActivity extends AppCompatActivity {
    private Button btnLoad;
    private TextView txtView,txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_khttp);
        btnLoad=findViewById(R.id.btnLoad);
        txtView=findViewById(R.id.txtView);
        txtContent=findViewById(R.id.txtContent);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithOkHttp();
            }
        });
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://10.0.2.2:8080/MyWebProjec/MyServlet?username=admin&password=123").build();
                    Response response=client.newCall(request).execute();

                    final String responseData=response.body().string();
                    Gson gson=new Gson();
                   News news=gson.fromJson(responseData,News.class);
                    printNews(news);

                    //JSONArray jsonArray=new JSONArray(responseData);

                    JSONObject jsonObject=new JSONObject(responseData);
                    final String title=jsonObject.getString("title");
                    final String content=jsonObject.getString("content");

                    JSONArray jsonArray=jsonObject.getJSONArray("keywords");
                    for(int i=0;i<jsonArray.length();i++){
                        System.out.println("keywords:"+jsonArray.get(i).toString());
                    }


                    txtView.post(new Runnable() {
                        @Override
                        public void run() {
                            txtView.setText(title);
                            txtContent.setText(content);
                        }
                    });
                } catch (IOException e ) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
    public void printNews(News news){
        System.out.println(news.toString());
    }
}