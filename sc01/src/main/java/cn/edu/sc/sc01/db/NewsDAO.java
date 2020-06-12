package cn.edu.sc.sc01.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cn.edu.sc.sc01.News;


public class NewsDAO {
    private Context context;
    private DBHelper dbHelper;
    public NewsDAO(Context context){
        dbHelper=new DBHelper(context,"news.db",null,1);
    }
    public void insertNews(News news){
        String insertStr="insert into news(title,content,editdate) values(?,?,?)";
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        sqLiteDatabase.execSQL(insertStr,new String[]{news.getTitle(),news.getContent(),news.getEditdate()});
        sqLiteDatabase.close();
    }
    public ArrayList<News> getAllNews(){
        ArrayList<News> arrayList=new ArrayList<News>();
        String queryAll="select * from news";
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(queryAll,null);
        while (cursor.moveToNext()){
            News news=new News();
            news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            news.setContent(cursor.getColumnName(cursor.getColumnIndex("content")));
            news.setEditdate(cursor.getColumnName(cursor.getColumnIndex("editdate")));
            arrayList.add(news);
        }
        return arrayList;
    }

}
