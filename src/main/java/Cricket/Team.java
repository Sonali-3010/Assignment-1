package Cricket;

import java.util.ArrayList;

public class Team
{
    private int teamID;
    private String name;
    private Player captain;
    private ArrayList<Player> team;
    private int bowler = 0;
    private int striker = 0;
    private int other = 1; //The Batsman who isn't Striking
    private int next=2;
    private BattingScoreCard battingScoreCard;
    private BowlingScoreCard bowlingScoreCard;

    public Team(int teamID, String name)
    {
        this.teamID = teamID;
        this.name = name;
        team = new ArrayList<>();
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();
    }
    public Team(int teamID, String name, Player cap)
    {
        this.teamID = teamID;
        this.name = name;
        this.captain = cap;
        team = new ArrayList<>();
        team.add(cap);
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();
    }
    public int getID() { return teamID; }
    public String getName() { return name; }
    public void addPlayer(Player p)    { team.add(p); }
    public void setCaptain(Player cap) { this.captain = cap; }
    public void setAndAddCaptain(Player cap) { this.setCaptain(cap); this.addPlayer(cap); }
    public Player getBowler() { return team.get(bowler); }
    public Player getStriker() { return team.get(striker); }
    public Player getOther() { return team.get(other); }
    public Player getNextBowler()
    {
        bowler = (bowler+1)%11;
        return this.getBowler();
    }
    public void strikerOut()
    {
        striker = next;
        next++;
    }
    public void strikeChange()
    {
        int temp = striker;
        striker = other;
        other = temp;
    }
    public BattingScoreCard getBattingScoreCard() { return battingScoreCard; }
    public BowlingScoreCard getBowlingScoreCard() { return bowlingScoreCard; }
    public int runsScored()
    {
        int runs = team.get(striker).runsScored();
        if(runs==7) return runs;

        battingScoreCard.runsScored(runs);
        team.get(striker).ballPlayed();
        if(runs!=4 && runs!=6)
        {
            (team.get(other)).runsScored(runs);
            team.get(other).ballPlayed();;
            if(runs%2==1)
                strikeChange();
        }
        else
            boundaryScored();
        return runs;
    }

    //Batting
    public int getNoOfBalls()       { return battingScoreCard.getNoOfBalls();      }
    public int getNoOfRuns()        { return battingScoreCard.getNoOfRuns();       }
    public int getNoOfBoundaries()  { return battingScoreCard.getNoOfBoundaries(); }
    public void runsScored(int runs)
    {
        battingScoreCard.runsScored(runs);
        team.get(striker).runsScored(runs);
        team.get(striker).ballPlayed();
        if(runs!=4 && runs!=6)
        {
            (team.get(other)).runsScored(runs);
            team.get(other).ballPlayed();;
            if(runs%2==1)
                strikeChange();
        }
        else
            boundaryScored();
    }
    public void ballPlayed() { battingScoreCard.ballPlayed(); }
    public void boundaryScored() { battingScoreCard.boundaryScored(); team.get(striker).boundaryScored(); }
    //Bowling
    public int getNoOfOvers()       { return bowlingScoreCard.getNoOfOvers();    }
    public int getRunsGiven()       { return bowlingScoreCard.getRunsGiven();    }
    public int getWicketsTaken()    { return bowlingScoreCard.getWicketsTaken(); }
    public int getMaidenOvers()     { return bowlingScoreCard.getMaidenOvers();  }
    public void runsGiven(int runs) { bowlingScoreCard.runsGiven(runs); team.get(bowler).runsGiven(runs); }
    public void wicketTaken()       { bowlingScoreCard.wicketTaken(); team.get(bowler).wicketTaken(); }
    public void maidenOver()        { bowlingScoreCard.maidenOver(); team.get(bowler).maidenOver(); }
    public void overStarted()       { team.get(bowler).overPlayed(); bowlingScoreCard.overPlayed(); }
    public void overPlayed()        { bowler = (bowler+1)%11; }

    public String printPlayerBat()
    {
        String print = "";
        for(int i=0; i<11; i++)
            print += team.get(i).printBat();
        return print;
    }
    public void addPlayers(ArrayList<PlayersInfo> a)
    {
        for(int i=0; i<11; i++)
        {
            a.add(new PlayersInfo(team.get(i)));
        }
    }
    /*public String printPlayerBowl()
    {
        String print = "";
        for(int i=0; i<11; i++)
            print += team.get(i).printBowl();
        return print;
    }*/
}
