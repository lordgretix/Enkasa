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
import com.gp3.enkasa.Fragments.Adapters.AlojamientoRecyclerViewAdapter;
import com.gp3.enkasa.MainActivity;
import com.gp3.enkasa.Models.Json.JsonData;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AlojamientoFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = AlojamientoFragment.class.getName() + ".ARG_COLUMN_COUNT";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private JsonData mJsonData;
    private AlojamientoRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlojamientoFragment() {

    }

    public static AlojamientoFragment newInstance(int columnCount) {
        AlojamientoFragment fragment = new AlojamientoFragment();
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
            mJsonData = AlojamientosActivity.jsonData;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alojamiento_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            ArrayList<Traducciones> traducciones = mJsonData.getData().getTraducciones(MainActivity.getCurrentLang());

            Collections.sort(traducciones, new Comparator<Traducciones>() {
                @Override
                public int compare(Traducciones t1, Traducciones t2) {
                    return t1.getNombre().compareToIgnoreCase(t2.getNombre());
                }
            });
            mAdapter = new AlojamientoRecyclerViewAdapter(traducciones, mListener);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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

    public void updateUI(ArrayList<Traducciones> traducciones) {

        if (mAdapter != null) {
            mAdapter.setTraducciones(traducciones);
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Traducciones tr);
    }
}
