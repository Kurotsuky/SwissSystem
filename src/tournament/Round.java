package tournament;

import java.util.ArrayList;
import utility.Utility;

public class Round 
{
    private int number;
    private ArrayList<Match> matchs;
    private ArrayList<Player> players;

    private Player restPlayer;


    public Round() 
    {
        this(0, new ArrayList<Player>());
    }
    
    public Round(ArrayList<Player> players) 
    {
        this(0, players);
    }
    
    public Round(int number, ArrayList<Player> players) 
    {
        this.number = number;
        this.players = players;
        restPlayer = null;
        matchs = FindMatchs();
    }


    private ArrayList<Match> FindMatchs() 
    {
        ArrayList<Match> matchs = new ArrayList<Match>();
        ArrayList<Player> playersToPlay = (ArrayList<Player>)players.clone();

        if(playersToPlay.size() % 2 == 1) 
        {
            FindRestPlayer(playersToPlay);
        }

        while(playersToPlay.size() > 0) 
        {
            matchs.add(FindMatch(playersToPlay));

            if(matchs.get(matchs.size()-1) == null) 
            {
                RemoveMatchs(matchs, playersToPlay);
            }
        }
        return matchs;
    }

    private Match FindMatch(ArrayList<Player> playersToPlay) 
    {
        Player player1 = null;
        Player player2 = null;
        boolean ignoreSide = false;

        while(player1 == null || player2 == null) 
        {
            player1 = FindPlayer1(playersToPlay, ignoreSide);
            player2 = FindPlayer2(playersToPlay, player1, ignoreSide);

            if(player1 == null || player2 == null) 
            {
                if(ignoreSide) 
                {
                    return null;
                }
                ignoreSide = true;
            }
        }

        playersToPlay.remove(player1);
        playersToPlay.remove(player2);
        return new Match(player1, player2);
    }

    private Player FindPlayer1(ArrayList<Player> playersToPlay, boolean ignoreSide) 
    {
        ArrayList<Player> checkerList = (ArrayList<Player>)playersToPlay.clone();
        Player player;

        while(checkerList.size() > 0) 
        {
            player = checkerList.get(Utility.RandomNumber(0, checkerList.size()-1));

            if(player.GetLastSide() != Side.B || ignoreSide) 
            {
                return player;
            }
            checkerList.remove(player);
        }
        return null;
    }
    
    private Player FindPlayer2(ArrayList<Player> playersToPlay, Player opponent, boolean ignoreSide) 
    {
        Player player;
        int scoreDiff = 0;

        if(opponent == null) { return null; }

        while(scoreDiff < number*MatchResult.Win.GetValue()) 
        {
            ArrayList<Player> checkerList = (ArrayList<Player>)playersToPlay.clone();

            while(checkerList.size() > 0) 
            {
                player = checkerList.get(Utility.RandomNumber(0, checkerList.size()-1));
                
                
                if(!player.IsConfronted(opponent) 
                    && Math.abs(player.GetScore() - opponent.GetScore()) <= scoreDiff
                    && (player.GetLastSide() != Side.R || ignoreSide)
                    && player != opponent) 
                {
                    return player;
                }
                checkerList.remove(player);
            }
            scoreDiff += MatchResult.Win.GetValue();
        }
        return null;
    }

    private void FindRestPlayer(ArrayList<Player> playersToPlay) 
    {
        while(restPlayer == null) 
        {
            for(int i = playersToPlay.size()-1; i >= 0; i--) 
            {
                Player current = playersToPlay.get(i);

                if(!current.GetRest()) 
                {
                    restPlayer = current;
                    current.Rest();
                    playersToPlay.remove(current);
                }
            }
        }
    }

    private void RemoveMatchs(ArrayList<Match> matchs, ArrayList<Player> playersToPlay) 
    {
        while(matchs.size() > 0) 
        {
            Match match = matchs.get(0);

            if(match != null) 
            {
                playersToPlay.add(match.GetPlayers()[0]);
                playersToPlay.add(match.GetPlayers()[1]);
            }
            matchs.remove(match);
        }
    }

    public void PlayMatchs() 
    {
        for (Match match : matchs) 
        {
            match.Play();
        }
    }

    public void ShowRound() 
    {
        for (Match match : matchs) 
        {
            System.out.print(match.GetPlayers()[0].GetNumber());
            System.out.print(" vs ");
            System.out.print(match.GetPlayers()[1].GetNumber());
            
            System.out.println("");
        }
    }
    
    public void ShowResult() 
    {
        for (Match match : matchs) 
        {
            System.out.print(match.GetPlayers()[0].GetNumber() + "  ");
            System.out.print(match.GetResult()[0] + " vs ");
            System.out.print(match.GetResult()[1] + "  ");
            System.out.print(match.GetPlayers()[1].GetNumber());
            
            System.out.println("");
        }
    }


    public int GetNumber() { return number; }
    public ArrayList<Match> GetMatchs() { return matchs; }
    public ArrayList<Player> GetPlayers() { return players; }
}
