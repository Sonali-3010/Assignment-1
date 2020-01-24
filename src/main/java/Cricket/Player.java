package Cricket;

public class Player
{
    private final int playerID;
    private final String name;
    private final Team team;
    private BattingScoreCard battingScoreCard;
    private BowlingScoreCard bowlingScoreCard;
    public Player(int playerID, String name, Team team)
    {
        this.playerID = playerID;
        this.name = name;
        this.team = team;
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();
    }
    public String getName() { return name; }
    public BattingScoreCard getBattingScoreCard() { return battingScoreCard; }
    public BowlingScoreCard getBowlingScoreCard() { return bowlingScoreCard; }
    //Batting
    public int getNoOfBalls()       { return battingScoreCard.getNoOfBalls();      }
    public int getNoOfRuns()        { return battingScoreCard.getNoOfRuns();       }
    public int getNoOfBoundaries()  { return battingScoreCard.getNoOfBoundaries(); }
    public void runsScored(int runs){ battingScoreCard.runsScored(runs);        }
    public void ballPlayed()        { battingScoreCard.ballPlayed();           }
    public void boundaryScored()    { battingScoreCard.boundaryScored();      }
    //Bowling
    public int getNoOfOvers()    { return bowlingScoreCard.getNoOfOvers();    }
    public int getRunsGiven()       { return bowlingScoreCard.getRunsGiven();    }
    public int getWicketsTaken()    { return bowlingScoreCard.getWicketsTaken(); }
    public int getMaidenOvers()     { return bowlingScoreCard.getMaidenOvers();  }
    public void runsGiven(int runs) { bowlingScoreCard.runsGiven(runs);     }
    public void overPlayed()        { bowlingScoreCard.overPlayed();         }
    public void wicketTaken()       { bowlingScoreCard.wicketTaken();      }
    public void maidenOver()        { bowlingScoreCard.maidenOver();       }
    //Print function
    public String printBat()
    {
        String tab = "&nbsp";
        String print = String.format("%-" + 6 + "s", name) + tab +
                String.format("%-" + 4 + "s", battingScoreCard.getNoOfRuns()) + tab +
                String.format("%-" + 5 + "s",battingScoreCard.getNoOfBalls()) + tab +
                battingScoreCard.getNoOfBoundaries() + "<br>" ;
        return print;
    }
}
