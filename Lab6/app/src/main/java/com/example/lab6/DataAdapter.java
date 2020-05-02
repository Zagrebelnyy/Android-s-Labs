package com.example.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;//Обязательное поле
    private ArrayList<Product> products;// список набора контента, который будет наполнять список
    private Context context;
    private  DBHelper dbHelper;
    SQLiteDatabase database;

    public DataAdapter(Context context, ArrayList<Product> products){
        dbHelper =  new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        this.products = products;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewpager_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.nameView.setText(product.getName());
        holder.priceView.setText(product.getPrice() + "");
        holder.countView.setText(product.getCount() + "");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setCount(product.getCount() - 1);
                holder.countView.setText(product.getCount() +"");
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.getKeyCount(), product.getCount());
                database.update(DBHelper.getTableName(),contentValues,DBHelper.getKeyName() +
                        "= ?",new String[]{product.getName()});
            }
        });
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final TextView priceView;
        final TextView countView;
        final TextView btn;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.nameProduct);
            priceView = (TextView) view.findViewById(R.id.priceProduct);
            countView = (TextView) view.findViewById(R.id.countProduct);
            btn = (TextView) view.findViewById(R.id.btnBuy);
        }
    }
}