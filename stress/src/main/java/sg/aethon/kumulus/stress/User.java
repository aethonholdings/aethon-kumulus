/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.stress;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author theo
 */
public class User implements Runnable {
    
    final Properties p;
    
    public User(Properties p)
    {
        this.p = p;
    }

    private void login(WebDriver driver)
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
    
    private String createContainer(WebDriver driver) throws Exception
    {
        JdbcTemplate conn = Commons.getKumulusConnection(p);
        String barcode = RandomStringUtils.random(17, true, true);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(conn).withTableName("barcode").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>(2); 
        parameters.put("version", 0); 
        parameters.put("printed", 0);
        parameters.put("text", barcode);
        parameters.put("used", 0);
        insert.execute(parameters);
        driver.get(p.site_url + "/capture/collect/1");
        Thread.sleep(100);
        while (true)
        {
            try
            {
                WebElement root = driver.findElement(By.className("dynatree-title"));
                root.sendKeys(" ");
                break;
            }
            catch (org.openqa.selenium.StaleElementReferenceException e)
            {
                Thread.sleep(10);
            }
        }
        driver.findElement(By.id("button-add")).click();
        sendKeysWhenReady(driver.findElement(By.id("barcode")), barcode + "\t");
        sendKeysWhenReady(driver.findElement(By.id("type")), "Box");
        sendKeysWhenReady(driver.findElement(By.id("name")), barcode);
        driver.findElement(By.id("button-save")).click();
        boolean used = conn.queryForObject("select used from barcode where text=?",
                                           new Object[] { barcode }, Boolean.class);
        if (!used)
        {
            throw new UserCannotWorkException(UserCannotWorkReason.CANNOT_CREATE_CONTAINER);
        }
        return barcode;
    }
    
    private void sendKeysWhenReady(WebElement elem, String keys) throws Exception
    {
        while (!elem.isEnabled()) { Thread.sleep(10); }
        elem.sendKeys(keys);
    }
    
    private void scanDo(String barcode) throws Exception
    {
        ScanDo scando = new ScanDo(p);
        scando.login();
        String[] session = scando.locate(barcode);
        scando.upload(session);
    }
    
    @Override
    public void run()
    {
        try
        {
            WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
            String barcode;
            try
            {
                login(driver);
                barcode = createContainer(driver);
            }
            finally { driver.quit(); }
            scanDo(barcode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
