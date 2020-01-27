package Cricket;
import org.json.JSONArray;
//import org.json.JSONObject;

import java.util.ArrayList;

public class Match
{
    private InningData i1;
    private InningData i2;
    private Team tossWinner;
    private Team other; //the team other than toss winner
    private Choice choice;
    private enum Choice { BAT, BOWL }

    public Match()
    {
        Team a = new Team(1, "India");
        Team b = new Team(2, "Australia");
        int t, rating=1;
        for(int i=0; i<11; i++)
        {
            t = i<3 ? 3 : (i<6 ? 1 : 2);
            rating = 1;
            if(t==1)    rating = (int) (Math.random() * 3) + 2;
            if(t==3)    rating=2;
            Player p = new Player(i,String.format("%02d", i), a, t, rating);
            a.addPlayer(p);
        }
        for(int i=0; i<11; i++)
        {
            t = i<3 ? 3 : (i<6 ? 1 : 2);
            rating = 1;
            if(t==1)    rating = (int) (Math.random() * 3) + 2;
            if(t==3)    rating=2;
            Player p = new Player(i+11,String.format("%02d", i+11), b, t, rating);
            b.addPlayer(p);
        }

        //TOSS
        double n = Math.random();
        double m = Math.random();
        Team bat;
        Team bowl;
        if(n<0.5)   //a won the Toss
        {
            tossWinner = a;
            other = b;
            if(m<0.5)
            {
                choice = Choice.BAT;
                bat = a;
                bowl = b;
            }
            else
            {
                choice = Choice.BOWL;
                bat = b;
                bowl = a;
            }
        }
        else    //B won the toss
        {
            tossWinner = b;
            other = a;
            if(m<0.5)
            {
                choice = Choice.BAT;
                bat = b;
                bowl = a;
            }
            else
            {
                choice = Choice.BOWL;
                bat = a;
                bowl = b;
            }
        }

        i1 = new InningData(bat, bowl);
        i2 = new InningData(bowl, bat);
    }

    public int playInning()
    {
        int runs;
        boolean flag = true;
        for(int i=0; i<300; i++)
        {
            if(i%6==1)  i1.getBowlingTeam().overStarted();
            runs = i1.runsScored();
            i1.ballPlayed();
            if(runs==7)
            {
                i1.wicketTaken();
                if(i1.allWicketsTaken()) break;
            }
            if(runs>0 && runs<7)  flag = false;
            if(i%6==0 && i>0)
            {
                if(flag) i1.getBattingTeam().maidenOver();
                i1.getBowlingTeam().overPlayed();
                flag = true;
            }
        }
        return i1.getRunsScored();
    }

