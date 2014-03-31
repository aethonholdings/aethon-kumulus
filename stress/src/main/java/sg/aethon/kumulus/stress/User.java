/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.stress;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

/**
 *
 * @author theo
 */
public class User implements Runnable {
    
    final Properties p;
    final WebClient webClient = new WebClient();
    
    public User(Properties p)
    {
        this.p = p;
        run();
    }

    private void login() throws Exception
    {
        webClient.setThrowExceptionOnScriptError(false);
        
        // Get the first page
        final HtmlPage page1 = (HtmlPage) webClient.getPage(p.site_url);

        // Get the form that we are dealing with and within that form, 
        // find the submit button and the field that we want to change.
        final HtmlForm form = (HtmlForm) page1.getForms().get(0);
        
        final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByValue("Login");
        final HtmlTextInput username = (HtmlTextInput) form.getInputByName("j_username");
        final HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("j_password");

        // Change the value of the text fields
        username.setValueAttribute(p.auth_username);
        password.setValueAttribute(p.auth_password);

        // Now submit the form by clicking the button and get back the second page.
        button.focus();
        final Page page2 = button.click();
        if (!page2.getWebResponse().getUrl().toString().equals(p.site_url+"/home"))
        {
            throw new UserCannotWorkException(UserCannotWorkReason.CANNOT_LOGIN);
        }
    }
    
    @Override
    public void run()
    {
        try
        {
            login();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
