package com.gp3.enkasa.Fragments.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.R;

public class AlojamientosViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private TextView mTitle;
    private Alojamientos mItem;

    public AlojamientosViewHolder(View view) {
        super(view);
        mView = view;
        mTitle = view.findViewById(R.id.content);
        //mTitle.setText(mItem.getNombre());
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(TextView title) {
        mTitle = title;
    }

    public Alojamientos getItem() {
        return mItem;
    }

    public void setItem(Alojamientos item) {
        mItem = item;
    }
}
