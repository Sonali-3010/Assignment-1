package Cricket;

public class PlayersBowlingInfo extends PlayersInfo
{
    public int noOfOvers;
    public int runsGiven;
    public int wicketsTaken;
    public int maidenOvers;
    public PlayersBowlingInfo(Player p)
    {
        super(p);
        noOfOvers = p.getNoOfOvers();
        runsGiven = p.getRunsGiven();
        wicketsTaken = p.getWicketsTaken();
        maidenOvers = p.getMaidenOvers();
    }
}
