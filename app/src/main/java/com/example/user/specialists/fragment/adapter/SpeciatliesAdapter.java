package com.example.user.specialists.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.specialists.R;
import com.example.user.specialists.fragment.adapter.view_holder.SpeciatliesViewHolder;
import com.example.user.specialists.model.Specialty;

import java.util.List;

public class SpeciatliesAdapter extends RecyclerView.Adapter<SpeciatliesViewHolder> {
    private List<Specialty> specialties;
    private onItemSelectedListener itemSelectedListener;

    public SpeciatliesAdapter(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    @NonNull
    @Override
    public SpeciatliesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_in_list, viewGroup, false);
        return new SpeciatliesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciatliesViewHolder speciatliesViewHolder, int i) {
        final Specialty specialty = specialties.get(i);
        speciatliesViewHolder.getSpecialtyName().setText(specialty.getSpecName());

        speciatliesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.onItemSelected(specialty);
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public void setOnItemSelectedListener(onItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }


    public interface onItemSelectedListener {
        void onItemSelected(Specialty specialty);
    }
}
