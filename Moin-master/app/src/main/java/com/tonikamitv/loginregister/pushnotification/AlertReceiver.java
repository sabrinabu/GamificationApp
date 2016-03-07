package com.tonikamitv.loginregister.pushnotification;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.tonikamitv.loginregister.R;

/**
 * Created by sabrina on 3/1/2016.
 */
public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String username = intent.getStringExtra("username");
        String title = intent.getStringExtra("title");
        String eventName = intent.getStringExtra("eventName");
        String message = intent.getStringExtra("message");
        ExecuteNotification(eventName, username, title, context);
    }

    private void ExecuteNotification(String eventName, String userName, String title, Context ctx) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent intent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, ClickShowPushNotification.class), 0);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(ctx);
        nb.setSmallIcon(R.drawable.location_icon);
        nb.setSound(sound);
        nb.setContentTitle(title+ " " +userName);
                nb.setContentText("Please give feedback on '" + eventName + "' event");
        nb.setContentIntent(intent);
        nb.setAutoCancel(true);

        android.app.NotificationManager nm = (android.app.NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(100, nb.build());
    }
}
