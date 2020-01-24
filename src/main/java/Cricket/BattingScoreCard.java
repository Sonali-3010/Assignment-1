package Cricket;

public class BattingScoreCard extends ScoreCard
{
    private int noOfBalls;
    private int noOfRuns;
    private int noOfBoundaries;
    public BattingScoreCard()
    {
        noOfBalls = 0;
        noOfRuns = 0;
        noOfBoundaries = 0;
    }
    public BattingScoreCard(int noOfBalls, int noOfRuns, int noOfBoundaries)
    {
        this.noOfBalls = noOfBalls;
        this.noOfRuns = noOfRuns;
        this.noOfBoundaries = noOfBoundaries;
    }
    public int getNoOfBalls()       { return noOfBalls;      }
    public int getNoOfRuns()        { return noOfRuns;       }
    public int getNoOfBoundaries()  { return noOfBoundaries; }
    public void runsScored(int runs){ noOfRuns+=runs;        }
    public void ballPlayed()        { noOfBalls++;           }
    public void boundaryScored()    { noOfBoundaries++;      }
}
