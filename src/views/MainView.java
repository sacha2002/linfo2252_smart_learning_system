package views;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class MainView {

   private JFrame frame;
   private JPanel panel;
   private BorderLayout borderLayout1;

   private List<JLabel> exercises = new ArrayList<>();

    public MainView(){
        // Create window with title "application" that stop the program when closed
        this.frame = new JFrame("LEARNING APP");
        this.borderLayout1 = new BorderLayout();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating a panel that will hold the buttons
        panel = new JPanel();
        panel.setLayout(borderLayout1);

        // Setup frame with the panel
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true); // show the window
        JLabel title = new JLabel("learning app",JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        panel.add(title, BorderLayout.NORTH);

        frame.repaint();
        frame.revalidate();

    }

    public void displayExercise(String question){
        this.clearLabels();
        JLabel label1 = new JLabel(question,JLabel.CENTER);
        panel.add(label1, BorderLayout.CENTER);
        exercises.add(label1);
        frame.repaint();
        frame.revalidate();

    }

    public void clearLabels(){
        for( JLabel label : exercises){
            panel.remove(label);
        }
    }

    public void closeWindow() {
        frame.dispose();
    }



}
