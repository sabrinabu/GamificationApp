package com.tonikamitv.loginregister.pushnotification;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.tonikamitv.loginregister.R;

public class ClickShowPushNotification extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickshowpushnotification);

        android.app.NotificationManager nm = (android.app.NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(100);
    }


}
