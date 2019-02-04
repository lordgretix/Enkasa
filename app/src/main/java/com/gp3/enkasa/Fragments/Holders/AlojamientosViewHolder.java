package com.gp3.enkasa.Fragments.Holders;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

public class AlojamientosViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private ImageView mIcon;
    private TextView mTitle;
    private TextView mResume;
    private Traducciones mItem;

    public AlojamientosViewHolder(View view) {
        super(view);
        mView = view;
        mTitle = view.findViewById(R.id.alojamiento_fragment_title);
        mResume = view.findViewById(R.id.alojamiento_fragment_resume);
        mIcon = view.findViewById(R.id.alojamiento_fragment_icon);
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public ImageView getIcon() {
        return mIcon;
    }

    public void setIcon(ImageView icon) {
        mIcon = icon;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(TextView title) {
        mTitle = title;
    }

    public TextView getResume() {
        return mResume;
    }

    public void setResume(TextView resume) {
        mResume = resume;
    }

    public Traducciones getItem() {
        return mItem;
    }

    public void setItem(Traducciones item) {
        mItem = item;
    }
}
