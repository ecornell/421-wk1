/**
 * Title:          Week 1 - Singleton Pattern Program (Track Lane Manager)
 * Author:         Elijah Cornell
 * Creation Date:  2016-01-19
 * Class:          PRG/421 - Roland Morales
 */
package ecornell.wk1.singleton;

/**
 * Singleton track management class
 * <p/>
 * Usage: TrackManager trackManager = TrackManager.getTrackInstance();
 */
public class TrackManager {

    /**
     * Singleton instance variable of TrackManager
     */
    private static final TrackManager SINGLETON = new TrackManager(8);

    /**
     * @return A singleton instance of the TrackManager class
     */
    public static TrackManager getTrackInstance() {
        return SINGLETON;
    }

    /**
     * Total number of lanes available
     */
    private int numLanes;

    /**
     * Array of lane assignment information
     */
    private String[] lanes;

    /**
     * Default value to indicate an open lane
     */
    private final static String OPEN_LANE_INDICATOR = "-";

    /**
     * Default private TrackManager constructor to support singleton pattern
     *
     * @param numLanes Number of lanes on track
     */
    private TrackManager(int numLanes) {
        this.numLanes = numLanes;
        this.lanes = new String[numLanes];
        resetLaneAssignments();
    }

    /**
     * Assign runner to lane
     *
     * @param laneNumber Lane number
     * @param runner     Runner's name
     * @throws AssignRunnerException
     */
    public void assignRunner(int laneNumber, String runner) throws AssignRunnerException {

        if (laneNumber < 1 || laneNumber > numLanes) {
            throw new AssignRunnerException("Invalid lane number - " + laneNumber);
        }

        if (!isLaneAvailable(laneNumber)) {
            throw new AssignRunnerException("Lane number already assigned - " + laneNumber);
        }

        lanes[laneNumber - 1] = runner;

    }

    /**
     * Reset all lanes to OPEN_LANE_INDICATOR value
     */
    public void resetLaneAssignments() {
        for (int i = 0; i < numLanes; i++) {
            lanes[i] = OPEN_LANE_INDICATOR;
        }
    }

    /**
     * Check if a runner has already been assign to a lane
     *
     * @param laneNumber lane number
     * @return boolean
     * @throws AssignRunnerException
     */
    public boolean isLaneAvailable(int laneNumber) throws AssignRunnerException {
        if (laneNumber < 1 || laneNumber > numLanes) {
            throw new AssignRunnerException("Invalid lane number - " + laneNumber);
        }
        return OPEN_LANE_INDICATOR.equals(lanes[laneNumber - 1]);
    }

    /**
     * Checks if a open lane existing within the field
     *
     * @return boolean
     */
    public boolean hasOpenLane() {
        try {
            for (int i = 1; i <= numLanes; i++) {
                if (isLaneAvailable(i)) {
                    return true;
                }
            }
        } catch (AssignRunnerException e) {
            return false;
        }
        return false;
    }

    /**
     * Returns all current lane assignments
     *
     * @return Array of all lane assignments
     */
    public String[] getLaneAssignments() {
        return lanes;
    }

    /**
     * Returns the runner assigned to a single lane
     *
     * @param laneNumber Lane number
     * @return Assigned runner's name
     * @throws AssignRunnerException
     */
    public String getLaneAssignment(int laneNumber) throws AssignRunnerException {
        if (laneNumber < 1 || laneNumber > numLanes) {
            throw new AssignRunnerException("Invalid lane number - " + laneNumber);
        }
        return lanes[laneNumber - 1];
    }

    /**
     * @return Total number of lanes
     */
    public int getNumLanes() {
        return numLanes;
    }
}
