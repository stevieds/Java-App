package Controller;

import db.Conn;
import db.DbAnello;
import db.DbCastCouple;
import model.Anello;
import model.Coppia;
import model.Film;
import model.Personaggio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoupleController {

    public static boolean addCouple (Coppia coppia, Film film) {
        if (DbCastCouple.addCouple(coppia)) {
            film.addCouple(coppia);
            return true;
        };
        return false;
    }


    public static ArrayList getAllCharacters () throws SQLException {
        ArrayList cast = new ArrayList();
        ResultSet rs = DbCastCouple.getAllCharacters();
        while (rs.next()) {
            Personaggio pers = new Personaggio(rs.getString("castname"), rs.getString("castid") );
            cast.add(pers);
        }
        return cast;

    }

    public static String addCastToFilm (Film film, Coppia couple, Personaggio pers) {
            if (!film.checkPersonaggio(pers.getName())) {
                if (DbCastCouple.updatePersName(couple, pers)) {
                    couple.setPers(pers);
                    return "Operazione eseguita correttamente.";
                } else {
                    return "Si è verificato un errore";
                }
            } else {
                return "ERRORE \n Il personaggio esiste già.";
            }
    }








}
