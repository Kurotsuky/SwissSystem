package tournament;

public enum MatchResult 
{
    Win(3), Tie(1), Lose(0);

    private int value;

    private MatchResult(int value) 
    {
        this.value = value;
    }

    public int GetValue() { return value; }
}
