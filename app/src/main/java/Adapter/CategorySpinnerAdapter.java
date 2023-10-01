package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class CategorySpinnerAdapter extends ArrayAdapter<String> {

    private ArrayList<String> categories;

    public CategorySpinnerAdapter(Context context, int resource, ArrayList<String> categories){
        super(context, resource, categories);
        this.categories = categories;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(categories.get(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(categories.get(position));

        return convertView;
    }


}
