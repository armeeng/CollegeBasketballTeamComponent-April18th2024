import java.util.Random;

import components.map.Map1L;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public abstract class CollegeBasketballTeamComponentSecondary
        implements CollegeBasketballTeam {

    @Override
    public void runSimulation(CollegeBasketballTeam team2) {

        SimpleWriter out = new SimpleWriter1L();

        // Retrieve statistics for both teams

        Sequence1L<StatCategory> categories1 = this.getAllCategories();
        Sequence1L<StatCategory> categories2 = team2.getAllCategories();

        // Calculate score for team 1
        double score1 = 0.0;
        for (StatCategory category : categories1) {
            Map1L<Integer, Double> statistics = this
                    .getStatisticsByCategory(category);
            for (Map1L.Pair<Integer, Double> entry : statistics) {
                score1 += entry.value();
            }
        }

        // Calculate score for team 2
        double score2 = 0.0;
        for (StatCategory category : categories2) {
            Map1L<Integer, Double> statistics = team2
                    .getStatisticsByCategory(category);
            for (Map1L.Pair<Integer, Double> entry : statistics) {
                score2 += entry.value(); // Accumulate scores for all statistics
            }
        }

        // Introduce some randomness
        Random random = new Random();
        score1 *= random.nextDouble(); // Scale the score by a random factor between 0 and 1
        score2 *= random.nextDouble();

        // Determine the winner
        String winner;
        if (score1 > score2) {
            winner = "Team 1";
        } else if (score1 < score2) {
            winner = "Team 2";
        } else {
            winner = "Tie"; // Handle tie scenario
        }

        // Print simulation results
        out.println("Simulation Results:");
        out.println("Team 1 Score: " + score1);
        out.println("Team 2 Score: " + score2);
        out.println("Winner: " + winner);
        out.close();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team Statistics:\n");
        Sequence1L<StatCategory> categories = this.getAllCategories();
        for (StatCategory category : categories) {
            sb.append(category).append(":\n");
            Map1L<Integer, Double> statistics = this
                    .getStatisticsByCategory(category);
            for (Map1L.Pair<Integer, Double> statistic : statistics) {
                sb.append("Rank ").append(statistic.key()).append(": ")
                        .append(statistic.value()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object team2) {
        // Filter code for special cases
        if (team2 == this) {
            return true;
        }
        if (team2 == null) {
            return false;
        }
        if (!(team2 instanceof CollegeBasketballTeam)) {
            return false;
        }

        CollegeBasketballTeam thatTeam = (CollegeBasketballTeam) team2;

        // Check if the teams have the same statistics data

        // Check if the categories are the same
        Sequence1L<StatCategory> team1Categories = this.getAllCategories();
        Sequence1L<StatCategory> team2Categories = thatTeam.getAllCategories();

        if (!team1Categories.equals(team2Categories)) {
            return false;
        }

        // Check if the statistics for each category are the same
        for (StatCategory category : team1Categories) {
            Map1L<Integer, Double> thisStats = this
                    .getStatisticsByCategory(category);
            Map1L<Integer, Double> thoseStats = thatTeam
                    .getStatisticsByCategory(category);

            if (!thisStats.equals(thoseStats)) {
                return false;
            }
        }

        return true;

    }

    @Override
    public int hashCode() {
        int hashCode = 0;

        // Iterate through each category of statistics data
        // then add the rank of the team to the hashCode value.
        Sequence1L<StatCategory> categories = this.getAllCategories();
        for (StatCategory category : categories) {
            Map1L<Integer, Double> stats = this
                    .getStatisticsByCategory(category);
            for (Map1L.Pair<Integer, Double> pair : stats) {
                int stat = pair.key();
                hashCode += stat;
            }
        }

        return hashCode;
    }

}
