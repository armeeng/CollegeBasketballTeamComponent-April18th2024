/**
 *
 * @author Armeen Ghoorkhanian
 */
public final class Sample1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Sample1() {
        // no code needed here
    }

    public static void scrapeData(CollegeBasketballTeam1 team, TeamNames name) {
        // uses the methods from the component, to do things like
        // add, remove, etc... and fill the object with desired stats

        team.addCustomStatistic(StatCategory.POINTS_FROM_THREE_POINTERS, 1,
                100);
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

        ohioState.runSimulation(nebraska);

    }

}
