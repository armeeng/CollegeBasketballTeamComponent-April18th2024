import components.map.Map1L;

/**
 *
 * @author Armeen Ghoorkhanian
 */
public final class Sample2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Sample2() {
        // no code needed here
    }

    private static void scrapeData(CollegeBasketballTeam1 team,
            TeamNames name) {
        // scrape data college basketball data using the TeamName and then
        // uses the methods from the component, to do things like
        // add, remove, etc... and fill the object with the desired stats

        team.addCustomStatistic(StatCategory.POINTS_FROM_THREE_POINTERS, 1,
                100);
        team.addCustomStatistic(StatCategory.AVERAGE_FIRST_HALF_MARGIN, 323,
                -18);
        team.addCustomStatistic(StatCategory.DEFENSIVE_EFFICIENCY, 34, 10);
        team.addCustomStatistic(StatCategory.HOME_BY_OTHER, 101, 101);

    }

    private static void generateNewStats(CollegeBasketballTeam1 team) {

        // use some sort of logic, to take the already existing stats
        // in the object, and create new stats. For example, take
        // points per game, and total games played, to create a new
        // stat called "Total Points", and then add that to the object

        // can create very advanced stats, and predictive stats

        // Retrieve the statistics for the specified category
        Map1L<Integer, Double> statMap = team.getStatisticsByCategory(
                StatCategory.ASSISTS_PER_FIELD_GOAL_MADE);

        int gamesPlayed = 100;

        // Iterate over the entries in the map and multiply each value by 100
        for (Map1L.Pair<Integer, Double> entry : statMap) {
            double value = entry.value();
            // Multiply the value by 100
            value *= gamesPlayed;

            team.addCustomStatistic(StatCategory.ASSISTS_PER_FIELD_GOAL_MADE,
                    entry.key(), value);

            // you could add this statistic to the component or do
            // whatever you want with it.
        }

    }

    private static void uploadToWebsite(CollegeBasketballTeam1 ohioState) {

        ohioState.bestAndWorstStatistics();

        // upload the new statistics, and best statistics to a website
        // that keeps track of teams stats, and has its own rankings.
        // when you upload to the website, it updates the rankings, and stats
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        CollegeBasketballTeam1 ohioState = new CollegeBasketballTeam1();
        CollegeBasketballTeam1 nebraska = new CollegeBasketballTeam1();

        // method to fill the object with stats
        scrapeData(ohioState, TeamNames.OHIO_STATE);
        scrapeData(nebraska, TeamNames.NEBRASKA);

        generateNewStats(ohioState);

        uploadToWebsite(ohioState);

    }

}
