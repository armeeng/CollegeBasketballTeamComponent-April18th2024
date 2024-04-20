import components.map.Map1L;
import components.sequence.Sequence1L;
import components.standard.Standard;

/**
 * Kernel interface for managing college basketball team statistics.
 */
public interface CollegeBasketballTeamKernel
        extends Standard<CollegeBasketballTeam> {

    /**
     * Adds a custom statistic to the team's data.
     *
     * @param category
     *            the category of the statistic
     * @param rank
     *            the rank of the statistic
     * @param value
     *            the value of the statistic
     * @requires category != null && value != null
     * @ensures the custom statistic is added to the team's data
     */
    void addCustomStatistic(StatCategory category, int rank, double value);

    /**
     * Removes a statistic category from the team's data.
     *
     * @param category
     *            the category of the statistic to remove
     * @requires category != null
     * @ensures the specified statistic category is removed from the team's data
     */
    void removeStatistic(StatCategory category);

    /**
     * Retrieves statistics by category for the team.
     *
     * @param category
     *            the category of statistics to retrieve
     * @return a map containing the statistics for the specified category
     * @requires category != null
     * @ensures returns a map containing the statistics for the specified
     *          category
     */
    Map1L<Integer, Double> getStatisticsByCategory(StatCategory category);

    /**
     * Retrieves all categories of statistics for the team.
     *
     * @return a sequence containing all statistic categories
     * @ensures returns a sequence containing all statistic categories
     */
    Sequence1L<StatCategory> getAllCategories();

    /**
     * Identifies the top 5 and bottom 5 statistics for the team.
     *
     * @ensures the top 5 and bottom 5 statistics for the team are identified
     */
    void bestAndWorstStatistics();
}
