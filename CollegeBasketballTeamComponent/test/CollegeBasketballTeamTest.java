import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import components.map.Map1L;
import components.sequence.Sequence1L;
import components.set.Set1L;

public class CollegeBasketballTeamTest {

    private CollegeBasketballTeam1 team;
    private CollegeBasketballTeam1 team2;

    @Before
    public void constructor() {
        this.team = new CollegeBasketballTeam1();
        this.team2 = new CollegeBasketballTeam1();

    }

    @Test
    public void testAddCustomStatistic_AddNewCategory() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(true, statistics.hasKey(1));
        assertEquals(20.5, statistics.value(1), 0.0);
    }

    @Test
    public void testAddCustomStatistic_ReplaceExistingCategory() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);

        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(true, statistics.hasKey(2));
        assertEquals(25.0, statistics.value(2), 0.0);
    }

    @Test
    public void testAddCustomStatistic_AddToMultipleCategories() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);

        Map1L<Integer, Double> pointsStatistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);
        Map1L<Integer, Double> reboundsStatistics = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);

        assertEquals(true, pointsStatistics.hasKey(1));
        assertEquals(20.5, pointsStatistics.value(1), 0.0);

        assertEquals(true, reboundsStatistics.hasKey(1));
        assertEquals(10.0, reboundsStatistics.value(1), 0.0);
    }

    @Test
    public void testAddCustomStatistic_AddDuplicateRank() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 25.0);

        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(true, statistics.hasKey(1));
        assertEquals(25.0, statistics.value(1), 0.00);
    }

    @Test
    public void testAddCustomStatistic_AddMultipleStatsDifferentCategories() {
        // Add multiple statistics with different categories
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 2,
                15.0);

        Map1L<Integer, Double> pointsStatistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);
        Map1L<Integer, Double> reboundsStatistics = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);

        assertEquals(true, pointsStatistics.hasKey(1));
        assertEquals(20.5, pointsStatistics.value(1), 0.0);

        assertEquals(true, reboundsStatistics.hasKey(2));
        assertEquals(15.0, reboundsStatistics.value(2), 0.0);
    }

    @Test
    public void testRemoveStatistic_ExistingCategory() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);

        this.team.removeStatistic(StatCategory.POINTS_PER_GAME);
        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(0, statistics.size());

    }

    @Test
    public void testRemoveStatistic_NonExistingCategory() {
        this.team.removeStatistic(StatCategory.POINTS_PER_GAME);
        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(0, statistics.size());
    }

    @Test
    public void testRemoveStatistic_CategoryWithMultipleStatistics() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);

        this.team.removeStatistic(StatCategory.POINTS_PER_GAME);

        Map1L<Integer, Double> pointsStatistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(false, pointsStatistics.hasKey(1));
        assertEquals(false, pointsStatistics.hasKey(2));
    }

    @Test
    public void testRemoveStatistic_NonExistingAndExistingCategory() {
        this.team.addCustomStatistic(StatCategory.ASSISTS_PER_FIELD_GOAL_MADE,
                1, 10.0);
        this.team.removeStatistic(StatCategory.POINTS_PER_GAME);

        Map1L<Integer, Double> assistsStatistics = this.team
                .getStatisticsByCategory(
                        StatCategory.ASSISTS_PER_FIELD_GOAL_MADE);

        assertEquals(true, assistsStatistics.hasKey(1));
        assertEquals(10.0, assistsStatistics.value(1), 0.0);

    }

    @Test
    public void testRemoveStatistic_LeaveOtherCategoriesIntact() {
        this.team.addCustomStatistic(StatCategory.FIRST_HALF_BY_OTHER, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_FROM_TWO_POINTERS, 1,
                10.0);

        this.team.removeStatistic(StatCategory.FIRST_HALF_BY_OTHER);

        Map1L<Integer, Double> pointsStatistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_FROM_TWO_POINTERS);

        assertEquals(true, pointsStatistics.hasKey(1));
        assertEquals(10.0, pointsStatistics.value(1), 0.01);
    }

    @Test
    public void testGetStatisticsByCategory() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);
        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);
        assertEquals(true, statistics.hasKey(1));
        assertEquals(20.5, statistics.value(1), 0.0);
    }

    @Test
    public void testGetStatisticsByCategory_ExistingCategory() {
        // Add some initial data
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);

        // Retrieve statistics for an existing category
        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        // Check if the returned statistics map contains the expected entries
        assertEquals(true, statistics.hasKey(2));
        assertEquals(25.0, statistics.value(2), 0.0);
    }

    @Test
    public void testGetStatisticsByCategory_NonExistingCategory() {
        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);

        assertEquals(0, statistics.size());
    }

    @Test
    public void testGetStatisticsByCategory_EmptyCategory() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);

        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);

        assertEquals(0, statistics.size());
    }

    @Test
    public void testGetAllCategories() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);

        Set1L<StatCategory> expectedSet = new Set1L<>();
        expectedSet.add(StatCategory.POINTS_PER_GAME);
        expectedSet.add(StatCategory.TEAM_REBOUNDS_PER_GAME);

        Sequence1L<StatCategory> categories = this.team.getAllCategories();

        for (int i = 0; i < categories.length(); i++) {
            assertEquals(true, expectedSet.contains(categories.entry(i)));
        }

        assertEquals(false,
                expectedSet.contains(StatCategory.ASSIST_TO_TURNOVER_RATIO));
    }

    @Test
    public void testGetAllCategories_EmptyTeam() {

        Sequence1L<StatCategory> expectedCategories = new Sequence1L<>();
        Sequence1L<StatCategory> categories = this.team.getAllCategories();
        assertEquals(expectedCategories, categories);
    }

    @Test
    public void testGetAllCategories_MultipleCategories() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);
        this.team.addCustomStatistic(StatCategory.ASSISTS_PER_GAME, 1, 15.0);

        Set1L<StatCategory> expectedSet = new Set1L<>();
        expectedSet.add(StatCategory.POINTS_PER_GAME);
        expectedSet.add(StatCategory.TEAM_REBOUNDS_PER_GAME);
        expectedSet.add(StatCategory.ASSISTS_PER_GAME);

        Sequence1L<StatCategory> categories = this.team.getAllCategories();

        for (int i = 0; i < categories.length(); i++) {
            assertEquals(true, expectedSet.contains(categories.entry(i)));
        }

        assertEquals(false,
                expectedSet.contains(StatCategory.ASSISTS_PER_POSSESSION));
        assertEquals(false, expectedSet.contains(StatCategory.LUCK_BY_OTHER));

    }

    @Test
    public void testGetAllCategories_SameCategoryMultipleTimes() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);

        Sequence1L<StatCategory> expectedCategories = new Sequence1L<>();
        expectedCategories.add(0, StatCategory.POINTS_PER_GAME);

        Sequence1L<StatCategory> categories = this.team.getAllCategories();

        assertEquals(expectedCategories, categories);
    }

    @Test
    public void testBestAndWorstStatistics_NoStatistics() {
        this.team.bestAndWorstStatistics();
    }

    @Test
    public void testBestAndWorstStatistics_LessThan5Statistics() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);
        this.team.bestAndWorstStatistics();
    }

    @Test
    public void testBestAndWorstStatistics_MoreThan5Statistics() {
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);
        this.team.addCustomStatistic(StatCategory.EXTRA_CHANCES_PER_GAME, 5,
                25.0);
        this.team.addCustomStatistic(StatCategory.OFFENSIVE_REBOUNDS_PER_GAME,
                3, 3.0);
        this.team.addCustomStatistic(StatCategory.VS_51_100_BY_OTHER, 3, 43.0);

        this.team.addCustomStatistic(StatCategory.NON_CONFERENCE_SOS_BY_OTHER,
                83, 20.5);
        this.team.addCustomStatistic(StatCategory.NEUTRAL_BY_OTHER, 92, 25.0);
        this.team.addCustomStatistic(StatCategory.FUTURE_SOS_BY_OTHER, 105,
                25.0);
        this.team.addCustomStatistic(StatCategory.FTA_PER_FGA, 75, 3.0);
        this.team.addCustomStatistic(StatCategory.OFFENSIVE_EFFICIENCY, 120,
                43.0);

        this.team.addCustomStatistic(StatCategory.AWAY_BY_OTHER, 323, 25.0);
        this.team.addCustomStatistic(StatCategory.AVERAGE_SECOND_HALF_MARGIN,
                283, -25.0);
        this.team.addCustomStatistic(StatCategory.BLOCK_PERCENTAGE, 129, 3.0);
        this.team.addCustomStatistic(
                StatCategory.EFFECTIVE_FIELD_GOAL_PERCENTAGE, 222, 5.0);
        this.team.addCustomStatistic(StatCategory.CONSISTENCY_BY_OTHER, 300,
                -32.0);

        this.team.bestAndWorstStatistics();
    }

    @Test
    public void testBestAndWorstStatistics_RepeatedRanks() {

        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                20.5);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 25.0);
        this.team.addCustomStatistic(StatCategory.EXTRA_CHANCES_PER_GAME, 5,
                25.0);
        this.team.addCustomStatistic(StatCategory.OFFENSIVE_REBOUNDS_PER_GAME,
                2, 3.0);
        this.team.addCustomStatistic(StatCategory.VS_51_100_BY_OTHER, 2, 43.0);

        this.team.addCustomStatistic(StatCategory.NON_CONFERENCE_SOS_BY_OTHER,
                83, 20.5);
        this.team.addCustomStatistic(StatCategory.NEUTRAL_BY_OTHER, 75, 25.0);
        this.team.addCustomStatistic(StatCategory.FUTURE_SOS_BY_OTHER, 105,
                25.0);
        this.team.addCustomStatistic(StatCategory.FTA_PER_FGA, 75, 3.0);
        this.team.addCustomStatistic(StatCategory.OFFENSIVE_EFFICIENCY, 120,
                43.0);

        this.team.addCustomStatistic(StatCategory.AWAY_BY_OTHER, 323, 25.0);
        this.team.addCustomStatistic(StatCategory.AVERAGE_SECOND_HALF_MARGIN,
                283, -25.0);
        this.team.addCustomStatistic(StatCategory.BLOCK_PERCENTAGE, 323, 3.0);
        this.team.addCustomStatistic(
                StatCategory.EFFECTIVE_FIELD_GOAL_PERCENTAGE, 323, 5.0);
        this.team.addCustomStatistic(StatCategory.CONSISTENCY_BY_OTHER, 323,
                -32.0);

        this.team.bestAndWorstStatistics();
    }

    @Test
    public void testTransferFrom() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team2.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);

        this.team.transferFrom(this.team2);

        Map1L<Integer, Double> teamStatistics = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);
        assertEquals(true, teamStatistics.hasKey(1));
        assertEquals(10.0, teamStatistics.value(1), 0.01);

        Sequence1L<StatCategory> categories = this.team2.getAllCategories();

        assertEquals(0, categories.length());

    }

    @Test
    public void testTransferFrom_OneEmptyOneNot() {
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team2.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);

        this.team.transferFrom(this.team2);

        Map1L<Integer, Double> reboundsStatistics = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);
        Map1L<Integer, Double> pointsStatistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(true, reboundsStatistics.hasKey(1));
        assertEquals(10.0, reboundsStatistics.value(1), 0.01);

        assertEquals(true, pointsStatistics.hasKey(1));
        assertEquals(20.5, pointsStatistics.value(1), 0.01);

        Sequence1L<StatCategory> categories = this.team2.getAllCategories();
        assertEquals(0, categories.length());
    }

    @Test
    public void testTransferFrom_MultipleCategories() {
        this.team.addCustomStatistic(StatCategory.BLOCK_PERCENTAGE, 103, 340.5);
        this.team.addCustomStatistic(StatCategory.FIRST_HALF_BY_OTHER, 12,
                99.0);
        this.team.addCustomStatistic(StatCategory.ASSISTS_PER_GAME, 2, 12.0);

        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team2.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                10.0);
        this.team2.addCustomStatistic(StatCategory.ASSISTS_PER_GAME, 1, 15.0);

        this.team.transferFrom(this.team2);

        Map1L<Integer, Double> teamReboundsStats = this.team
                .getStatisticsByCategory(StatCategory.TEAM_REBOUNDS_PER_GAME);
        assertEquals(true, teamReboundsStats.hasKey(1));
        assertEquals(10.0, teamReboundsStats.value(1), 0.01);

        Map1L<Integer, Double> pointsStats = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);
        assertEquals(true, pointsStats.hasKey(1));
        assertEquals(20.5, pointsStats.value(1), 0.01);

        Map1L<Integer, Double> assistsStats = this.team
                .getStatisticsByCategory(StatCategory.ASSISTS_PER_GAME);
        assertEquals(true, assistsStats.hasKey(1));
        assertEquals(15.0, assistsStats.value(1), 0.01);

        Sequence1L<StatCategory> categories = this.team2.getAllCategories();
        assertEquals(0, categories.length());
    }

    @Test
    public void testClear() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 20.5);
        this.team.addCustomStatistic(StatCategory.FREE_THROW_RATE, 1, 10.0);

        this.team.clear();

        Map1L<Integer, Double> pointsStatistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);
        Map1L<Integer, Double> freeThrowStatistics = this.team
                .getStatisticsByCategory(StatCategory.FREE_THROW_RATE);

        assertEquals(0, pointsStatistics.size());
        assertEquals(0, freeThrowStatistics.size());
    }

    @Test
    public void testClearEmpty() {
        this.team.clear();

        Map1L<Integer, Double> statistics = this.team
                .getStatisticsByCategory(StatCategory.POINTS_PER_GAME);

        assertEquals(0, statistics.size());
    }

    @Test
    public void testNewInstance() {
        CollegeBasketballTeam1 newInstance = this.team.newInstance();

        assertEquals(newInstance, this.team);

    }

    @Test
    public void testRunSimulation_Team1Wins() {

        // this only works like 99.9% of the time because there is some randomness

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 0.0);

        this.team.runSimulation(this.team2);

    }

    @Test
    public void testRunSimulation_Team2Wins() {

        // this only works like 99.9% of the time because there is some randomness

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 0.0);
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);

        this.team.runSimulation(this.team2);

    }

    @Test
    public void testToString() {
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                40.0);

        String expected = "Team Statistics:\n" + "TEAM_REBOUNDS_PER_GAME:\n"
                + "Rank 1: 40.0\n" + "POINTS_PER_GAME:\n" + "Rank 1: 80.0\n";
        assertEquals(expected, this.team.toString());
    }

    @Test
    public void testToString_EmptyTeam() {

        String expected = "Team Statistics:\n";
        assertEquals(expected, this.team.toString());
    }

    @Test
    public void testToString_MultipleStatsSameCategory() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 2, 90.0);

        String expected = "Team Statistics:\n" + "POINTS_PER_GAME:\n"
                + "Rank 2: 90.0\n";
        assertEquals(expected, this.team.toString());
    }

    @Test
    public void testEquals_SameTeams() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);

        assertEquals(this.team, this.team2);
    }

    @Test
    public void testEquals_DifferentTeams() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 70.0);

        assertEquals(false, this.team.equals(this.team2));
    }

    @Test
    public void testEquals_SameStatisticsDifferentOrder() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                40.0);
        this.team2.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                40.0);
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);

        assertEquals(this.team, this.team2);
    }

    @Test
    public void testEquals_DifferentNumberOfStatistics() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                40.0);
        this.team2.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);

        assertFalse(this.team.equals(this.team2));
    }

    @Test
    public void testHashCode() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                40.0);

        assertEquals(2, this.team.hashCode());
    }

    @Test
    public void testHashCode_EmptyTeam() {

        assertEquals(0, this.team.hashCode());
    }

    @Test
    public void testHashCode_SingleStatistic() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);

        assertEquals(1, this.team.hashCode());
    }

    @Test
    public void testHashCode_MultipleStatistics() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 2,
                40.0);

        assertEquals(3, this.team.hashCode());
    }

    @Test
    public void testHashCode_OrderIndependence() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 2,
                40.0);
        int hashCode1 = this.team.hashCode();

        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 2,
                40.0);
        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        int hashCode2 = this.team.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testHashCode_Consistency() {

        this.team.addCustomStatistic(StatCategory.POINTS_PER_GAME, 1, 80.0);
        this.team.addCustomStatistic(StatCategory.TEAM_REBOUNDS_PER_GAME, 1,
                40.0);

        int hashCode1 = this.team.hashCode();
        int hashCode2 = this.team.hashCode();
        int hashCode3 = this.team.hashCode();

        assertEquals(hashCode1, hashCode2);
        assertEquals(hashCode2, hashCode3);
    }

}
