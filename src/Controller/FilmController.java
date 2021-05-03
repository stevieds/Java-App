package Controller;

import db.DbAnello;
import db.DbCastCouple;
import db.DbFilm;
import javafx.util.Pair;
import model.*;
import view.film.FilmDetailsAction;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmController {

    public static Film populateCoppie (Film film) {

        ArrayList<Pair> couplesFromDb = null;

        try {
            couplesFromDb = DbCastCouple.allCast();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i =0; i<couplesFromDb.size(); i++) {
            if (film.getFilmId().equals(couplesFromDb.get(i).getKey())) {
                film.addCouple((Coppia) couplesFromDb.get(i).getValue());
            }
        }
        return film;
    }





    public static Film populateAnelli (Film film) {
        ArrayList<Pair> anelliFromDb = null;
        try {
            anelliFromDb = DbAnello.selectAnelli();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (int i =0; i<anelliFromDb.size(); i++) {
            if (film.getFilmId().equals(anelliFromDb.get(i).getKey())) {
                film.addAnello((Anello) anelliFromDb.get(i).getValue());
            }
        }
        return film;
    }

    public static ArrayList<Film> listAllFilms () throws SQLException {
        return DbFilm.allFilm();
    }

}
