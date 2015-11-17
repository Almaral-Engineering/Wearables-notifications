package com.almareng.wearablesnotifications;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
    private final static String GROUP_KEY_EMAILS = "group_key_emails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displaySimpleNotification(View view) {
        int notificationId = 1;
        Intent viewIntent = new Intent(this, NotificationEventActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_media_play)
                .setContentTitle(getString(R.string.almaral_engineering))
                .setContentText(getString(R.string.simple_notification_message))
                .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void displayActionNotification(View view) {
        int notificationId = 2;

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:37.7833,-122.4167");
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent = PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_media_pause)
                .setContentTitle(getString(R.string.almaral_engineering))
                .setContentText(getString(R.string.action_notification_message))
                .setContentIntent(mapPendingIntent)
                .addAction(android.R.drawable.ic_dialog_map, getString(R.string.map), mapPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void displayWearableOnlyActionNotification(View view) {
        int notificationId = 3;
        Intent actionIntent = new Intent(this, NotificationEventActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, 0);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(android.R.drawable.ic_input_add,
                                                      getString(R.string.wearable_only_action),
                                                      actionPendingIntent)
                .build();

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_media_next)
                .setContentTitle(getString(R.string.almaral_engineering))
                .setContentText(getString(R.string.wearable_only_action_notification_message))
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);
    }

    public void displayBigViewNotification(View view) {
        final int notificationId = 4;
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(getString(R.string.big_view_style_notification_message));

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_media_previous)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.landscape))
                .setContentTitle(getString(R.string.almaral_engineering))
                .setContentText("Resumed Description")
                .setStyle(bigStyle);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void displayPagesNotification(View view) {
        final int notificationId = 5;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_media_rew)
                .setContentTitle("Page 1")
                .setContentText("Short message");

        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Page 2")
                .bigText("A lot of text...");

        Notification secondPageNotification = new NotificationCompat.Builder(this)
                .setStyle(secondPageStyle)
                .build();

        Notification thirdPageNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Page 3")
                .setContentText("Another page just because I want")
                .build();

        Notification notification = notificationBuilder.extend(new NotificationCompat.WearableExtender()
                                                                       .addPage(secondPageNotification)
                                                                       .addPage(thirdPageNotification))
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);
    }

    public void displayStackNotification(View view) {
        final int notificationId = 6;
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + getString(R.string.almaral_engineering))
                .setContentText("Learn Android for Free on www.almareng.wordpress.com")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);

        final int notificationId2 = 7;

        Notification notification2 = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from student")
                .setContentText("Thanks for the info, I will check it right now")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        notificationManager.notify(notificationId2, notification2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
