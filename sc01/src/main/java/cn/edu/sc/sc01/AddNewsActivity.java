package cn.edu.sc.sc01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.sc.sc01.db.NewsDAO;

public class AddNewsActivity extends AppCompatActivity {
    private EditText edtTitle,edtContent;
    private TextView textView;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        edtContent=findViewById(R.id.editDetail);
        edtTitle=findViewById(R.id.editTitle);
        textView=findViewById(R.id.txtDate);

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        textView.setText(sdf.format(date));


        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=edtTitle.getText().toString();
                String content=edtContent.getText().toString();
                String strdate=textView.getText().toString();
                if(TextUtils.isEmpty(title)){
                    edtTitle.setError("新闻标题不能为空");
                }else if(TextUtils.isEmpty(content)){
                    edtContent.setError("新闻内容不能为空");
                }else{
                    News news=new News();
                    news.setTitle(title);
                    news.setContent(content);
                    news.setEditdate(strdate);
                    NewsDAO dao=new NewsDAO(AddNewsActivity.this);
                    dao.insertNews(news);
                    finish();
                }
            }
        });
    }
}