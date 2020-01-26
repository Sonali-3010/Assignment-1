package Cricket;

public class Player
{
    private final int playerID;
    private final String name;
    private final Team team;
    private BattingScoreCard battingScoreCard;
    private BowlingScoreCard bowlingScoreCard;
    private int rating;
    private Type type;
    public enum Type { BATSMAN, BOWLER }

    private int[] freq = new int[]{1,1,1,1,1,1,1,1};
    private int[] arr  = new int[]{0,1,2,3,4,5,6,7};
    private int[] prefix = new int[]{0,0,0,0,0,0,0,0};
    public Player(int playerID, String name, Team team, int t, int rating)
    {
        this.playerID = playerID;
        this.name = name;
        this.team = team;
        this.type = t==1 ? Type.BATSMAN : Type.BOWLER;
        this.rating = rating;
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();

        for(int i=1; i<=rating; i++) freq[i]++;
        prefix[0] = freq[0];
        for (int i = 1; i < 8; ++i)
            prefix[i] = prefix[i - 1] + freq[i];
    }
    public String getName() { return name; }

    public int getRating() { return rating; }
    public Type getType() { return type; }

    public BattingScoreCard getBattingScoreCard() { return battingScoreCard; }
    public BowlingScoreCard getBowlingScoreCard() { return bowlingScoreCard; }
    public int runsScored()
    {
        int runs;
        if(rating<3) runs = (int) (Math.random()*8);
        else
        {
            int r = (int)(Math.random() * prefix[7]);
            int mid, l=0, h=7;
            while (l < h)
            {
                mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
                if ((r > prefix[mid])) l = mid + 1;
                else h = mid;
            }
            int index = (prefix[l] >= r) ? l : -1;
            runs = arr[index];
        }
        if(runs!=7)   runsScored(runs);
        //System.out.println("playerID : " + playerID + "Runs = " + runs);
        return runs;
    }
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
