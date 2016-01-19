package ecornell.wk1.singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private Track track = Track.getTrackInstance();


    private void runTrackAssignment() throws IOException {

        System.out.print("" +
                "================================\n" +
                " Track Lane Assignment Manager\n" +
                " POS-421 - Wk1 - Elijah Cornell\n" +
                "================================"
        );


        String menuSelection;

        do {
            menuSelection = readInputString("\n\nA: Assign runner to lane\nL: List current lane assignments\nX: Exit\nMenu selection (A/L/X) : ");

            if (menuSelection.equalsIgnoreCase("A")) {


                try {

                    int lane = readInputInt("\nEnter lane number: ");
                    String name = readInputString("\nEnter runner's name: ");

                    track.assignRunner(lane, name);

                } catch (NumberFormatException nfe) {
                    System.out.println("\nError: Invalid lane number");
                } catch (AssignRunnerException assignRunnerException) {
                    System.out.println("\nError: " + assignRunnerException.getMessage());
                }


                printLaneAssignments();

            } else if (menuSelection.equalsIgnoreCase("L")) {


            }


        } while (!menuSelection.equalsIgnoreCase("X"));


    }

    private void printLaneAssignments() {

        System.out.print("\nCurrent Lane Assignments\n========================\n");

        String[] laneAssignments = track.getLaneAssignments();
        for (int i = 0; i < laneAssignments.length ; i++) {
            System.out.print(" " + (i+1) + " : " + (laneAssignments[i] != null ? laneAssignments[i] : "Open")  + "\n");
        }

        System.out.print("========================\n");

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
