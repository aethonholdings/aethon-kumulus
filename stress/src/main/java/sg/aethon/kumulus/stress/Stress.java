package sg.aethon.kumulus.stress;

public class Stress 
{
    public static void main(String[] args)
            throws Exception
    {
        Properties p = new Properties();
        Thread u = new Thread(new User(p));
        u.start();
        u.join();
    }
}
