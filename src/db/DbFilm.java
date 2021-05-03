package db;

import javafx.util.Pair;
import model.*;
import org.apache.commons.lang.RandomStringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbFilm {




    public static boolean insert (Film film) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String iTitle = "'"+film.getiTitle()+"'";
        String oTitle = "'"+film.getoTitle()+"'";
        String length = "'"+film.lengthToString()+"'";
        String status = "'"+film.getStatus().toLowerCase()+"'";
        String id = "'fi"+ RandomStringUtils.random(9, true, true)+"'";
        String sql = "INSERT INTO film VALUES ("+
                id.concat(", ").concat(iTitle).concat(", ").concat(oTitle).concat(", ").concat(length).concat(
                        ", ").concat(status).concat(")");


        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        return result;
    }

    public static ArrayList<Film> allFilm () throws SQLException {
        ArrayList<Film> films = new ArrayList<>();
        Conn conn = new Conn();
        conn.connect();
        String sql = "SELECT * FROM film;";
        ResultSet rs = conn.queryToSelect(sql);
        while (rs.next()) {
            Film film = new Film(rs.getString("ititle"), rs.getString(
                    "otitle"), new MyTime(rs.getString("length")),
                    Status.valueOf(rs.getString("status").toUpperCase()));
            film.setFilmId(rs.getString("filmid"));
            films.add(film);
        }
        conn.disconnect();
        return films;
    }
}
