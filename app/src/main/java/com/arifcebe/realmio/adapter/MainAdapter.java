package com.arifcebe.realmio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arifcebe.realmio.Cat;
import com.arifcebe.realmio.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arifcebe on 28/12/15.
 */
public class MainAdapter extends BaseAdapter {

    private Context context;
    private List<Cat> catList;
    private MainViewHolder holder;

    public MainAdapter(Context context, List<Cat> catList) {
        this.context = context;
        this.catList = catList;
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return catList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.main_item,null);
            holder = new MainViewHolder(convertView);
            convertView.setTag(holder);

        }else{
            holder = (MainViewHolder) convertView.getTag();
        }

        Cat cat = catList.get(position);
        holder.name.setText(cat.getNo()+"\n"+cat.getName());
        holder.age.setText(String.valueOf(cat.getAge()));

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = holder.name.getLayoutParams();
                params.height = 370;
                holder.name.setLayoutParams(params);
            }
        });

        return convertView;
    }

    static class MainViewHolder{
        @Bind(R.id.item_name)
        TextView name;
        @Bind(R.id.item_age)
        TextView age;

        public MainViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
