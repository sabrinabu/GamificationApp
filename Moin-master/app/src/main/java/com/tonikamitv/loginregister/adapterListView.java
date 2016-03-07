package com.tonikamitv.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by susana on 27/02/16.
 */
public class adapterListView extends ArrayAdapter<String> {

private final Context context;
private final String[] values;

public adapterListView(Context context, String[] values) {
        super(context, R.layout.activity_missions, values);
        this.context = context;
        this.values = values;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_missions, parent, false);
        TextView textViewDescription = (TextView) rowView.findViewById(R.id.desctiptionMission);
        TextView textViewLevel = (TextView) rowView.findViewById(R.id.levelCompleted);
        ImageView imageViewTrophy = (ImageView) rowView.findViewById(R.id.trophyImage);
        ImageView imageViewProgress = (ImageView) rowView.findViewById(R.id.progresBar);
        textViewDescription.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        System.out.println(s);

        if (s.equals("Create two events")) {
            imageViewTrophy.setImageResource(R.drawable.trophy5);
        } else if (s.equals("Create two events")) {
            imageViewTrophy.setImageResource(R.drawable.trophy10);
        } else if (s.equals("Create two events")) {
            imageViewTrophy.setImageResource(R.drawable.trophy20);
        } else {
            imageViewTrophy.setImageResource(R.drawable.trophy40);
        }

        return rowView;
        }
}
