package Cricket;
import java.util.ArrayList;

public class Result
{
    private int runsScored;
    private int wicketsTaken;
    private int numOfBalls;
    private String winningTeam;
    public Result(int runsScored, int wicketsTaken, int numOfBalls, String winningTeam)
    {
        this.numOfBalls = numOfBalls;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
        this.winningTeam = winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void RunsScored(int runs) { runsScored += runs; }
    public void wicketTaken() { wicketsTaken++; }
    public void numOfBalls()    {   numOfBalls++;   }
    public boolean allWicketsTaken() { return (wicketsTaken == 10) ;}


    @Override public String toString() {
        return "Result{ " +
                "Runs scored: " + runsScored +
                ", Wickets taken: " + wicketsTaken +
                ", Balls played: " + numOfBalls +
                ", Winning Team: '" + winningTeam + '\'' +
                '}';
    }
    public ArrayList<String> stats()
    {
        ArrayList<String> result = new ArrayList<String>();
        result.add(String.valueOf(runsScored));
        result.add(String.valueOf(wicketsTaken));
        result.add(String.valueOf(numOfBalls));
        return result;
    }

    public String printWin(int target)
    {
        if(winningTeam.equals("TeamA"))         return (winningTeam + " won by " + (target-runsScored) + " runs!");
        else if(winningTeam.equals("TeamB"))    return  (winningTeam + " won by " + (10-wicketsTaken) + " wickets!");
        else                                    return ("Match tied!");
    }

}
