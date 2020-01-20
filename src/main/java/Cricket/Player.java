package Cricket;

public class Player
{
    private final String name;
    private final String team;
    private int runsScored;
    private int wicketsTaken;

    public Player(String name, String team)
    {
        this.name = name;
        this.team = team;
        runsScored = 0;
        wicketsTaken = 0;
    }

    public int runsScored(int runs)
    {
        runsScored += runs;
        return runsScored;
    }

    public int wicketTaken()
    {
        wicketsTaken++;
        return wicketsTaken;
    }

    @Override
    public String toString() {
        return "Player " + name +
                ", member of " + team +
                " has scored " + runsScored +
                " runs and took " + wicketsTaken +
                " wickets.";
    }
}
