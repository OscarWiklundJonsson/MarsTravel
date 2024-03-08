package com.uu.grupp3.marstravel.controllers;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class sammanstallningController {
    private JEditorPane editorPane;

    public sammanstallningController() {
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Add the scroll pane to your GUI layout
        // For example:
        // JFrame frame = new JFrame("HTML Viewer");
        // frame.getContentPane().add(scrollPane);
        // frame.setSize(600, 400);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setVisible(true);
    }

    public void loadMostRecentHtml() {
        // Find the most recent HTML file
        File[] htmlFiles = new File(".").listFiles((dir, name) -> name.startsWith("order") && name.endsWith(".html"));
        if (htmlFiles != null && htmlFiles.length > 0) {
            Arrays.sort(htmlFiles, Comparator.comparingLong(f -> Long.parseLong(f.getName().replaceAll("[^\\d]", ""))));
            File mostRecentHtmlFile = htmlFiles[htmlFiles.length - 1];

            // Load the most recent HTML file into the editor pane
            try {
                editorPane.setPage(mostRecentHtmlFile.toURI().toURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        sammanstallningController controller = new sammanstallningController();
        controller.loadMostRecentHtml();
    }
}
