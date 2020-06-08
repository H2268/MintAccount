package com.example.test;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String param1, String param2) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view1, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //System.out.println("我在oncreate里面"+getActivity());
        TableLayout t=getActivity().findViewById(R.id.table);
        SqliteDbManager sqliteDbManager=SqliteDbManager.getInstance();
        sqliteDbManager.setSqliteDbOpen(getActivity());
        sqliteDbManager.update();
        Cursor rawQuery=sqliteDbManager.query();
        if (null != rawQuery)
        {
            while (rawQuery.moveToNext())
            {
                String state = rawQuery.getString(rawQuery.getColumnIndex("state"));
                String type = rawQuery.getString(rawQuery.getColumnIndex("type"));
                String date = rawQuery.getString(rawQuery.getColumnIndex("date"));
                String fee = rawQuery.getString(rawQuery.getColumnIndex("fee"));
//                String remark = rawQuery.getString(rawQuery.getColumnIndex("remark"));
                TableRow tableRow = new TableRow(getActivity());
                TextView textView1=new TextView(getActivity());
                textView1.setText("       "+state);
                TextView textView2=new TextView(getActivity());
                textView2.setText("               "+type);
                TextView textView3=new TextView(getActivity());
                textView3.setText("                 "+fee+"元");
//                TextView textView4=new TextView(getActivity());
//                textView4.setText("  "+remark);
                TextView textView5=new TextView(getActivity());
                textView5.setText("                 "+date);
                tableRow.addView(textView1);
                tableRow.addView(textView2);
                tableRow.addView(textView3);

//                tableRow.addView(textView4);
                tableRow.addView(textView5);
                t.addView(tableRow);

                  }
                rawQuery.close();
                 }
    }


    }




