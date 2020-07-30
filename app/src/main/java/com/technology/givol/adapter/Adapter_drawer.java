package com.technology.givol.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technology.givol.R;

public class Adapter_drawer extends BaseAdapter {
    Context context;
    String[] titlesArray;

    public Adapter_drawer(Context context2, String[] titlesArray2) {
        this.titlesArray = titlesArray2;
        this.context = context2;
    }

    public int getCount() {
        return this.titlesArray.length;
    }

    public Object getItem(int position) {
        return this.titlesArray[position];
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = (LinearLayout) LinearLayout.inflate(this.context, R.layout.listitem_drawer, null);
        }

        ((TextView) convertView.findViewById(R.id.titleListItem)).setText(this.titlesArray[position]);
        View gradientView = convertView.findViewById(R.id.gradientView);
        if (this.titlesArray.length == position + 1) {
            gradientView.setVisibility(View.VISIBLE);
        } else {
            gradientView.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
