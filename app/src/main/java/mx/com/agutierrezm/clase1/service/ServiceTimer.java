package mx.com.agutierrezm.clase1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Alumno on 18/06/2016.
 */
public class ServiceTimer extends Service {
    public static final  String TAG = "clase1_tag";
    public static final String ACTION_SEND_TIMER = "mx.com.agutierrezm.clase.SEND_TIMER";
    int counter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            counter++;
            handler.postDelayed(runnable,1000);
            Intent i = new Intent((ACTION_SEND_TIMER));
            i.putExtra("timer",counter);
            sendBroadcast(i);
            Log.d(TAG,"contador " + counter);
            }
    } ;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"OnStartCommand called");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"OnCreate servicio");
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy servicio");
        handler.removeCallbacks(runnable);
    }

}
