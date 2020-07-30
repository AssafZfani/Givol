package com.technology.givol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.technology.givol.R;
import com.technology.givol.model.DataModel;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {
    Context mContext;
    private ArrayList<DataModel> dataSet;

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    public void onClick(View v) {
        DataModel dataModel = getItem((Integer) v.getTag());
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        DataModel dataModel = (DataModel) getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            View view = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            View view2 = convertView;
        }
        viewHolder.txtName.setText(dataModel.getName());
        return convertView;
    }

    private static class ViewHolder {
        TextView txtName;

        private ViewHolder() {
        }
    }
}
