import java.util.Scanner;
import java.util.ArrayList;

public class Tournament 
{
    private int capacity;
    private ArrayList<Player> players;
    private ArrayList<Round> rounds;
    private int numberOfRounds;
    private int turn;

    // CONSTRUCTORES
    public Tournament() 
    {
        capacity = 0;
        players = new ArrayList<Player>();
        rounds = new ArrayList<Round>();
        numberOfRounds = 0;
        turn = 1;
    }
    
    public Tournament(int capacity) 
    {
        this.capacity = capacity;
        players = RegistryPlayers(capacity, "Juan");
        rounds = new ArrayList<Round>();
        numberOfRounds = 0;
        turn = 1;
    }
    
    public Tournament(int capacity, int numberOfRounds) 
    {
        this.capacity = capacity;
        players = RegistryPlayers(capacity, "Juan");
        this.rounds = new ArrayList<Round>();
        this.numberOfRounds = numberOfRounds;
        turn = 1;
    }


    // METODOS
    private ArrayList<Player> RegistryPlayers(int capacity) 
    {
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < capacity; i++) 
        {
            System.out.print("Nombre del Participante nº" + i + ": ");
            String namePlayer = sc.next();
            players.add(new Player(namePlayer));
        }
        return players;
    }
    
    private ArrayList<Player> RegistryPlayers(int capacity, String name) 
    {
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i = 0; i < capacity; i++) 
        {
            players.add(new Player(name));
        }
        return players;
    }

    public void NewRound() 
    {
        rounds.add(new Round(rounds.size()+1, players, turn));
        if((rounds.size() / turn) % players.size() == 0) 
        {
            turn++;
        }
    }

    public void ShowRating() 
    {
        for(int i = 0; i < players.size(); i++) 
        {
            Player player = players.get(i);
            System.out.printf("%dº Posición %s, Nº %d -- %d Pts. \n", (i+1), player.GetName(), player.number, player.GetScore());
        }
    }  

    public void SortByScore() 
    {
        players.sort((o1, o2) -> o2.GetScore() - o1.GetScore());
    }

    // GETS
    public int GetCapacity() 
    {
        return capacity;
    }
    
    public ArrayList<Player> GetPlayers() 
    {
        return players;
    }
    
    public ArrayList<Round> GetRounds() 
    {
        return rounds;
    }
    
    public int GetNumberOfRounds() 
    {
        return numberOfRounds;
    }
    
    public int GetTurn() 
    {
        return turn;
    }
}
