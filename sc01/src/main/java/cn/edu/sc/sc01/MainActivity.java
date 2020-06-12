package cn.edu.sc.sc01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.edu.sc.sc01.db.NewsDAO;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_NEWS=0x101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idNew:
                Intent intent=new Intent(MainActivity.this,AddNewsActivity.class);
                startActivityForResult(intent,ADD_NEWS);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_NEWS){
//            NewsDAO newsDAO=new NewsDAO(MainActivity.this);
//            ArrayList<News> newsArrayList=newsDAO.getAllNews();
//            for (News news:newsArrayList){
//                Log.i("MainActivity",news.toString());
//            }
            NewsTitleFragment titleFragment=(NewsTitleFragment) getSupportFragmentManager().findFragmentById(R.id.news_title_fragment);
            titleFragment.refresh();

        }
    }
}
