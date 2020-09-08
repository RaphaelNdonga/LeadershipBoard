package com.inihood.leardershipboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inihood.leardershipboard.R;
import com.inihood.leardershipboard.model.UsersLearners;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.MyViewHolder> {

    Context context;
    private List<UsersLearners> usersLearnersList;
    private int lastPosition = 0;

    public LearningAdapter(Context context, List<UsersLearners> usersLearnersList){
        this.context = context;
        this.usersLearnersList = usersLearnersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.learning_single_item, parent, false);
        return new MyViewHolder(v);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.user_name.setText(usersLearnersList.get(position).getName());
        int score = usersLearnersList.get(position).getHours();
        String country = usersLearnersList.get(position).getCountry();
        String image = usersLearnersList.get(position).getBadgeUrl();
        holder.score.setText(new StringBuilder().append(score).append(context.getString(R.string.hrs)).append(country).toString());
       // holder.score.setText("learning hours, " + country);
        if(!image.isEmpty()){
            Glide.with(context).load(image).into(holder.badge);
        }

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_name, score;
        ImageView badge;
        MyViewHolder(View view) {
            super(view);
            user_name = view.findViewById(R.id.name);
            score = view.findViewById(R.id.hrs);
            badge = view.findViewById(R.id.badge);
        }
    }

    @Override
    public int getItemCount() {
        return usersLearnersList.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}