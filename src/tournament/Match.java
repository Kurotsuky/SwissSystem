package tournament;

import utility.Utility;

public class Match 
{
    private Player[] players;
    private int[] result;
    private Side[] sides;


    public Match() 
    {
        this(new Player(), new Player());
    }
    
    public Match(Player player1, Player player2) 
    {
        players = new Player[] { player1, player2 };
        result = new int[2];
        sides = new Side[] { Side.B, Side.R };
    }


    public void Play() 
    {
        RandomResult();

        for(int i = 0; i < players.length; i++) 
        {
            if(players[i] == null) { continue; }

            int otherIndex = (i+1)-(i*2);
            MatchResult matchResult = result[i] > result[otherIndex] ?
            MatchResult.Win : result[0] == result[1] ?
            MatchResult.Tie :
            MatchResult.Lose;

            players[i].UpdateScore(matchResult);
            players[i].AddConfronted(players[otherIndex]);
            players[i].SetSide(sides[i]);
        }
    }

    public void RandomResult() 
    {
        if(players[1] == null) 
        {
            result[0] = 1;
            return;
        }

        result[0] = Utility.RandomNumber(0, 1);
    
        if(result[0] == 0) 
        {
            result[1] = 1;
        }
    }
    
    public void GiveResult() 
    {
        if(players[1] == null) 
        {
            result[0] = 1;
            return;
        }

        System.out.println("El resultado del partido entre " + players[0].GetNumber() + " vs " + players[1].GetNumber());
        result[0] = Utility.AskInt(players[0] + ": ");
        result[1] = Utility.AskInt(players[1] + ": ");
    }


    public Player[] GetPlayers() { return players; }
    public int[] GetResult() { return result; }
}
