package com.gp3.enkasa.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp3.enkasa.Activities.AlojamientosActivity;
import com.gp3.enkasa.Fragments.Adapters.MisReservasRecyclerViewAdapter;
import com.gp3.enkasa.Models.Json.Models.Reservas;
import com.gp3.enkasa.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MisReservasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = MisReservasFragment.class.getName()+".ARG_COLUMN_COUNT";
    private int mColumnCount = 1;
    private MisReservasFragment.OnListFragmentInteractionListener mListener;

    private MisReservasRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MisReservasFragment() {

    }

    public static MisReservasFragment newInstance(int columnCount) {
        MisReservasFragment fragment = new MisReservasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_reservas_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            ArrayList<Reservas> reservas = AlojamientosActivity.jsonData.getData().getReservasByUserID(AlojamientosActivity.jsonData.getUser().getID());

            mAdapter = new MisReservasRecyclerViewAdapter(reservas, mListener);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MisReservasFragment.OnListFragmentInteractionListener) {
            mListener = (MisReservasFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateUI(ArrayList<Reservas> resevas){

        if(mAdapter!=null){
            mAdapter.setReservas(resevas);
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Reservas rs);
        void onListFragmentDeleteIconClick(Reservas rs);
    }
}
