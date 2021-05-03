package model;

import java.util.ArrayList;

public interface Methods {

    public default long durataTotAnelli(ArrayList<Anello> anelli) {
        long durata = 0;
        for (Anello anello : anelli) {
            durata+= anello.lengthInMilli();
        }
        return durata;
    }
}


