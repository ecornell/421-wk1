package ecornell.wk1.singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ecornell on 1/20/2016.
 */
public class UI {

    private static final UI SINGLETON = new UI();

    public static final String newLine = System.lineSeparator();

    public static UI getInstance() {
        return SINGLETON;
    }

    private UI() {
    }

    public void spacer() {
        System.out.print(newLine);
    }

    public void display(String text) {
        System.out.println(text);
    }

    public void displayPrompt(String text) {
        System.out.print(text);
    }

    public void displayTitle(String title) {
        StringBuilder titleBar = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            titleBar.append("-");
        }
        StringBuilder titleBarText = new StringBuilder();
        for (int i = 0; i < (18 - title.length() / 2) ; i++) {
            titleBarText.append(" ");
        }
        titleBarText.append(title);
        for (int i = titleBarText.length(); i < 38 ; i++) {
            titleBarText.append(" ");
        }

        spacer();
        display(titleBar.toString());
        display("|" + titleBarText.toString() + "|");
        display(titleBar.toString());
    }

    public void displayError(String text) {
        display("!!!  ERROR: " + text + "  !!!");
    }

    public String readInputString()  {
        String in = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            in = br.readLine();

        } catch (IOException e) {
            displayError("Input failure - " + e.getMessage());
        }

        return in;
    }

    public int readInputInt() {
        return Integer.parseInt(readInputString());
    }

}
