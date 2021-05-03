package db;

import model.Personale;
import org.apache.commons.lang.RandomStringUtils;

public class DbSound {


    public static boolean insert (Personale dir) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String fName = "'"+dir.getFName()+"'";
        String lName = "'"+dir.getLName()+"'";
        String contact = "'"+dir.getRecapito()+"'";
        String id = "'st"+ RandomStringUtils.random(9, true, true)+"'";
        String sql = "INSERT INTO staff VALUES ("+
                id.concat(", ").concat(fName).concat(", ").concat(lName).concat(", ").concat(contact).concat(", ").concat("'sound'").concat(", ").concat("NULL").concat(")");



        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        return result;
    }
}
