package assignment1_411.assignment3.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import assignment1_411.assignment3.R;
import assignment1_411.assignment3.StudentDetailActivity;
import assignment1_411.assignment3.model.Student;
import assignment1_411.assignment3.model.StudentDB;

public class SummaryListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return StudentDB.getInstance().getStudentList().size();
    }

    @Override
    public Object getItem(int i) {
        return StudentDB.getInstance().getStudentList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row_view;
        if (view == null) {
            //Add a new row from layout inflater if ListView is empty
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
            row_view.setPadding(10,10,10,10);

        } else row_view = view;
            Student p = StudentDB.getInstance().getStudentList().get(i);
            ((TextView) row_view.findViewById(R.id.first_name)).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) row_view.findViewById(R.id.last_name)).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) row_view.findViewById(R.id.first_name)).setTextSize(24);
            ((TextView) row_view.findViewById(R.id.last_name)).setTextSize(24);
            ((TextView) row_view.findViewById(R.id.first_name)).setText(p.getFirstName());
            ((TextView) row_view.findViewById(R.id.last_name)).setText(p.getLastName());

        row_view.setTag(new Integer(i));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Toast.makeText(view.getContext(), "View Object was touched by user.", Toast.LENGTH_SHORT).show();

                        // Page Navigation
                        Intent intent = new Intent(view.getContext(), StudentDetailActivity.class);
                        intent.putExtra("StudentIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;
    }
}
