package com.gp3.enkasa.Fragments.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp3.enkasa.Activities.AlojamientosActivity;
import com.gp3.enkasa.Fragments.AlojamientoFragment.OnListFragmentInteractionListener;
import com.gp3.enkasa.Fragments.Holders.AlojamientosViewHolder;
import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;


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

        Alojamientos aloj = mData.getAlojamientos().get(position);
        Traducciones tr = mData.getTraduccionByAlojaminetoID(aloj.getId(), AlojamientosActivity.LANG);
        holder.setItem( aloj );
        holder.getTitle().setText(tr.getResumen());

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(AlojamientosActivity.jsonData.getData(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.getAlojamientos().size();
    }

}
