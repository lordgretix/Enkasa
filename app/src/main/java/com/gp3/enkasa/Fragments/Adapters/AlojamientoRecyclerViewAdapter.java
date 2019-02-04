package com.gp3.enkasa.Fragments.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp3.enkasa.Fragments.AlojamientoFragment.OnListFragmentInteractionListener;
import com.gp3.enkasa.Fragments.Holders.AlojamientosViewHolder;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.ArrayList;


public class AlojamientoRecyclerViewAdapter extends RecyclerView.Adapter<AlojamientosViewHolder> {

    private final ArrayList<Traducciones> mTraducciones;
    private final OnListFragmentInteractionListener mListener;

    public AlojamientoRecyclerViewAdapter(ArrayList<Traducciones> traducciones, OnListFragmentInteractionListener listener) {
        mTraducciones = traducciones;
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

        Traducciones tr = mTraducciones.get(position);
        holder.setItem( tr );
        holder.getIcon().setImageDrawable(holder.getView().getResources().getDrawable(R.drawable.ic_agroturismo_icon));
        System.out.println("NOMBRE: "+tr.getNombre());
        holder.getTitle().setText(tr.getNombre());
        holder.getResume().setText(tr.getResumen());

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mTraducciones.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTraducciones.size();
    }

}
