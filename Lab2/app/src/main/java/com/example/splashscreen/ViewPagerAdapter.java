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

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ListItem> listItems;
    private Context context;

    public ViewPagerAdapter(Context context, List<ListItem> innerItems) {
        this.context = context;
        this.listItems = innerItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewpager_list_item, parent, false);
        return new ViewPagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewPagerAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        Glide.with(holder.imageView.getContext()) // здесь получаете контекст
                .load(listItem.getImage())
                .into(holder.imageView); //из холдера получаете imageView
        holder.nameView.setText(listItem.getName());
        holder.helpTextView.setText(listItem.getHelptext());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final TextView helpTextView;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            nameView = (TextView) view.findViewById(R.id.name);
            helpTextView = (TextView) view.findViewById(R.id.helptext);
        }
    }
}
