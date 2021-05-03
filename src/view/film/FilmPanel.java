package view.film;

import Controller.FilmController;
import db.DbFilm;
import model.Film;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmPanel extends JPanel {

    public FilmPanel ()  {
        ArrayList<Film> films = null;
        try {
            films = FilmController.listAllFilms();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int rows = films.toArray().length+1;
        this.setLayout(new GridLayout(rows, 4));
        this.add(new JLabel("Titolo originale"));
        this.add(new JLabel("Titolo italiano"));
        this.add(new JLabel("Stato"));
        this.add(new JLabel(""));
        for (Film film : films) {
            this.add(new JLabel(film.getoTitle()));
            this.add(new JLabel(film.getiTitle()));
            this.add(new JLabel(film.getStatus()));
            JButton open = new JButton();
            open.setAction(new FilmDetailsAction("Visualizza", film));
            this.add(open);
        }
    }
}
