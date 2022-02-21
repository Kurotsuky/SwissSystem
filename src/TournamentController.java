import java.util.Scanner;

public class TournamentController {
    public static void main(String[] args) throws Exception 
    {
        int capacity = AskInt("¿Cuántos jugadores jugarán? ");
        int rounds = AskInt("¿Cuántas rondas se jugarán? ");

        Tournament swiss = new Tournament(capacity, rounds);


        for(int i = 0; i < swiss.GetNumberOfRounds(); i++) 
        {
            System.out.println(swiss.GetRounds().size()+1);
            swiss.NewRound();
            Round newRound = swiss.GetRounds().get(i);

            newRound.ShowRound();
            //System.console().readLine();
            
            newRound.PlayMatchs();
            newRound.ShowResult();
            // System.console().readLine();
            
            swiss.SortByScore();
            swiss.ShowRating();
            // System.console().readLine();
        }
    }

    private static int AskInt(String question) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print(question);
        return sc.nextInt();
    }
}
