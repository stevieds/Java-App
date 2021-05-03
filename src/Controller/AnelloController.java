package Controller;

import db.DbAnello;
import model.Anello;
import model.Coppia;
import model.Film;
import model.Personaggio;

public class AnelloController {

    public static boolean deleteAnello (Anello anello, Film film) {
        film.getAnelli().remove(anello);
        if (DbAnello.deleteAnelloCouple(anello)) {
            return DbAnello.deleteAnello(anello);
        } else {
            return false;
        }
    }

    public static boolean addAnello (Film film, Anello anello) {
        if (DbAnello.insert(film, anello)) {
            film.addAnello(anello);
            return true;
        };
        return false;
    }

    public static boolean editAnelloCouple (Anello anello, Coppia coppia, Film film) {
        if (CoupleController.addCouple(coppia, film)) {
        if (anello.getCouple() == null) {
            anello.addCouple(coppia);
            return DbAnello.insertAnelloCouple(anello, coppia);
        } else {
            anello.addCouple(coppia);
            return DbAnello.editAnelloCouple(anello, coppia);
        }
        } else {
            return false;
        }
    }






}
