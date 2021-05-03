package view.film;

import model.Film;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FilmDetailsAction extends AbstractAction {
    Film film;
    JLabel oTitle;
    JLabel iTitle;
    JPanel top;
    JPanel middle;
    JPopupMenu status;
    JPanel couples;
    JPanel timeFrames;


    public FilmDetailsAction(String text, Film film) {
        super(text);
        this.film=film;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PopUpFilmDetails dialog = new PopUpFilmDetails(this.film);
        dialog.setVisible(true);
    }

    @Override
    public boolean accept(Object sender) {
        return false;
    }
}
