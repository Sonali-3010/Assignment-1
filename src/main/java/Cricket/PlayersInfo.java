package Cricket;

public class PlayersInfo
{
    public String name;
    public int noOfBalls;
    public int noOfRuns;
    public int noOfBoundaries;
    public int noOfOvers;
    public int runsGiven;
    public int wicketsTaken;
    public int maidenOvers;
    public PlayersInfo(Player p)
    {
        name = p.getName();
        noOfBalls = p.getNoOfBalls();
        noOfRuns = p.getNoOfRuns();
        noOfBoundaries = p.getNoOfBoundaries();
        noOfOvers = p.getNoOfOvers();
        runsGiven = p.getRunsGiven();
        wicketsTaken = p.getWicketsTaken();
        maidenOvers = p.getMaidenOvers();
    }
}
