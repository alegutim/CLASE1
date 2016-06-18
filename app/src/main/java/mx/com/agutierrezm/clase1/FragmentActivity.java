package mx.com.agutierrezm.clase1;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mx.com.agutierrezm.clase1.fragment.FragmentList;
import mx.com.agutierrezm.clase1.fragment.FragmentProfile;
import mx.com.agutierrezm.clase1.service.ServiceTimer;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTimer;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getExtras().getInt("timer");
            txtTimer.setText(String.format("Session length %s seconds", count));

            //intent.getAction().equals(ServiceTimer.ACTION_SEND_TIMER))))};
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        String userName = getIntent().getExtras().getString("key_user");

        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);
        txtTimer = (TextView)findViewById(R.id.txtTimer);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFragmentA:
                changeFragmentA();
                break;
            case R.id.btnFragmentB:
                changeFragmentB();
                break;

        }
    }



    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance("Hola Mundo");
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();


    }

    private void changeFragmentB() {
        //FragmentProfile f = FragmentProfile.newInstance("Adios Mundo :V");
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentList()).commit();
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_xml);
        if ( fragment!= null ) {
            ((FragmentProfile)fragment).changeImage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,intentFilter);
        Log.d(ServiceTimer.TAG,"OnPause iniciando broadcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ServiceTimer.TAG,"OnPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTimer.TAG,"onDestroy terminando broadcast");
        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }
}

