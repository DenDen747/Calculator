package com.denesgarda.Calculator;

import com.denesgarda.Calculator.util.TextAreaOutputStream;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

public class Window extends JFrame {
    private JPanel panel;
    private JScrollPane scrollPane;
    public JTextArea textArea;
    public JTextField textField;

    public Window() {
        super("Calculator v" + Main.VERSION + " - Menu");
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        TextAreaOutputStream out = new TextAreaOutputStream(textArea, "");
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(out));
        this.setSize(512, 512);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
        textField.requestFocus();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = textField.getText();
                    textField.setText("");
                    System.out.println(input);
                    Main.processInput(input);
                }
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField.setText(textField.getText() + e.getKeyChar());
                textField.requestFocus();
                textField.setCaretPosition(textField.getText().length() - 1);
            }
        });

        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent fe) {
                textField.setCaretPosition(textField.getDocument().getLength());
            }

            public void focusLost(FocusEvent fe) {

            }
        });
    }
}
