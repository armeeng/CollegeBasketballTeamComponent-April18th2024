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
    }

    private static void generateNewStats(CollegeBasketballTeam1 team) {

        // use some sort of logic, to take the already existing stats
        // in the object, and create new stats. For example, take
        // points per game, and total games played, to create a new
        // stat called "Total Points", and then add that to the object

        // can create very advanced stats, and predictive stats

        Map1L stat1 = team.getStatisticsByCategory(
                StatCategory.ASSISTS_PER_FIELD_GOAL_MADE);

        int gamesPlayed = 100;

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
