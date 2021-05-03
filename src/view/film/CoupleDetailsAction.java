package view.film;

import model.Coppia;
import model.Film;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CoupleDetailsAction extends AbstractAction {
    Film film;
    Coppia couple;

    public CoupleDetailsAction(Film film, Coppia couple) {
        this.film=film;
        this.couple = couple;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PopUpAddVoiceToCouple dialog = null;
        try {
            dialog = new PopUpAddVoiceToCouple(this.film, this.couple);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dialog.setVisible(true);
    }

    @Override
    public boolean accept(Object sender) {
        return false;
    }
}
