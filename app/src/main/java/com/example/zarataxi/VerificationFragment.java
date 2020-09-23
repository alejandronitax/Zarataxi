package com.example.zarataxi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class VerificationFragment extends Fragment {

    private Context contexto;
    private SharedPreferences prefe;
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
        return inflater.inflate(R.layout.fragment_verification, container, false);
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

        prefe = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = prefe.edit();

        view.findViewById(R.id.buttonVerification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(VerificationFragment.this);
                        if(prefe.getBoolean("",true)) {
                            NavHostFragment.findNavController(VerificationFragment.this).navigate(R.id.action_verificationFragment_to_optionalRegisterFragment);
                        } else {
                            NavHostFragment.findNavController(VerificationFragment.this).navigate(R.id.action_verificationFragment2_to_mapsFragment);

                        }
            }
        });
    }
}