    public Team playInning(int target)
    {
        Team winningTeam = null;
        boolean flag = true;
        int runs;
        for(int i=0; i<300 && i2.getRunsScored()<=target; i++)
        {
            if(i%6==1)  i2.getBowlingTeam().overStarted();
            runs = i2.runsScored();
            i2.ballPlayed();
            if(runs==7)
            {
                i2.wicketTaken();
                if(i2.allWicketsTaken()) break;
            }
            if(runs>0 && runs<7)  flag = false;
            if(i%6==0 && i>0)
            {
                if(flag) i1.getBattingTeam().maidenOver();
                i2.getBowlingTeam().overPlayed();
                flag = true;
            }
            if(i2.getRunsScored()>target) winningTeam = i2.getBattingTeam();
            else if(i2.getRunsScored()<target) winningTeam = i2.getBowlingTeam();
        }
        return winningTeam;
    }
    public String printStats(InningData i)
    {
        ArrayList<Integer> rs1 = i.stats();
        String result = rs1.get(0) + "/" + rs1.get(1) + "(";
        float noOfBalls = (float) rs1.get(2);
        float overs = Math.floorDiv((int) noOfBalls, 6);
        overs += (noOfBalls - overs*6)/10;
        result += (String.format("%,.1f", overs) + ")");
        return result;
    }
    public String printWin(Team winningTeam, int target)
    {
        try{
            if(winningTeam.getID() == tossWinner.getID())
            {
                if(choice==Choice.BAT) return (winningTeam.getName() + " won by " + (target-i2.getRunsScored()) + " runs!");
                else return (winningTeam.getName() + " won by " + (10-i2.getWicketsTaken()) + " wickets!");
            }
            else
            {
                if(choice==Choice.BAT)  return (winningTeam.getName() + " won by " + (10-i2.getWicketsTaken()) + " wickets!");
                else return (winningTeam.getName() + " won by " + (target-i2.getRunsScored()) + " runs!");
            }
        }
        catch (Exception e)
        {
            return "Match Tied";
        }

    }
    public String getBattingTeamName(boolean bool)  //true->firstInning, false->secondInning
    {
        if(bool)     //FirstInning
        {
            if(choice==Choice.BAT)  return tossWinner.getName();
            else    return other.getName();
        }
        //Second Inning
        if(choice==Choice.BAT)  return other.getName();
        else    return tossWinner.getName();
    }
    public void inningScoreCard(ArrayList<PlayersBattingInfo> a1, ArrayList<PlayersBowlingInfo> b1,
                                ArrayList<PlayersBattingInfo> a2, ArrayList<PlayersBowlingInfo> b2)
    {
        i1.getBattingTeam().addBatsmen(a1);
        i1.getBowlingTeam().addBowlers(b1);
        i2.getBattingTeam().addBatsmen(a2);
        i2.getBowlingTeam().addBowlers(b2);
    }
    public String putBattingTeam(int inning)
    {
        if(inning==1)   return i1.getBattingTeam().getName();
        return i2.getBattingTeam().getName();
    }
    public String putBowlingTeam(int inning)
    {
        if(inning==1)   return i1.getBowlingTeam().getName();
        return i2.getBowlingTeam().getName();
    }

    public static JSONArray go()
    {
        Match match = new Match();
        int target = match.playInning();
        Team winningTeam = match.playInning(target);
        JSONArray jsArray = new JSONArray();
        ArrayList<PlayersBattingInfo> firstInningBatsmen = new ArrayList<>();
        ArrayList<PlayersBowlingInfo> firstInningBowlers = new ArrayList<>();
        ArrayList<PlayersBattingInfo> secondInningBatsmen = new ArrayList<>();
        ArrayList<PlayersBowlingInfo> secondInningBowlers = new ArrayList<>();

        jsArray.put(match.tossWinner.getName() + " won the toss and chose to " + match.choice);// + "\n");
        jsArray.put(match.getBattingTeamName(true) + " : "+ match.printStats(match.i1));
        jsArray.put(match.getBattingTeamName(false) + " : " + match.printStats(match.i2));
        jsArray.put(match.printWin(winningTeam, target));

        match.inningScoreCard(firstInningBatsmen, firstInningBowlers, secondInningBatsmen, secondInningBowlers);
        jsArray.put("");
        jsArray.put("First Inning:");
        jsArray.put(match.putBattingTeam(1));
        for(int i = 0; i < firstInningBatsmen.size(); i++)
            jsArray.put(firstInningBatsmen.get(i));

        jsArray.put(match.putBowlingTeam(1));
        for(int i = 0; i < firstInningBowlers.size(); i++)
            jsArray.put(firstInningBowlers.get(i));

        jsArray.put("");
        jsArray.put("Second Inning:");
        jsArray.put(match.putBattingTeam(2));
        for(int i = 0; i < secondInningBatsmen.size(); i++)
            jsArray.put(secondInningBatsmen.get(i));

        jsArray.put(match.putBowlingTeam(2));
        for(int i = 0; i < secondInningBowlers.size(); i++)
            jsArray.put(secondInningBowlers.get(i));

        return jsArray;
    }

}
