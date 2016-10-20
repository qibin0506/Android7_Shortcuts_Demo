package org.loader.shortcutsdynamic;

import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by qibin on 16-10-20.
 */

public class Adapter extends BaseAdapter {

    private List<String> mList;

    public void setDatas(List<String> list) {
        mList = list;
    }

    public void set(String name, int index) {
        mList.set(index, name);
        notifyDataSetChanged();
    }

    public void remove(int index){
        mList.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        TextView name = (TextView) itemView.findViewById(R.id.item);
        name.setText(mList.get(i));
        return itemView;
    }
}
