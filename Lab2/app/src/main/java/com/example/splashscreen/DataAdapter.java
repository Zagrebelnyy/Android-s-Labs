package com.example.splashscreen;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ListItem> listItems;
    //
    private Context context;
    //

    public DataAdapter(Context context, List<ListItem> innerItems){
        this.context = context;
        this.listItems = innerItems;

        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        ListItem listItem = listItems.get(position);
        Glide.with(holder.imageView.getContext()) // здесь получаете контекст
                .load(listItem.getImage())//
                .into(holder.imageView); //из холдера получаете imageView
        holder.nameView.setText(listItem.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPagerFragment viewPagerFragment;
                viewPagerFragment = new ViewPagerFragment(context, MainActivity.getList(),position);
                MainActivity activity = (MainActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.view_pager_fragment,  viewPagerFragment).
                        addToBackStack(null).
                        commit();
            }
        });

    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final View view;
        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.imageView);
            nameView = (TextView) view.findViewById(R.id.name);
            this.view = view;
        }
    }
}