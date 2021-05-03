package Controller;
import db.DbCastCouple;
import db.DbStaff;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoiceController {

    public static ArrayList allVoices () throws SQLException {
        return DbStaff.allVoices();
    }

    public static String addVoiceToCouple (Film film, Coppia couple, Doppiatore voice) {
        String ret;
        // Nuova voce
        if (couple.getDopp() == null) {
            couple.setDopp(voice);
            DbCastCouple.addCouple(couple);
        } else if (!voice.getStaffId().equals(couple.getDopp().getStaffId())) {
            couple.setDopp(voice);
            DbCastCouple.updateCouple(couple);
        }
        return "OK!";

    }

}
