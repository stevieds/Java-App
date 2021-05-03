package view.film;

import Controller.AnelloController;
import Controller.CoupleController;
import Controller.VoiceController;
import db.DbCastCouple;
import db.DbStaff;
import model.*;
import view.LayerPanel;
import view.PopUpFail;
import view.PopUpSuccess;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class LayerEditAnello extends LayerPanel implements ItemListener  {
    JLabel startL;
    JLabel endL;
    JLabel persL;
    JLabel doppL;

    JPanel times;

    JComboBox voicesList;
    JComboBox persList;

    JPanel peopleP;

    JPanel jStartPanel;
    JPanel jEndPanel;
    JSpinner hhS;
    JSpinner mmS;
    JSpinner ssS;
    JSpinner sssS;

    JSpinner hhE;
    JSpinner mmE;
    JSpinner ssE;
    JSpinner sssE;

    JButton delete;

    Personaggio pers;
    Doppiatore dopp;

    Anello anello;
    Film film;


    public LayerEditAnello(Film film, Anello anello) throws SQLException {
        this.anello = anello;
        this.film = film;
/*

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

         */
        times = new JPanel();
        times.setLayout(new GridLayout(2, 2));
        this.data.setLayout(new BorderLayout());

        peopleP = new JPanel();
        peopleP.setLayout(new GridLayout(2, 2));

        persL = new JLabel("Personaggio");
        doppL = new JLabel("Doppiatore");
        voicesList = new JComboBox(VoiceController.allVoices().toArray());
        //voicesList.setSelectedItem(anello.getCouple().getDopp());
        voicesList.setName("Doppiatore");
        voicesList.addItemListener(this);
        persList = new JComboBox(CoupleController.getAllCharacters().toArray());
        //persList.setSelectedItem(anello.getCouple().getPers());
        persList.setName("Personaggio");
        persList.addItemListener(this);
        peopleP.add(persL);
        peopleP.add(persList);
        peopleP.add(doppL);
        peopleP.add(voicesList);
        data.add(peopleP, BorderLayout.NORTH);


        jStartPanel = new JPanel();
        jStartPanel.setLayout(new FlowLayout());

        this.hhS = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
        hhS.setValue(anello.getStart().getHour());
        this.jStartPanel.add(this.hhS);
        this.mmS = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        mmS.setValue(anello.getStart().getMin());
        this.jStartPanel.add(this.mmS);
        this.ssS = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        ssS.setValue(anello.getStart().getSec());
        this.jStartPanel.add(this.ssS);
        this.sssS = new JSpinner(new SpinnerNumberModel(000, 000, 999, 001));
        sssS.setValue(anello.getStart().getSss());
        this.jStartPanel.add(this.sssS);

        this.times.add(new JLabel("Inizio"));
        this.times.add(jStartPanel);

        jEndPanel = new JPanel();
        jEndPanel.setLayout(new FlowLayout());

        this.hhE = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
        hhE.setValue(anello.getEnd().getHour());
        this.jEndPanel.add(this.hhE);
        this.mmE = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        mmE.setValue(anello.getEnd().getMin());
        this.jEndPanel.add(this.mmE);
        this.ssE = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        ssE.setValue(anello.getEnd().getSec());
        this.jEndPanel.add(this.ssE);
        this.sssE = new JSpinner(new SpinnerNumberModel(000, 000, 999, 001));
        sssE.setValue(anello.getEnd().getSss());
        this.jEndPanel.add(this.sssE);

        this.times.add(new JLabel("Fine"));
        this.times.add(jEndPanel);

        this.data.add(times, BorderLayout.CENTER);
        delete = new JButton("Elimina");
        delete.addActionListener(this);
        this.buttons.add(delete);

        this.ok.setText("Salva");
        this.ok.addActionListener(this);
        this.ko.setText("Annulla");
        this.ko.addActionListener(this);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox combo = (JComboBox) e.getSource();

        if (combo.getName().equals("Personaggio")) {
            pers = (Personaggio) e.getItem();
        }
        if (combo.getName().equals("Doppiatore")) {
            dopp = (Doppiatore) e.getItem();
        }

    }



    public void actionPerformed(ActionEvent e) {
        PopUpFilmDetails parent = (PopUpFilmDetails) this.getTopLevelAncestor();
        boolean result = false;

        if (e.getActionCommand().equals("Salva")) {
            System.out.println(pers);
            System.out.println(dopp);
            if (this.pers != null && this.dopp != null) {
                result = AnelloController.editAnelloCouple(anello, new Coppia(dopp, pers), film);
            }
            if (result) {
                new PopUpSuccess().setVisible(true);
            } else {
                new PopUpFail().setVisible(true);
            }
            this.pers = null;
            this.dopp = null;
            this.removeAll();
            parent.revalidate();
            parent.repaint();
        }
        else if (e.getActionCommand().equals("Annulla")) {
            this.pers = null;
            this.dopp = null;
            this.removeAll();
            parent.revalidate();
            parent.repaint();

        } else if (e.getActionCommand().equals("Elimina")) {
            result = AnelloController.deleteAnello(anello, film);
            if (result) {
                new PopUpSuccess().setVisible(true);
            } else {
                new PopUpFail().setVisible(true);
            }
            this.pers = null;
            this.dopp = null;
            this.removeAll();
            parent.revalidate();
            parent.repaint();
        }
    }

    }


