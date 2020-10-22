package com.example.zarataxi;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
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

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        contexto = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(contexto, "atras", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        ImageView imageView = view.findViewById(R.id.imageViewVarius);
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
                NavHostFragment.findNavController(SplashFragment.this)
                        .navigate(R.id.action_SplashFragment_to_PromotionFragment);

                //getActivity().finish();
/*                if (!navController.popBackStack()) {
                    // Call finish() on your Activity
                    finish();
                }*/

            }
        },DurationSplash);

    }


}