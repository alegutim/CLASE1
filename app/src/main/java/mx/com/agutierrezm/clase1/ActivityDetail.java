package mx.com.agutierrezm.clase1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;


public class ActivityDetail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tv = (TextView)findViewById(R.id.activity_detail_textview);
        tv.setText(getIntent().getExtras().getString("key_user"));

    }

}
