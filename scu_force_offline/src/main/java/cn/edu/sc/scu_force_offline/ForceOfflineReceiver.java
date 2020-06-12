package cn.edu.sc.scu_force_offline;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("严重警告！");
        builder.setMessage("探测到你在另外一台设备登录，为了安全强制下线，重新登录！");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityManager.finishAll();
                Intent intent=new Intent(context,LoginActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}
