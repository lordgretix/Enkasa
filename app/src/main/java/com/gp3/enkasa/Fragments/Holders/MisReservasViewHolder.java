package com.gp3.enkasa.Fragments.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp3.enkasa.Models.Json.Models.Reservas;
import com.gp3.enkasa.R;

public class MisReservasViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private ImageView mIcon;
    private TextView mTitle;
    private TextView mDate;
    private ImageView mDeleteIcon;
    private Reservas mItem;

    public MisReservasViewHolder(View view) {
        super(view);
        mView = view;
        mTitle = view.findViewById(R.id.txtMisReservasTitle);
        mDate = view.findViewById(R.id.txtMisReservasDate);
        mIcon = view.findViewById(R.id.imgMisReservasIcon);
        mDeleteIcon = view.findViewById(R.id.imgMisReservasDeleteIcon);
    }

    public View getView() {
        return mView;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public ImageView getIcon() {
        return mIcon;
    }

    public void setIcon(ImageView mIcon) {
        this.mIcon = mIcon;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(TextView mTitle) {
        this.mTitle = mTitle;
    }

    public TextView getDate() {
        return mDate;
    }

    public void setDate(TextView mDate) {
        this.mDate = mDate;
    }

    public ImageView getDeleteIcon() {
        return mDeleteIcon;
    }

    public void setDeleteIcon(ImageView mDeleteIcon) {
        this.mDeleteIcon = mDeleteIcon;
    }

    public Reservas getItem() {
        return mItem;
    }

    public void setItem(Reservas mItem) {
        this.mItem = mItem;
    }
}
