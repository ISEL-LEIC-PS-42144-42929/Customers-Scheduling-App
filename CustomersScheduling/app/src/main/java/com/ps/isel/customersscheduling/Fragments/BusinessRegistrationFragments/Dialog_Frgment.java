package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.UserInfoContainer;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Dialog_Frgment extends DialogFragment {

    private CustomersSchedulingApp customersSchedulingApp;

    private TextView insertNIF;
    private EditText nif;
    private Button ok;
    private Button cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dialog_nif, null);

        Toast.makeText(getActivity(), "Because it´s the first time you register a store you have to insert your NIF",Toast.LENGTH_LONG).show();

        insertNIF         = v.findViewById(R.id.nifDialog);
        nif         = v.findViewById(R.id.nif);
        ok         = v.findViewById(R.id.ok);
        cancel         = v.findViewById(R.id.cancel);

        customersSchedulingApp = ((CustomersSchedulingApp)getActivity().getApplicationContext());

        addListenersToButtons();
        return v;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenersToButtons() {

        ok.setOnClickListener(view -> {
            JSONObject json = new JSONObject();

            try {
                json.put("email", UserInfoContainer.getInstance().getEmail());
                json.put("nif",nif.getText());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            customersSchedulingApp.registerOwner(json);
            dismiss();
        });

        cancel.setOnClickListener(view -> dismiss());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        return alert.show();
    }
}
