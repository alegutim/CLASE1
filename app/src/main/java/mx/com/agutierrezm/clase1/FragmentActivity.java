package mx.com.agutierrezm.clase1;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.com.agutierrezm.clase1.fragment.FragmentList;
import mx.com.agutierrezm.clase1.fragment.FragmentProfile;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        String userName = getIntent().getExtras().getString("key_user");

        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);

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
}

