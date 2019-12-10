package assignment1_411.assignment3.model;

import java.util.ArrayList;

public class Student {
    protected String mFirstName;
    protected String mLastName;
    protected int mcwid;

    protected ArrayList<CourseEnrollment> mCourseEnrollment;

    public Student(String fName, String lName, int _cwid) {
        mFirstName = fName;
        mLastName = lName;
        mcwid = _cwid;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getcwid() {
        return mcwid;
    }

    public void setcwid(int _cwid) {
        mcwid = _cwid;
    }

    public ArrayList<CourseEnrollment> getmCourseEnrollments() {
        return mCourseEnrollment;
    }

    public void setmCourseEnrollments(ArrayList<CourseEnrollment> courseEnrollments) {
        mCourseEnrollment = courseEnrollments;
    }
}
