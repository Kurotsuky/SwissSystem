package tournament;

import java.util.ArrayList;
import utility.Utility;

public class Tournament 
{
    private ArrayList<Player> players;
    private int capacity;
    private ArrayList<Round> rounds;
    private int numberOfRounds;


    public Tournament(int capacity, int numberOfRounds) 
    {
        players = RegistryPlayers(capacity, "Juan");
        this.capacity = capacity;
        rounds = new ArrayList<Round>();
        this.numberOfRounds = numberOfRounds;
    }


    private ArrayList<Player> RegistryPlayers(int capacity) 
    {
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i = 0; i < capacity; i++) 
        {
            String namePlayer = Utility.AskString("Nombre del Participante nº" + i + ": ");
            players.add(new Player(i+1, namePlayer));
        }
        return players;
    }
    
    private ArrayList<Player> RegistryPlayers(int capacity, String name) 
    {
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i = 0; i < capacity; i++) 
        {
            players.add(new Player(i+1, name));
        }
        return players;
    }

    public void NewRound() 
    {
        rounds.add(new Round(rounds.size()+1, players));
    }
    
    public void PlayLastRound() 
    {
        rounds.get(rounds.size()-1).PlayMatchs();
        SortByScore();
    }

    public void ShowRating() 
    {
        for(int i = 0; i < players.size(); i++) 
        {
            Player player = players.get(i);
            System.out.printf("%dº Posición %s, Nº %d -- %d Pts. \n", (i+1), player.GetName(), player.GetNumber(), player.GetScore());
        }
    }  

    private void SortByScore() 
    {
        players.sort((o1, o2) -> o2.GetScore() - o1.GetScore());
    }


    public int GetCapacity() { return capacity; }
    public ArrayList<Player> GetPlayers() { return players; }
    public ArrayList<Round> GetRounds() { return rounds; }
    public int GetNumberOfRounds() { return numberOfRounds; }
}
