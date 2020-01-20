package Cricket;
import java.util.ArrayList;

public class Match
{
    public ArrayList<Player> teamA;
    public ArrayList<Player> teamB;

    public Match()
    {
        teamA = new ArrayList<Player>();
        teamB = new ArrayList<Player>();
        for(int i=0; i<22; i++)
        {
            if(i%2==0)
                teamA.add(new Player(String.valueOf(i), "teamA"));
            else
                teamB.add(new Player(String.valueOf(i), "teamB"));
        }
    }
    public int playInnings(Result result1)
    {
        int num;
        int bowler = 0;
        int striker = 0;
        int other = 1;
        int next=2;
        for(int i=0; i<300; i++)
        {
            num = (int) (Math.random()*8);
            result1.numOfBalls();
            if(num==7)
            {
                result1.wicketTaken();
                striker = next;
                next++;
                (teamB.get(bowler)).wicketTaken();
                if(result1.allWicketsTaken()) break;
            }
            else if(num==0) continue;
            else
            {
                result1.RunsScored(num);
                (teamA.get(striker)).runsScored(num);
                if(num!=4 && num!=6)
                {
                    (teamA.get(striker)).runsScored(num);
                    if(num%2==1)
                    {
                        int temp = striker;
                        striker = other;
                        other = temp;
                    }
                }
            }
            if(i%6==0 && i>0)
            {
                bowler = (bowler+1)%11;
                int temp = striker;
                striker = other;
                other = temp;
            }
        }
        return result1.getRunsScored()+1;
    }
    public void playInnings(Result result2, int target)
    {
        int num;
        int bowler = 0;
        int striker = 0;
        int other = 1;
        int next=2;
        for(int i=0; i<300 && result2.getRunsScored()<=target; i++)
        {
            num = (int) (Math.random()*8);
            result2.numOfBalls();
            if(num==7)
            {
                result2.wicketTaken();
                striker = next;
                next++;
                (teamA.get(bowler)).wicketTaken();
                if(result2.allWicketsTaken()) break;
            }
            else
            {
                result2.RunsScored(num);
                (teamB.get(striker)).runsScored(num);
                if(num!=4 && num!=6)
                {
                    (teamB.get(striker)).runsScored(num);
                    if(num%2==1)
                    {
                        int temp = striker;
                        striker = other;
                        other = temp;
                    }
                }
            }
            if(i%6==0 && i>0)
            {
                bowler = (bowler+1)%11;
                int temp = striker;
                striker = other;
                other = temp;
            }

            String winningTeam;
            if(result2.getRunsScored()>target)
                winningTeam = "TeamB";
            else if(result2.getRunsScored()==target)
                winningTeam = "Tied";
            else
                winningTeam = "TeamA";
            result2.setWinningTeam(winningTeam);
        }
    }
    public String printStats(Result result1)
    {
        ArrayList<String> rs1 = result1.stats();
        String result = rs1.get(0) + "/" + rs1.get(1) + "(";
        float noOfBalls = Float.parseFloat(rs1.get(2));
        float overs = Math.floorDiv((int) noOfBalls, 6);
        overs += (noOfBalls - overs*6)/10;
        result += (String.format("%,.1f", overs) + ")");
        return result;
    }

    public void teamAStats()
    {
        for( Player p: teamA )
            System.out.println(p);
    }
    public void teamBStats()
    {
        for( Player p: teamB )
            System.out.println(p);
    }

    public static String go()
    {
        Match match = new Match();
        Result result1 = new Result(0, 0, 0, "Na");
        Result result2 = new Result(0, 0, 0, "Na");
        String display = "Team A : ";

        int target = match.playInnings(result1);
        match.playInnings(result2, target);

        display = display + match.printStats(result1) + " <br> " + "Team B : ";
        display += (match.printStats(result2) + " <br>");
        display += result2.printWin(target);
        return display;
    }

}
