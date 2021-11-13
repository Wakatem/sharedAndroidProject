package com.example.project242.Students;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project242.Home.HomeSection;
import com.example.project242.R;

import java.util.ArrayList;

public class FragmentAllStudents extends Fragment {

    ListView listView;
    AdapterAllStudents studentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_students, container, false);

        listView = (ListView) view.findViewById(R.id.fragment_all_students_listView_all_students_1);
        studentAdapter = new AdapterAllStudents(getContext(), HomeSection.allStudentsArrayList, listView);
        listView.setAdapter(studentAdapter);

        return listView;
    }
}