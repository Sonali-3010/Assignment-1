package Cricket;
import java.util.ArrayList;

public class InningData
{
    private Team battingTeam;
    private Team bowlingTeam;
    private int runsScored;
    private int wicketsTaken;
    private int numOfBalls;

    public InningData(Team battingTeam, Team bowlingTeam)
    {
        this(battingTeam, bowlingTeam, 0, 0, 0);
    }
    public InningData(Team battingTeam, Team bowlingTeam, int runsScored, int wicketsTaken, int numOfBalls)
    {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.numOfBalls = numOfBalls;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
    }
    public Team getBattingTeam() { return battingTeam; }
    public Team getBowlingTeam() { return bowlingTeam; }
    public int getRunsScored()   { return runsScored; }
    public int getWicketsTaken()   { return wicketsTaken; }
    public void wicketTaken()
    {
        wicketsTaken++;
        battingTeam.strikerOut();
        bowlingTeam.wicketTaken();
    }
    public void ballPlayed()
    {
        numOfBalls++;
        battingTeam.ballPlayed();
        bowlingTeam.ballPlayed();
    }
    public boolean allWicketsTaken() { return (wicketsTaken == 10) ;}
    public void RunsScored(int runs)
    {
        runsScored += runs;
        battingTeam.runsScored(runs);
        bowlingTeam.runsGiven(runs);
    }
    public ArrayList<Integer> stats()
    {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(runsScored);
        result.add(wicketsTaken);
        result.add(numOfBalls);
        return result;
    }

    /*public String printWin(int target)
    {
        if(winningTeam.equals("TeamA"))         return (winningTeam + " won by " + (target-runsScored) + " runs!");
        else if(winningTeam.equals("TeamB"))    return  (winningTeam + " won by " + (10-wicketsTaken) + " wickets!");
        else                                    return ("Match tied!");
    }*/

}