package com.gp3.enkasa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gp3.enkasa.AlojamientoFragment.OnListFragmentInteractionListener;
import com.gp3.enkasa.Model.Json.Alojamientos;
import com.gp3.enkasa.Model.Json.Data;
import com.gp3.enkasa.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AlojamientoRecyclerViewAdapter extends RecyclerView.Adapter<AlojamientosViewHolder> {

    private final Data mData;
    private final OnListFragmentInteractionListener mListener;

    public AlojamientoRecyclerViewAdapter(Data data, OnListFragmentInteractionListener listener) {
        mData = data;
        mListener = listener;
    }

    @Override
    public AlojamientosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_alojamiento, parent, false);
        return new AlojamientosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlojamientosViewHolder holder, final int position) {

        holder.setItem( mData.getAlojamientos().get(position));
        holder.getTitle().setText(mData.getTraduccionByAlojaminetoID(holder.getItem().getId(), AlojamientosActivity.LANG).getResumen());

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mData, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.getAlojamientos().size();
    }

}
