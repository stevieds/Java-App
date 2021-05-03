package db;

import com.mysql.cj.conf.ConnectionUrlParser;
import model.*;
import org.apache.commons.lang.RandomStringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;

public class DbCastCouple {

    public static ResultSet getAllCharacters () throws SQLException {
        ResultSet rs;
        Conn conn = new Conn();
        conn.connect();
        String sql = "SELECT * FROM cast;";
        rs = conn.queryToSelect(sql);
        //conn.disconnect();
        return rs;
    }

    public static ArrayList<Pair> allCast () throws SQLException {
        ResultSet rs;
        ArrayList<Pair> cast = new ArrayList<>();
        Conn conn = new Conn();
        conn.connect();
        String sql = "SELECT cast.castid, cast.castname, cast.filmid, couple.coupleid, couple.voiceid FROM cast LEFT JOIN couple ON cast.castid = couple.castid;";
        rs = conn.queryToSelect(sql);
        ArrayList<Doppiatore> voices = DbStaff.allVoices();
        System.out.println(rs.toString());

        while (rs.next()) {
            Personaggio pers = new Personaggio(rs.getString("castname"), rs.getString("castid"));
            if (rs.getString("coupleid") != null) {
                for (int i=0; i<voices.size(); i++) {
                    if (voices.get(i).getStaffId().equals(rs.getString("voiceid"))) {
                        Coppia couple = new Coppia(voices.get(i), pers, rs.getString("coupleid"));
                        cast.add(new Pair(rs.getString("filmid"), couple));
                    }
                }
            }
            else {
                cast.add(new Pair(rs.getString("filmid"), new Coppia(pers)));
            }

        }
        return cast;
    }


    public static boolean addCast(Film film, Coppia couple) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String filmId = "'"+ film.getFilmId() +"'";
        String castId = "'ca"+ RandomStringUtils.random(9, true, true)+"'";
        String castName = "'"+ couple.getPersName() +"'";
        String sql = "";
            sql += "INSERT INTO cast VALUES ("+
                    castId.concat(", ").concat(castName).concat(", ").concat(filmId).concat(");");

        System.out.println(sql);

        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        sql = "";
        return result;
    }




    public static boolean deleteFromCast (Film film, Coppia couple) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String filmId = "'"+ film.getFilmId() +"'";
        String castId = "'"+ couple.getPers().getId() +"'";
        String sql = "DELETE FROM `cast` WHERE `cast`.`castid` = " + castId;
        System.out.println(sql);

        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        sql = "";
        return result;
    }

    public static boolean addCouple(Coppia couple) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String coupleId = "'co"+ RandomStringUtils.random(9, true, true)+"'";
        String voiceId = "'" + couple.getDopp().getStaffId() + "'";
        String castId = "'" + couple.getPers().getId() + "'";


        String sql = "INSERT into couple VALUES (" + coupleId + ", " + voiceId + ", " + castId + ");";
        System.out.println(sql);


        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        sql = "";
        return result;
    }

    public static boolean updateCouple(Coppia couple) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String coupleId = "'"+ couple.getCoupleId() +"'";
        String voiceId = "'" + couple.getDopp().getStaffId() + "'";

        String sql = "UPDATE couple SET voiceid =" + voiceId + "WHERE coupleid =" + coupleId +";";
        System.out.println(sql);

        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        sql = "";
        return result;
    }

    public static boolean updatePersName(Coppia couple, Personaggio pers) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String coupleId = "'"+ couple.getCoupleId() +"'";
        String persId = "'" + couple.getPers().getId() + "'";

        String sql =
        "UPDATE cast SET castName =" + "'" + pers.getName() + "'" + " WHERE castId =" + persId +";";
        System.out.println(sql);

        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        sql = "";
        return result;
    }


    public static boolean deleteFromCouple(Coppia couple) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String coupleId = "'"+ couple.getCoupleId() +"'";
        String sql = "DELETE FROM `couple` WHERE `couple`.`coupleid` = " + coupleId;

        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        sql = "";
        return result;
    }
}
