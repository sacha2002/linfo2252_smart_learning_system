package views;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class MainView {

   private JFrame frame;
   private JPanel panel;

   private List<JLabel> exercises = new ArrayList<>();

    public MainView(){
        // Create window with title "application" that stop the program when closed
        this.frame = new JFrame("LEARNING APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating a panel that will hold the buttons
        panel = new JPanel();

        // Setup frame with the panel
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true); // show the window
        JLabel title = new JLabel("learning app");
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        panel.add(title);

        frame.repaint();
        frame.revalidate();

    }

    public void displayExercise(String question){

        JLabel label1 = new JLabel(question);
        panel.add(label1);
        exercises.add(label1);

        frame.repaint();
        frame.revalidate();
    }

    public void clearLabels(){
        for( JLabel label : exercises){
            panel.remove(label);
        }
    }



}
