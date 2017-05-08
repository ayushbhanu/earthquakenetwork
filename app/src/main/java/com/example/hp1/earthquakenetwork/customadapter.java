package com.example.hp1.earthquakenetwork;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp1.earthquakenetwork.R;
import com.example.hp1.earthquakenetwork.info;

import java.util.List;

/**
 * Created by hp 1 on 23-Feb-17.
 */
public class customadapter extends ArrayAdapter<info> {

    //color resource id
    private int colourid;

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.mag1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.mag2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.mag3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.mag4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.mag5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.mag6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.mag7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.mag8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.mag9;
                break;
            default:
                magnitudeColorResourceId = R.color.mag10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    public customadapter(Activity context, List<info> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, words);
        this.colourid=colourid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// View listitemview=convertView;
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        info currentword = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlayout, parent, false);
        }

        TextView mag = (TextView) convertView.findViewById(R.id.mag);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentword.getmymag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        mag.setText(currentword.getmymag1());

        TextView loc = (TextView) convertView.findViewById(R.id.loc);
        loc.setText(currentword.getmyloc());

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(currentword.getmyate());

        TextView area = (TextView) convertView.findViewById(R.id.area);
        area.setText(currentword.getarea());



        return convertView;

    }
}
