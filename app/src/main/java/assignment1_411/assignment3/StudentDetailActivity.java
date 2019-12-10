package assignment1_411.assignment3;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import assignment1_411.assignment3.model.CourseEnrollment;
import assignment1_411.assignment3.model.PersistentManager;
import assignment1_411.assignment3.model.Student;
import assignment1_411.assignment3.model.StudentDB;

public class StudentDetailActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected int studentIndx;
    protected Student sObj;
    protected final String TAG = "Detail Screen";
    ArrayList<CourseEnrollment> courseEnrollments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final PersistentManager pm = new PersistentManager(getApplicationContext());
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        studentIndx = getIntent().getIntExtra("StudentIndex", 0);
        TextView tv = (TextView) findViewById(R.id.display_string_id);

        String origStr = (String) tv.getText();
        tv.setText(origStr + studentIndx);
        tv.setTextSize(24);
        tv.setTextColor(Color.parseColor("#FFFFFF"));


        sObj = StudentDB.getInstance().getStudentList().get(studentIndx);
        courseEnrollments = StudentDB.getInstance().getStudentList().get(studentIndx).getmCourseEnrollments();

        LinearLayout ll = (LinearLayout) findViewById(R.id.courses);
        ll.removeAllViews();
        for(int i = 0; i < courseEnrollments.size(); i++) {
            LinearLayout hll = new LinearLayout(this);
            hll.setOrientation(LinearLayout.HORIZONTAL);
            TextView textV = new TextView(this);
            TextView textV2 = new TextView(this);
            textV.setText(courseEnrollments.get(i).getmCourseID());
            hll.addView(textV, hll.getChildCount());
            textV2.setText(" " + courseEnrollments.get(i).getmGrade());
            hll.addView(textV2);
            ll.addView(hll);
        }


        EditText editView = (EditText) findViewById(R.id.p_first_name_id);
        editView.setText(sObj.getFirstName());
        editView.setEnabled(false);
        editView = (EditText) findViewById(R.id.p_last_name_id);
        editView.setText(sObj.getLastName());
        editView.setEnabled(false);
        editView = (EditText) findViewById(R.id.p_cwid);
        editView.setText(Integer.valueOf(sObj.getcwid()).toString());
        editView.setEnabled(false);


        ((Button) findViewById(R.id.p_add_course)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editView = (EditText) findViewById(R.id.p_course_id);
                        String courseid = editView.getText().toString();
                        editView.setEnabled(true);
                        editView = (EditText) findViewById(R.id.p_grade);
                        String grade = editView.getText().toString();
                        editView.setEnabled(true);
                        courseEnrollments.add(new CourseEnrollment(courseid, grade));
                        pm.addCourse(courseid, grade, sObj.getcwid());
                        sObj.setmCourseEnrollments(courseEnrollments);
                        makeNewTexts(courseEnrollments);
                    }
                }
        );
    }

    public void makeNewTexts(ArrayList<CourseEnrollment> courseEnrollments_){
        LinearLayout ll = (LinearLayout) findViewById(R.id.courses);
        ll.removeAllViews();
        for(int i = 0; i < courseEnrollments_.size(); i++) {
            LinearLayout hll = new LinearLayout(this);
            hll.setOrientation(LinearLayout.HORIZONTAL);
            TextView textV = new TextView(this);
            TextView textV2 = new TextView(this);
            textV.setText(courseEnrollments_.get(i).getmCourseID());
            hll.addView(textV, hll.getChildCount());
            textV2.setText(" " + courseEnrollments_.get(i).getmGrade());
            hll.addView(textV2);
            ll.addView(hll);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);
        detailMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            EditText editView = (EditText) findViewById(R.id.p_first_name_id);
            editView.setEnabled(true);
            editView = (EditText) findViewById(R.id.p_last_name_id);
            editView.setEnabled(true);
            editView = (EditText) findViewById(R.id.p_cwid);
            editView.setEnabled(true);

            item.setVisible(false);
            detailMenu.findItem(R.id.action_done).setVisible(true);
        } else if (item.getItemId() == R.id.action_done) {
            EditText editView = (EditText) findViewById(R.id.p_first_name_id);
            StudentDB.getInstance().getStudentList().get(studentIndx).setFirstName(editView.getText().toString());
            editView.setEnabled(false);
            editView = (EditText) findViewById(R.id.p_last_name_id);
            StudentDB.getInstance().getStudentList().get(studentIndx).setLastName(editView.getText().toString());
            editView.setEnabled(false);
            editView = (EditText) findViewById(R.id.p_cwid);
            StudentDB.getInstance().getStudentList().get(studentIndx).setcwid(Integer.valueOf(editView.getText().toString()));
            editView.setEnabled(false);
            item.setVisible(false);
            detailMenu.findItem(R.id.action_edit).setVisible(true);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }

}
