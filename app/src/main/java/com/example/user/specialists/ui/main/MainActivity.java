package com.example.user.specialists.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.specialists.R;
import com.example.user.specialists.fragment.EmployeesFragment;
import com.example.user.specialists.fragment.SpecialityFragment;

public class MainActivity extends AppCompatActivity {
    private SpecialityFragment.OnItemTapListener onItemTapListener = new SpecialityFragment.OnItemTapListener() {
        @Override
        public void getSpecialtyName(String specName) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EmployeesFragment.newInstance(specName))
                    .commitNow();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        SpecialityFragment specialityFragment = SpecialityFragment.newInstance();
        specialityFragment.setOnItemTapListener(onItemTapListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, specialityFragment)
                    .commitNow();
        }
    }
}
