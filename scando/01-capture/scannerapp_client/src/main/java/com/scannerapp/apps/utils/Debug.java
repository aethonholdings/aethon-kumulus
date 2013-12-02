package com.scannerapp.apps.utils ;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class Debug
{
  private static boolean iDebug = true ;
//start by shraddha for Unit Test Issue#6168
  private static boolean isSimple = true ;
  private static boolean isInput = true ;
  private static boolean isOutput = true ;
  private static boolean isQuery = true ;
//finish by shraddha for Unit Test Issue#6168
  private static Debug instance = null ;
  private Debug ()
  {
    super () ;
  }

  public static Debug getInstance ()
  {
    if ( instance == null )
    {
      instance = new Debug () ;
    }
    return instance ;
  }

  public static boolean isDebug ()
  {
    return iDebug ;
  }

  public static void setDebug ( boolean newValue )
  {
    iDebug = newValue ;
  }
  
  public static void setSimple ( boolean newValue )
  {
	  isSimple = newValue ;
  }
  public static void setInput ( boolean newValue )
  {
    isInput = newValue ;
  }
  public static void setOutput ( boolean newValue )
  {
    isOutput = newValue ;
  }
  public static void setQuery ( boolean newValue )
  {
    isQuery = newValue ;
  }

  public static void debugLog ( String aMessage )
  {
    if ( isDebug () )
    {
      System.out.println ( aMessage ) ;
    }
  }
  
  //start by shraddha for Unit Test Issue#6168

  public static void simpleLog ( String aMessage )
  {
    if ( isSimple )
    {
      System.out.println ( aMessage ) ;
    }
  } 
  
  public static void inputLog ( String aMessage )
  {
    if ( isInput )
    {
      System.out.println ( aMessage ) ;
    }
  }

  public static void outputLog ( String aMessage )
  {
    if ( isOutput )
    {
      System.out.println ( aMessage ) ;
    }
  }

  public static void queryLog ( String aMessage )
  {
    if ( isQuery )
    {
      System.out.println ( aMessage ) ;
    }
  }
//finish by shraddha for Unit Test Issue#6168
  
  public static void errorLog ( String aMessage , Throwable t )
  {
    System.out.println ( "ERROR: " + aMessage ) ;
    if ( t != null )
    {
      t.printStackTrace () ;
    }
  }

  public static void errorLog ( String aMessage )
  {
    errorLog ( aMessage , null ) ;
  }
}
