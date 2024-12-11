package controller;

import models.Model;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class PassToPremium  implements ActionListener{

    private final Optional<MainView> mv;
    private final Model model;
    private final Controller controller;

    public PassToPremium(Controller controller) {
        this.mv = controller.mv;
        this.model = controller.model;
        this.controller = controller;

    }


        private boolean isPremium = true;
        public void actionPerformed(ActionEvent e) {
            mv.ifPresent(mv -> {
                if (e.getSource() == mv.getPremiumButton()) {
                    if (isPremium) {
                        isPremium = false;
                        controller.activate(new String[]{"PREMIUM"}, new String[]{});

                        model.getEnergySystem().addObserver(mv.getPremiumLabelObserver());

                    } else {
                        isPremium = true;
                        controller.activate(new String[]{}, new String[]{"PREMIUM"});
                        model.getEnergySystem().removeObserver(mv.getPremiumLabelObserver());

                    }

                }
            });
        }

}
