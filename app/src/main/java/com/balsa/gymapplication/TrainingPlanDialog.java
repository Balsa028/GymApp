package com.balsa.gymapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TrainingPlanDialog extends DialogFragment {

    private PassingPlanInterface passingPlanInterface;
    private EditText edtTextMinutes;
    private Spinner spinner;
    private TextView txtName;
    private Button btnDismiss, btnAdd;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.training_plan_dialog, null);
        initViews(view);
        //creating custom dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Training training = bundle.getParcelable("training");
            if (training != null) {
                txtName.setText("Training name: " + training.getName());
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = spinner.getSelectedItem().toString();
                        int minutes = Integer.parseInt(edtTextMinutes.getText().toString());
                        TrainingPlan plan = new TrainingPlan(training, minutes, day, false);
                        try {
                            passingPlanInterface = (PassingPlanInterface) getActivity();
                            passingPlanInterface.passingTrainingPlan(plan);
                            dismiss();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            dismiss();
                        }
                    }
                });
            }
        }

        return builder.create();
    }

    private void initViews(View view) {
        edtTextMinutes = view.findViewById(R.id.edtTextMinutes);
        spinner = view.findViewById(R.id.spinnerDays);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        btnAdd = view.findViewById(R.id.btnAdd);
        txtName = view.findViewById(R.id.txtTrainingName);
    }
}
