/**
 * Enhanced interface for managing college basketball team statistics and
 * simulations.
 */
public interface CollegeBasketballTeam extends CollegeBasketballTeamKernel {

    /**
     * Runs a simulation to predict the outcome of a game between two teams.
     *
     * @param team1
     *            object of team1 which contains the statistics
     * @param team2
     *            object of team2 which contains the statistics
     * @requires team1 != null && team2 != null
     * @ensures a simulation is run to predict the outcome of the game between
     *          the two teams
     */
    void runSimulation(CollegeBasketballTeam team2);
}
