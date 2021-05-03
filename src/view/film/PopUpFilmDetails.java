package view.film;

import Controller.FilmController;
import db.DbAnello;
import db.DbCastCouple;
import javafx.util.Pair;
import model.Anello;
import model.Coppia;
import model.Film;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PopUpFilmDetails extends JDialog implements ActionListener {
    JLabel oTitle;
    JLabel iTitle;
    JPanel top;
    JPanel middle;
    JPopupMenu status;
    JLayeredPane couples;
    JLayeredPane timeFrames;
    JPanel couplesList;
    JPanel timeFramesList;
    Film film;



    public PopUpFilmDetails (Film film) {
        this.film = film;
        // Aggiungi coppie
        // Aggiungi anelli
        FilmController.populateAnelli(film);
        FilmController.populateCoppie(film);

        this.setSize(1024, 576);
        this.setLayout(new BorderLayout());
        top = new JPanel();
        top.setLayout(new GridLayout(2, 1));
        oTitle = new JLabel(this.film.getoTitle());
        top.add(oTitle);
        iTitle = new JLabel(this.film.getiTitle() + " - " + this.film.lengthToString());
        top.add(iTitle);
        this.add(top, BorderLayout.NORTH);

        middle = new JPanel();
        middle.setLayout(new GridLayout(1, 2));
        couples = new JLayeredPane();
        couples.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Doppiatori"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        couples.setLayout(new FlowLayout());
        JButton addCouple = new JButton("Aggiungi personaggio");
        addCouple.addActionListener(this);
        couples.add(addCouple);

        couplesList = new JPanel();
        couplesList.setLayout(new GridLayout(this.film.getCoppie().size()+1, 3));
        couplesList.add(new JLabel("Personaggio"));
        couplesList.add(new JLabel("Voce"));
        couplesList.add(new JLabel(""));

        for (Coppia couple : this.film.getCoppie()) {
            JTextField pers = new JTextField(couple.getPersName());
            pers.setEditable(false);
            couplesList.add(pers);
            if (couple.getDopp()!=null) {
                JTextField dopp = new JTextField(couple.getDopp().getFName()+" "+couple.getDopp().getLName());
                dopp.setEditable(false);
                couplesList.add(dopp);
            } else {
                JTextField dopp = new JTextField("");
                dopp.setEditable(false);
                couplesList.add(dopp);
            }
        JButton btnMod = new JButton("Modifica");

            btnMod.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        couples.add(new PopUpAddVoiceToCouple(film, couple), JLayeredPane.POPUP_LAYER);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            couplesList.add(btnMod);
        }
        couples.add(couplesList);

        timeFrames = new JLayeredPane();
        timeFrames.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Anelli"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        timeFrames.setLayout(new FlowLayout());
        JButton addTimeFrame = new JButton("Aggiungi anello");
        addTimeFrame.addActionListener(this);
        timeFrames.add(addTimeFrame);

        timeFramesList = new JPanel();
        timeFramesList.setLayout(new GridLayout(this.film.getAnelli().size()+1, 4));
        timeFramesList.add(new JLabel("Inizio"));
        timeFramesList.add(new JLabel("Fine"));
        timeFramesList.add(new JLabel("Voce"));
        timeFramesList.add(new JLabel(""));

        for (Anello anello : this.film.getAnelli()) {
            JTextField start = new JTextField(anello.getStart().toString());
            start.setEditable(false);
            timeFramesList.add(start);
            JTextField end = new JTextField(anello.getEnd().toString());
            end.setEditable(false);
            timeFramesList.add(end);
            JTextField voice = new JTextField("");

            if (anello.getCouple() != null) {
                voice.setText(anello.getCouple().getDopp().getFullName());
            }
            voice.setEditable(false);
            timeFramesList.add(voice);

            JButton btnMod = new JButton("Modifica anello");

            btnMod.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        timeFrames.add(new LayerEditAnello(film, anello), JLayeredPane.POPUP_LAYER);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            timeFramesList.add(btnMod);
        }
        timeFrames.add(timeFramesList);
        //****


        middle.add(couples);
        middle.add(timeFrames);



        this.add(middle, BorderLayout.CENTER);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Aggiungi anello")) {
            PopUpNewTimeFrame popUpNewTimeFrame = new PopUpNewTimeFrame (film);
            popUpNewTimeFrame.setVisible(true);
        }
        else if (command.equals("Aggiungi personaggio")) {
            //PopUpNewChar popUpNewChar = new PopUpNewChar (film);
            //popUpNewChar.setVisible(true);

            this.revalidate();
            this.repaint();
        }

    }

}



