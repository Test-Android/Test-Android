package com.nicodangelo.listexample;

import android.content.Context;
import android.view.*;
import android.widget.*;

public class CustomAdapter extends ArrayAdapter<String>
{
    //int resource == the array you want to deal with
    public CustomAdapter(Context context, String[] foods)
    {
        super(context,R.layout.custom_row, foods);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater nicosinflater = LayoutInflater.from(getContext());
        View customView = nicosinflater.inflate(R.layout.custom_row, parent, false);

        String singleFoodItem = getItem(position);
        TextView nicosText = (TextView) customView.findViewById(R.id.nicosText);
        ImageView nicosImage = (ImageView) customView.findViewById(R.id.nicosView);

        nicosText.setText(singleFoodItem);
        nicosImage.setImageResource(R.drawable.lizard);

        return customView;
    }
}