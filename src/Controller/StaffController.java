package Controller;

import db.DbStaff;
import model.Doppiatore;
import model.Personale;

public class StaffController {

    public static boolean insert (Personale personale, String string) {
        return DbStaff.insertStaff(personale, string);
    }

    public static boolean insertVoice (Doppiatore doppiatore) {
        return DbStaff.insertStaff(doppiatore);
    }

}
