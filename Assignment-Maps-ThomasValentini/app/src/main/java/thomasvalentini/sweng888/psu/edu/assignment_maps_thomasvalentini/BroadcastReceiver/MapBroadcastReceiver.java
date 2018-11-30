package thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.R;

public class MapBroadcastReceiver extends BroadcastReceiver {
    private static final String MAP_TAG = "MAP_TAG";

    public static final String MAP_LOCATION_BROADCAST = "thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.MAP_LOCATION_BROADCAST";
    public static final String EXTRA_LATITUDE = "LAT";
    public static final String EXTRA_LONGITUDE = "LONG";
    public static final String MAP_LOCATION = "LOCATION";

    public static final int CHANNEL_ID = 1;
    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    public static final String CHANNEL_DESCRIPTION = "BROADCAST MAP CHANNEL";
    public static final String CHANNEL_NAME = "MAPS";

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Gather the information / params from the Intent.
        Double latitude = intent.getDoubleExtra(EXTRA_LATITUDE, Double.NaN);
        Double longitude = intent.getDoubleExtra(EXTRA_LONGITUDE, Double.NaN);
        String location = intent.getStringExtra(MAP_LOCATION);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        builder = new NotificationCompat.Builder(context, CHANNEL_NAME);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle(location);
        builder.setContentText("Location Coordinates Are" + latitude + ", " + longitude);

        notificationManager.createNotificationChannel(getNotificationChannel());

        notificationManager.notify(CHANNEL_ID, builder.build());

        Log.d(MAP_TAG, "BROADCAST_SENT");
    }

    private NotificationChannel getNotificationChannel(){

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_NAME, CHANNEL_DESCRIPTION, CHANNEL_IMPORTANCE);
        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationChannel.setShowBadge(true);

        return notificationChannel;
    }
}
