package com.technology.givol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.technology.givol.R;

public class CustomListAdapter extends BaseAdapter {
    //private ArrayList<Item> items; //data source of the list adapter
    String[] titlesArray;
    private Context context; //context

    //public constructor
    public CustomListAdapter(Context context, String[] titlesArray) {
        this.context = context;
        this.titlesArray = titlesArray;
    }

    @Override
    public int getCount() {
        return titlesArray.length; //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return titlesArray[position]; //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.listitem_drawer, parent, false);
        }

        // get current item to be displayed
        // Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.titleListItem);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(titlesArray[position]);
        //textViewItemDescription.setText(currentItem.getItemDescription());

        // returns the view for the current row
        return convertView;
    }
}