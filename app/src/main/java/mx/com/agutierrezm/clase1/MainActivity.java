package mx.com.agutierrezm.clase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText vUser;
    private EditText vPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vUser = (EditText)findViewById(R.id.activity_relative_user);
        vPass = (EditText)findViewById(R.id.activity_relative_pass);
        findViewById(R.id.activity_relative_btnlogin).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_relative_btnlogin:
                processLogin();
                break;

        }
    }

    private void processLogin() {
        final String user =    vUser.getText().toString();
        final String pass = vPass.getText().toString();
        /*loading.setVisibility(View.VISIBLE);*/
        if (user.equals("alegutim") && pass.equals("1234a")){
            Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
            Intent intent =new  Intent(getApplicationContext(),FragmentActivity.class);
            /*ntent intent =new  Intent(getApplicationContext(),ActivityDetail.class);*/
            intent.putExtra("key_user",user);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }
    }
}
