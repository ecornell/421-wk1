package ecornell.wk1.singleton;

/**
 * Created by ecornell on 1/19/2016.
 */
public class TrackManager {

    private static final TrackManager SINGLETON = new TrackManager(8);

    private int numLanes;
    private String[] lanes;

    public final static String OPEN_LANE_INDICATOR = "-";

    private TrackManager(int numLanes) {
        this.numLanes = numLanes;
        this.lanes = new String[numLanes];
        resetLaneAssignments();
    }

    public static TrackManager getTrackInstance() {
        return SINGLETON;
    }

    public void assignRunner(int laneNumber, String runner) throws AssignRunnerException {

        if (laneNumber < 1 || laneNumber > numLanes) {
            throw new AssignRunnerException("Invalid lane number - " + laneNumber);
        }

        if (!isLaneAvailabile(laneNumber)) {
            throw new AssignRunnerException("Lane number already assigned - " + laneNumber);
        }

        lanes[laneNumber - 1] = runner;

    }

    public void resetLaneAssignments() {
        for (int i = 0; i < numLanes; i++) {
            lanes[i] = OPEN_LANE_INDICATOR;
        }
    }

    public boolean isLaneAvailabile(int laneNumber) throws AssignRunnerException {
        if (laneNumber < 1 || laneNumber > numLanes) {
            throw new AssignRunnerException("Invalid lane number - " + laneNumber);
        }
        return lanes[laneNumber - 1].equals(OPEN_LANE_INDICATOR);
    }

    public boolean hasOpenLane() {
        try {
            for (int i = 1; i <= numLanes; i++) {
                if (isLaneAvailabile(i)) {
                    return true;
                }
            }
        } catch (AssignRunnerException e) {
            return false;
        }
        return false;
    }

    public String[] getLaneAssignments() {
        return lanes;
    }

    public int getNumLanes() {
        return numLanes;
    }
}
