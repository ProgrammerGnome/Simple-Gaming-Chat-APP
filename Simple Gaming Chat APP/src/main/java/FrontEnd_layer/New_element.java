package FrontEnd_layer;

import BackEnd_layer.New_element_to_node;
import Internet_layer.DriveQuickstart;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class New_element implements ActionListener{
    
    JFrame window0 = new JFrame("Write a new message");
    
    JTextField inputitemTextField = new JTextField("Névtelen Lajhármaki");
    
    JTextField descriptionTextField = new JTextField();
    
    JLabel inputitemLabel = new JLabel("Username: ", JLabel.CENTER);
    
    JLabel descriptionLabel = new JLabel("Message: ", JLabel.CENTER);
    
    
    JButton signUpButton = new JButton("Send");
    JLabel blank = new JLabel();
    FileWriter fileWriter;
    
    
    New_element() {
        
        GridLayout g1 = new GridLayout();
        //g1.setColumns(2);
        g1.setRows(3);
        
        window0.setLayout(g1);
        
        signUpButton.addActionListener(this);

        
        window0.add(inputitemLabel);
        window0.add(inputitemTextField);
        
        
        window0.add(descriptionLabel);
        window0.add(descriptionTextField);
        
        
        window0.add(blank);
        window0.add(signUpButton);
        
        window0.setSize(600,400);

        
        window0.setVisible(true);
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals(signUpButton.getActionCommand()))
        {
            try {
               
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                //System.out.println(formatter.format(date));
                
                New_element_to_node.name = inputitemTextField.getText();
                
                New_element_to_node.date = formatter.format(date);
                
                New_element_to_node.description = descriptionTextField.getText();
                
                New_element_to_node.ERROR_nodetag_bovito();
                
                DriveQuickstart.keres();
                DriveQuickstart.torol();
                DriveQuickstart.feltolt();
                
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e+"");
            } catch (IOException ex) {
                Logger.getLogger(New_element.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(New_element.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}