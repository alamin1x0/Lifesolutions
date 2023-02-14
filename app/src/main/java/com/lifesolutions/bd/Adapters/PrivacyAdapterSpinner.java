package com.lifesolutions.bd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lifesolutions.bd.Models.SpinnerItem;
import com.lifesolutions.bd.R;

import java.util.ArrayList;

public class PrivacyAdapterSpinner extends ArrayAdapter<SpinnerItem> {

    public PrivacyAdapterSpinner(Context context, ArrayList<SpinnerItem> countryList) {
        super(context, 0, countryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.privacy_spinner_row, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_privacy);
        TextView textViewName = convertView.findViewById(R.id.text_view_privacy);

        SpinnerItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getImage());
            textViewName.setText(currentItem.getTitle());
        }

        return convertView;
    }
}
