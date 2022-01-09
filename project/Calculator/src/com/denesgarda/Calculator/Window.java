package com.denesgarda.Calculator;

import com.denesgarda.Calculator.util.TextAreaOutputStream;

import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class Window extends JFrame {
    private JPanel panel;
    private JTextArea textArea;

    public Window() {
        super("Calculator v" + Main.VERSION + " - Menu");
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        try {
            Font consolas = Font.createFont(Font.TRUETYPE_FONT, new File("src/CONSOLA.TTF")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(consolas);
            textArea.setFont(consolas);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        TextAreaOutputStream out = new TextAreaOutputStream(textArea, "");
        System.setOut(new PrintStream(out));
        this.setSize(512, 512);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
        implementListeners();
    }

    private void implementListeners() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = null;
                    for (String line : textArea.getText().split("\\n")) {
                        input = line;
                    }
                    try {
                        Main.processInput(input);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (ScriptException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        textArea.requestFocus();
    }
}
