package com.balsa.gymapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class PlanRecyclerViewAdapter extends RecyclerView.Adapter<PlanRecyclerViewAdapter.ViewHolder> {

    private RemovePlanInterface removePlanInterface;

    private Context context;
    private ArrayList<TrainingPlan> plans = new ArrayList<>();
    private String type = "";

    public void setPlans(ArrayList<TrainingPlan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public PlanRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTrainingName.setText(plans.get(position).getTraining().getName());
        holder.txtTime.setText("Duration: " + String.valueOf(plans.get(position).getMinutes()) + " minutes");
        holder.txtDiscription.setText("Discription: " + plans.get(position).getTraining().getShortDiscription());
        Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.trainingImage);
        if (plans.get(position).isAccomplished()) {
            holder.emptyCircle.setVisibility(View.GONE);
            holder.checkedCircle.setVisibility(View.VISIBLE);
        } else {
            holder.emptyCircle.setVisibility(View.VISIBLE);
            holder.checkedCircle.setVisibility(View.GONE);
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("training", plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

        if (type.equals("edit")) {
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Did you finish this training?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (TrainingPlan plan : Utils.getPlans()) {
                                if (plan.equals(plans.get(position))) {
                                    plan.setAccomplished(true);
                                }
                            }
                            notifyDataSetChanged();
                        }
                    });
                    builder.create().show();
                }
            });

            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setMessage("Are you sure you want to delete this plan?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        removePlanInterface = (RemovePlanInterface) context;
                                        removePlanInterface.removePlan(plans.get(position));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                    builder.create().show();
                    return true;
                }
            });

        }
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTrainingName, txtTime, txtDiscription;
        private ImageView trainingImage, emptyCircle, checkedCircle;
        private MaterialCardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTrainingName = itemView.findViewById(R.id.txtTrainName);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtDiscription = itemView.findViewById(R.id.txtShortTrainingDisc);
            trainingImage = itemView.findViewById(R.id.trainingImage);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);
            checkedCircle = itemView.findViewById(R.id.checkedCircle);
            parent = itemView.findViewById(R.id.parent);

        }
    }
}

