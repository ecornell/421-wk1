package ecornell.wk1.singleton;

/**
 * Created by ecornell on 1/19/2016.
 */
public class Track {

    private static final Track SINGLETON = new Track(8);

    private int numLanes;
    private String[] lanes;

    private Track(int numLanes) {
        this.numLanes = numLanes;
        clearLaneAssignments();
    }

    public static Track getTrackInstance() {
        return SINGLETON;
    }

    public void assignRunner(int laneNumber, String runner) throws AssignRunnerException {

        if (laneNumber < 1 || laneNumber > numLanes) {
            throw new AssignRunnerException("Invalid lane number - " + laneNumber);
        }

        if (lanes[laneNumber - 1] != null) {
            throw new AssignRunnerException("Lane number already assigned - " + laneNumber);
        }

        lanes[laneNumber - 1] = runner;

    }

    public String[] getLaneAssignments() {
        return lanes;
    }

    public void clearLaneAssignments() {
        lanes = new String[numLanes];
    }

}
