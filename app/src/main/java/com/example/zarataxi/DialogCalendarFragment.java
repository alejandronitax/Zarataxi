package com.example.zarataxi;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/*
public class CalendarFragment extends DialogFragment {
*//*


*/
/*    public interface MyDialogoListener {
        void onDialogStationServicesClick();

        void onDialogStreetServicesClick();
    }

    MyDialogoListener miEscuchador;

    // Sobreescribimos el método onAttach() para instanciar el
    //escuchador
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            miEscuchador = (MyDialogoListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement MiDialogoListener");
        }
    }*//*


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_calendar, null);
   */
/*     View btnAcceptEmail = view.findViewById(R.id.btnEmisora);
        final Button btnCancelPrice = view.findViewById(R.id.btnPuerta);
        builder.setView(view);
        btnAcceptEmail.setOnClickListener(new SingleClickListener() {
            @Override
            public void performClick(View v) {
                miEscuchador.onDialogStationServicesClick();
                dismiss();
            }
        });
        btnCancelPrice.setOnClickListener(new SingleClickListener() {
            @Override
            public void performClick(View v) {
                miEscuchador.onDialogStreetServicesClick();
                dismiss();
            }
        });*//*

        builder.setView(view);
        return builder.create();
    }
*/

    public class DialogCalendarFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public interface MyDialogoListener {
            void onDialogSendDate(String date);
        }

        DialogCalendarFragment.MyDialogoListener miEscuchador;

        @Override
        public void onDetach() {
            super.onDetach();

        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            try {
                miEscuchador = (DialogCalendarFragment.MyDialogoListener) getActivity();
            } catch (ClassCastException e) {
                throw new ClassCastException(getActivity().toString()
                        + " must implement MiDialogoListener");
            }
        }


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Toast.makeText(getActivity(),"Has escogido " + day + "-" + month + "-" +
                    year,Toast.LENGTH_LONG).show();
            miEscuchador.onDialogSendDate(day+"/"+month+"/"+year);
            dismiss();
        }
    }
