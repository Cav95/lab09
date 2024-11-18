package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private final Controller myController = new Controller();
/**
 * 
 * @param arg
 */
    public static void main(final String[] arg) {
     final SimpleGUI myGui = new SimpleGUI();

        myGui.start();
    }

    SimpleGUI() {
        frame.setSize(frame.getMaximumSize());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
               final int n = JOptionPane.showConfirmDialog(frame, "exit?", "Exit?", JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        frame.setVisible(true);
    }

    /**
     * 
     */
    public void start() {

        final JPanel pannel = new JPanel(new BorderLayout());
        final JPanel pannelSecond = new JPanel(new BorderLayout());
        frame.add(pannel, BorderLayout.CENTER);
        frame.add(pannelSecond, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        final JTextField textField = new JTextField();
        final JButton saveButton = new JButton("Save");
        final JButton broswer = new JButton("Broswer");

        pannel.add(saveButton, BorderLayout.SOUTH);
        pannel.add(textArea, BorderLayout.CENTER);
        pannelSecond.add(broswer, BorderLayout.WEST);
        pannelSecond.add(textField);

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                try (DataOutputStream dstrem = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(myController.getFile())));) {
                    dstrem.writeUTF(textArea.getText());

                } catch (final IOException ev) {
                    System.out.println("errore"); // NOPMD suppressed as it is a false positive
                }
            }

        });

        broswer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chose = new JFileChooser();
                if (JFileChooser.APPROVE_OPTION == chose.showSaveDialog(broswer)) {
                    myController.setMyFile(chose.getSelectedFile().getPath());
                    textField.setText(myController.getPathMyFile());
                }
            }

        });

    }

}
