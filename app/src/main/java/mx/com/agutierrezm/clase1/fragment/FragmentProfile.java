package mx.com.agutierrezm.clase1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mx.com.agutierrezm.clase1.R;

/**
 * Created by Alumno on 11/06/2016.
 */
public class FragmentProfile extends Fragment {
    ImageView imgPRofile;
    Boolean change = true;
    public static FragmentProfile newInstance(String name)
    {
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("key_user", name);
        f.setArguments(b);
        return f;

    }

    public void changeImage() {
        imgPRofile.setImageResource(change?R.drawable.ic_action_bug_report:R.drawable.ic_action_face_unlock);
        change=!change;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
         imgPRofile = (ImageView )view.findViewById(R.id.imgProfile);
        TextView textUSerFragment  = (TextView) view.findViewById(R.id.textUSerFragment);
        String user;
        Bundle bundle = getArguments();
        if ( bundle!=null) {
            user = bundle.getString("key_user");
        }else{
            user = "XML inflate";
        }

        textUSerFragment.setText(user);
        return view;
    }


}
