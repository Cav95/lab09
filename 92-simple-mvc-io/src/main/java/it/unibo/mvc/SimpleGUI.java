package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private Controller myController = new Controller();

    public static final void main(String[] arg) {
        SimpleGUI myGui = new SimpleGUI();

        myGui.start();
    }

    SimpleGUI() {
        frame.setSize(frame.getMaximumSize());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(frame, "e", "Exit?", JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);

                }
            }
        });
    }

    /**
     * 
     */
    public void start(){

        final JPanel pannel = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea(20,30);
        JButton saveButton = new JButton("Save");

        pannel.add(saveButton , BorderLayout.SOUTH);
        pannel.add(textArea , BorderLayout.CENTER);
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try (final DataOutputStream dstrem = new DataOutputStream(
                    new BufferedOutputStream(
                        new FileOutputStream(myController.getFile())));
                        ) {
                            dstrem.writeUTF(textArea.getText());
                    
                    
                } catch (IOException ev) {
                    // TODO: handle exception
                }
            }
            
        });


        



    }

}
