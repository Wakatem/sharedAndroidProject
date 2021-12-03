package com.example.project242.Students;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.project242.general.DataContainer;
import com.example.project242.R;

public class FragmentAllStudents extends Fragment {

    ListView listView;
    AdapterAllStudents studentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_students, container, false);

        listView = (ListView) view.findViewById(R.id.fragment_all_students_listView_all_students_1);
        studentAdapter = new AdapterAllStudents(getContext(), DataContainer.allStudentsArrayList, listView);
        listView.setAdapter(studentAdapter);

        return listView;
    }

    @Override
    public void onResume() {
        super.onResume();

        studentAdapter.notifyDataSetChanged();
        listView.invalidateViews();
    }
}