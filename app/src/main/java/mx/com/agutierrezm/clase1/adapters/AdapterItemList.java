package mx.com.agutierrezm.clase1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.com.agutierrezm.clase1.R;
import mx.com.agutierrezm.clase1.model.ModelItem;

/**
 * Created by Alumno on 11/06/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelItem> {
    private final String url_1 = "https://mir-s3-cdn-cf.behance.net/projects/202/12792733.54832468a07db.jpg";
    private final String url_2 = "http://laeconomia.com.mx/wp-content/uploads/pase-urbano.png";

    public AdapterItemList(Context context,  List<ModelItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        }
        TextView txtItemDescription   = (TextView) convertView.findViewById(R.id.txtItem2);
        TextView txtTitle= (TextView) convertView.findViewById(R.id.txtItem1);
        ImageView img = (ImageView) convertView.findViewById(R.id.row_image);
        ModelItem modelitem = getItem(position);
        Picasso.with(getContext()).load(modelitem.resource_id==R.drawable.ic_action_bug_report?url_1:url_2).into(img);
        txtTitle.setText(modelitem.item);
        txtItemDescription.setText(modelitem.description);
        //img.setImageResource(modelitem.resource_id);
        return convertView;
    }
}
