import java.util.Scanner;

public class Match 
{
    public enum MatchResult { Win, Tie, Lose }

    private Player[] players;
    private int[] result;

    // CONSTRUCTORES
    public Match() 
    {
        players = new Player[] { new Player(), new Player() };
        result = new int[2];
    }
    
    public Match(Player player1, Player player2) 
    {
        players = new Player[] { player1, player2 };
        result = new int[2];
    }

    // METODOS
    public void PlayMatch() 
    {
        SetResult(true);
        
        for(int i = 0; i < players.length; i++) 
        {
            MatchResult matchResult = result[i] > result[(i+1)-(i*2)] ?
            MatchResult.Win : result[0] == result[1] ?
            MatchResult.Tie :
            MatchResult.Lose;
            
            try{ players[i].UpdateScore(matchResult); }
            catch(Exception e) { }
        }
        try {
            players[0].SetNewOpponent(players[1]);
            players[1].SetNewOpponent(players[0]);
        } catch(Exception e) { }
    }

    private void SetResult(Boolean random) 
    {
        if(players[1] == null) 
        {
            result[0] = 1;
            return;
        }

        if(random) 
        {
            result[0] = (int)Math.floor(Math.random() * 2);
    
            if(result[0] == 1) 
            {
                result[1] = 0;
                return;
            }
            result[1] = 1;
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("El resultado entre " + players[0].number + " vs " + players[1].number + " fue: ");
        result[0] = sc.nextInt();
        System.out.print(" a ");
        result[1] = sc.nextInt();
    }

    public Boolean CheckMatch() 
    {
        return players[1] == null;
    }

    public Boolean CheckOpponents(Player player) 
    {
        for(int i = 0; i < players.length; i++) 
        {
            if(!player.CheckOpponent(players[i])) 
            {
                return true;
            }
        }
        return false;
    }

    // GETS
    public Player[] GetPlayers() 
    {
        return players;
    }
    
    public int[] GetResult() 
    {
        return result;
    }
}
