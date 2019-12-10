package assignment1_411.assignment3.model;

import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB ourInstance = new StudentDB();

    private ArrayList<Student> mStudentList;
    private ArrayList<CourseEnrollment> courseEnrollments;

    static public StudentDB getInstance() {
        return ourInstance;
    }

    public StudentDB() {
        mStudentList = new ArrayList<>();
        courseEnrollments = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return mStudentList;
    }

    public void setPersonList(ArrayList<Student> studentList) {
        mStudentList = studentList;
    }



    protected void createStudentObjects(String fName, String lName, int _cwid, ArrayList<CourseEnrollment> CE) {
        courseEnrollments = new ArrayList<>();
        Student student = new Student(fName, lName, _cwid);
        courseEnrollments = CE;
        student.setmCourseEnrollments(courseEnrollments);
        mStudentList.add(student);
    }
}
