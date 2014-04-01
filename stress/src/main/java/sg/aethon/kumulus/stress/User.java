/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.stress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author theo
 */
public class User implements Runnable {
    
    final Properties p;
    final WebDriver driver = new HtmlUnitDriver();
    
    public User(Properties p)
    {
        this.p = p;
        run();
    }

    private void login() throws Exception
    {
        driver.get(p.site_url);
        driver.findElement(By.name("j_username")).sendKeys(p.auth_username);
        driver.findElement(By.name("j_password")).sendKeys(p.auth_password);
        driver.findElement(By.id("submit")).click();
        
        if (!driver.getCurrentUrl().equals(p.site_url+"/home"))
        {
            throw new UserCannotWorkException(UserCannotWorkReason.CANNOT_LOGIN);
        }
    }
    
    private void createContainer() throws Exception
    {
        JdbcTemplate conn = Commons.getKumulusConnection(p);
        String barcodeString = conn.queryForObject("select text from barcode where used=0 limit 1", String.class);
    }
    
    @Override
    public void run()
    {
        try
        {
            login();
            createContainer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
