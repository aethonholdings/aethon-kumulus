package com.scannerapp.apps.application ;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktop.view.DeskTopFrame;
import com.scannerapp.apps.framework.plaf.IMTheme;
import com.scannerapp.apps.framework.utils.CommandLineParser;

public class IMApplicationMain
{

  String loginId = new String () ;
  private static DeskTopFrame frame ;
  public IMApplicationMain ()
  {
	  try
	  {
		  MetalLookAndFeel metalLookAndFeel = new MetalLookAndFeel();
		  MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		  UIManager.setLookAndFeel(metalLookAndFeel);
	  }
	  catch(Exception e)
	  {
		  
		  
	  }
	frame = DeskTopFrame.getInstance () ;
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible ( true ) ;
    frame.getController ().initialize () ;

  }

  public static void main ( String[] args )
  {

    //Splash.create(_logo.getImageIcon());
	
    try
    {
      InitLog4j.initializeLogger();     //For Log4j
      final Logger log = Logger.getLogger(IMApplicationMain.class);//For Log4j
      CommandLineParser parser = new CommandLineParser ( args ) ;
      IMTheme im = new IMTheme () ;
      MetalLookAndFeel.setCurrentTheme ( im ) ;
      UIManager.setLookAndFeel ( UIManager.getCrossPlatformLookAndFeelClassName () ) ;      
      new IMApplicationMain () ;
    }
    catch ( Exception ex )
    {
      System.out.println(" main method :: *** Exception on Application Startup: \n" + ex ) ;
      ex.printStackTrace () ;
    }
  }
}
