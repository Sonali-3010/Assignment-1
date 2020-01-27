package Cricket;

public class PlayersBattingInfo extends PlayersInfo
{
    public int noOfBalls;
    public int noOfRuns;
    public int noOfBoundaries;
    public PlayersBattingInfo(Player p)
    {
        super(p);
        noOfBalls = p.getNoOfBalls();
        noOfRuns = p.getNoOfRuns();
        noOfBoundaries = p.getNoOfBoundaries();
    }
}
