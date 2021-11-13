package com.example.project242.Students;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.project242.Home.Home;
import com.example.project242.R;

import java.util.ArrayList;

public class FragmentCurrentStudents extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_students, container, false);

        listView = (ListView) view.findViewById(R.id.fragment_current_students_listView_current_students_1);
        AdapterCurrentStudents studentAdapter = new AdapterCurrentStudents(getActivity(), Home.currentStudentsArrayList);
        listView.setAdapter(studentAdapter);

        return view;
    }
}