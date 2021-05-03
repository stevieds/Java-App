package view;

import view.film.FilmPanel;
import view.film.PopUpNewFilm;
import view.room.PopUpNewRoom;
import view.staff.PopUpNewDir;
import view.staff.PopUpNewSound;
import view.staff.PopUpNewVoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JPanel mainPanel;






    public MainWindow() {
        this.setSize(1280, 720);
        this.setTitle("Sigma Dubbing");
        this.setLayout(new BorderLayout());

        this.menuBar = new JMenuBar();

        JMenu menuFilm = new JMenu("Film");
        JMenuItem menuFNew = new JMenuItem("Aggiungi nuovo film");
        menuFNew.addActionListener(this);
        menuFilm.add(menuFNew);
        JMenuItem menuFWip = new JMenuItem("Lavr");
        menuFilm.add(menuFWip);
        JMenuItem menuFArc = new JMenuItem("Archivio film");
        menuFArc.addActionListener(this);
        menuFilm.add(menuFArc);
        this.menuBar.add(menuFilm);

        JMenu menuShift = new JMenu("Turni");
        JMenuItem menuSWip = new JMenuItem("Aggiungi nuovo");
        menuShift.add(menuSWip);
        JMenuItem menuSCal = new JMenuItem("Calendario");
        menuShift.add(menuSCal);
        this.menuBar.add(menuShift);

        JMenu menuAdmin = new JMenu("Admin");
        JMenu menuAStaff = new JMenu("Personale");
        JMenu menuARoom = new JMenu("Sale");

        JMenu menuSVoice = new JMenu("Doppiatori");
        JMenuItem menuSVoiceNew = new JMenuItem("Aggiungi nuovo doppiatore");
        menuSVoiceNew.addActionListener(this);
        menuSVoice.add(menuSVoiceNew);
        JMenuItem menuSVoiceBrowse = new JMenuItem("Elenco");
        menuSVoice.add(menuSVoiceBrowse);
        menuAStaff.add(menuSVoice);

        JMenu menuSDir = new JMenu("Direttori");
        JMenuItem menuSDirNew = new JMenuItem("Aggiungi nuovo direttore");
        menuSDirNew.addActionListener(this);
        menuSDir.add(menuSDirNew);
        JMenuItem menuSDirBrowse = new JMenuItem("Elenco");
        menuSDir.add(menuSDirBrowse);
        menuAStaff.add(menuSDir);

        JMenu menuSSound = new JMenu("Fonici");
        JMenuItem menuSSoundNew = new JMenuItem("Aggiungi nuovo fonico");
        menuSSoundNew.addActionListener(this);
        menuSSound.add(menuSSoundNew);
        JMenuItem menuSSoundBrowse = new JMenuItem("Elenco");
        menuSSound.add(menuSSoundBrowse);
        menuAStaff.add(menuSSound);
        menuAdmin.add(menuAStaff);

        JMenuItem menuRNew = new JMenuItem("Aggiungi sala");
        menuRNew.addActionListener(this);
        menuARoom.add(menuRNew);
        menuAdmin.add(menuARoom);


        this.menuBar.add(menuAdmin);
        this.add(menuBar, BorderLayout.NORTH);

        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.CYAN);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case ("Aggiungi nuovo film"):
                PopUpNewFilm newF = new PopUpNewFilm();
                newF.setVisible(true);
                break;
            case ("Aggiungi nuovo doppiatore"):
                PopUpNewVoice newV = new PopUpNewVoice();
                newV.setVisible(true);
                break;
            case ("Aggiungi nuovo direttore"):
                PopUpNewDir newD = new PopUpNewDir();
                newD.setVisible(true);
                break;
            case ("Aggiungi nuovo fonico"):
                PopUpNewSound newS = new PopUpNewSound();
                newS.setVisible(true);
                break;
            case ("Aggiungi sala"):
                PopUpNewRoom newSa = new PopUpNewRoom();
                newSa.setVisible(true);
                break;
            case ("Archivio film"):
                this.mainPanel.removeAll();
                FilmPanel filmPanel = new FilmPanel();
                this.mainPanel.add(filmPanel);
                this.revalidate();
                break;
        }




    }
}
