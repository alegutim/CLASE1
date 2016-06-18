package mx.com.agutierrezm.clase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mx.com.agutierrezm.clase1.model.user;
import mx.com.agutierrezm.clase1.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText vUser;
    private EditText vPass;
    private PreferenceUtil preferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vUser = (EditText)findViewById(R.id.activity_relative_user);
        vPass = (EditText)findViewById(R.id.activity_relative_pass);
        findViewById(R.id.activity_relative_btnlogin).setOnClickListener(this);
        findViewById(R.id.activity_relative_btnregister).setOnClickListener(this);
        preferenceUtil = new PreferenceUtil(getApplicationContext());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_relative_btnlogin:
                processLogin();
                break;
            case R.id.activity_relative_btnregister:
                launchRegister();
                break;

        }
    }

    private void launchRegister() {
        startActivity(new Intent(getApplicationContext(),ActivityRegister.class));
    }

    private void processLogin() {
        final String muser = vUser.getText().toString();
        final String mpass = vPass.getText().toString();
        //loading.setVisibility(View.)
        user modeluser = preferenceUtil.getUser();
        if (modeluser != null) {
            if (muser.equals(modeluser.userName) && mpass.equals(modeluser.password)) {
                Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FragmentActivity.class);
            /*ntent intent =new  Intent(getApplicationContext(),ActivityDetail.class);*/
                intent.putExtra("key_user", muser);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(getApplicationContext(), "Register User", Toast.LENGTH_SHORT).show();
        }
    }
}
