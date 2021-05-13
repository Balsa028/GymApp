package com.balsa.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class YourPlanActivity extends AppCompatActivity {

    private TextView mondayEdit, tuesdayEdit, wednesdayEdit, thursdayEdit, fridayEdit, saturdayEdit, sundayEdit;
    private RecyclerView mondayRecView, tuesdayRecView, wednesdayRecView, thursdayRecView, fridayRecView, saturdayRecView, sundayRecView;
    private RelativeLayout noPlanRelativeLayout;
    private NestedScrollView nestedScrollView;
    private Button btnAddPlan;

    private PlanRecyclerViewAdapter mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter, saturdayAdapter, sundayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_plan);
        initViews();
        ArrayList<TrainingPlan> plans = Utils.getPlans();
        //checking for items in array list
        if (plans == null) {
            nestedScrollView.setVisibility(View.GONE);
            noPlanRelativeLayout.setVisibility(View.VISIBLE);
            btnAddPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(YourPlanActivity.this, AllTrainingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        } else {
            if (plans.size() > 0) {
                //in case there are plans in araylist
                nestedScrollView.setVisibility(View.VISIBLE);
                noPlanRelativeLayout.setVisibility(View.GONE);
                initRecViews();
                setEditClickListeners();
            } else {
                nestedScrollView.setVisibility(View.GONE);
                noPlanRelativeLayout.setVisibility(View.VISIBLE);
                btnAddPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YourPlanActivity.this, AllTrainingsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
        }


    }

    private void setEditClickListeners() {
        Intent intent = new Intent(YourPlanActivity.this, EditActivity.class);
        mondayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "monday");
                startActivity(intent);
            }
        });

        tuesdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "tuesday");
                startActivity(intent);
            }
        });

        wednesdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "wednesday");
                startActivity(intent);
            }
        });

        thursdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "thursday");
                startActivity(intent);
            }
        });

        fridayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "friday");
                startActivity(intent);
            }
        });

        saturdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "saturday");
                startActivity(intent);
            }
        });

        sundayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "sunday");
                startActivity(intent);
            }
        });
    }

    private void initRecViews() {
        mondayAdapter = new PlanRecyclerViewAdapter(this);
        mondayRecView.setAdapter(mondayAdapter);
        mondayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mondayAdapter.setPlans(getPlansByDay("monday"));

        tuesdayAdapter = new PlanRecyclerViewAdapter(this);
        tuesdayRecView.setAdapter(tuesdayAdapter);
        tuesdayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        tuesdayAdapter.setPlans(getPlansByDay("tuesday"));

        wednesdayAdapter = new PlanRecyclerViewAdapter(this);
        wednesdayRecView.setAdapter(wednesdayAdapter);
        wednesdayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        wednesdayAdapter.setPlans(getPlansByDay("wednesday"));

        thursdayAdapter = new PlanRecyclerViewAdapter(this);
        thursdayRecView.setAdapter(thursdayAdapter);
        thursdayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        thursdayAdapter.setPlans(getPlansByDay("thursday"));

        fridayAdapter = new PlanRecyclerViewAdapter(this);
        fridayRecView.setAdapter(fridayAdapter);
        fridayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        fridayAdapter.setPlans(getPlansByDay("friday"));

        saturdayAdapter = new PlanRecyclerViewAdapter(this);
        saturdayRecView.setAdapter(saturdayAdapter);
        saturdayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        saturdayAdapter.setPlans(getPlansByDay("saturday"));

        sundayAdapter = new PlanRecyclerViewAdapter(this);
        sundayRecView.setAdapter(sundayAdapter);
        sundayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        sundayAdapter.setPlans(getPlansByDay("sunday"));
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
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void initViews() {
        mondayEdit = findViewById(R.id.txtEditMonday);
        tuesdayEdit = findViewById(R.id.txtEditTuesday);
        wednesdayEdit = findViewById(R.id.txtEditWednesday);
        thursdayEdit = findViewById(R.id.txtEditThursday);
        fridayEdit = findViewById(R.id.txtEditFriday);
        saturdayEdit = findViewById(R.id.txtEditSaturday);
        sundayEdit = findViewById(R.id.txtEditSunday);

        mondayRecView = findViewById(R.id.mondayRecView);
        tuesdayRecView = findViewById(R.id.tuesdayRecView);
        wednesdayRecView = findViewById(R.id.wednesdayRecView);
        thursdayRecView = findViewById(R.id.thursdayRecView);
        fridayRecView = findViewById(R.id.fridayRecView);
        saturdayRecView = findViewById(R.id.saturdayRecView);
        sundayRecView = findViewById(R.id.sundayRecView);

        noPlanRelativeLayout = findViewById(R.id.noPlanRelativeLayout);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        btnAddPlan = findViewById(R.id.btnAddtoYourPlan2);
    }
}