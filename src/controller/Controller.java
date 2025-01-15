package controller;

import models.Logger;
import models.Model;
import views.MainView;

import javax.swing.*;
import java.util.*;
;
public class Controller implements ControllerInterface{

        Optional<MainView> mv = Optional.empty();
        Model model = new Model("OEM", 0);


        public Controller() {
        }

        @Override
        public int activate(String[] deactivations, String[] activations) {
            for (String feat : activations) {
                System.out.println("activated: " + feat);
                model.activateFeature(feat);
            }

            for (String feat : deactivations) {
                System.out.println("deactivated: " + feat);
                model.deactivateFeature(feat);
            }
            return 0;
        }


        @Override
        public boolean enableUIView() {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mv = Optional.of(new MainView());
            mv.get().addCourseButtonListener(new ChooseCourse(this));
            mv.get().addTextfieldListener(new EnterAnswer(this));
            mv.get().addHintButtonListener( new GetHint(this));
            mv.get().addChangeQuestionButtonListener(new ChangeQuestion(this));
            mv.get().addPremiumButtonListener(new PassToPremium(this));
            model.getStreak().addObserver(mv.get().getStreakLabelObserver());
            return true;
        }

        @Override
        public boolean disableUIView() {
            mv.ifPresent(MainView::closeWindow);
            mv = Optional.empty();
            return true;
        }

        @Override
        public String[] getStateAsLog() {
            List<String> currentStateLogs = Logger.getInstance().getAllCurrentStates();
            return currentStateLogs.toArray(new String[0]);
        }

        public static void main(String[] args) throws Exception {
            Controller controller = new Controller();
            controller.enableUIView();
            controller.activate(new String[]{}, new String[]{"PREMIUM"});
        }
    }
