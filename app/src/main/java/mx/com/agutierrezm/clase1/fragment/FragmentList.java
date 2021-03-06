package mx.com.agutierrezm.clase1.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import mx.com.agutierrezm.clase1.R;
import mx.com.agutierrezm.clase1.adapters.AdapterItemList;
import mx.com.agutierrezm.clase1.model.ModelItem;
import mx.com.agutierrezm.clase1.sql.ItemDataSource;

/**
 * Created by Alumno on 11/06/2016.
 */
public class FragmentList extends Fragment implements View.OnClickListener {
private ListView listView;
//private List<ModelItem> array = new ArrayList<>()    ;
private int counter;
private EditText mItemText;
private Boolean IsTrue = false;
private ItemDataSource itemDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        listView = (ListView) view.findViewById(R.id.ltsView);
        mItemText = (EditText) view.findViewById(R.id.mItemText);
        view.findViewById(R.id.btnAddItem).setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                                                ModelItem modelItem = adapter.getItem(position);
                                               // ModelItem modelItem2 = array.get(position);
                                                Toast.makeText(getActivity(),modelItem.item,Toast.LENGTH_SHORT).show();


                                            }
                                        });
        List<ModelItem> modelItemList = itemDataSource.getAllItems();
        IsTrue = !(modelItemList.size()%2==0);
        counter  = modelItemList.size();
        listView.setAdapter(new AdapterItemList(getActivity(),modelItemList));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                final ModelItem modelItem = adapter.getItem(position);
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.delete_title)
                        .setMessage(String.format("¿Desea borrar el elemento  %s?",modelItem.item))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemDataSource.deleteItem(modelItem);
                                listView.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems()));
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).create().show();


                return true;
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAddItem:
                String itemData = mItemText.getText().toString() ;
                if (!TextUtils.isEmpty(itemData)){
                    ModelItem item = new ModelItem();
                    item.item= itemData;
                    item.description = "Description: " + counter;
                    item.resource_id = IsTrue ? R.drawable.ic_action_face_unlock:R.drawable.ic_action_bug_report;
                    //array.add(item);
                    itemDataSource.saveItem(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems()));
                    IsTrue=!IsTrue;
                    counter++;
                }

                break;
        }
    }


}
