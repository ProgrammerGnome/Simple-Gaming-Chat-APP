package FrontEnd_layer;

import Internet_layer.DriveQuickstart;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI_Main_Masolat {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        Teszt t = new Teszt();
        t.teszt();
    }
}
class Teszt extends JFrame implements ActionListener {
    JFrame ablak = new JFrame();
    
    JButton A = new JButton("Write a new message");
    JButton B = new JButton("Keresés a tranzakciók között");
    JButton C = new JButton("Tranzakció adatainak módosítása");
    JButton D = new JButton("Megadott tranzakció törlése");
    JButton E = new JButton("Refresh and View chatroom");
    JButton F = new JButton("További információk");
    public void teszt() throws IOException, GeneralSecurityException {
        
        DriveQuickstart.keres();
        DriveQuickstart.letolt();
        
        GridLayout g1 = new GridLayout();
        //g1.setColumns(2);
        g1.setColumns(2);
        g1.setRows(4);
        g1.setVgap(25); g1.setHgap(25);
        
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                final JOptionPane optionPane = new JOptionPane("Saving data ...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                final JDialog dialog = new JDialog();
                dialog.setTitle("EXIT");
                dialog.setModal(true);
                dialog.setContentPane(optionPane);
                dialog.setLocation(550, 275);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                //create timer to dispose of dialog after 5 seconds
                Timer timer = new Timer(0, new AbstractAction() {
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            DriveQuickstart.keres();
                            DriveQuickstart.torol();
                            DriveQuickstart.feltolt();
                            dialog.dispose();
                        } catch (IOException | GeneralSecurityException ex) {
                            Logger.getLogger(Teszt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                timer.setRepeats(false);//the timer should only go off once
                //start timer to close JDialog as dialog modal we must start the timer before its visible
                timer.start();
                dialog.setVisible(true);
                
                File file = new File("program_database_chat.xml");
                if (file.delete()) {
                    System.out.println("File deleted successfully");
                } else {
                    System.out.println("Failed to delete the file");
                }
                ablak.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        };
        ablak.addWindowListener(exitListener);


        ablak.setLayout(g1);
        ablak.setLocation(280, 125);
        ablak.setTitle(LAUNCHER.Main.ablakcím);
        
        ablak.add(A);

        ablak.add(E);
        //
        JLabel label_1 = new JLabel("<html>Simple Gaming Chat<br><br>"
                + "With this application, multiple users can chat at the same time "
                + "without any registration or account creation.</html>");
        ablak.add(label_1);

        
        JLabel label_2 = new JLabel("<html>Attention!<br><br>"
                + "You should always refresh the chatroom window with the 'Refresh and View chatroom' button.</html>");
        ablak.add(label_2);
        

        //

        ablak.setSize(800, 500);
        ablak.setVisible(true);
        
        A.addActionListener(this);
        B.addActionListener(this);
        C.addActionListener(this);
        D.addActionListener(this);
        E.addActionListener(this);
        F.addActionListener(this);
    }
    @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource() == A) {
            New_element a = new New_element();
            System.out.print(a);
        }

        else if (e.getSource() == E) {
            try {
                
                DriveQuickstart.keres();
                DriveQuickstart.letolt();
                Kiír a = new Kiír();
                a.Ki();
                
            } catch (IOException ex) {
                Logger.getLogger(Teszt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(Teszt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}