package tournament;

public enum Side 
{
    NA("Ninguno", -1), B("Blue", 0), R("Red", 1);

    private String name;
    private int value;
    private int times;

    private Side(String name, int value) 
    {
        this.name = name;
        this.value = value;
        this.times = 0;
    }

    public void AddTime() 
    {
        times++;
    }
    
    public void ResetTime() 
    {
        times = 0;
    }

    public String GetName() { return name; }
    public int GetValue() { return value; }
    public int GetTimes() { return times; }
}
