package ecornell.wk1.singleton;

public class Main {

    private UI ui = UI.getInstance();

    private TrackManager trackManager = TrackManager.getTrackInstance();

    private void mainLoop() {

        ui.display("========================================");
        ui.display("|    Track Lane Assignment Manager     |");
        ui.display("|    POS-421 - Wk1 - Elijah Cornell    |");
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

                assignLane();

            } else if (menuSelection.equalsIgnoreCase("L")) {

                displayLaneAssignments();

            } else if (menuSelection.equalsIgnoreCase("R")) {

                resetLaneAssignments();

            }

        } while (!menuSelection.equalsIgnoreCase("X"));

    }

    private void assignLane() {

        ui.displayTitle("Lane Assignment");


        if (!trackManager.hasOpenLane()) {
            ui.displayError("No open lanes available");
            return;
        }

        try {

            StringBuilder sbAvailableLanes = new StringBuilder();
            for (int i = 1; i <= trackManager.getNumLanes(); i++) {
                if (trackManager.isLaneAvailabile(i)) {
                    sbAvailableLanes.append(i + " ");
                }
            }
            ui.display("Open lanes: " + sbAvailableLanes);
            ui.spacer();

        } catch (AssignRunnerException assignRunnerException) {
            ui.displayError(assignRunnerException.getMessage());
        }


        // Set lane number

        int lane = 0;

        do {
            try {

                ui.displayPrompt("Enter lane number: ");
                lane = ui.readInputInt();

                trackManager.isLaneAvailabile(lane);

            } catch (NumberFormatException nfe) {
                ui.displayError("Invalid lane number");
                lane = 0;
            } catch (AssignRunnerException assignRunnerException) {
                ui.displayError(assignRunnerException.getMessage());
                lane = 0;
            }

        } while (lane == 0);

        // Set runner's name

        String runnerName = "";
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

    private void resetLaneAssignments() {
        ui.displayTitle("Lane Assignments Reset");
        trackManager.resetLaneAssignments();
        printLaneAssignments();
    }

    private void displayLaneAssignments() {
        ui.displayTitle("Current Lane Assignments");
        printLaneAssignments();
    }

    private void printLaneAssignments() {

        String[] laneAssignments = trackManager.getLaneAssignments();
        for (int i = 0; i < laneAssignments.length; i++) {
            ui.display(" " + (i + 1) + " : " + (laneAssignments[i] != null ? laneAssignments[i] : "*"));
        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        m.mainLoop();

    }

}
