import java.util.Random;

import components.map.Map1L;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class ProofOfConcept {

    public enum StatCategory {
        FIRST_HALF_POINTS_PER_GAME, SECOND_HALF_POINTS_PER_GAME, ASSIST_TO_TURNOVER_RATIO, ASSISTS_PER_FIELD_GOAL_MADE, ASSISTS_PER_GAME, ASSISTS_PER_POSSESSION, AVERAGE_FIRST_HALF_MARGIN, AVERAGE_SECOND_HALF_MARGIN, AVERAGE_OVERTIME_MARGIN, AVERAGE_SCORING_MARGIN, BLOCK_PERCENTAGE, BLOCKS_PER_GAME, DEFENSIVE_EFFICIENCY, DEFENSIVE_REBOUNDING_PERCENTAGE, DEFENSIVE_REBOUNDS_PER_GAME, EFFECTIVE_FIELD_GOAL_PERCENTAGE, EFFECTIVE_POSSESSION_RATIO, EXTRA_CHANCES_PER_GAME, FIELD_GOALS_ATTEMPTED_PER_GAME, FIELD_GOALS_MADE_PER_GAME, FLOOR_PERCENTAGE, FREE_THROW_PERCENTAGE, FREE_THROW_RATE, FREE_THROWS_ATTEMPTED_PER_GAME, FREE_THROWS_MADE_PER_GAME, FTA_PER_FGA, FTM_PER_100_POSSESSIONS, NON_BLOCKED_TWO_POINT_PERCENTAGE, OFFENSIVE_EFFICIENCY, OFFENSIVE_REBOUNDING_PERCENTAGE, OFFENSIVE_REBOUNDS_PER_GAME, OPPONENT_FIRST_HALF_POINTS_PER_GAME, OPPONENT_SECOND_HALF_POINTS_PER_GAME, OPPONENT_ASSIST_TO_TURNOVER_RATIO, OPPONENT_ASSISTS_PER_FIELD_GOAL_MADE, OPPONENT_ASSISTS_PER_GAME, OPPONENT_ASSISTS_PER_POSSESSION, OPPONENT_AVERAGE_SCORING_MARGIN, OPPONENT_BLOCK_PERCENTAGE, OPPONENT_BLOCKS_PER_GAME, OPPONENT_DEFENSIVE_REBOUNDING_PERCENTAGE, OPPONENT_DEFENSIVE_REBOUNDS_PER_GAME, OPPONENT_EFFECTIVE_FIELD_GOAL_PERCENTAGE, OPPONENT_EFFECTIVE_POSSESSION_RATIO, OPPONENT_FIELD_GOALS_ATTEMPTED_PER_GAME, OPPONENT_FIELD_GOALS_MADE_PER_GAME, OPPONENT_FLOOR_PERCENTAGE, OPPONENT_FREE_THROW_PERCENTAGE, OPPONENT_FREE_THROW_RATE, OPPONENT_FREE_THROWS_ATTEMPTED_PER_GAME, OPPONENT_FREE_THROWS_MADE_PER_GAME, OPPONENT_FTA_PER_FGA, OPPONENT_FTM_PER_100_POSSESSIONS, OPPONENT_NON_BLOCKED_TWO_POINT_PERCENTAGE, OPPONENT_OFFENSIVE_REBOUNDING_PERCENTAGE, OPPONENT_OFFENSIVE_REBOUNDS_PER_GAME, OPPONENT_OVERTIME_POINTS_PER_GAME, OPPONENT_PERCENT_OF_POINTS_FROM_TWO_POINTERS, OPPONENT_PERCENT_OF_POINTS_FROM_THREE_POINTERS, OPPONENT_PERCENT_OF_POINTS_FROM_FREE_THROWS, OPPONENT_PERSONAL_FOUL_PERCENTAGE, OPPONENT_PERSONAL_FOULS_PER_GAME, OPPONENT_PERSONAL_FOULS_PER_POSSESSION, OPPONENT_POINTS_FROM_TWO_POINTERS, OPPONENT_POINTS_FROM_THREE_POINTERS, OPPONENT_POINTS_PER_GAME, OPPONENT_SHOOTING_PERCENTAGE, OPPONENT_STEAL_PERCENTAGE, OPPONENT_STEALS_PER_GAME, OPPONENT_STEALS_PER_POSSESSION, OPPONENT_TEAM_REBOUNDS_PER_GAME, OPPONENT_THREE_POINTERS_ATTEMPTED_PER_GAME, OPPONENT_THREE_POINTERS_MADE_PER_GAME, OPPONENT_THREE_POINT_PERCENTAGE, OPPONENT_THREE_POINT_RATE, OPPONENT_TOTAL_REBOUNDS_PER_GAME, OPPONENT_TRUE_SHOOTING_PERCENTAGE, OPPONENT_TURNOVER_PERCENTAGE, OPPONENT_TURNOVERS_PER_GAME, OPPONENT_TURNOVERS_PER_POSSESSION, OPPONENT_TWO_POINT_PERCENTAGE, OPPONENT_TWO_POINT_RATE, OVERTIME_POINTS_PER_GAME, PERCENT_OF_POINTS_FROM_TWO_POINTERS, PERCENT_OF_POINTS_FROM_THREE_POINTERS, PERCENT_OF_POINTS_FROM_FREE_THROWS, PERSONAL_FOUL_PERCENTAGE, PERSONAL_FOULS_PER_GAME, PERSONAL_FOULS_PER_POSSESSION, POINTS_FROM_TWO_POINTERS, POINTS_FROM_THREE_POINTERS, POINTS_PER_GAME, POSSESSIONS_PER_GAME, SHOOTING_PERCENTAGE, STEAL_PERCENTAGE, STEALS_PER_GAME, STEALS_PER_POSSESSION, TEAM_REBOUNDS_PER_GAME, THREE_POINTERS_ATTEMPTED_PER_GAME, THREE_POINTERS_MADE_PER_GAME, THREE_POINT_PERCENTAGE, THREE_POINT_RATE, TOTAL_REBOUNDING_PERCENTAGE, TOTAL_REBOUNDS_PER_GAME, TRUE_SHOOTING_PERCENTAGE, TURNOVER_PERCENTAGE, TURNOVERS_PER_GAME, TURNOVERS_PER_POSSESSION, TWO_POINT_PERCENTAGE, TWO_POINT_RATE, AWAY_BY_OTHER, CONSISTENCY_BY_OTHER, HOME_ADV_BY_OTHER, HOME_BY_OTHER, LUCK_BY_OTHER, NEUTRAL_BY_OTHER, PREDICTIVE_BY_OTHER, FIRST_HALF_BY_OTHER, FUTURE_SOS_BY_OTHER, IN_CONFERENCE_BY_OTHER, IN_CONFERENCE_SOS_BY_OTHER, LAST_10_GAMES_BY_OTHER, LAST_5_GAMES_BY_OTHER, NON_CONFERENCE_BY_OTHER, NON_CONFERENCE_SOS_BY_OTHER, SCHEDULE_STRENGTH_BY_OTHER, SEASON_SOS_BY_OTHER, SECOND_HALF_BY_OTHER, SOS_BASIC_BY_OTHER, VS_101_200_BY_OTHER, VS_1_25_BY_OTHER, VS_201_AND_UP_BY_OTHER, VS_26_50_BY_OTHER, VS_51_100_BY_OTHER
    }

    private Map1L<StatCategory, Map1L<Integer, Double>> teamData;

    private void createNewRep() {
        this.teamData = new Map1L<StatCategory, Map1L<Integer, Double>>();
    }

    /**
     * No-argument constructor.
     */
    public ProofOfConcept() {
        this.createNewRep();
    }

    // Kernel Methods

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

    public void removeStatistic(StatCategory category) {
        SimpleWriter out = new SimpleWriter1L();
        if (this.teamData.hasKey(category)) {
            this.teamData.remove(category);
        } else {
            out.println("Statistic Not Found");
        }
        out.close();
    }

    public Map1L<Integer, Double> getStatisticsByCategory(
            StatCategory category) {
//        returns a map with the key being the statistics rank, and the value of the map being the value of the value of the statistic
        return this.teamData.value(category);

    }

    public Sequence1L<StatCategory> getAllCategories() {
//        return a list of the all the keys in this

        Sequence1L<StatCategory> categories = new Sequence1L<>();

        for (Map1L.Pair<StatCategory, Map1L<Integer, Double>> pair : this.teamData) {
            categories.add(0, pair.key());
        }

        return categories;
    }

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

                // Logic for best statistics
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

                // Logic for worst statistics
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

        // Print out the best statistics names
        out.println("Top 5 statistics:");
        for (Map1L.Pair<StatCategory, Integer> bestStat : bestStatistics) {
            out.println(bestStat.key());
        }

        // Print out the worst statistics names
        out.println("Bottom 5 statistics:");
        for (Map1L.Pair<StatCategory, Integer> worstStat : worstStatistics) {
            out.println(worstStat.key());
        }

        out.close();

    }

    // Secondary Methods

    public static void runSimulation(ProofOfConcept team1,
            ProofOfConcept team2) {

        // using the stats from each team and all the
        // methods above, come up with a simple
        // method to try and predict the
        // outcome of the game, and also use some sort of
        // randomness so each simulation
        // isn't the same

        // for now ill just generate a random number between -2 and 1.
        // if the number is positive, team1 wins, otherwise team2 wins.
        // reason i'm using -2 to 1 instead of -1 to 1 is because team2
        // typically wins more often because they have home court advantage.

        //* good to note that team1 should represent the away team
        // and team2 represents the home team *

        Random random = new Random();
        double randomValue = -2 + (1 - (-2)) * random.nextDouble();

        if (randomValue >= 0) {
            System.out.println("Team 1 wins!");
        } else {
            System.out.println("Team 2 wins!");
        }
    }

    public static void main(String[] args) {
        ProofOfConcept ohioState = new ProofOfConcept();
        ProofOfConcept nebraska = new ProofOfConcept();

        // Adding statistics for Ohio State
        ohioState.addCustomStatistic(StatCategory.FIRST_HALF_POINTS_PER_GAME, 0,
                0);

        // Ideally the user wouldnt manually enter the stats like this.
        // They would automatically by scraping data or pulling from
        // a database or something.

        ohioState.bestAndWorstStatistics(); // can be used to compare the strengths and weakness of each team
        nebraska.bestAndWorstStatistics();

        runSimulation(ohioState, nebraska);
    }
}
