package tournament;

public class TournamentController 
{
    public static void main(String[] args) throws Exception 
    {
        int capacity = 32;
        int rounds = 31;

        Tournament swiss = new Tournament(capacity, rounds);

        for(int i = 0; i < swiss.GetNumberOfRounds(); i++) 
        {
            System.out.println("Ronda " + (swiss.GetRounds().size()+1));
            swiss.NewRound();
            Round newRound = swiss.GetRounds().get(i);
            
            newRound.ShowRound();
            System.in.read();
            System.out.println();
            
            swiss.PlayLastRound();
            newRound.ShowResult();
            System.in.read();
            System.out.println();
            
            swiss.ShowRating();
            System.in.read();
            System.out.println();
        }
    }
}
