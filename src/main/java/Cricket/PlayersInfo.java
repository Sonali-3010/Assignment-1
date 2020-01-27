package Cricket;

public class PlayersInfo
{
    public String name;
    public Player.Type type;
    public int rating;

    public PlayersInfo(Player p)
    {
        name = p.getName();
        type = p.getType();
        rating = p.getRating();
    }
}
