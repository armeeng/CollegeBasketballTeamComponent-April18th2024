import components.map.Map1L;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class CollegeBasketballTeam1
        extends CollegeBasketballTeamComponentSecondary {

    /**
     * Kernel implementation of CollegeBasketballTeam using a Map of Maps
     * representation.
     *
     * Convention: The outer Map stores statistics categorized by StatCategory.
     * Each StatCategory is mapped to an inner Map, which stores statistics
     * ranked by an integer value. The statistics are stored as (rank, value)
     * pairs.
     *
     * Correspondence: Each StatCategory maps to an inner Map containing the
     * ranked statistics for that category. The outer Map represents the entire
     * collection of statistics for the team.
     */

    private Map1L<StatCategory, Map1L<Integer, Double>> teamData;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.teamData = new Map1L<>();
    }

    /**
     * Default constructor.
     */
    public CollegeBasketballTeam1() {
        this.createNewRep();
    }

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
    @Override
    public void addCustomStatistic(StatCategory category, int rank,
            double value) {
        if (this.teamData.hasKey(category)) {
            Map1L<Integer, Double> newStatistic = new Map1L<>();
            newStatistic.add(rank, value);
            this.teamData.replaceValue(category, newStatistic);
        } else {
            Map1L<Integer, Double> newCategoryData = new Map1L<>();
            newCategoryData.add(rank, value);
            this.teamData.add(category, newCategoryData);
        }
    }

    /**
     * Removes a statistic category from the team's data.
     *
     * @param category
     *            the category of the statistic to remove
     * @requires category != null
     * @ensures the specified statistic category is removed from the team's data
     */
    @Override
    public void removeStatistic(StatCategory category) {
        SimpleWriter out = new SimpleWriter1L();
        if (this.teamData.hasKey(category)) {
            this.teamData.remove(category);
        } else {
            out.println("Statistic Not Found");
        }
        out.close();
    }

    /**
     * Retrieves statistics by category for the team.
     *
     * @param category
     *            the category of statistics to retrieve
     * @return a map containing the statistics for the specified category, or an
     *         empty map if the category does not exist
     * @requires category != null
     * @ensures returns a map containing the statistics for the specified
     *          category, or an empty map if the category does not exist
     */
    @Override
    public Map1L<Integer, Double> getStatisticsByCategory(
            StatCategory category) {
        if (this.teamData.hasKey(category)) {
            return this.teamData.value(category);
        } else {
            return new Map1L<>();
        }
    }

    /**
     * Retrieves all categories of statistics for the team.
     *
     * @return a sequence containing all statistic categories
     * @ensures returns a sequence containing all statistic categories
     */
    @Override
    public Sequence1L<StatCategory> getAllCategories() {

        Sequence1L<StatCategory> categories = new Sequence1L<>();

        for (Map1L.Pair<StatCategory, Map1L<Integer, Double>> pair : this.teamData) {
            categories.add(0, pair.key());
        }

        return categories;
    }

    /**
     * Identifies the top 5 and bottom 5 statistics for the team.
     *
     * @ensures the top 5 and bottom 5 statistics for the team are identified
     */
    @Override
    public void bestAndWorstStatistics() {

        SimpleWriter out = new SimpleWriter1L();
        Map1L<StatCategory, Integer> bestStatistics = new Map1L<>();
        Map1L<StatCategory, Integer> worstStatistics = new Map1L<>();

        // Iterate over the teamData map to find the best and worst statistics
        for (Map1L.Pair<StatCategory, Map1L<Integer, Double>> team : this.teamData) {
            StatCategory category = team.key();
            Map1L<Integer, Double> teamValues = team.value();
            for (Map1L.Pair<Integer, Double> teamPair : teamValues) {
                int rank = teamPair.key();

                // Logic for worst statistics
                if (bestStatistics.size() < 5) {
                    bestStatistics.add(category, rank);
                } else {
                    int worstRank = Integer.MAX_VALUE;
                    StatCategory worstCategory = null;
                    for (Map1L.Pair<StatCategory, Integer> bestStats : bestStatistics) {
                        int currentRank = bestStats.value();
                        if (currentRank < worstRank) {
                            worstRank = currentRank;
                            worstCategory = bestStats.key();
                        }
                    }
                    if (rank > worstRank) {
                        bestStatistics.remove(worstCategory);
                        bestStatistics.add(category, rank);
                    }
                }

                // Logic for best statistics
                if (worstStatistics.size() < 5) {
                    worstStatistics.add(category, rank);
                } else {
                    int bestRank = -1; // Initialize to minimum possible value
                    StatCategory bestCategory = null;
                    for (Map1L.Pair<StatCategory, Integer> worstStats : worstStatistics) {
                        int currentRank = worstStats.value();
                        if (currentRank > bestRank) {
                            bestRank = currentRank;
                            bestCategory = worstStats.key();
                        }
                    }
                    if (rank < bestRank) {
                        worstStatistics.remove(bestCategory);
                        worstStatistics.add(category, rank);
                    }
                }
            }
        }

        // Print out the worst statistics names
        out.println("Top 5 statistics:");
        for (Map1L.Pair<StatCategory, Integer> worstStat : worstStatistics) {
            out.println(worstStat.key());
        }

        // Print out the best statistics names
        out.println("Bottom 5 statistics:");
        for (Map1L.Pair<StatCategory, Integer> bestStat : bestStatistics) {
            out.println(bestStat.key());
        }

        out.close();

    }

    @Override
    public void transferFrom(CollegeBasketballTeam team2) {

        // Clear the current team's data
        this.clear();

        // Iterate over the categories of team2 and transfer their statistics
        Sequence1L<StatCategory> categories = team2.getAllCategories();
        for (int i = 0; i < categories.length(); i++) {
            StatCategory category = categories.entry(i);
            Map1L<Integer, Double> stats = team2
                    .getStatisticsByCategory(category);

            // Transfer each statistic from team2 to this team
            for (Map1L.Pair<Integer, Double> pair : stats) {
                int rank = pair.key();
                double value = pair.value();
                this.addCustomStatistic(category, rank, value);
            }
        }

        // Clear team2's data after transfer
        team2.clear();
    }

    @Override
    public void clear() {
        this.teamData.clear();
    }

    @Override
    public CollegeBasketballTeam1 newInstance() {
        return new CollegeBasketballTeam1();
    }
}
