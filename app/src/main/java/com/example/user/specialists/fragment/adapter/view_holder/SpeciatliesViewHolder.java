package com.example.user.specialists.fragment.adapter.view_holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.specialists.R;

public class SpeciatliesViewHolder extends RecyclerView.ViewHolder {
    private TextView specialtyName;

    public SpeciatliesViewHolder(@NonNull View itemView) {
        super(itemView);
        specialtyName = itemView.findViewById(R.id.specialty);
    }

    public TextView getSpecialtyName() {
        return specialtyName;
    }
}
