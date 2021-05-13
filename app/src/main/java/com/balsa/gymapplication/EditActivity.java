package com.balsa.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements RemovePlanInterface {

    private TextView txtDay;
    private RecyclerView editRecView;
    private Button btnAddMorePlan;
    private PlanRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();
        adapter = new PlanRecyclerViewAdapter(this);
        adapter.setType("edit");
        editRecView.setAdapter(adapter);
        editRecView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent != null) {
            String day = intent.getStringExtra("day");
            if (day != null) {
                txtDay.setText(day);

                ArrayList<TrainingPlan> plansByDay = getPlansByDay(day);
                adapter.setPlans(plansByDay);

            }
        }
        btnAddMorePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlanIntent = new Intent(EditActivity.this, AllTrainingsActivity.class);
                startActivity(addPlanIntent);
            }
        });


    }

    private ArrayList<TrainingPlan> getPlansByDay(String day) {
        ArrayList<TrainingPlan> plans = Utils.getPlans();
        ArrayList<TrainingPlan> plansByDay = new ArrayList<>();
        for (TrainingPlan plan : plans) {
            if (plan.getDay().equalsIgnoreCase(day)) {
                plansByDay.add(plan);
            }
        }
        return plansByDay;
    }

    @Override
    public void removePlan(TrainingPlan plan) {
        if (Utils.removePlan(plan)) {
            Toast.makeText(this, "You have successfully removed plan", Toast.LENGTH_SHORT).show();
            //refreshing list in below , because for some reasom method notify data change dont work
            adapter.setPlans(getPlansByDay(plan.getDay()));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditActivity.this, YourPlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initViews() {
        txtDay = findViewById(R.id.txtDay);
        editRecView = findViewById(R.id.EditRecView);
        btnAddMorePlan = findViewById(R.id.btnAddMorePlans);
    }
}