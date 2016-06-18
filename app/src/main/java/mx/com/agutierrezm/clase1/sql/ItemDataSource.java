package mx.com.agutierrezm.clase1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.agutierrezm.clase1.model.ModelItem;

/**
 * Created by Alumno on 17/06/2016.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;

    public ItemDataSource(Context context){
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void  saveItem(ModelItem modelItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_NAME,modelItem.item);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_DESC,modelItem.id);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.resource_id);
        db.insert(MySqliteHelper.TABLE_NAME,null,contentValues);
    }

    public List<ModelItem> getAllItems(){
        List<ModelItem> modelItemList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            int resource_id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));
            ModelItem modelItem = new ModelItem();
            modelItem.item = itemName;
            modelItem.id = descripcion;
            modelItem.resource_id = resource_id;
            modelItemList.add(modelItem);
        }
        return modelItemList;
    }

}
