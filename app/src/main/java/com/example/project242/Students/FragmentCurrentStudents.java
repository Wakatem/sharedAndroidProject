package com.example.project242.Students;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.project242.DataContainer;
import com.example.project242.Home.HomeSection;
import com.example.project242.R;

import java.util.ArrayList;

public class FragmentCurrentStudents extends Fragment {
    private ListView listView;
    private AdapterCurrentStudents studentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_students, container, false);

        listView = (ListView) view.findViewById(R.id.fragment_current_students_listView_current_students_1);
        studentAdapter = new AdapterCurrentStudents(getContext(), DataContainer.currentStudentsArrayList, listView);
        listView.setAdapter(studentAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        studentAdapter.notifyDataSetChanged();
        listView.invalidateViews();
    }
}