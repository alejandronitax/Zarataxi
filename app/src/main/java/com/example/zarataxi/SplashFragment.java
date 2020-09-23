package com.example.zarataxi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashFragment extends Fragment {

    private Context contexto;
    private final int DurationSplash = 1500;
    /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashFragment() {
        // Required empty public constructor
    }*/

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        contexto = context;
    }

/*
    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // This callback will only be called when MyFragment is at least Started.


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_splash, container, false);

        ImageView imageView = view.findViewById(R.id.imageView4);
        TextView textoSplash = view.findViewById(R.id.textView8);

        Animation leftImage, downText;

        leftImage = AnimationUtils.loadAnimation(contexto, R.anim.lefttraslate);
        downText = AnimationUtils.loadAnimation(contexto, R.anim.downtraslate);
        /*ActionBar actionBar = getSupportActionBar();*/

  /*      if(actionBar.isShowing()) {
            actionBar.hide();
        }
        else {
            actionBar.show();
        }
*/
        imageView.setAnimation(leftImage);
        textoSplash.setAnimation(downText);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/*
                SharedPreferences prefe = getSharedPreferences("preferences", Context.MODE_PRIVATE);
*/
                Intent intent;

                NavHostFragment.findNavController(SplashFragment.this)
                        .navigate(R.id.action_SplashFragment_to_PromotionFragment);

                //getActivity().finish();
/*                if (!navController.popBackStack()) {
                    // Call finish() on your Activity
                    finish();
                }*/

            }
        },DurationSplash);


        return view;
    }

}