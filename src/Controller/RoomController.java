package Controller;

import db.DbRoom;
import db.DbStaff;
import model.Personale;
import model.Room;

public class RoomController {

    public static boolean addRoom (Room room) {
        return DbRoom.insert(room);
    }

}
