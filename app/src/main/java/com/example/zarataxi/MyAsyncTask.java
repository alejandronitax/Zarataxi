package com.example.zarataxi;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {

    //private static CustomDialogFragment pbarProgreso;
    private MainActivity context;
    private static CustomDialogFragment newFragment;

    public MyAsyncTask(Context contexto) {
        super();
        context = (MainActivity) contexto;
       /* pbarProgreso = new CustomDialogFragment ();
        pbarProgreso.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pbarProgreso.setProgressDrawable(R.layout.prograss_bar_dialog);
        //ProgressBar  mProgressBar1 = pbarProgreso.findViewById(R.id.progress_bar);
        //TextView progressText =  pbarProgreso.findViewById(R.id.progress_text);
        //progressText.setText("Guardando");
        //progressText.setVisibility(View.VISIBLE);
        //mProgressBar1.setVisibility(View.VISIBLE);
        //mProgressBar1.setIndeterminate(true);*/


        FragmentManager fragmentManager = context.getSupportFragmentManager();

        newFragment = new CustomDialogFragment();

        /*if (isLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog");*/
        //} else {
        // The device is smaller, so show the fragment fullscreen
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // For a little polish, specify a transition animation
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // To make it fullscreen, use the 'content' root view as the container
        // for the fragment, which is always the root view for the activity
        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();


    }

    public static void hideProgress() {

        if (newFragment != null) {
            newFragment.dismiss();
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            publishProgress(i * 10);
            if (isCancelled())
                break;
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(newFragment != null ){
            newFragment.dismiss();
        }

        if (result) {
            Toast.makeText(context, "Tarea finalizada true!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Tarea finalizada false!", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onCancelled() {
        newFragment.dismiss();
    }

}