package cn.edu.sc.scu02;


import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class WordObsever extends ContentObserver {
    private Context context;

    private Handler handler;
    public WordObsever( Handler handler,Context context) {
        super(handler);
        this.context=context;
        this.handler=handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.i("observer","--------------------"+System.currentTimeMillis());
        Toast.makeText(context,"刷新纪录",Toast.LENGTH_LONG).show();
        Message message=Message.obtain();
        message.what=1;
        handler.sendMessage(message);
    }
}
