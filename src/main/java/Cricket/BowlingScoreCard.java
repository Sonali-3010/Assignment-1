package Cricket;

public class BowlingScoreCard extends ScoreCard
{
    private int noOfOvers;
    private int runsGiven;
    private int wicketsTaken;
    private int maidenOvers;
    public BowlingScoreCard()
    {
        this(0, 0, 0, 0);
    }
    public BowlingScoreCard(int noOfOvers, int runsGiven, int wicketsTaken, int maidenOvers)
    {
        this.noOfOvers = noOfOvers;
        this.runsGiven = runsGiven;
        this.wicketsTaken = wicketsTaken;
        this.maidenOvers = maidenOvers;
    }
    public int getNoOfOvers()       { return noOfOvers;    }
    public int getRunsGiven()       { return runsGiven;    }
    public int getWicketsTaken()    { return wicketsTaken; }
    public int getMaidenOvers()     { return maidenOvers;  }
    public void runsGiven(int runs) { runsGiven+=runs;     }
    public void wicketTaken()       { wicketsTaken++;      }
    public void maidenOver()        { maidenOvers++;       }
    public void overPlayed()        { noOfOvers++; }
}
