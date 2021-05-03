package db;

import model.Personale;
import model.Room;
import org.apache.commons.lang.RandomStringUtils;

public class DbRoom {


    public static boolean insert (Room room) {
        boolean result = false;
        int sql_res;
        Conn conn = new Conn();
        conn.connect();

        String name = "'"+room.getName()+"'";
        String id = "'rm"+ RandomStringUtils.random(9, true, true)+"'";
        String sql = "INSERT INTO room VALUES ("+
                id.concat(", ").concat(name).concat(")");

        sql_res=conn.queryToInsert(sql);
        conn.disconnect();
        if (sql_res != 0) {
            result = true;
        }
        return result;
    }
}
