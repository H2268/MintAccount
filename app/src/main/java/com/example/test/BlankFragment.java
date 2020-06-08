package com.example.test;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BlankFragment extends Fragment {
    String state;
    TextView textView3;
    private OnFragmentInteractionListener mListener;
    private View mEditText;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank1, container, false);
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
        //View v1= getActivity().getLayoutInflater().inflate(R.layout.fragment_blank, null);这样写监听器失效

        final RadioGroup radioGroup=getActivity().findViewById(R.id.radio1);

        getActivity().findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView3.setText("");
            }
        });






        final Spinner spinner=getActivity().findViewById(R.id.Spin);

        final DatePicker datePicker=getActivity().findViewById(R.id.DataPicker);

        //final   t=getActivity().findViewById(R.id.charge);

//        final EditText t2=getActivity().findViewById(R.id.remark);

        final SqliteDbManager sqliteDbManager=SqliteDbManager.getInstance();
        textView3=getActivity().findViewById(R.id.money);


        final TextView num0=getActivity().findViewById(R.id.num0);

        final TextView num1=getActivity().findViewById(R.id.num1);
        final TextView num2=getActivity().findViewById(R.id.num2);
        final TextView num3=getActivity().findViewById(R.id.num3);
        final TextView num4=getActivity().findViewById(R.id.num4);
        final TextView num5=getActivity().findViewById(R.id.num5);
        final TextView num6=getActivity().findViewById(R.id.num6);
        final TextView num7=getActivity().findViewById(R.id.num7);
        final TextView num8=getActivity().findViewById(R.id.num8);
        final TextView num9=getActivity().findViewById(R.id.num9);
        final TextView point=getActivity().findViewById(R.id.point);
        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num0.getText().toString()+"我被执行了");
                textView3.append(num0.getText().toString());
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num1.getText().toString()+"我被执行了");
                textView3.append(num1.getText().toString());
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num2.getText().toString()+"我被执行了");
                textView3.append(num2.getText().toString());
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num3.getText().toString()+"我被执行了");
                textView3.append(num3.getText().toString());
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num4.getText().toString()+"我被执行了");
                textView3.append(num4.getText().toString());
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num5.getText().toString()+"我被执行了");
                textView3.append(num5.getText().toString());
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num6.getText().toString()+"我被执行了");
                textView3.append(num6.getText().toString());
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num7.getText().toString()+"我被执行了");
                textView3.append(num7.getText().toString());
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num8.getText().toString()+"我被执行了");
                textView3.append(num8.getText().toString());
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(num9.getText().toString()+"我被执行了");
                textView3.append(num9.getText().toString());
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(point.getText().toString()+"我被执行了");
                textView3.append(point.getText().toString());
            }
        });



         Button b=getActivity().findViewById(R.id.confirm);
         b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//




                RadioButton radioButton=getActivity().findViewById(radioGroup.getCheckedRadioButtonId());
                state=radioButton.getText().toString();


                String type=spinner.getSelectedItem().toString();
                System.out.println(type);
                int Month=datePicker.getMonth()+1;
                int Day=datePicker.getDayOfMonth();
                String date= String.valueOf(datePicker.getYear()+"-"+Month+"-"+Day);


                System.out.println(date);







             String fee=textView3.getText().toString();
                System.out.println(fee);

//                String remark=t2.getText().toString();
//                System.out.println(remark);

                sqliteDbManager.setSqliteDbOpen(getActivity());
//                sqliteDbManager.insert(state,type,date,fee,remark);
                //点击确认按钮后重绘ViewFragment
                TableLayout t=getActivity().findViewById(R.id.table);
                Cursor rawQuery=sqliteDbManager.query();

                        TableRow tableRow = new TableRow(getActivity());
                        TextView textView1=new TextView(getActivity());
                        textView1.setText("       "+state);
                        TextView textView2=new TextView(getActivity());
                        textView2.setText("               "+type);
                        TextView textView3=new TextView(getActivity());
                        textView3.setText("                 "+fee+"元");
//                        TextView textView4=new TextView(getActivity());
//                        textView4.setText("  "+remark);
                        TextView textView5=new TextView(getActivity());
                        textView5.setText("                 "+date);
                        tableRow.addView(textView1);
                        tableRow.addView(textView2);
                        tableRow.addView(textView3);
//                        tableRow.addView(textView4);
                        tableRow.addView(textView5);
                        t.addView(tableRow);



            }

        });
    }



}
