package com.balsa.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PassingPlanInterface {

    private Button btnAddToYourPlan;
    private TextView txtName, txtLongDiscription;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            Training training = intent.getParcelableExtra("training");
            if (training != null) {
                txtName.setText(training.getName());
                txtLongDiscription.setText(training.getLongDiscription());
                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(image);
                btnAddToYourPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TrainingPlanDialog dialog = new TrainingPlanDialog();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("training", training);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "plan detail dialog");
                    }
                });
            }
        }

    }

    private void initViews() {
        btnAddToYourPlan = findViewById(R.id.btnAddToYourPlan);
        txtName = findViewById(R.id.txtTrainingName);
        txtLongDiscription = findViewById(R.id.txtLongDisc);
        image = findViewById(R.id.image);
    }

    @Override
    public void passingTrainingPlan(TrainingPlan plan) {
        if (Utils.addPlan(plan)) {
            Toast.makeText(this, "You have successfully added plan.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TrainingActivity.this, YourPlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}