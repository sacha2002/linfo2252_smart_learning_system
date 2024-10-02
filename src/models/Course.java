package models;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private ArrayList<String> questions = new ArrayList<>();
    private int courseRank;


    public Course(){

        }

    public void setMyCourses(ArrayList<String> myCourses) {

    }

    public ArrayList<String> getMyquestions() {
        return questions;
    }


}
