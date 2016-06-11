package mx.com.agutierrezm.clase1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.com.agutierrezm.clase1.R;
import mx.com.agutierrezm.clase1.model.ModelItem;

/**
 * Created by Alumno on 11/06/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelItem> {
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
        txtTitle.setText(modelitem.item);
        txtItemDescription.setText(modelitem.id);
        img.setImageResource(modelitem.resource_id);
        return convertView;
    }
}
