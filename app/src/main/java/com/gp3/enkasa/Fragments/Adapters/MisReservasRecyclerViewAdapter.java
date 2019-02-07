package com.gp3.enkasa.Fragments.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp3.enkasa.Activities.AlojamientosActivity;
import com.gp3.enkasa.Fragments.MisReservasFragment.OnListFragmentInteractionListener;
import com.gp3.enkasa.Fragments.Holders.MisReservasViewHolder;
import com.gp3.enkasa.MainActivity;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.Models.Reservas;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.ArrayList;


public class MisReservasRecyclerViewAdapter extends RecyclerView.Adapter<MisReservasViewHolder> {

    private ArrayList<Reservas> mReservas;
    private final OnListFragmentInteractionListener mListener;

    public MisReservasRecyclerViewAdapter(ArrayList<Reservas> reservas, OnListFragmentInteractionListener listener) {
        mReservas = reservas;
        mListener = listener;
    }

    @Override
    public MisReservasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mis_reservas, parent, false);
        return new MisReservasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MisReservasViewHolder holder, final int position) {

        Reservas rs = mReservas.get(position);
        Traducciones tr = AlojamientosActivity.jsonData.getData().getTraduccionByAlojaminetoID(rs.getAlojamiento(), MainActivity.getCurrentLang());

        holder.setItem( rs );
        holder.getIcon().setImageDrawable(holder.getView().getResources().getDrawable(Data.getAlojamientoIcon(holder.getView().getContext(), tr.getTipo())));
        holder.getTitle().setText(tr.getNombre());
        holder.getDate().setText(rs.getFechaInicio(true)+" - "+rs.getFechaFin(true));


        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mReservas.get(position));
                }
            }
        });

        holder.getDeleteIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onListFragmentDeleteIconClick(mReservas.get(position));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mReservas.size();
    }

    public ArrayList<Reservas> getmReservas() {
        return mReservas;
    }

    public void setReservas(ArrayList<Reservas> mReservas) {
        this.mReservas = mReservas;
    }
}
