package cn.edu.sc.scu_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.sc.scu_contentprovider.db.DBHelper;

public class WordsProvider extends ContentProvider {
    public static final int WORD=1;
    public static final int WORDS=0;
    private static final UriMatcher uriMatcher;
    public  static final String AUTHORITY="cn.edu.sc.scu.wordsprovider";
    private DBHelper dbHelper;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"words",WORDS);
        uriMatcher.addURI(AUTHORITY,"word/*",WORD);
    }
    // content://cn.edu.sc.scu.wordsprovider/words
    // content://cn.edu.sc.scu.wordsprovider/word/*

    public WordsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
        int num=0;
        switch (uriMatcher.match(uri)){
            case WORD:
                String word=uri.getPathSegments().get(1);
                SQLiteDatabase database=dbHelper.getReadableDatabase();//getWriteableDatabase();
                num=database.delete("words","word=?",new String[]{word});
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return  num;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //throw new UnsupportedOperationException("Not yet implemented");
        Uri retunUri=null;
        switch (uriMatcher.match(uri)){
            case WORD:
            case WORDS:
                SQLiteDatabase database=dbHelper.getReadableDatabase();//getWriteableDatabase();
                Long id=database.insert("words",null,values);
                retunUri=Uri.parse("content://"+AUTHORITY+"/word/"+id);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return  retunUri;

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper=new DBHelper(getContext(),"words.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case WORD:
                // content://cn.edu.sc.scu.wordsprovider/word/*
                String word=uri.getPathSegments().get(1);
                cursor=database.query("words",projection,"word=?",new String[]{word},null,null,sortOrder);
                break;
            case WORDS:
                cursor=database.query("words",projection,selection,selectionArgs,null,null,sortOrder);
                break;

        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
