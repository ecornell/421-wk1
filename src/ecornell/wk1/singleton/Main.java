package ecornell.wk1.singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private Track track = Track.getTrackInstance();


    private void runTrackAssignment() throws IOException {

        String menuSelection;

        do {
            menuSelection = readInputString("\n\nA: Add another runner\nL: List current runners\nX: Exit\nMenu selection (A/L/X) : ");


            if (menuSelection.equalsIgnoreCase("A")) {


                try {

                    int lane = readInputInt("\nEnter lane number: ");
                    String name = readInputString("\nEnter the runners name: ");

                    track.assignRunner(lane, name);

                } catch (NumberFormatException nfe) {
                    System.out.println("\nError: Invalid lane number");
                } catch (AssignRunnerException assignRunnerExcption) {
                    System.out.println("\nError: " + assignRunnerExcption.getMessage());
                }


            } else if (menuSelection.equalsIgnoreCase("L")) {


            }


        } while (!menuSelection.equalsIgnoreCase("X"));


    }

    private String readInputString(String prompt) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        return br.readLine();
    }

    private int readInputInt(String prompt) throws IOException {
        return Integer.parseInt(readInputString(prompt));
    }


    public static void main(String[] args) {
        Main m = new Main();
        try {
            m.runTrackAssignment();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
