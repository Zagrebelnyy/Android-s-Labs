package com.example.myapplication;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Integer numbers[];
    private String arr[];

    DataAdapter(Context context, Integer newArr[]) {
        numbers = new Integer[newArr.length];
        for(int i = 0; i < newArr.length; i++)
        {
            numbers[i] = newArr[i];
        }
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.nameView.setText(numbers[position].toString());
        if((position%2) == 0)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#CCCCCC"));
        }
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return numbers.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.number);
            imageView = (ImageView)view.findViewById(R.id.imageView);
        }
    }
}

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final int sizeArr = 1000000;
        Integer arr[] = new Integer[sizeArr];
        for (int i = 0; i < sizeArr; i++)
        {
            arr[i] = i + 1;
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this, arr);
        recyclerView.setAdapter(adapter);
    }
}

