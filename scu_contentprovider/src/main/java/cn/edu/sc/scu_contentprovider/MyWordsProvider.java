package cn.edu.sc.scu_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.sc.scu_contentprovider.db.DBHelper;

public class MyWordsProvider extends ContentProvider {

    public static final String AUTHORITY="cn.edu.sc.scu.mywrodsprovider";
    private DBHelper dbHelper;
    public MyWordsProvider() {
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        long newWordId=db.insert("words",null,values);
        Uri uriReturn=Uri.parse("content://"+AUTHORITY+"/word/"+newWordId);
        return uriReturn;
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
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        cursor=db.query("words",projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        // throw new UnsupportedOperationException("Not yet implemented");
        return "vnd.android.cursor.dir/cn.edu.sc.scu.mywrodsprovider.words";
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
