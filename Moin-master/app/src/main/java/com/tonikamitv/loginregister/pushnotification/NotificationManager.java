package com.tonikamitv.loginregister.pushnotification;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.tonikamitv.loginregister.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sabrina on 2/25/2016.
 */
public class NotificationManager extends Activity {

    private Timer myTimer;
    Context ctx;

    NotificationManager(Context ctx) {
        this.ctx = ctx;
    }

    //Notify after 2 minutes of the event
    public void NotificationTimer(Date startTime, final String eventName, final String userName){
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ExecuteNotification(eventName, userName);
            }
        }, startTime, 100000);
    }

    private void ExecuteNotification(String eventName, String userName) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent intent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, ClickShowPushNotification.class), 0);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(ctx);
        nb.setSmallIcon(R.drawable.location_icon);
        nb.setSound(sound);
        nb.setContentTitle("knock knock, " + userName);
        nb.setContentText("Please give feedback about '"+ eventName + "' event");
        nb.setContentIntent(intent);

        android.app.NotificationManager nm = (android.app.NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(100, nb.build());
        myTimer.cancel();
    }
}
