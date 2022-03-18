package com.example.saycure;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_search extends RecyclerView.Adapter<Adapter_search.SearchViewHolder>{

    private ArrayList<SearchItem> mExampleList;

    static Context c;

    public Adapter_search(Context ct,ArrayList<SearchItem> exampleList) {
        c=ct;
        mExampleList = exampleList;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        Button bt_select;

        public SearchViewHolder(View itemView) {
            super(itemView);

            tv_title=itemView.findViewById(R.id.title);
            bt_select=itemView.findViewById(R.id.select_bt);

        }
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_doctors_search_rv, parent, false);
        SearchViewHolder evh = new SearchViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchItem currentItem = mExampleList.get(position);

        holder.tv_title.setText(currentItem.getMname());
        holder.bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,currentItem.getMname(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<SearchItem> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }

}
