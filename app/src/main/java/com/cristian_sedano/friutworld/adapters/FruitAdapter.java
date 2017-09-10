package com.cristian_sedano.friutworld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristian_sedano.friutworld.R;
import com.cristian_sedano.friutworld.models.Fruit;

import java.util.List;

/**
 * Created by Christian on 01/08/2017.
 */

public class FruitAdapter  extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Fruit> list;

    public FruitAdapter(Context context, int layout, List<Fruit> list){

        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textViewName);
            holder.origin = (TextView) convertView.findViewById(R.id.textViewOrigin);
            holder.icon = (ImageView) convertView.findViewById(R.id.imageViewIcon);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Fruit currentFruit = (Fruit) getItem(position);
        holder.name.setText(currentFruit.getName());
        holder.origin.setText(currentFruit.getOrigin());
        holder.icon.setImageResource(currentFruit.getIcon());

        return convertView;
    }

    static class ViewHolder{
        private TextView name;
        private TextView origin;
        private ImageView icon;
    }
}
