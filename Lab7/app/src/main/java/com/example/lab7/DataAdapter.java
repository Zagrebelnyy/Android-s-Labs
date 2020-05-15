package com.example.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
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
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DataAdapter(Context context, ArrayList<Product> products) {
        dbHelper = new DBHelper(context);
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
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        final Product product = products.get(position);
        holder.nameView.setText(product.getName());
        holder.priceView.setText(product.getPrice() + "");
        holder.countView.setText(product.getCount() + "");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getCount() > 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            product.setCount(product.getCount()-1);
                            holder.countView.setText(product.getCount() +"");
                            MyAsyncTask myAsyncTask = new MyAsyncTask();
                            myAsyncTask.execute(position);
                        }
                    }, 1000);
                }
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

        ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.nameProduct);
            priceView = (TextView) view.findViewById(R.id.priceProduct);
            countView = (TextView) view.findViewById(R.id.countProduct);
            btn = (TextView) view.findViewById(R.id.btnBuy);
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Void, Void> {
        protected void onPreExecute() {

        }

        protected Void doInBackground(Integer... voids) {
            Integer index = voids[0];
            Product product = products.get(index);
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.getKeyCount(), product.getCount() - 1);
            database.update(DBHelper.getTableName(),contentValues,DBHelper.getKeyName() +
                    "= ?",new String[]{product.getName()});
            return null;
        }

        protected void onPostExecute(Void aVoid) {

        }
    }
}