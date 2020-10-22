package com.example.zarataxi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class VerificationFragment extends Fragment {

    private static final int RESULT_OK = 1234;
    private static final int RESOLVE_HINT =342 ;
    private Context contexto;
    private SharedPreferences prefe;
    private String code = "";

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

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
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

        final EditText editText = view.findViewById(R.id.editTextNumber);
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager) contexto.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        final EditText editText2 = view.findViewById(R.id.editTextNumber2);
        final EditText editText3 = view.findViewById(R.id.editTextNumber3);
        final EditText editText4 = view.findViewById(R.id.editTextNumber4);

        changeFocusAndGetCode(editText,editText2);
        changeFocusAndGetCode(editText2,editText3);
        changeFocusAndGetCode(editText3,editText4);
        changeFocusAndGetCode(editText4,editText4);

    }

    private void changeFocusAndGetCode(final EditText editText,final EditText editText2) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!editText.getText().toString().isEmpty()){
                    editText2.requestFocus();
                    code += editText.getText().toString();
                }

                if(code.length()==4){

                   // InputMethodManager inputMethodManager = (InputMethodManager) contexto.getSystemService(Context.INPUT_METHOD_SERVICE);
                    //inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    NavHostFragment.findNavController(VerificationFragment.this);
                    if(prefe.getBoolean("",true)) {
                        NavHostFragment.findNavController(VerificationFragment.this).navigate(R.id.action_verificationFragment_to_optionalRegisterFragment);
                    } else {
                      //  NavHostFragment.findNavController(VerificationFragment.this).navigate(R.id.action_verificationFragment2_to_mapsFragment);
                    }

                }

            }
        });



    }

/*    string service = "https://gatewayapi.com/rest/mtsms?" + "token=GFCXRQ0qRdOL2DveVRs9lrUn6U3NBHLCji1zsD2Bjt6ySWflUxkg_Y-6gTNGLO6M";
    string recipients = "";
                if (phoneNumber.Length == 9) recipients = "recipients.0.msisdn=" + "34" + phoneNumber; //9digits
                else recipients = "recipients.0.msisdn=" + phoneNumber;  //11 digits
    string sender = "sender=" + Constants.CompanyAcronym;
    string message = "message=" + SIDUSTaxi4YouResources.SMSTextForCode.Replace(" ", "+") + code.ToString();
    string url = service + "&" + recipients + "&" + message + "&" + sender;*/


    /*
    // Construct a request for phone numbers and show the picker
    private void requestHint() {
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

         apiClient = null;
        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(apiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),RESOLVE_HINT, null, 0, 0, 0,null);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    // Obtain the phone number from the result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                // credential.getId();  <-- will need to process phone number string
            }
        }
    }

     */
}
