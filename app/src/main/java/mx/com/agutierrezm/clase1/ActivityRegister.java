package mx.com.agutierrezm.clase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import mx.com.agutierrezm.clase1.model.user;
import mx.com.agutierrezm.clase1.util.PreferenceUtil;

public class ActivityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText txtUserRegister = (EditText) findViewById(R.id.txtUserRegister);
        final EditText txtPassRegister = (EditText) findViewById(R.id.txtPassRegister);
        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vuser = txtUserRegister.getText().toString();
                String vpass = txtPassRegister.getText().toString();
                PreferenceUtil util = new PreferenceUtil(getApplicationContext());
                util.saverUser(new user(vuser,vpass));
                finish();

            }
        });
    }
}


