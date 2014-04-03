package sg.aethon.kumulus.stress;

public class Stress 
{
    public static void main(String[] args)
            throws Exception
    {
        Properties p = new Properties();
        Thread u1 = new Thread(new User(p));
        Thread u2 = new Thread(new User(p));
        u1.start();
        u2.start();
        u1.join();
        u2.join();
    }
}
