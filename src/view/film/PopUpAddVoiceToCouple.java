package view.film;

import Controller.CoupleController;
import Controller.VoiceController;
import db.DbCastCouple;
import db.DbStaff;
import model.Coppia;
import model.Doppiatore;
import model.Film;
import model.Personaggio;
import view.LayerPanel;
import view.PopUpSEmpty;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PopUpAddVoiceToCouple extends LayerPanel implements ItemListener {
    JLabel namePLabel;
    JLabel voiceLabel;
    JComboBox voicesList;
    JTextField namePText;
    JButton delete;
    ArrayList<Doppiatore> allVoices;
    Coppia couple;
    Film film;
    Doppiatore voice;
    Personaggio pers;
    DocumentListener namePListener;


    public PopUpAddVoiceToCouple(Film film, Coppia couple) throws SQLException {

        namePListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!film.checkPersonaggio(namePText.getText())) {
                    pers = new Personaggio(namePText.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        };


        this.couple = couple;
        this.film = film;
        this.data.setLayout(new GridLayout(2, 2));
        namePLabel = new JLabel("Personaggio");
        namePText = new JTextField(couple.getPersName());
        namePText.getDocument().addDocumentListener(namePListener);

        allVoices = DbStaff.allVoices();
        voicesList = new JComboBox(allVoices.toArray());
        voiceLabel = new JLabel("Voce");
        voicesList.setSelectedItem(couple.getDopp().toString());
        voicesList.addItemListener(this);

        this.data.add(namePLabel);
        this.data.add(namePText);
        this.data.add(voiceLabel);
        this.data.add(voicesList);

        delete = new JButton("Elimina");
        delete.addActionListener(this);
        this.buttons.add(delete);

        this.ok.setText("Salva");
        this.ok.addActionListener(this);
        this.ko.setText("Annulla");
        this.ko.addActionListener(this);


    }


    public void actionPerformed(ActionEvent e) {
        String ret="";
        PopUpFilmDetails parent = (PopUpFilmDetails) this.getTopLevelAncestor();


        if (e.getActionCommand().equals("Salva")) {
            if (this.pers != null || this.voice != null) {

                // Controlla e aggiunge personaggio
                if (this.pers != null) {
                    ret = CoupleController.addCastToFilm(this.film, this.couple, this.pers);
                }
                if (this.voice != null) {
                    //ret = VoiceController.addVoiceToCouple (this.film, this.couple, this.voice);
                }
            } else {
                pers = null;
                voice = null;
                this.removeAll();
                parent.revalidate();
                parent.repaint();
            }
            pers = null;
            voice = null;
            System.out.println(ret);
            new PopUpSEmpty(ret).setVisible(true);
            //this.removeAll();
            //parent.revalidate();
            //parent.repaint();

            //System.out.println(parent);
            //this.getTopLevelAncestor();
            //this.removeAll();


        } else if (e.getActionCommand().equals("Annulla")) {
            pers = null;
            voice = null;
            this.removeAll();
            parent.revalidate();
            parent.repaint();

        } else if (e.getActionCommand().equals("Elimina")) {
            DbCastCouple.deleteFromCouple(this.couple);
            DbCastCouple.deleteFromCast(this.film, this.couple);
            this.film.getCoppie().remove(this.couple);
            this.removeAll();
            parent.revalidate();
            parent.repaint();
        }
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        this.voice = (Doppiatore) e.getItem();
    }
}
