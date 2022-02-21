import java.util.ArrayList;

public class Player 
{
    private static int numberPlayers = 0;

    public int number;
    private String name;
    private int score;
    private ArrayList<Player> opponents;

    // CONSTRUCTORES
    public Player() 
    {
        number = ++numberPlayers;
        name = "";
        score = 0;
        opponents = new ArrayList<Player>();
    }

    public Player(String name) 
    {
        number = ++numberPlayers;
        this.name = name;
        score = 0;
        opponents = new ArrayList<Player>();
    }
    
    public Player(String name, int score) 
    {
        number = ++numberPlayers;
        this.name = name;
        this.score = score;
        opponents = new ArrayList<Player>();
    }

    // METODOS
    public void UpdateScore(Match.MatchResult matchResult) 
    {
        switch(matchResult) 
        {
            case Win:
                score += 3;
                break;
            case Tie:
                score += 1;
                break;
            case Lose:
                score += 0;
                break;
        }
    }

    public Boolean CheckOpponent(Player opponent) 
    {
        return opponents.contains(opponent);
    }

    public void SetNewOpponent(Player opponent) 
    {
        opponents.add(opponent);

        if(opponents.size() >= numberPlayers-1) 
        {
            ResetOpponents();
        }
    }

    public void ShowOpponents() 
    {
        for(int i = 0; i < opponents.size(); i++) 
        {
            System.out.println(opponents.get(i).number + ", ");
        }
    }

    // Resetea la lista de oponentes enfrentados
    private void ResetOpponents() 
    {
        opponents.clear();
    }

    // GETS
    public String GetName() 
    {
        return name;
    }
    
    public int GetScore() 
    {
        return score;
    }
    
    public ArrayList<Player> GetOpponents() 
    {
        return opponents;
    }
}
