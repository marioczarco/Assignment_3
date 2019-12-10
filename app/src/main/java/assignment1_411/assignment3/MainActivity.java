package assignment1_411.assignment3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import assignment1_411.assignment3.model.Student;
import assignment1_411.assignment3.model.StudentDB;

public class MainActivity extends AppCompatActivity {

    LinearLayout _root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createPersonObjects();
        setContentView(R.layout.student_list);

        _root = (LinearLayout)findViewById(R.id.student_list);

        ArrayList<Student> studentList = StudentDB.getInstance().getStudentList();

        for (int i=0; i<studentList.size(); i++) {
            Student p = studentList.get(i);
            //
            LayoutInflater inflater = LayoutInflater.from(this);
            View row_view = inflater.inflate(R.layout.student_row, _root, false);
            //
            ((TextView) row_view.findViewById(R.id.first_name)).setText(p.getFirstName());
            ((TextView) row_view.findViewById(R.id.last_name)).setText(p.getLastName());
            //((TextView) row_view.findViewById(R.id.cwid)).setText(Integer.valueOf(p.getcwid()).toString());
            _root.addView(row_view);
        }

    }

    protected void createPersonObjects() {}
}
