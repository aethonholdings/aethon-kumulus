package com.scannerapp.common ;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Pranav Shah
 * @version 1.0
 */


import java.text.NumberFormat;
import java.util.Locale;

public class NumberConverter
{
    
	/*static Locale pLocale=new Locale("pt","PT");
	static Locale esLocale=new Locale("es","ES");
	static Locale usLocale=new Locale("en","US");*/
	
	static Locale pLocale=new Locale("el","GR");
	static Locale esLocale=new Locale("ro","RO");
	static Locale usLocale=new Locale("en","US");
	
	// Locale change by manish
	
	//private static Logger log = Logger.getLogger(NumberConverter.class);//For Log4j
//    public static void main(String args[]) throws Exception
//    {
//     if(args!=null)
//     {
//        if (args[0].equals("PT"))
//         Locale.setDefault(pLocale);
//        if (args[0].equals("ES"))
//         Locale.setDefault(esLocale);
//     }
//
//     showDemo();
//
//    }

    public static void showDemo() throws Exception
    {
	Locale defaultl ;
	defaultl=Locale.getDefault();
	if (defaultl.equals(pLocale))
	  System.out.println(" Default Locale is Portugal ");
	else if (defaultl.equals(esLocale))
	  System.out.println(" Default Locale is Spanish ");
	else if (defaultl.equals(usLocale))
	  System.out.println(" Default Locale is US ");
	else
	System.out.println(" Default Locale is different than us,pt and es");

	int a=1234567;
	String stra;
	stra=getIntToString(a);

	System.out.println("Orgiginal value of a  is "+a);
	//System.out.println(" String value of a in default locale is "+getIntToString(a));
	System.out.println(" String value of a in default locale is "+stra);
	System.out.println(" Now doing Reverse Engineering ....");
	int a1=getStringToInt(stra);
	System.out.println("Orgiginal value of a  is "+a1);



	double d=12345.67;
	String strd;
	strd=getDoubleToString(d);

	System.out.println("value of d  is "+d);
	//System.out.println(" String value of d in default locale is "+getDoubleToString(d));
	System.out.println(" String value of d in default locale is "+strd);
	System.out.println(" Now doing Reverse Engineering ....");
	double d1=getStringToDouble(strd);
	System.out.println("value of d  is "+d1);

	System.out.println("a123!.4 is valid double in default locale ???"+isValidDouble("a123!.4"));



    }
    /*
        This method returns Locale based String representation of integer value
    */
    public static String getIntToString(int intval)
    {
	    NumberFormat nformat;
	     if(Locale.getDefault().equals(pLocale))
	     {
	     	nformat=NumberFormat.getInstance(pLocale);
	     	return nformat.format(intval);
	     }
	     else if(Locale.getDefault().equals(esLocale))
             {
             	nformat=NumberFormat.getInstance(esLocale);
             	return nformat.format(intval);
             }
            else
             {
           	nformat=NumberFormat.getInstance(usLocale);
           	return nformat.format(intval);
             }


    }

    /*
        This method returns Locale based String representation of float value
    */
    public static String getDoubleToString(double dobval)
    {
            NumberFormat nformat;
	     if(Locale.getDefault().equals(pLocale))
	     {
	     	nformat=NumberFormat.getInstance(pLocale);
	     	return nformat.format(dobval);
	     }
	     else if(Locale.getDefault().equals(esLocale))
             {
             	nformat=NumberFormat.getInstance(esLocale);
             	return nformat.format(dobval);
             }
            else
             {
           	nformat=NumberFormat.getInstance(usLocale);
           	return nformat.format(dobval);
             }
    }

    /*
        This method converts Locale based String representation of double value into double variable
    */
    public static double getStringToDouble(String dobval)
    {
      try
      {

        NumberFormat nformat ;
        if ( Locale.getDefault ().equals ( pLocale ) )
        {
          nformat = NumberFormat.getInstance ( pLocale ) ;
          return nformat.parse ( dobval ).doubleValue () ;
        }
        else if ( Locale.getDefault ().equals ( esLocale ) )
        {
          nformat = NumberFormat.getInstance ( esLocale ) ;
          return nformat.parse ( dobval ).doubleValue () ;
        }
        else
        {
          nformat = NumberFormat.getInstance ( usLocale ) ;
          return nformat.parse ( dobval ).doubleValue () ;
        }
      }
      catch ( Exception ex )
      {
        return 0 ;
      }
    }


    /*
        This method converts Locale based String representation of integer value into integer variable.

    */

    public static int getStringToInt(String intval)throws Exception
    {
            NumberFormat nformat;
	     if(Locale.getDefault().equals(pLocale))
	     {
	     	nformat=NumberFormat.getInstance(pLocale);
	     	return nformat.parse(intval).intValue();
	     }
	     else if(Locale.getDefault().equals(esLocale))
             {
             	nformat=NumberFormat.getInstance(esLocale);
             	return nformat.parse(intval).intValue();
             }
            else
             {
           	nformat=NumberFormat.getInstance(usLocale);
           	return nformat.parse(intval).intValue();
             }
    }

/* This method will return True if the argument is valid double number in default locale
*/
public static boolean isValidDouble(String dobval)
    {
    	 try
    	 {
            NumberFormat nformat;
            double dVal;
	     if(Locale.getDefault().equals(pLocale))
	     {
	     	nformat=NumberFormat.getInstance(pLocale);
	     	dVal= nformat.parse(dobval).doubleValue();
	     	System.out.println(" double value is ..."+dVal);
	     }
	     else if(Locale.getDefault().equals(esLocale))
             {
             	nformat=NumberFormat.getInstance(esLocale);
             	dVal= nformat.parse(dobval).doubleValue();
             	System.out.println(" double value is ..."+dVal);
             }
            else
             {
           	nformat=NumberFormat.getInstance(usLocale);
           	dVal= nformat.parse(dobval).doubleValue();
           	System.out.println(" double value is ..."+dVal);
             }
             return true;
         }
        catch(Exception e)
        {
        	return false;
        }

    }

}

