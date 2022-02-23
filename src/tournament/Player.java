package tournament;

import java.util.ArrayList;

public class Player 
{
    private int number;
    private String name;
    private int score;

    private ArrayList<Player> confronted;
    private Side lastSide;
    private boolean rest;


    public Player() 
    {
        this(0, "", 0);
    }
    
    public Player(int number) 
    {
        this(number, "", 0);
    }
    
    public Player(String name) 
    {
        this(0, name, 0);
    }
    
    public Player(int number, String name) 
    {
        this(number, name, 0);
    }
    
    public Player(int number, String name, int score) 
    {
        this.number = number;
        this.name = name;
        this.score = score;
        confronted = new ArrayList<Player>();
        lastSide = Side.NA;
        rest = false;
    }


    public void UpdateScore(MatchResult result) 
    {
        score += result.GetValue();
        rest = false;
    }

    public void AddConfronted(Player opponent) 
    {
        confronted.add(opponent);
    }

    public boolean IsConfronted(Player opponent) 
    {
        return confronted.contains(opponent);
    }

    public void Rest() 
    {
        rest = true;
        lastSide = Side.NA;
        UpdateScore(MatchResult.Tie);
    }

    public void SetSide(Side side) 
    {
        lastSide = side;
    }


    public int GetNumber() { return number; }
    public String GetName() { return name; }
    public int GetScore() { return score; }
    public ArrayList<Player> GetEncountered() { return confronted; }
    public Side GetLastSide() { return lastSide; }
    public boolean GetRest() { return rest; }
}
