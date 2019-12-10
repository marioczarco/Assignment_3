package assignment1_411.assignment3;

/**
 * Created by Zarco on 11/10/2019.
 */

import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import assignment1_411.assignment3.model.CourseEnrollment;
import assignment1_411.assignment3.model.PersistentManager;
import assignment1_411.assignment3.model.Student;
import assignment1_411.assignment3.model.StudentDB;


public class studentEnrollment extends AppCompatActivity {

    ArrayList<Student> students;
    protected Menu detailMenu;
    protected int studentIndx;
    protected Student sObj;
    protected final String TAG = "Detail Screen";
    ArrayList<CourseEnrollment> courseEnrollments;
    ArrayList<Student> mStudentList;
    PersistentManager pm;
    public studentEnrollment(){
        students = StudentDB.getInstance().getStudentList();
        courseEnrollments = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        studentIndx = getIntent().getIntExtra("StudentIndex", 0);
        TextView tv = (TextView) findViewById(R.id.display_string_id);

        String origStr = (String) tv.getText();
        tv.setText(origStr + studentIndx);
        tv.setTextSize(24);
        tv.setTextColor(Color.parseColor("#FFFFFF"));




        EditText editView = (EditText) findViewById(R.id.p_first_name_id);
        editView.setText("");
        editView = (EditText) findViewById(R.id.p_last_name_id);
        editView.setText("");
        editView = (EditText) findViewById(R.id.p_cwid);
        editView.setText("");




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
                        Toast.makeText(view.getContext(), "Class Added!", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.detail_add, menu);
        menu.findItem(R.id.action_done).setVisible(true);
        detailMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            pm = new PersistentManager(getApplicationContext());
            String studentFN;
            String studentLN;
            int  studentCWID;
            EditText editView = (EditText) findViewById(R.id.p_first_name_id);
            studentFN = editView.getText().toString();
            editView.setEnabled(false);
            editView = (EditText) findViewById(R.id.p_last_name_id);
            studentLN = " " + editView.getText().toString();
            editView.setEnabled(false);
            editView = (EditText) findViewById(R.id.p_cwid);
            studentCWID = Integer.valueOf(editView.getText().toString());
            editView.setEnabled(false);
            item.setVisible(false);
            sObj = new Student(studentFN, studentLN, studentCWID);
            pm.addStudent(studentFN, studentLN, studentCWID);
            for(int i = 0; i < courseEnrollments.size(); i++){
                pm.addCourse(courseEnrollments.get(i).getmCourseID(), courseEnrollments.get(i).getmGrade(), studentCWID);
            }
            sObj.setmCourseEnrollments(courseEnrollments);
            students.add(sObj);
            LinearLayout v = (LinearLayout) findViewById(R.id.wholeThing);
            Intent intent = new Intent(v.getContext(), SummaryLVActivity.class);
            v.getContext().startActivity(intent);
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
