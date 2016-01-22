package ecornell.wk1.singleton;

/**
 * Title:          Week 1 - Singleton Pattern Program (Track Lane Manager)
 * Author:         Elijah Cornell
 * Creation Date:  2016-01-19
 * Class:          PRG/421 - Roland Morales
 *
 * Program Requirements:
 *
 *  Key parts:
 *    - A private static variable to store the single instance called the singleton
 *    - A public static method for callers to get a reference to the instance
 *    - A private constructor so no callers can instantiate the object directly
 *
 *  Allow a user of the program to assign only one runner to each of the 8 lanes
 *  of running track in a field.
 *
 *  Both the TrackManager and UI classes utilize the singleton pattern
 *
 *  Program Flow:
 *     Display a main menu
 *          -> Assign runner to lane
 *          -> List current lane assignments
 *          -> Reset lane assignments
 *          -> Exit
 *
 * Input: Console
 * Output: Console
 */
public class Main {

    private final UI ui = UI.getInstance();

    private final TrackManager trackManager = TrackManager.getTrackInstance();

    /**
     * Main program loop
     */
    private void mainLoop() {

        ui.display("========================================");
        ui.display("|    Track Lane Assignment Manager     |");
        ui.display("|    PRG-421 - Wk1 - Elijah Cornell    |");
        ui.display("========================================");

        String menuSelection;

        do {

            ui.displayTitle("Main Menu");

            ui.display("A: Assign runner to lane");
            ui.display("L: List current lane assignments");
            ui.display("R: Reset lane assignments");
            ui.display("X: Exit");
            ui.displayPrompt("Menu selection (A/L/R/X) : ");


            menuSelection = ui.readInputString();

            if (menuSelection.equalsIgnoreCase("A")) {

                ui.displayTitle("Lane Assignment");
                assignLane();

            } else if (menuSelection.equalsIgnoreCase("L")) {

                ui.displayTitle("Current Lane Assignments");
                displayLaneAssignments();

            } else if (menuSelection.equalsIgnoreCase("R")) {

                ui.displayTitle("Lane Assignments Reset");
                resetLaneAssignments();

            }

        } while (!menuSelection.equalsIgnoreCase("X"));

    }

    /**
     * Prompt for, validate, and store the runner's information for a new lane assignment
     */
    private void assignLane() {

        // Check if there are open lane available

        if (!trackManager.hasOpenLane()) {
            ui.displayError("No open lanes available");
            return;
        }

        // Display open lane numbers

        try {

            StringBuilder sbAvailableLanes = new StringBuilder();
            for (int i = 1; i <= trackManager.getNumLanes(); i++) {
                if (trackManager.isLaneAvailable(i)) {
                    sbAvailableLanes.append(" ");
                    sbAvailableLanes.append(i);
                }
            }
            ui.display("Open lanes:" + sbAvailableLanes);
            ui.spacer();

        } catch (AssignRunnerException assignRunnerException) {
            ui.displayError(assignRunnerException.getMessage());
        }


        // Prompt for and validate lane number

        int lane;
        do {
            try {

                ui.displayPrompt("Enter lane number: ");
                lane = ui.readInputInt();

                if (!trackManager.isLaneAvailable(lane)) {
                    ui.displayError("Lane " + lane + " is already assigned to " + trackManager.getLaneAssignment(lane));
                    lane = 0;
                }

            } catch (NumberFormatException nfe) {
                ui.displayError("Invalid lane number");
                lane = 0;
            } catch (AssignRunnerException assignRunnerException) {
                ui.displayError(assignRunnerException.getMessage());
                lane = 0;
            }

        } while (lane == 0);

        // Prompt for and validate runner's name

        String runnerName;
        do {
            ui.displayPrompt("Enter runner's name: ");
            runnerName = ui.readInputString();
        } while ("".equals(runnerName));

        // Store lane and runner assignment

        try {

            trackManager.assignRunner(lane, runnerName);

        } catch (AssignRunnerException assignRunnerException) {
            ui.displayError(assignRunnerException.getMessage());
        }

        //--

        displayLaneAssignments();

    }

    /**
     * Display the current lane assignments
     */
    private void displayLaneAssignments() {
        String[] laneAssignments = trackManager.getLaneAssignments();
        for (int i = 0; i < laneAssignments.length; i++) {
            ui.display(" " + (i + 1) + " : " + (laneAssignments[i] != null ? laneAssignments[i] : "*"));
        }
    }

    /**
     * Reset all current lane assignments
     */
    private void resetLaneAssignments() {
        trackManager.resetLaneAssignments();
        displayLaneAssignments();
    }

    /**
     * Main program entry point
     * @param args None
     */
    public static void main(String[] args) {

        Main m = new Main();
        m.mainLoop();

    }

}
