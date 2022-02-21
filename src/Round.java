import java.util.ArrayList;

public class Round 
{
    private int number;
    private ArrayList<Player> players;
    private ArrayList<Match> matchs;
    private int turn;

    // CONSTRUCTORES
    public Round() 
    {
        number = 0;
        turn = 1;
        players = new ArrayList<Player>();
        matchs = FindCorrectMatchs();
    }
    
    public Round(int number) 
    {
        this.number = number;
        turn = 1;
        players = new ArrayList<Player>();
        matchs = FindCorrectMatchs();
    }
    
    public Round(ArrayList<Player> players) 
    {
        number = 0;
        turn = 1;
        this.players = players;
        matchs = FindCorrectMatchs();
    }
    
    public Round(int number, ArrayList<Player> players) 
    {
        this.number = number;
        turn = 1;
        this.players = players;
        matchs = FindCorrectMatchs();
    }
    
    public Round(int number, ArrayList<Player> players, int turn) 
    {
        this.number = number;
        this.turn = turn;
        this.players = players;
        matchs = FindCorrectMatchs();
    }

    // METODOS
    private ArrayList<Match> FindCorrectMatchs() 
    {
        ArrayList<Match> matchs = new ArrayList<Match>();
        ArrayList<Player> playersToPlay = (ArrayList<Player>)players.clone();

        do 
        {
            matchs = RemoveBadMatchs(matchs, playersToPlay);
            matchs = FindMatchs(matchs, playersToPlay);
        }
        while(CheckMatchs(matchs));

        // Refactorizar para poder remover partidos facilmente
        // Cambiar para que si es la ronda 30 o 31, los dos jugadores que se quedan sueltos, jueguen

        return matchs;
    }

    private ArrayList<Match> FindMatchs(ArrayList<Match> matchs, ArrayList<Player> playersToPlay) 
    {
        while(playersToPlay.size() > 0) 
        {
            matchs.add(FindMatch(playersToPlay));
        }
        matchs = OrderMatchs(matchs);
        return matchs;
    }

    private Match FindMatch(ArrayList<Player> playersToPlay) 
    {
        for(int i = 0; i < playersToPlay.size(); i++) 
        {
            Player player = playersToPlay.get(i);

            for(int j = i+1; j < playersToPlay.size(); j++) 
            {
                Player opponent = playersToPlay.get(j);

                if(CheckMatchPlayer(player, opponent)) 
                {
                    playersToPlay.remove(player);
                    playersToPlay.remove(opponent);
                    return new Match(player, opponent);
                }
            }
        } 

        Player lastPlayer = playersToPlay.get(0);
        playersToPlay.remove(lastPlayer);
        return new Match(lastPlayer, null);
    }

    private Boolean CheckMatchPlayer(Player player, Player opponent) 
    {
        return !player.GetOpponents().contains(opponent);
    }

    private Boolean CheckMatchs(ArrayList<Match> matchs) 
    {
        int count = 0;
        int countLimit = players.size() % 2 == 0 ? 0 : 1;
        
        if(number - ((players.size()-1) * (turn-1)) >= players.size()-2) 
        {
            countLimit += 2;
        }

        for(int i = 0; i < matchs.size(); i++) 
        {
            if(matchs.get(i).CheckMatch()) 
            {
                count++;
            }
        }
        return count > countLimit ? true : false;
    }

    private ArrayList<Match> RemoveBadMatchs(ArrayList<Match> matchs, ArrayList<Player> playersToPlay) 
    {
        for(int i = 0; i < matchs.size();) 
        {
            Match match = matchs.get(i);

            if(match.CheckMatch()) 
            {
                for(int j = 0; j < 2; j++) 
                {
                    Player matchPlayer = match.GetPlayers()[j];
                    if(matchPlayer != null) 
                    {
                        playersToPlay.add(matchPlayer);
                    }
                }
                matchs.remove(match);
                continue;
            }
            i++;
        }
        matchs = RemoveRelatedPlayers(matchs, playersToPlay);
        return matchs;
    }

    private ArrayList<Match> RemoveRelatedPlayers(ArrayList<Match> matchs, ArrayList<Player> playersToPlay) 
    {
        for(int i = 0; i < playersToPlay.size(); i++) 
        {
            Player removalPlayer = playersToPlay.get(i);

            for(int j = 0; j < matchs.size();) 
            {
                Match currentMatch = matchs.get(j);

                if(currentMatch.CheckOpponents(removalPlayer)) 
                {
                    for(int k = 0; k < 2; k++) 
                    {
                        Player matchPlayer = currentMatch.GetPlayers()[k];
                        if(matchPlayer != null) 
                        {
                            playersToPlay.add(matchPlayer);
                        }
                        if(k == 1) 
                        {
                            matchs.remove(currentMatch);
                        }
                    }
                    continue;
                }
                j++;
            }
        }
        return matchs;
    }

    private ArrayList<Match> OrderMatchs(ArrayList<Match> matchs) 
    {
        ArrayList<Match> remainingMatchs = (ArrayList<Match>)matchs.clone();
        ArrayList<Match> orderMatchs = new ArrayList<Match>();
        
        while(remainingMatchs.size() > 0) 
        {
            int randomNumber = (int)Math.floor(Math.random() * remainingMatchs.size());
            Match currentMatch = remainingMatchs.get(randomNumber);

            remainingMatchs.remove(currentMatch);
            orderMatchs.add(currentMatch);
        }
        return orderMatchs;
    }

    public void ShowRound() 
    {
        for(int i = 0; i < matchs.size(); i++) 
        {
            Match currentMatch = matchs.get(i);

            System.out.print(currentMatch.GetPlayers()[0].number + " vs ");
            try { System.out.print(currentMatch.GetPlayers()[1].number); }
            catch(Exception e) { } 
            System.out.println("");
        }
    }
    
    public void ShowResult() 
    {
        for(int i = 0; i < matchs.size(); i++) 
        {
            Match currentMatch = matchs.get(i);
            
            System.out.print(currentMatch.GetPlayers()[0].number + "  " + currentMatch.GetResult()[0] + " vs ");
            try { System.out.print(currentMatch.GetResult()[1] + "  " + currentMatch.GetPlayers()[1].number); }
            catch(Exception e) { }
            System.out.println("");
        }
    }

    public void PlayMatchs() 
    {
        for(int i = 0; i < matchs.size(); i++) 
        {
            matchs.get(i).PlayMatch();
        }
    }

    // GETS
    public int GetNumber() 
    {
        return number;
    }
    
    public ArrayList<Match> GetMatchs() 
    {
        return matchs;
    }
    
    public ArrayList<Player> GetPlayers() 
    {
        return players;
    }
}
