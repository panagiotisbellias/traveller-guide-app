package gui;

import io.files.TxtFile;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/** The Construction of a class that shows app usage to the user according to event.
 * Implements MouseListener Interface.
* @since 31-05-2020
* @version 1.4
* @author it21871 */
public class ShowHelp implements MouseListener{

    private JDialog helpDialog;
    
    //=======================================================mouseClicked()=====================================================
    /** The method is implemented so as to catch mouse-clicked event and executes commands to help user use the app.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        
        JFrame f = new JFrame();
        helpDialog = new JDialog(f, "Help", true);
        helpDialog.setSize(500, 500);
        helpDialog.setLocation(200,200);
        helpDialog.setLayout(new FlowLayout());
        JButton okButton = new JButton("OK");
        okButton.setBounds(40, 20, 100, 100);
        okButton.addActionListener((ActionEvent e1) -> {
            helpDialog.setVisible(false);  
        });
        ArrayList<String> data = TxtFile.read("help.txt");
        JLabel label1 = new JLabel(data.get(0));
        JLabel label2 = new JLabel(data.get(1));
        JLabel label3 = new JLabel(data.get(2));
        JLabel label4 = new JLabel(data.get(3));
        JLabel label5 = new JLabel(data.get(4));
        
        label1.setBounds(10, 10, 200, 200);
        label2.setBounds(10, 20, 200, 200);
        label3.setBounds(10, 30, 200, 200);
        label4.setBounds(10, 40, 200, 200);
        label5.setBounds(10, 50, 200, 200);
        
        helpDialog.add(label1);
        helpDialog.add(label2);
        helpDialog.add(label3);
        helpDialog.add(label4);
        helpDialog.add(label5);
        
        helpDialog.add(okButton);
        helpDialog.setSize(500,200);    
        helpDialog.setVisible(true);
    }
    //====================================================End of mouseClicked()=================================================
    
    //=======================================================mousePressed()=====================================================
    /** The method is implemented so as to catch mouse-pressed event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mousePressed(MouseEvent e) {

    }
    //====================================================End of mousePressed()=================================================
    
    //=======================================================mouseReleased()====================================================
    /** The method is implemented so as to catch mouse-released event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    //====================================================End of mouseReleased()================================================

    //=======================================================mouseEntered()=====================================================
    /** The method is implemented so as to catch mouse-entered event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    //====================================================End of mouseEntered()=================================================

    //=======================================================mouseExited()======================================================
    /** The method is implemented so as to catch mouse-exited event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseExited(MouseEvent e) {

    }
    //====================================================End of mouseExited()==================================================
    
}//======================================================End of Class ShowHelp ==================================================
