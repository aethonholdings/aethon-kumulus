package sg.aethon.kumulus.stress;

public class Stress 
{
    public static void main(String[] args)
            throws Exception
    {
        Properties p = new Properties();
        for (int i=0; i < 10; i++)
        {
            new Thread(new User(p)).start();
        }
    }
}
