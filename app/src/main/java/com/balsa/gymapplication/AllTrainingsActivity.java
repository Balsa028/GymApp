package com.balsa.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class AllTrainingsActivity extends AppCompatActivity {

    private RecyclerView RecView;
    private TrainingRecyclerViewAdapter adapter;
    private ArrayList<Training> trainings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trainings);

        RecView = findViewById(R.id.recView);
        trainings = Utils.getAllTrainings();
        adapter = new TrainingRecyclerViewAdapter(this);
        RecView.setAdapter(adapter);
        RecView.setLayoutManager(new GridLayoutManager(this, 2));
        if (trainings != null) {
            adapter.setTrainings(trainings);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllTrainingsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}