package com.scannerapp.apps.utils ;
// GenUtils#4 Starts
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;

import com.scannerapp.apps.application.ApplicationConstants;
import com.scannerapp.apps.application.Version;
import com.scannerapp.apps.component.StyledScrollPane;
import com.scannerapp.apps.component.StyledTable;
import com.scannerapp.apps.desktop.view.DeskTopFrame;
import com.scannerapp.apps.desktop.view.DeskTopPanel;
import com.scannerapp.apps.framework.view.BaseJInternalFrame;
import com.scannerapp.apps.framework.view.ClientSessionInfo;
import com.scannerapp.apps.framework.view.ErrorMessage;
import com.scannerapp.apps.framework.view.IMConstants;
import com.scannerapp.apps.login.view.LoginHelper;
import com.scannerapp.apps.login.view.LoginJPanel;
import com.scannerapp.common.ErrorConstants;
import com.scannerapp.common.IMModule;
import com.scannerapp.common.IMScreen;
import com.scannerapp.common.ProductInfo;
import com.scannerapp.common.SessionInfo;
import com.scannerapp.common.XMLBundle;

public class GeneralUtils
    extends java.lang.Object implements Serializable
{
  private static int current_index ;
  private static Logger log = Logger.getLogger(GeneralUtils.class);//For Log4j
  private static ProductInfo pInfo ;
  private static ArrayList locationInfo = null ;
  private static String errors ;
  
  private static Hashtable htable ;
 
  //GenUtils#90
  private  static final String FONT_DIR = "C://windows//fonts//";
  private static final String FONT_NAME = "COUR.ttf";
  private static final String ENCODING_TYPE = "CP1253";
  //GenUtils#90
  
   
  //Finish GenUtils#41
  public static void center ( Window aWindow )
  {
    if ( aWindow instanceof JDialog )
    {
      ( ( JDialog ) aWindow ).setLocationRelativeTo ( JOptionPane.getRootFrame () ) ;
    }
    else
    {
      Component parent = JOptionPane.getRootFrame () ;
      Dimension d ;
      Point p = new Point ( 0 , 0 ) ;
      if ( parent != null )
      {
        d = parent.getSize () ;
        p = parent.getLocationOnScreen () ;
      }
      else
      {
        d = Toolkit.getDefaultToolkit ().getScreenSize () ;
      }
      Dimension dui = aWindow.getSize () ;

      //center the aWindow to the parent form
      aWindow.setBounds ( p.x + ( d.width - dui.width ) / 2 ,
                          p.y + ( d.height - dui.height ) / 2 , dui.width ,
                          dui.height ) ;
    }
  }

  public static void setTableColumnsWidth ( StyledTable jtbl ,
                                            StyledScrollPane jsp ,
                                            String[] columnNames , int column )
  {
    int columnWidths[] = getTableColumnsSize ( jtbl , jsp , columnNames ,
                                               column ) ;
    TableColumnModel tcmItems = jtbl.getColumnModel () ;
    TableColumn tcbi = null ;
    for ( int i = 0 ; i < columnWidths.length ; i++ )
    {
      tcbi = tcmItems.getColumn ( i ) ;
      tcbi.setWidth ( columnWidths[ i ] ) ;
    }
    for ( int i = 0 ; i < columnWidths.length ; i++ )
    {
      jtbl.sizeColumnsToFit ( i ) ;
    }
    jtbl.setColumnModel ( tcmItems ) ;
    jtbl.setPreferredScrollableViewportSize ( jtbl.getPreferredSize () ) ;

    //jtbl.getTableHeader().setReorderingAllowed( false );
    jtbl.validate () ;
    tcmItems = null ;
    tcbi = null ;
  }

  private static int[] getTableColumnsSize ( StyledTable jtbl ,
                                             StyledScrollPane jsp ,
                                             String[] columnNames , int column )
  {
    int totalWidth = jsp.getWidth () - ( jsp.getVerticalScrollBar ().getWidth () ) -
        6 ;
    int arrayLength = 0 ;
    arrayLength = columnNames.length ;
    int tmpHeaderSize[] = new int[ arrayLength ] ;
    int tmpTotalWidth = 0 ;
    java.awt.Font font = jtbl.getTableHeader ().getFont () ;
    java.awt.FontMetrics fm = jtbl.getTableHeader ().getFontMetrics ( font ) ;
    for ( int i = 0 ; i < arrayLength ; i++ )
    {
      int tmpColWidth = 0 ;
      tmpColWidth = fm.stringWidth ( columnNames[ i ] ) + 20 ;
      tmpTotalWidth += tmpColWidth ;
      tmpHeaderSize[ i ] = tmpColWidth ;
    }
    if ( tmpTotalWidth < totalWidth )
    {
      int diff = totalWidth - tmpTotalWidth ;
      tmpHeaderSize[ column ] = diff + tmpHeaderSize[ column ] ;
    }
    columnNames = null ;
    totalWidth = 0 ;
    arrayLength = 0 ;
    font = null ;
    fm = null ;
    return tmpHeaderSize ;
  }

  public static void setCurrentIndex ( int currentIndex )
  {
    current_index = currentIndex ;
  }

  public static int getCurrentIndex ()
  {
    return current_index ;
  }
  public static void setProduct ( ProductInfo info )
  {
    pInfo = info ;
  }

  public static ProductInfo getProduct ()
  {
    return pInfo ;
  }

  public static ProductInfo getProduct ( int sellingCode , String luCode )
  {

    //////this comment has been done for removing call the lucodeOK

    ///// commented by kunal will explain afterwards

    //if(luCodeOk(sellingCode,luCode)==true) {
    return pInfo ;

    //}

    //else

    //		return null;
  }

  public static ProductInfo getProduct ( int sellingCode , String luCode ,
                                         ArrayList aProductsInfo )
  {
    ProductInfo productInfo = null ;
    boolean flag = false ;
    for ( int i = 0 , size = aProductsInfo.size () ; i < size ; i++ )
    {
      productInfo = ( ProductInfo ) aProductsInfo.get ( i ) ;

      if ( productInfo.getSellingId ().intValue () == sellingCode &&
           productInfo.getItemId ().equals ( luCode ) )
      {
        flag = true ;
        break ;
      }
      else
      {
        flag = false ;
      }
    }
    if ( flag )
    {
      return productInfo ;
    }
    else
    {
      return null ;
    }

    //return new ProductInfo();
  }
//<!-- Start GenUtils#5-->
  public static ProductInfo getexistingProduct ( int sellingCode , String luCode ,
          ArrayList aProductsInfo )
{
	 ProductInfo productInfo = null ;
	 boolean flag = false ;
	 productInfo = ( ProductInfo ) aProductsInfo.get ( 0 ) ;
	 for ( int i = 0 , size = aProductsInfo.size () ; i < size ; i++ )
	    {
	      productInfo = ( ProductInfo ) aProductsInfo.get ( i ) ;

	      if ( productInfo.getSellingId ().intValue () == sellingCode &&
	           productInfo.getItemId ().equals ( luCode ) )
	      {
	            
	       flag = true ;
	       break ;
	      }
	      else
	      {
	    	  flag = false ;
	      }
	    }
	    if ( flag )
	    {
	      return productInfo ;
	    }
	    else
	    {
	    	boolean productstatus=false;
	    	SessionInfo.getInstance ().setProductStatus(productstatus);
	    	productInfo = ( ProductInfo ) aProductsInfo.get ( 0 ) ;
	    	return productInfo ;
	    	
	    }

   
}
  
  //<!-- End GenUtils#5-->
  

  public static ProductInfo getProductLUCode ( String luCode ,
                                               ArrayList aProductsInfo )
  {
    ProductInfo productInfo = null ;
    boolean flag = false ;
    for ( int i = 0 , size = aProductsInfo.size () ; i < size ; i++ )
    {
      productInfo = ( ProductInfo ) aProductsInfo.get ( i ) ;

      if ( productInfo.getItemId ().equals ( luCode ) )
      {
        flag = true ;
        break ;
      }
      else
      {
        flag = false ;
      }
    }
    if ( flag )
    {
      return productInfo ;
    }
    else
    {
      return null ;
    }

    //return new ProductInfo();
  }

  public static void setLocation ( ArrayList location )
  {
    locationInfo = location ;

  }

  public static ArrayList getLocation ()
  {
    return locationInfo ;
  }
  
  //start GenUtils#42
  private static String CoPackerDeliveryNoteNumber = "" ;
  public static void setCoPackerDeliveryNoteNo(String docno)
  {
	  CoPackerDeliveryNoteNumber=docno;
  }
  public static String getCoPackerDeliveryNoteno()
  {
	  return CoPackerDeliveryNoteNumber;
  }
  //end GenUtils#42

  public static Calendar convertTOCalendar ( String strDate )
  {
    String SEPERATOR = "/" ;
    Calendar calendarObj = Calendar.getInstance () ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    int dateInt = 0 ;
    int monthInt = 0 ;
    int yearInt = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    dateInt = Integer.parseInt ( date ) ;
    monthInt = Integer.parseInt ( month ) ;
    monthInt = monthInt - 1 ;
    yearInt = Integer.parseInt ( year ) ;
    calendarObj.set ( yearInt , monthInt , dateInt ) ;
    
    //Added for GenUtils#76 
    calendarObj.set(Calendar.HOUR, 0);
    calendarObj.set(Calendar.MINUTE, 0);
    calendarObj.set(Calendar.SECOND, 0);
    calendarObj.set(Calendar.MILLISECOND, 0);

    //finished for GenUtils#76
    return calendarObj ;
  }

  public static String convertFrontEndDate ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    String returnDate = year + month + date ;

    return returnDate ;
  }

  //Added for GenUtils#64
  public static String convertToyyyymmdd ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    String returnDate = year +"-"+ month +"-"+ date ;

    return returnDate ;
  }
  //finished for GenUtils#64
  
  //Added for GenUtils#65
  public static int getMonth ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    return Integer.parseInt(month) ;
  }
  public static int getYear ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    return Integer.parseInt(year);
  }  
  //finished for GenUtils#65
  
  //Added for GenUtils#66
  public static int getDay ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    return Integer.parseInt(date);
  }  
  
  public static String getAddOneDay ( String strDate )
  {
    String date="";
     
	  String DATE_FORMAT = "yyyy-MM-dd";
	  java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat(DATE_FORMAT);
	  Calendar c1 = Calendar.getInstance(); 
	  
	  c1.set(GeneralUtils.getYear(strDate), GeneralUtils.getMonth(strDate)-1 , GeneralUtils.getDay(strDate));
	  c1.add(Calendar.DATE,1);
	  //System.out.println("Date + 1 days is : " + sdf.format(c1.getTime()));  
    
    return convertToddmmyyyy(sdf.format(c1.getTime()));
  }
  public static String convertToddmmyyyy ( String strDate )
  {
	//This method convert yyyy-mm-dd to dd  
    String SEPERATOR = "-" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    year = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    date = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    String returnDate = date +"/"+ month +"/"+ year ;

    return returnDate ;
  }
  //finished for GenUtils#66

  public static String convertToddmmDash ( String strDate )
  {
	//This method convert yyyy-mm-dd to dd  
    String SEPERATOR = "-" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    year = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    date = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    String returnDate = date +SEPERATOR+ month +SEPERATOR+ year ;

    return returnDate ;
  }

  //Start GenUtils#32
  public static String convertBackEndmmddyyy ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
//        int monSepIndex = 0;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 6 , 8 ) ;
    month = inputStrDate.substring ( 4 , 6 ) ;
    year = inputStrDate.substring ( 0 , 4 ) ;
    String returnDate = month + SEPERATOR + date + SEPERATOR + year ;

    return returnDate ;
  }
  //End GenUtils#32

  public static String convertBackEndDate ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
//        int monSepIndex = 0;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 6 , 8 ) ;
    month = inputStrDate.substring ( 4 , 6 ) ;
    year = inputStrDate.substring ( 0 , 4 ) ;
    String returnDate = date + SEPERATOR + month + SEPERATOR + year ;

    return returnDate ;
  }
  //Added for GenUtils#74
  public static String convertDateInDotformat ( String strDate )
  {
    String SEPERATOR = "." ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
//        int monSepIndex = 0;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 6 , 8 ) ;
    month = inputStrDate.substring ( 4 , 6 ) ;
    year = inputStrDate.substring ( 0 , 4 ) ;
    String returnDate = date + SEPERATOR + month + SEPERATOR + year ;

    return returnDate ;
  }
  
  //finished for GenUtils#74
  public static boolean sellingCodeOK ( String sellingCode )
  {
    if ( sellingCode.trim ().equals ( "" ) )
    {
      return true ;
    }
    TreeMap productList = SessionInfo.getInstance ().getProductList () ;
    //added by manish for qa issue#5498
    if(productList==null || productList.size()==0)
    {
    	return true ;
    }
    //Finish by manish for qa issue#5498
    ArrayList requiredList = ( ArrayList ) productList.get ( Integer.valueOf (
        sellingCode.trim () ) ) ;
    if ( requiredList != null )
    {
      return true ;
    }
    else
    {
      return false ;
    }
  }

  //Added for GenUtils#31
  public static boolean LuCodeDescOK ( String luCodeDesc , ArrayList products )
  {
    /*if ( luCodeDesc.trim ().equals ( "" ) )
    {
      return true ;
    }*/
    for ( int i = 0 , size = products.size () ; i < size ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) products.get ( i ) ;
      if ( ( pInfo.getProductDesc().trim().startsWith(luCodeDesc) ) )
      {
        return true ;
      }
    }
    return false ;
  }
  public static boolean BrandOK ( String brand , ArrayList products )
  {
    /*if ( brand.trim ().equals ( "" ) )
    {
      return true ;
    }*/
    for ( int i = 0 , size = products.size () ; i < size ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) products.get ( i ) ;
      if ( ( pInfo.getFamilyDesc().trim().equals(brand) ) )
      {
        return true ;
      }
    }
    return false ;
  }
  //finished for GenUtils#31
  
  public static boolean sellingCodeOK ( String sellingCode , ArrayList products )
  {
    if ( sellingCode.trim ().equals ( "" ) )
    {
      return true ;
    }
    for ( int i = 0 , size = products.size () ; i < size ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) products.get ( i ) ;
      if ( ( pInfo.getSellingId ().intValue () ==
             Integer.parseInt ( sellingCode ) ) )
      {
        return true ;
      }
    }
    return false ;
  }

  public static boolean sellingCodeCheck ( String sellingCode )
  {
    if ( sellingCode.trim ().equals ( "" ) )
    {
      return true ;
    }
    TreeMap productList = SessionInfo.getInstance ().getProductList () ;
    ArrayList requiredList = ( ArrayList ) productList.get ( Integer.valueOf (
        sellingCode.trim () ) ) ;
    if ( requiredList != null )
    {
      return true ;
    }
    else
    {
      return false ;
    }
  }

  public static boolean sellingCodeOK ( String sellingCode , Vector products ,
                                        String location )
  {
    if ( sellingCode.trim ().equals ( "" ) )
    {
      return true ;
    }
    for ( int i = 0 , size = products.size () ; i < size ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) products.get ( i ) ;
      if ( ( pInfo.getSellingId ().intValue () ==
             Integer.parseInt ( sellingCode ) ) &&
           pInfo.getReasonCode ().toUpperCase ().equals ( location.toUpperCase () ) )
      {
        return true ;
      }
    }
    return false ;
  }

  public static boolean luCodeOk ( int sellingCode , String luCode )
  {
    boolean luCodeExist = false ;
    TreeMap productList = SessionInfo.getInstance ().getProductList () ;

    //ArrayList productList = SessionInfo.getInstance().getAProductList();
    ArrayList requiredList = ( ArrayList ) productList.get ( new Integer (
        sellingCode ) ) ;
    int size = requiredList.size () ;
    for ( int i = 0 ; i < size ; i++ )
    {
      String itemid = ( ( ProductInfo ) requiredList.get ( i ) ).getItemId () ;

      if ( luCode.equals ( itemid ) )
      {
        luCodeExist = true ;
      }
    }

    return luCodeExist ;
  }

  public static boolean luCodeOk ( int sellingCode , String luCode ,
                                   ArrayList requiredList )
  {
    boolean luCodeExist = false ;
    int size = requiredList.size () ;
    for ( int i = 0 ; i < size ; i++ )
    {
      String itemid = ( ( ProductInfo ) requiredList.get ( i ) ).getItemId () ;

      if ( luCode.equals ( itemid ) )
      {
        luCodeExist = true ;
      }
    }

    return luCodeExist ;
  }

  public static void setError ( String error )
  {
    errors = error ;
  }

  public static String getError ()
  {
    return errors ;
  }

  public static void refresh ()
  {
    try
    {
      getProducts () ;
      getLocations () ;
      //start GenUtils#37
      getRights ();
      //end GenUtils#37    
      ErrorMessage.displayMessage ( 'I' ,
                                    ErrorConstants.REFREESHED_SUCCESSFULLY ) ;
    }
    catch ( Exception e )
    {
      ErrorMessage.displayMessage ( 'E' , ErrorConstants.COULD_NOT_REFREESH ) ;
    }
  }
  
  //start GenUtils#37
  public static void getRights()
  {
	  ArrayList arlLogin = new ArrayList();
	  Hashtable htScreen =new Hashtable();
	  LoginHelper loginHelper=new LoginHelper();
	  SessionInfo s=SessionInfo.getInstance();
	  arlLogin.add(s.getUserId());
      arlLogin.add(s.getUserPwd());
      arlLogin.add(s.getCountry());
      arlLogin.add(s.getCompany());
      arlLogin.add(s.getLocation());  
	  try {
		  
		 // htScreen = loginHelper.getScreenAccess(arlLogin);
		  if (htScreen != null)
		  {
			  
			  setScreenRights(htScreen);
			  ClientSessionInfo.getInstance().setScreenRights(getScreenUOAHashtable(htScreen));
			  changeDeskTopPanel();
			  DeskTopFrame.getInstance().repaint();
	          DeskTopFrame.getInstance().validate();
	          DeskTopFrame.getInstance().createDeskTop();
	          DeskTopFrame.getInstance().getDesktopPanel().repaint();
	          DeskTopFrame.getInstance().getDesktopPanel().validate();
	          DeskTopFrame.getInstance().setTitle(GeneralUtils.getDesktopBundle().get("jlblTitle").toString()
						+ "  "
						+ Version.getInstance().toString()
						+ "   ( "
						+ GeneralUtils.getDesktopBundle().get("jlblUserId").toString()+" "
						+ s.getUserId()
						+ " )   ( "
						+ GeneralUtils.getDesktopBundle().get("jlblLocation").toString()
						+ s.getCountry()+"-"
						+ s.getCompany()+"-"
						+ s.getLocation()+":"
						+ s.getLocationDesc()+" )"
						+ " ( "+GeneralUtils.getDesktopBundle().get("jlblDBInstance").toString()
						+" "+ getDBInstance() +" )"//Added for GenUtils#82
						);
		  }
	  }
	  catch(Exception e)
	  {
		  log.debug("Exception occurs while calling getScreenAccess from GeneralUtils" + e.getMessage());
	  }
	  finally
	  {
		  loginHelper=null;
	  }
  }	
  //end GenUtils#37

  public static void getProducts ()
  {
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
        
    //GenUtils#4 STARTS
    LoginJPanel Lpanel =new LoginJPanel(); 
    //GenUtils#4 ENDS
    try
    {
     
      //added by manish for gap18
      //if condition added by manish for GenUtils#46
      if(!sessionInfo.isInventoryTransformationGoingon())
      {
    	  //start GenUtils#63
    	  //sessionInfo.setPackProducts((Hashtable)transferOutHelper.getPackProducts());
    	  //sessionInfo.setPackProducts((Hashtable)transferOutHelper.getPackProducts(transferOutHelper.setPackProducts()));
    	  //end GenUtils#63
      }
      //if condition ended by manish for GenUtils#46
      //finish by manish for gap18 
      
      //Added for GenUtils#31
      //ArrayList arlBrand=transferOutHelper.getBrandData();
      //sessionInfo.setBrandLists(arlBrand);
      //finished for GenUtils#31
      
      //Added for GenUtils#58   
		String sCountry = String.valueOf(sessionInfo.getCountry());
		String sCompany = String.valueOf(sessionInfo.getCompany());
		String sLocation = 	sessionInfo.getLocation();
		
     }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
  }

  public static void getLastInvDate ()
  {
    
  }

  public static void getLocations ()
  {
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
   
    if(sessionInfo.getLocCoreDestributor().equals("1"))
    {

    	try
        {
    		
    	}
        catch(Exception e)
        {
        	log.debug("Exception occurs while calling getMappedLuCode from GeneralUtils"+e.getLocalizedMessage());
        }
        finally
        {
        	
        }
    }
    //end GenUtils#37
    
  }
  
  //Added for GenUtils#37
  public static String getDBInstance ()
  {
	  String DBInstance = "";
	  try
      {
		    
      }
	  catch(Exception e)
      {
      	log.debug("Exception occurs while calling getMappedLuCode from GeneralUtils"+e.getLocalizedMessage());
      }
      
    return DBInstance;
    //end GenUtils#37
    
  }
  //finished for GenUtils#37
  
  //Added for GenUtils#81
  /*public static ArrayList getOrderOfDocument (HeaderInfo hInfo,ArrayList arlProduct)
  {
	ArrayList arlOrderItem= new ArrayList();  
	
    ArrayList arlfinalOrder= new ArrayList();
    
    
    for(int row= 0;row<arlProduct.size();row++){
    	ProductInfo pInfotemp=new ProductInfo();
    	pInfotemp.setItemId("xxx");
    	arlfinalOrder.add(pInfotemp);
    }
    
    TransferOutHelper transferOutHelper=new TransferOutHelper();
    	try
        {
    		hInfo.setDocumentNo(convertToBackEndDocNo(hInfo.getDocumentNo()));
    		arlOrderItem =transferOutHelper.getOrderOfDocument(hInfo);
    		hInfo.setDocumentNo(convertToFrontEndDocNo(hInfo.getDocumentNo()));
    	}
        catch(Exception e)
        {
        	log.debug("Exception occurs while calling getMappedLuCode from GeneralUtils"+e.getMessage());
        }
        finally
        {
        	transferOutHelper=null;
        }
   
    if(arlOrderItem.size()>0){     
    	
	    for(int row= 0;row<arlProduct.size();row++){
	  //  	ProductInfo pInfo = new ProductInfo();
	    //	pInfo =(ProductInfo)arlProduct.get(row);	
	    	
	    	if(arlfinalOrder.indexOf(((ProductInfo)arlProduct.get(row)).getSellingId()+":"+((ProductInfo)arlProduct.get(row)).getItemId())>=0){
	    		arlfinalOrder.set(arlfinalOrder.indexOf(((ProductInfo)arlProduct.get(row)).getSellingId()+":"+((ProductInfo)arlProduct.get(row)).getItemId())+1, (ProductInfo)arlProduct.get(row));
	    	}else{
	    	     arlfinalOrder.set(arlOrderItem.indexOf(((ProductInfo)arlProduct.get(row)).getSellingId()+":"+((ProductInfo)arlProduct.get(row)).getItemId()), (ProductInfo)arlProduct.get(row));
	    	}
	    }
	    
    }else
    {
    	arlfinalOrder=arlProduct;
    }
    
        
    return arlfinalOrder;
        
  }*/
  //finished for GenUtils#81

  public static void locationChange ()
  {
    ArrayList arlLogin = new ArrayList () ;
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
    arlLogin.add ( sessionInfo ) ;
    Hashtable htScreen = new Hashtable () ;
    try
    {
      LoginHelper loginHelper = new LoginHelper () ;
     // htScreen = loginHelper.getScreenAccess ( arlLogin ) ;
      getLastInvDate () ; //To get Inv Closing Date
      if ( htScreen == null )
      {
        ErrorMessage.displayMessage ( 'I' ,
                                      ErrorConstants.INVALID_USERID_PASSWORD ) ;

        //          view().resetForLogin();
      }
      else
      {
        BaseJInternalFrame internalFrame[] = DeskTopFrame.getInstance ().
            getDesktopPanel ().getInternalFrames () ;
        for ( int i = 0 ; i < internalFrame.length ; i++ )
        {
          internalFrame[ i ].dispose () ;
        }
        log.debug ( "htScreen :: " + htScreen ) ;
        ClientSessionInfo.getInstance ().setScreenRights (
            getScreenUOAHashtable ( htScreen ) ) ;
/////////////////
        setScreenRights ( htScreen ) ;
////////////////////
        ClientSessionInfo.getInstance ().setJIF ( new Hashtable () ) ;
        DeskTopFrame.getInstance ().repaint () ;
        DeskTopFrame.getInstance ().validate () ;
        DeskTopFrame.getInstance ().getDesktopPanel ().doLayout () ;
        DeskTopFrame.getInstance ().getDesktopPanel ().cleanup () ;
        DeskTopFrame.getInstance ().setDesktopPanel ( DeskTopPanel.getInstance () ) ;
        DeskTopFrame.getInstance ().createDeskTop () ;
        DeskTopFrame.getInstance ().getDesktopPanel ().doLayout () ;
        
        DeskTopFrame.getInstance ().getDesktopPanel ().getStatusPanel ().setLoginText ( "Language : " 
        		//start GenUtils#9
        		//+SessionInfo.getInstance().getLanguage()
        		+ GeneralUtils.getDesktopBundle().get ( "language" ).toString ());        		
        		//End GenUtils#9
                /*Commented for GenUtils#36
        		+"    Location : " 
        		+sessionInfo.getLocation () + "-"
        		+sessionInfo.getLocationDesc () ) ;*/ 
        //added for GenUtils#36
        DeskTopFrame.getInstance().setTitle(GeneralUtils.getDesktopBundle().get("jlblTitle").toString()
				+ "  "
				+ Version.getInstance().toString()
				+ "   ( "
				+ GeneralUtils.getDesktopBundle().get("jlblUserId").toString()+" "
				+ sessionInfo.getUserId()
				+ " )   ( "
				+ GeneralUtils.getDesktopBundle().get("jlblLocation").toString()
				+ sessionInfo.getCountry()+"-"
				+ sessionInfo.getCompany()+"-"
				+ sessionInfo.getLocation()+":"
				+ sessionInfo.getLocationDesc()+" )"
				+ " ( "+GeneralUtils.getDesktopBundle().get("jlblDBInstance").toString()
				+" "+ getDBInstance() +" )"//Added for GenUtils#82
				);
         //finished for  GenUtils#36
        DeskTopFrame.getInstance ().repaint () ;
        DeskTopFrame.getInstance ().validate () ;
      }
    }
    catch ( Exception e )
    {
      ErrorMessage.displayMessage ( 'E' , ErrorConstants.SELECT_ERROR_CODE ) ;
    }
  }

  public static Hashtable getScreenUOAHashtable ( Hashtable rights )
  {
    Hashtable screenRights = new Hashtable () ;
    if ( rights.get ( IMScreen.ADJUST_ACKNOWLEDGE ) == null )
    {
      screenRights.put ( IMScreen.ADJUST_ACKNOWLEDGE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ADJUST_ACKNOWLEDGE ,
                         rights.get ( IMScreen.ADJUST_ACKNOWLEDGE ) ) ;
    }
    
    if ( rights.get ( IMScreen.PURCHASE_RECEIPT ) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_RECEIPT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_RECEIPT ,rights.get ( IMScreen.PURCHASE_RECEIPT ) ) ;
      //COMMENTED BY MANISH FOR NOW ALLOWING PURCHASE RECEIPT SCREEN
      //screenRights.put ( IMScreen.PURCHASE_RECEIPT , "N" ) ;	
    }
    
    if ( rights.get ( IMScreen.PURCHASE_RETURN ) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_RETURN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_RETURN ,rights.get ( IMScreen.PURCHASE_RETURN ) ) ;
     //COMMENTED BY MANISH FOR NOW ALLOWING PURCHASE RECEIPT SCREEN
     //screenRights.put ( IMScreen.PURCHASE_RETURN  , "N" ) ;
    }
    
    if ( rights.get ( IMScreen.DOCUMENT_SEARCH ) == null )
    {
      screenRights.put ( IMScreen.DOCUMENT_SEARCH , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.DOCUMENT_SEARCH ,
                         rights.get ( IMScreen.DOCUMENT_SEARCH ) ) ;
    }
    if ( rights.get ( IMScreen.INVENTORY_TRANSFORMATION ) == null )
    {
      screenRights.put ( IMScreen.INVENTORY_TRANSFORMATION , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.INVENTORY_TRANSFORMATION ,
                         rights.get ( IMScreen.INVENTORY_TRANSFORMATION ) ) ;
    }
    if ( rights.get ( IMScreen.MISCELLANEOUS ) == null )
    {
      screenRights.put ( IMScreen.MISCELLANEOUS , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MISCELLANEOUS ,
                         rights.get ( IMScreen.MISCELLANEOUS ) ) ;
    }
    if ( rights.get ( IMScreen.PHYSICAL_INVENTORY ) == null )
    {
      screenRights.put ( IMScreen.PHYSICAL_INVENTORY , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PHYSICAL_INVENTORY ,
                         rights.get ( IMScreen.PHYSICAL_INVENTORY ) ) ;
    }
    if ( rights.get ( IMScreen.PICKING_SEQUENCE ) == null )
    {
      screenRights.put ( IMScreen.PICKING_SEQUENCE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PICKING_SEQUENCE ,
                         rights.get ( IMScreen.PICKING_SEQUENCE ) ) ;
    }
    if ( rights.get ( IMScreen.PRODUCTION_RECEIPT ) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT ,rights.get ( IMScreen.PRODUCTION_RECEIPT ) ) ;
    }
    if ( rights.get ( IMScreen.PRODUCTION_RETURN ) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_RETURN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RETURN ,
                         rights.get ( IMScreen.PRODUCTION_RETURN ) ) ;
    }
    if ( rights.get ( IMScreen.REV_ROUTE_SHIPMENT ) == null )
    {
      screenRights.put ( IMScreen.REV_ROUTE_SHIPMENT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.REV_ROUTE_SHIPMENT ,
                         rights.get ( IMScreen.REV_ROUTE_SHIPMENT ) ) ;
    }
    if ( rights.get ( IMScreen.ROUTE_ALLOCATION ) == null )
    {
      screenRights.put ( IMScreen.ROUTE_ALLOCATION , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ROUTE_ALLOCATION ,
                         rights.get ( IMScreen.ROUTE_ALLOCATION ) ) ;
    }
    if ( rights.get ( IMScreen.ROUTE_SHIPMENT ) == null )
    {
      screenRights.put ( IMScreen.ROUTE_SHIPMENT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ROUTE_SHIPMENT ,
                         rights.get ( IMScreen.ROUTE_SHIPMENT ) ) ;
    }
    if ( rights.get ( IMScreen.STOCK_INQUIRY ) == null )
    {
      screenRights.put ( IMScreen.STOCK_INQUIRY , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.STOCK_INQUIRY ,
                         rights.get ( IMScreen.STOCK_INQUIRY ) ) ;
    }
    
    //Start : GenUtils#13	
    if ( rights.get ( IMScreen.PRODUCTION_COST ) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_COST , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_COST ,
                         rights.get ( IMScreen.PRODUCTION_COST ) ) ;
    }
    //End : GenUtils#13	
      
    if ( rights.get ( IMScreen.TCOMMS_CHECKING ) == null )
    {
      screenRights.put ( IMScreen.TCOMMS_CHECKING , "N" ) ;
    }
    else
    {   //uncommented for GenUtils#55
        screenRights.put ( IMScreen.TCOMMS_CHECKING ,rights.get ( IMScreen.TCOMMS_CHECKING ) ) ;
    	//COMMENTED BY MANISH FOR NOW ALLOWING TCOMM SCREEN
    	//screenRights.put ( IMScreen.TCOMMS_CHECKING  , "N" ) ;
    }
    if ( rights.get ( IMScreen.TRANSFER_IN ) == null )
    {
      screenRights.put ( IMScreen.TRANSFER_IN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.TRANSFER_IN ,
                         rights.get ( IMScreen.TRANSFER_IN ) ) ;
    }
    if ( rights.get ( IMScreen.TRANSFER_OUT ) == null )
    {
      screenRights.put ( IMScreen.TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.TRANSFER_OUT ,
                         rights.get ( IMScreen.TRANSFER_OUT ) ) ;
    }
//Start GenUtils#1
    if ( rights.get ( IMScreen.FAST_ENTRY_ON_LU ) == null )
    {
      screenRights.put ( IMScreen.FAST_ENTRY_ON_LU , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.FAST_ENTRY_ON_LU ,
                         rights.get ( IMScreen.FAST_ENTRY_ON_LU ) ) ;
    }
//End GenUtils#1
    //Start : GenUtils#3 : Setting Screen Rights
    if ( rights.get ( IMScreen.INV_MOVEMENT_REPORT_COMPANY ) == null )
    {
      screenRights.put ( IMScreen.INV_MOVEMENT_REPORT_COMPANY , "N" ) ;
    }
    else
    {
      
      //COMMENTED BY MANISH FOR NOW ALLOWING INVENTORY MOVEMENT COMPANY REPORT 
      //screenRights.put ( IMScreen.INV_MOVEMENT_REPORT_COMPANY ,rights.get ( IMScreen.INV_MOVEMENT_REPORT_COMPANY ) ) ;
  	  screenRights.put ( IMScreen.INV_MOVEMENT_REPORT_COMPANY  , "N" ) ;
    }

    if ( rights.get ( IMScreen.TRANS_SUMMARY_REPORT_COMPANY ) == null )
    {
      screenRights.put ( IMScreen.TRANS_SUMMARY_REPORT_COMPANY , "N" ) ;
    }
    else
    {
      //COMMENTED BY MANISH FOR NOW ALLOWING TRANSACTION SUMMARY COMPANY SCREEN	
      //screenRights.put ( IMScreen.TRANS_SUMMARY_REPORT_COMPANY ,rights.get ( IMScreen.TRANS_SUMMARY_REPORT_COMPANY ) ) ;
    	screenRights.put ( IMScreen.TRANS_SUMMARY_REPORT_COMPANY  , "N" ) ;
    }

    if ( rights.get ( IMScreen.MISC_SUMMARY_REPORT_COMPANY ) == null )
    {
      screenRights.put ( IMScreen.MISC_SUMMARY_REPORT_COMPANY , "N" ) ;
    }
    else
    {
    	//COMMENTED BY MANISH FOR NOW ALLOWING Miscelaneous summary company report SUMMARY COMPANY SCREEN
    	//screenRights.put ( IMScreen.MISC_SUMMARY_REPORT_COMPANY ,rights.get ( IMScreen.MISC_SUMMARY_REPORT_COMPANY ) ) ;
    	screenRights.put ( IMScreen.MISC_SUMMARY_REPORT_COMPANY  , "N" ) ;
    }
    //End : GenUtils#3
    
    //Start : GenUtils#6
    //MANUAL RADIO BUTTON
    if ( rights.get ( IMScreen.MANUAL_TRANSFER_OUT ) == null )
    {
      screenRights.put ( IMScreen.MANUAL_TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_TRANSFER_OUT ,
                         rights.get ( IMScreen.MANUAL_TRANSFER_OUT ) ) ;
    }
    
    //FINAL PHYSICAL RADIO BUTTON
    if ( rights.get ( IMScreen.FINAL_PHYSICAL_INVENTORY ) == null )
    {
      screenRights.put ( IMScreen.FINAL_PHYSICAL_INVENTORY , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.FINAL_PHYSICAL_INVENTORY ,rights.get ( IMScreen.FINAL_PHYSICAL_INVENTORY ) ) ;
    }    
    
    //CO-PACKER TRANSFER OUT
    if ( rights.get ( IMScreen.COPACKER_TRANSFER_OUT ) == null )
    {
      screenRights.put ( IMScreen.COPACKER_TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.COPACKER_TRANSFER_OUT ,
                         rights.get ( IMScreen.COPACKER_TRANSFER_OUT ) ) ;
    }  
    //STALE TRANSFER OUT
    if ( rights.get ( IMScreen.STALE_TRANSFER_OUT ) == null )
    {
      screenRights.put ( IMScreen.STALE_TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.STALE_TRANSFER_OUT ,
                         rights.get ( IMScreen.STALE_TRANSFER_OUT ) ) ;
    }
    //ITX TRANSFER OUT
    if ( rights.get ( IMScreen.ITX_TRANSFER_OUT ) == null )
    {
      screenRights.put ( IMScreen.ITX_TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ITX_TRANSFER_OUT ,
                         rights.get ( IMScreen.ITX_TRANSFER_OUT ) ) ;
    }
   
    //CO-PACKER TRANSFER IN
    if ( rights.get ( IMScreen.COPACKER_TRANSFER_IN ) == null )
    {
      screenRights.put ( IMScreen.COPACKER_TRANSFER_IN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.COPACKER_TRANSFER_IN ,
                         rights.get ( IMScreen.COPACKER_TRANSFER_IN ) ) ;
    }  
    //STALE TRANSFER OUT
    if ( rights.get ( IMScreen.STALE_TRANSFER_IN ) == null )
    {
      screenRights.put ( IMScreen.STALE_TRANSFER_IN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.STALE_TRANSFER_IN ,
                         rights.get ( IMScreen.STALE_TRANSFER_IN ) ) ;
    }
    //ITX TRANSFER OUT
    if ( rights.get ( IMScreen.ITX_TRANSFER_IN ) == null )
    {
      screenRights.put ( IMScreen.ITX_TRANSFER_IN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ITX_TRANSFER_IN ,
                         rights.get ( IMScreen.ITX_TRANSFER_IN ) ) ;
    }
    
    
    //INVENTORY TRANSFER OUT
    if ( rights.get ( IMScreen.INV_TRANSFER_OUT) == null )
    {
      screenRights.put ( IMScreen.INV_TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.INV_TRANSFER_OUT ,
                         rights.get ( IMScreen.INV_TRANSFER_OUT ) ) ;
    }
    //INVENTORY TRANSFER IN
    if ( rights.get ( IMScreen.INV_TRANSFER_IN) == null )
    {
      screenRights.put ( IMScreen.INV_TRANSFER_IN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.INV_TRANSFER_IN ,
                         rights.get ( IMScreen.INV_TRANSFER_IN ) ) ;
    }
    
    //NONINVENTORY TRANSFER OUT
    if ( rights.get ( IMScreen.NONINV_TRANSFER_OUT) == null )
    {
      screenRights.put ( IMScreen.NONINV_TRANSFER_OUT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.NONINV_TRANSFER_OUT ,
                         rights.get ( IMScreen.NONINV_TRANSFER_OUT ) ) ;
    }
    //NONINVENTORY TRANSFER IN
    if ( rights.get ( IMScreen.NONINV_TRANSFER_IN) == null )
    {
      screenRights.put ( IMScreen.NONINV_TRANSFER_IN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.NONINV_TRANSFER_IN ,
                         rights.get ( IMScreen.NONINV_TRANSFER_IN ) ) ;
    }
    //MANUAL MISCELLANEOUSJPANE
    if ( rights.get ( IMScreen.MANUAL_MISCELLANEOUSJPANEL) == null )
    {
      screenRights.put ( IMScreen.MANUAL_MISCELLANEOUSJPANEL , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_MISCELLANEOUSJPANEL ,
                         rights.get ( IMScreen.MANUAL_MISCELLANEOUSJPANEL ) ) ;
    } 
    //End : GenUtils#6
    
    //added by manish for costing
    //COMMENTED BY JITEN 
    /*if ( rights.get ( IMScreen.AVERAGE_ACTUAL_COST) == null )
    {
      screenRights.put ( IMScreen.AVERAGE_ACTUAL_COST , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.AVERAGE_ACTUAL_COST ,rights.get ( IMScreen.AVERAGE_ACTUAL_COST ) ) ;
    }*/
    //finish by manish for costing
     
    //ADDED BY MANISH FOR PRODUCTION RECEIPT AND RETURN RIGHTS
    if ( rights.get ( IMScreen.PRODUCTION_RECEIPT) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT ,rights.get ( IMScreen.PRODUCTION_RECEIPT ) ) ;
    }
    
   if ( rights.get ( IMScreen.PRODUCTION_RECEIPT_SUMMARY) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_SUMMARY , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_SUMMARY ,rights.get ( IMScreen.PRODUCTION_RECEIPT_SUMMARY ) ) ;
    }
    
    if ( rights.get ( IMScreen.MANUAL_PRODUCTION_RETURN) == null )//CHANGED FOR GenUtils#53
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RETURN , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RETURN ,rights.get ( IMScreen.MANUAL_PRODUCTION_RETURN ) ) ;
    }
   //FINISH BY MANISH FOR PRODUCTION RECEIPT AND RETURN RIGHTS
    
    //Added by manish for document date right
    if ( rights.get ( IMScreen.ALLOWTOCHANGEDATE) == null )
    {
      SessionInfo.getInstance().setDocumentdateright("N");
    }
    else
    {
    	if ( rights.get(IMScreen.ALLOWTOCHANGEDATE).toString().trim().equalsIgnoreCase("E"))
    	{
    	SessionInfo.getInstance().setDocumentdateright("E");
    	}
    	else
    	{
    		SessionInfo.getInstance().setDocumentdateright("N");
    	}
    }
    //finish by manish for document date right
    
    //Added for GenUtils#75
    if ( rights.get ( IMScreen.ALLOWTOCHANGEAUTOMATEDDATE) == null )
    {
      SessionInfo.getInstance().setAutoDocumentdateright("N");
    }
    else
    {
    	if ( rights.get(IMScreen.ALLOWTOCHANGEAUTOMATEDDATE).toString().trim().equalsIgnoreCase("E"))
    	{
    	SessionInfo.getInstance().setAutoDocumentdateright("E");
    	}
    	else
    	{
    		SessionInfo.getInstance().setAutoDocumentdateright("N");
    	}
    }
    //finished for GenUtils#75
    
    
    //Added by jiten for purchase receipt costing
    if ( rights.get ( IMScreen.EXPECTED_PURCHASERECEIPT) == null )
    {
      screenRights.put ( IMScreen.EXPECTED_PURCHASERECEIPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.EXPECTED_PURCHASERECEIPT ,rights.get ( IMScreen.EXPECTED_PURCHASERECEIPT ) ) ;
    	
    }
    
    if ( rights.get ( IMScreen.PURCHASE_RECEIPT_QTY) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_RECEIPT_QTY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_RECEIPT_QTY ,rights.get ( IMScreen.PURCHASE_RECEIPT_QTY ) ) ;
    	
    }
    
    if ( rights.get ( IMScreen.TEMPORARY_PURCHASE_VAL) == null )
    {
      screenRights.put ( IMScreen.TEMPORARY_PURCHASE_VAL , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.TEMPORARY_PURCHASE_VAL ,rights.get ( IMScreen.TEMPORARY_PURCHASE_VAL ) ) ;
    	
    }
    
    
    if ( rights.get ( IMScreen.PURCHASE_VALUE) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_VALUE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_VALUE ,rights.get ( IMScreen.PURCHASE_VALUE ) ) ;
    	
    }
    
    
    if ( rights.get ( IMScreen.VOID_PURCHASE_RECEIPT_QTY) == null )
    {
      screenRights.put ( IMScreen.VOID_PURCHASE_RECEIPT_QTY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.VOID_PURCHASE_RECEIPT_QTY ,rights.get ( IMScreen.VOID_PURCHASE_RECEIPT_QTY ) ) ;
    	
    }
    
    if ( rights.get ( IMScreen.PURCHASERECEIPT_COMMIT) == null )
    {
      screenRights.put ( IMScreen.PURCHASERECEIPT_COMMIT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASERECEIPT_COMMIT ,rights.get ( IMScreen.PURCHASERECEIPT_COMMIT ) ) ;
    	
    }
    if ( rights.get ( IMScreen.PURCHASERECEIPT_SAVE) == null )
    {
      screenRights.put ( IMScreen.PURCHASERECEIPT_SAVE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASERECEIPT_SAVE ,rights.get ( IMScreen.PURCHASERECEIPT_SAVE ) ) ;
    	
    }
    //finish by jiten for purchase receipt costing  
    
  //Added by jiten for purchase return costing
    if ( rights.get ( IMScreen.EXPECTED_PURCHASERETURN) == null )
    {
      screenRights.put ( IMScreen.EXPECTED_PURCHASERETURN , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.EXPECTED_PURCHASERETURN ,rights.get ( IMScreen.EXPECTED_PURCHASERETURN ) ) ;
    	
    }
    
    if ( rights.get ( IMScreen.PURCHASE_RETURN_QTY) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_RETURN_QTY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_RETURN_QTY ,rights.get ( IMScreen.PURCHASE_RETURN_QTY ) ) ;
    	
    }
    
    if ( rights.get ( IMScreen.TEMP_RETURN_VALUE) == null )
    {
      screenRights.put ( IMScreen.TEMP_RETURN_VALUE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.TEMP_RETURN_VALUE ,rights.get ( IMScreen.TEMP_RETURN_VALUE ) ) ;
    	
    }
    
    
    if ( rights.get ( IMScreen.PURCHASERETURN_VALUE) == null )
    {
      screenRights.put ( IMScreen.PURCHASERETURN_VALUE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASERETURN_VALUE ,rights.get ( IMScreen.PURCHASERETURN_VALUE ) ) ;
    	
    }
    
    
    if ( rights.get ( IMScreen.VOID_PURCHASE_RETURN_QTY) == null )
    {
      screenRights.put ( IMScreen.VOID_PURCHASE_RETURN_QTY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.VOID_PURCHASE_RETURN_QTY ,rights.get ( IMScreen.VOID_PURCHASE_RETURN_QTY ) ) ;
    	
    }
    
    if ( rights.get ( IMScreen.PURCHASERETURN_COMMIT) == null )
    {
      screenRights.put ( IMScreen.PURCHASERETURN_COMMIT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASERETURN_COMMIT ,rights.get ( IMScreen.PURCHASERETURN_COMMIT ) ) ;
    	
    }
    //finish by jiten for purchase return costing  
    
    //Added by jiten for Average Actual Costing 
    if ( rights.get ( IMScreen.AVERAGEACTUALCOST_COMMIT) == null )
    {
      screenRights.put ( IMScreen.AVERAGEACTUALCOST_COMMIT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.AVERAGEACTUALCOST_COMMIT ,rights.get ( IMScreen.AVERAGEACTUALCOST_COMMIT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.AVERAGE_ACTUAL_COST) == null )
    {
      screenRights.put ( IMScreen.AVERAGE_ACTUAL_COST, "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.AVERAGE_ACTUAL_COST ,rights.get ( IMScreen.AVERAGE_ACTUAL_COST )  ) ;
    	
    }
    //Finished by jiten 
    
    //Added by jiten for Period closing report 
    if ( rights.get ( IMScreen.PERIO_CLOSING_REPORT) == null )
    {
      screenRights.put ( IMScreen.PERIO_CLOSING_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PERIO_CLOSING_REPORT ,rights.get ( IMScreen.PERIO_CLOSING_REPORT ) ) ;
    
    }
    //finished by jiten 
    
    //Added by jiten for Inventory Movement  detail report
    
   /* commented for GenUtils#69
    *  if ( rights.get ( IMScreen.INV_MOV_DETAIL_REPORT) == null )
    {
      screenRights.put ( IMScreen.INV_MOV_DETAIL_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.INV_MOV_DETAIL_REPORT ,rights.get ( IMScreen.INV_MOV_DETAIL_REPORT ) ) ;
    
    }*/
    //Finshed by jiten 
    
    
    //Added by jite for Inventory Sales Forecasting    
    if ( rights.get ( IMScreen.INV_SALES_FORECASTING_REPORT) == null )
    {
      screenRights.put ( IMScreen.INV_SALES_FORECASTING_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.INV_SALES_FORECASTING_REPORT ,rights.get ( IMScreen.INV_SALES_FORECASTING_REPORT ) ) ;
    
    }
    //Finished by jiten
    
    //Added by jiten for TEST #2315
    if ( rights.get ( IMScreen.AVERAGEACTUALCOST_CANCEL) == null )
    {
      screenRights.put ( IMScreen.AVERAGEACTUALCOST_CANCEL , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.AVERAGEACTUALCOST_CANCEL ,rights.get ( IMScreen.AVERAGEACTUALCOST_CANCEL ) ) ;
    
    }
    //Finished by jiten for TEST #2315
    
    //Recalculate button of Greece physical inventory
    if ( rights.get ( IMScreen.PHYSICAL_INVENTORY_RECALCULATE) == null )
    {
      screenRights.put ( IMScreen.PHYSICAL_INVENTORY_RECALCULATE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PHYSICAL_INVENTORY_RECALCULATE ,rights.get ( IMScreen.PHYSICAL_INVENTORY_RECALCULATE ) ) ;
    
    }
    
    //Added by jiten for purchase summary report
    if ( rights.get ( IMScreen.PURCHASE_SUMMARY_REPORT) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_SUMMARY_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_SUMMARY_REPORT ,rights.get ( IMScreen.PURCHASE_SUMMARY_REPORT ) ) ;
    
    }
    //finished by jiten 
    
    
    //Added by jiten for GenUtils#7
    if ( rights.get ( IMScreen.PURCHASE_SUMMARY_REPORT) == null )
    {
      screenRights.put ( IMScreen.PURCHASE_SUMMARY_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASE_SUMMARY_REPORT ,rights.get ( IMScreen.PURCHASE_SUMMARY_REPORT ) ) ;
    
    }
    //finished by jiten GenUtils#7
    
    //Added by jiten for GenUtils#7
    if ( rights.get ( IMScreen.MANUAL_PRODUCTION_RECEIPT) == null )
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RECEIPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RECEIPT ,rights.get ( IMScreen.MANUAL_PRODUCTION_RECEIPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY) == null )
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY ,rights.get ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY ) ) ;
    
    }

    if ( rights.get ( IMScreen.ALLOW_MANUAL_DOCUMENT) == null )
    {
      screenRights.put ( IMScreen.ALLOW_MANUAL_DOCUMENT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ALLOW_MANUAL_DOCUMENT ,rights.get ( IMScreen.ALLOW_MANUAL_DOCUMENT ) ) ;
    }
    
    
    if ( rights.get ( IMScreen.PRODUCTION_RECEIPT_DETAIL_REPORT) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_DETAIL_REPORT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_DETAIL_REPORT ,rights.get ( IMScreen.PRODUCTION_RECEIPT_DETAIL_REPORT ) ) ;
    }
    
    //finished by jiten GenUtils#7
    //Start : GenUtils#8, 
    if ( rights.get ( IMScreen.PRC_SHIFT_WISE_REPORT) == null )
    {
      screenRights.put ( IMScreen.PRC_SHIFT_WISE_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PRC_SHIFT_WISE_REPORT ,rights.get ( IMScreen.PRC_SHIFT_WISE_REPORT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.PHY_INV_REPORT) == null )
    {
      screenRights.put ( IMScreen.PHY_INV_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PHY_INV_REPORT ,rights.get ( IMScreen.PHY_INV_REPORT ) ) ;
    
    }
    //End : GenUtils#8  
    
     
    //start GenUtils#14
    if ( rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT) == null )
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT ,rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_TOTAL) == null )
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_TOTAL , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_TOTAL ,rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_TOTAL ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_DETAIL) == null )
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_DETAIL , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_DETAIL ,rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_TRANSACTION_DETAIL ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_COPACKER) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_COPACKER , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_COPACKER ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_COPACKER ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_DETAILS) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_DETAILS , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_DETAILS ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_DETAILS ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_DETAILS) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_DETAILS , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_DETAILS ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_DETAILS ) ) ;
    
    }
    
    /* commented for GenUtils#57
     * if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_TOTALS) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_TOTALS , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_TOTALS ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_TOTALS ) ) ;
    
    }
    */
    if ( rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_ITEM_AND_TRANSACTION_TYPE) == null )
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_ITEM_AND_TRANSACTION_TYPE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_ITEM_AND_TRANSACTION_TYPE ,rights.get ( IMScreen.GREECE_ITEM_TRANSACTION_REPORT_PER_ITEM_AND_TRANSACTION_TYPE ) ) ;
    
    }
    
    
    
    //Ends GenUtils#14

    //Added for GenUtils#10
    if ( rights.get ( IMScreen.RECONCILIATIONJPANEL) == null )
    {
      screenRights.put ( IMScreen.RECONCILIATIONJPANEL , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.RECONCILIATIONJPANEL ,rights.get ( IMScreen.RECONCILIATIONJPANEL ) ) ;
    
    }
    if ( rights.get ( IMScreen.RECONCILIATIONJPANEL_CANCEL) == null )
    {
      screenRights.put ( IMScreen.RECONCILIATIONJPANEL_CANCEL , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.RECONCILIATIONJPANEL_CANCEL ,rights.get ( IMScreen.RECONCILIATIONJPANEL_CANCEL ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.RECONCILIATIONJPANEL_COMMIT) == null )
    {
      screenRights.put ( IMScreen.RECONCILIATIONJPANEL_COMMIT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.RECONCILIATIONJPANEL_COMMIT ,rights.get ( IMScreen.RECONCILIATIONJPANEL_COMMIT ) ) ;
    
    }
    //finished for GenUtils#10
    
    //Added for GenUtils#12
    if ( rights.get ( IMScreen.PURCHASERECEIPT_SAVE) == null )
    {
      screenRights.put ( IMScreen.PURCHASERECEIPT_SAVE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASERECEIPT_SAVE ,rights.get ( IMScreen.PURCHASERECEIPT_SAVE ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.PURCHASERETURN_SAVE) == null )
    {
      screenRights.put ( IMScreen.PURCHASERETURN_SAVE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PURCHASERETURN_SAVE ,rights.get ( IMScreen.PURCHASERETURN_SAVE ) ) ;
    
    }
    //finished for GenUtils#12
    
//start GenUtils#15
    if ( rights.get ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITH_COST) == null )
    {
      screenRights.put ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITH_COST , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITH_COST ,rights.get ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITH_COST ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITHOUT_COST) == null )
    {
      screenRights.put ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITHOUT_COST , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITHOUT_COST ,rights.get ( IMScreen.GREECE_ON_HAND_QTY_RPT_WITHOUT_COST ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_CUREENT_QTY_RPT_FOR_SELECTED_LOCATION) == null )
    {
      screenRights.put ( IMScreen.GREECE_CUREENT_QTY_RPT_FOR_SELECTED_LOCATION , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_CUREENT_QTY_RPT_FOR_SELECTED_LOCATION ,rights.get ( IMScreen.GREECE_CUREENT_QTY_RPT_FOR_SELECTED_LOCATION ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_PAST_DATE_QTY_RPT_FOR_SELECTED_LOCATION) == null )
    {
      screenRights.put ( IMScreen.GREECE_PAST_DATE_QTY_RPT_FOR_SELECTED_LOCATION , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PAST_DATE_QTY_RPT_FOR_SELECTED_LOCATION ,rights.get ( IMScreen.GREECE_PAST_DATE_QTY_RPT_FOR_SELECTED_LOCATION ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT ,rights.get ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT_TOTALS) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT_TOTALS , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT_TOTALS ,rights.get ( IMScreen.GREECE_INVENTORY_COUNT_DIFFERANCE_RPT_TOTALS ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_RPT ,rights.get ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_GROUP_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_GROUP_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_GROUP_RPT ,rights.get ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_GROUP_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_PER_LOCATION_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_PER_LOCATION_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_PER_LOCATION_RPT ,rights.get ( IMScreen.GREECE_INVENTORY_COUNT_VALUE_PER_ITEM_PER_LOCATION_RPT ) ) ;
    
    } 
    
    //Ends GenUtils#15
    
    //Added for GenUtils#17
    if ( rights.get ( IMScreen.GREECE_PEROID_CLOSING) == null )
    {
      screenRights.put ( IMScreen.GREECE_PEROID_CLOSING , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PEROID_CLOSING ,rights.get ( IMScreen.GREECE_PEROID_CLOSING ) ) ;//Added for GenUtils#18
    
    } 
    if ( rights.get ( IMScreen.US_PEROID_CLOSING) == null )
    {
      screenRights.put ( IMScreen.US_PEROID_CLOSING , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.US_PEROID_CLOSING ,rights.get ( IMScreen.US_PEROID_CLOSING ) ) ;//Added for GenUtils#18
    
    } 
    //finished for GenUtils#17
    if ( rights.get ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION) == null )
    {
      screenRights.put ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION ,"E" ) ;
    
    } 
    
    if ( rights.get ( IMScreen.US_PEROID_CLOSING) == null )
    {
      screenRights.put ( IMScreen.US_PEROID_CLOSING , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.US_PEROID_CLOSING ,"E" ) ;
    
    } 
    //start GenUtils#19
    if ( rights.get ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION) == null )
    {
      screenRights.put ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION ,rights.get ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_PER_LOCATION ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_TOTALS) == null )
    {
      screenRights.put ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_TOTALS , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_TOTALS ,rights.get ( IMScreen.GREECE_TRANSACTION_TO_STALEWAREHOUSE_PRT_TOTALS ) ) ;
    
    }
    
    //end GenUtils#19
    
    //start GenUtils#20
    if ( rights.get ( IMScreen.GREECE_DAILY_PURCHASES_BOOK_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_DAILY_PURCHASES_BOOK_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_DAILY_PURCHASES_BOOK_RPT ,rights.get ( IMScreen.GREECE_DAILY_PURCHASES_BOOK_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_DAILY_PRODUCTION_BOOK_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_DAILY_PRODUCTION_BOOK_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_DAILY_PRODUCTION_BOOK_RPT ,rights.get ( IMScreen.GREECE_DAILY_PRODUCTION_BOOK_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_PURCHASE_VALUE_CALCULATION_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_PURCHASE_VALUE_CALCULATION_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PURCHASE_VALUE_CALCULATION_RPT ,rights.get ( IMScreen.GREECE_PURCHASE_VALUE_CALCULATION_RPT ) ) ;
    
    }
    
    //end GenUtils#20

    //Start GenUtils#22
    if ( rights.get ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITHOUT_BALANCE) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITHOUT_BALANCE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITHOUT_BALANCE ,rights.get ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITHOUT_BALANCE ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITH_BALANCE) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITH_BALANCE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITH_BALANCE ,rights.get ( IMScreen.GREECE_INVENTORY_TRIAL_BALANCE_RPT_WITH_BALANCE ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_PURCHASE_TRIAL_BALANCE_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_PURCHASE_TRIAL_BALANCE_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PURCHASE_TRIAL_BALANCE_RPT ,rights.get ( IMScreen.GREECE_PURCHASE_TRIAL_BALANCE_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_DAILY_BOOK_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_DAILY_BOOK_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_DAILY_BOOK_RPT ,rights.get ( IMScreen.GREECE_INVENTORY_DAILY_BOOK_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_NEGATIVE_BALANCE_CHECKING_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_NEGATIVE_BALANCE_CHECKING_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_NEGATIVE_BALANCE_CHECKING_RPT ,rights.get ( IMScreen.GREECE_NEGATIVE_BALANCE_CHECKING_RPT ) ) ;
    
    }
    
    //end GenUtils#22
    
    //start GenUtils#23
    if ( rights.get ( IMScreen.GREECE_INVENTORY_VALUATION_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_VALUATION_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_VALUATION_RPT ,rights.get ( IMScreen.GREECE_INVENTORY_VALUATION_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_INVENTORY_VALUATION_RPT_PER_ITEM_CATEGORY) == null )
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_VALUATION_RPT_PER_ITEM_CATEGORY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_INVENTORY_VALUATION_RPT_PER_ITEM_CATEGORY ,rights.get ( IMScreen.GREECE_INVENTORY_VALUATION_RPT_PER_ITEM_CATEGORY ) ) ;
    
    }
    //end GenUtils#23
    
    //start GenUtils#43
   /* commented for GenUtils#69
    *  if ( rights.get ( IMScreen.GREECE_ITEM_BREAK_DOWN_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_ITEM_BREAK_DOWN_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_ITEM_BREAK_DOWN_RPT ,rights.get ( IMScreen.GREECE_ITEM_BREAK_DOWN_RPT ) ) ;
    
    }*/
    //End GenUtils#43
    
    //start GenUtils#45
    if ( rights.get ( IMScreen.GREECE_STATUTORY_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_STATUTORY_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_STATUTORY_RPT ,rights.get ( IMScreen.GREECE_STATUTORY_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_WAREHOUSE_TRAIL_BALANCE_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_WAREHOUSE_TRAIL_BALANCE_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_WAREHOUSE_TRAIL_BALANCE_RPT ,rights.get ( IMScreen.GREECE_WAREHOUSE_TRAIL_BALANCE_RPT ) ) ;
    
    }
    if ( rights.get ( IMScreen.GREECE_WHSE_BOOK_ANALYTICAL_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_WHSE_BOOK_ANALYTICAL_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_WHSE_BOOK_ANALYTICAL_RPT ,rights.get ( IMScreen.GREECE_WHSE_BOOK_ANALYTICAL_RPT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.GREECE_WHSE_BOOK_SUMMARY_RPT) == null )
    {
      screenRights.put ( IMScreen.GREECE_WHSE_BOOK_SUMMARY_RPT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_WHSE_BOOK_SUMMARY_RPT ,rights.get ( IMScreen.GREECE_WHSE_BOOK_SUMMARY_RPT ) ) ;
    
    }
    
    //End GenUtils#45
    
    //Added for GenUtils#24
    if ( rights.get ( IMScreen.TEMP_PURCHASE_VALUE_REPORT) == null )
    {
      screenRights.put ( IMScreen.TEMP_PURCHASE_VALUE_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.TEMP_PURCHASE_VALUE_REPORT ,rights.get ( IMScreen.TEMP_PURCHASE_VALUE_REPORT ) ) ;
    
    }
    //finished for GenUtils#24
    
    //start GenUtils#25
    if ( rights.get ( IMScreen.ROMANIA_MISC_DOCUMENT_PICKLIST) == null)
    {
      screenRights.put ( IMScreen.ROMANIA_MISC_DOCUMENT_PICKLIST , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.ROMANIA_MISC_DOCUMENT_PICKLIST ,rights.get ( IMScreen.ROMANIA_MISC_DOCUMENT_PICKLIST ) ) ;
    
    }
    
  
    if ( rights.get ( IMScreen.ROMANIA_MISC_DOCUMENT_COMMIT) == null)
    {
      screenRights.put ( IMScreen.ROMANIA_MISC_DOCUMENT_COMMIT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.ROMANIA_MISC_DOCUMENT_COMMIT ,rights.get ( IMScreen.ROMANIA_MISC_DOCUMENT_COMMIT ) ) ;
    
    }
    
    //end GenUtils#25
    
    //Added for GenUtils#27
    if ( rights.get ( IMScreen.PRODUCTIONSUMMARYREPORT) == null)
    {
      screenRights.put ( IMScreen.PRODUCTIONSUMMARYREPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTIONSUMMARYREPORT ,rights.get ( IMScreen.PRODUCTIONSUMMARYREPORT ) ) ;
    
    }
    //finished for GenUtils#27
    
    //Added for GenUtils#28
    if ( rights.get ( IMScreen.PENDINGTRANSACTIONREPORT) == null)
    {
      screenRights.put ( IMScreen.PENDINGTRANSACTIONREPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PENDINGTRANSACTIONREPORT ,rights.get ( IMScreen.PENDINGTRANSACTIONREPORT ) ) ;
    
    }
    
    if ( rights.get ( IMScreen.PHYSICALINVSUPPORTREPORT) == null)
    {
      screenRights.put ( IMScreen.PHYSICALINVSUPPORTREPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PHYSICALINVSUPPORTREPORT ,rights.get ( IMScreen.PHYSICALINVSUPPORTREPORT ) ) ;
    
    }
    //finished for GenUtils#28
    
    
    //Added for GenUtils#29
    if ( rights.get ( IMScreen.LOCATION_WISE_INV_DTL_MOV_REPORT) == null)
    {
      screenRights.put ( IMScreen.LOCATION_WISE_INV_DTL_MOV_REPORT , "E" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.LOCATION_WISE_INV_DTL_MOV_REPORT ,rights.get ( IMScreen.LOCATION_WISE_INV_DTL_MOV_REPORT ) ) ;
    
    }
    //finished for GenUtils#29
    
    //Start for GenUtils#32
    /* commented for GenUtils#69
     * if ( rights.get ( IMScreen.DAILY_SHIFT_REPORT) == null)
    {
      screenRights.put ( IMScreen.DAILY_SHIFT_REPORT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.DAILY_SHIFT_REPORT ,rights.get ( IMScreen.DAILY_SHIFT_REPORT ) ) ;
    
    }*/
    //finished for GenUtils#32
    
    //added for GenUtils#33
    if ( rights.get ( IMScreen.PRODUCTION_SUMMARY_SAVE) == null)
    {
      screenRights.put ( IMScreen.PRODUCTION_SUMMARY_SAVE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_SUMMARY_SAVE ,rights.get ( IMScreen.PRODUCTION_SUMMARY_SAVE ) ) ;
    
    }
    if ( rights.get ( IMScreen.PRODUCTION_SUMMARY_COMMIT) == null)
    {
      screenRights.put ( IMScreen.PRODUCTION_SUMMARY_COMMIT , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_SUMMARY_COMMIT ,rights.get ( IMScreen.PRODUCTION_SUMMARY_COMMIT ) ) ;
    
    }
    //finished for GenUtils#33
    
    //Added for GenUtils#34
    if ( rights.get ( IMScreen.PRODUCTION_RECEIPT_CREATE) == null)
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_CREATE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_CREATE ,rights.get ( IMScreen.PRODUCTION_RECEIPT_CREATE ) ) ;
    
    }
    if ( rights.get ( IMScreen.PRODUCTION_RECEIPT_MODIFY) == null)
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_MODIFY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_RECEIPT_MODIFY ,rights.get ( IMScreen.PRODUCTION_RECEIPT_MODIFY ) ) ;
    
    }
    //finished for GenUtils#34
    
    //Added for GenUtils#35
    if ( rights.get ( IMScreen.RE_PRINT_DLV_NOTE) == null)
    {
      screenRights.put ( IMScreen.RE_PRINT_DLV_NOTE , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.RE_PRINT_DLV_NOTE ,rights.get ( IMScreen.RE_PRINT_DLV_NOTE ) ) ;
    
    }
    //finished for GenUtils#35
    
    //Added for GenUtils#38
    if ( rights.get ( IMScreen.DETAIL_STOCK_INQUIRY) == null)
    {
      screenRights.put ( IMScreen.DETAIL_STOCK_INQUIRY , "N" ) ;
    	
    }
    else
    {
      screenRights.put ( IMScreen.DETAIL_STOCK_INQUIRY ,rights.get ( IMScreen.DETAIL_STOCK_INQUIRY ) ) ;
    
    }
    //finished for GenUtils#38
    
    //Added for GenUtils#39
    if ( rights.get ( IMScreen.TRUCK_MASTER ) == null )
    {
      screenRights.put ( IMScreen.TRUCK_MASTER , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.TRUCK_MASTER ,
                         rights.get ( IMScreen.TRUCK_MASTER ) ) ;
    }
    //Finished for GenUtils#39
    
    //start GenUtils#40 //CHANGED FOR GenUtils#68
    if ( rights.get ( IMScreen.MISCELLANEOUS_TRANSACTION_DATE_CHANGE ) == null )
    {
      screenRights.put ( IMScreen.MISCELLANEOUS_TRANSACTION_DATE_CHANGE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MISCELLANEOUS_TRANSACTION_DATE_CHANGE ,rights.get ( IMScreen.MISCELLANEOUS_TRANSACTION_DATE_CHANGE ) ) ;
    }
    //end GenUtils#39

//Added for GenUtils#41
    if ( rights.get ( IMScreen.CARRIER_TRUCK_ASSIGNMENT ) == null )
    {
      screenRights.put ( IMScreen.CARRIER_TRUCK_ASSIGNMENT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.CARRIER_TRUCK_ASSIGNMENT ,
                         rights.get ( IMScreen.CARRIER_TRUCK_ASSIGNMENT ) ) ;
    }
    //Finished for GenUtils#41
    
    //Added  for GenUtils#44
    if ( rights.get ( IMScreen.PERIOD_COST ) == null )
    {
      screenRights.put ( IMScreen.PERIOD_COST , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PERIOD_COST ,
                         rights.get ( IMScreen.PERIOD_COST ) ) ;
    }
    //Finished  for GenUtils#44
    
    //start GenUtils#47
    if ( rights.get ( IMScreen.ALLOW_THEORYTICAL_QTY_TO_CHANGE ) == null )
    {
      screenRights.put ( IMScreen.ALLOW_THEORYTICAL_QTY_TO_CHANGE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ALLOW_THEORYTICAL_QTY_TO_CHANGE ,rights.get ( IMScreen.ALLOW_THEORYTICAL_QTY_TO_CHANGE ) ) ;
    }
    
    if ( rights.get ( IMScreen.ALLOW_TO_POST_PHY_TRANSACTION ) == null )
    {
      screenRights.put ( IMScreen.ALLOW_TO_POST_PHY_TRANSACTION , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ALLOW_TO_POST_PHY_TRANSACTION ,rights.get ( IMScreen.ALLOW_TO_POST_PHY_TRANSACTION ) ) ;
    }
    //end GenUtils#47
    
    //start GenUtils#48
    if ( rights.get ( IMScreen.ALLOW_TEAM_FINAL ) == null )
    {
      screenRights.put ( IMScreen.ALLOW_TEAM_FINAL , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ALLOW_TEAM_FINAL ,rights.get ( IMScreen.ALLOW_TEAM_FINAL ) ) ;
    }
    //end GenUtils#48
    
    //start GenUtils#49
    if ( rights.get ( IMScreen.SUMMANRY_PRODUCTION_INFO ) == null )
    {
      screenRights.put ( IMScreen.SUMMANRY_PRODUCTION_INFO , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.SUMMANRY_PRODUCTION_INFO ,rights.get ( IMScreen.SUMMANRY_PRODUCTION_INFO ) ) ;
    }
    //end GenUtils#49
    
    //Added for GenUtils#50
    if ( rights.get ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY_DOC ) == null )
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY_DOC , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY_DOC ,rights.get ( IMScreen.MANUAL_PRODUCTION_RECEIPT_SUMMARY_DOC ) ) ;
    }
    //Finished for GenUtils#50
    
    //Added for GenUtils#51
    if ( rights.get ( IMScreen.DYNAMIC_TRANSACTION_CODE ) == null )
    {
      screenRights.put ( IMScreen.DYNAMIC_TRANSACTION_CODE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.DYNAMIC_TRANSACTION_CODE ,rights.get ( IMScreen.DYNAMIC_TRANSACTION_CODE ) ) ;
    }
    //Finished for GenUtils#51
    
    //start GenUtils#52
    if ( rights.get ( IMScreen.ALLOW_TEAM1 ) == null )
    {
      screenRights.put ( IMScreen.ALLOW_TEAM1 , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ALLOW_TEAM1 ,rights.get ( IMScreen.ALLOW_TEAM1 ) ) ;
    }
    
    if ( rights.get ( IMScreen.ALLOW_TEAM2 ) == null )
    {
      screenRights.put ( IMScreen.ALLOW_TEAM2 , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.ALLOW_TEAM2 ,rights.get ( IMScreen.ALLOW_TEAM2 ) ) ;
    }
    //end GenUtils#52
    
    //Added for GenUtils#54
    if ( rights.get ( IMScreen.GREECE_REPACKAGING_BILL_OF_MATERAIL_RPT ) == null )
    {
      screenRights.put ( IMScreen.GREECE_REPACKAGING_BILL_OF_MATERAIL_RPT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_REPACKAGING_BILL_OF_MATERAIL_RPT ,rights.get ( IMScreen.GREECE_REPACKAGING_BILL_OF_MATERAIL_RPT ) ) ;
    }
    
    if ( rights.get ( IMScreen.GREECE_REPACKAGING_TRANSACTION_TOTAL_RPT ) == null )
    {
      screenRights.put ( IMScreen.GREECE_REPACKAGING_TRANSACTION_TOTAL_RPT , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_REPACKAGING_TRANSACTION_TOTAL_RPT ,rights.get ( IMScreen.GREECE_REPACKAGING_TRANSACTION_TOTAL_RPT ) ) ;
    }
    //finsihed for GenUtils#54
    
    //Added for GenUtils#56
    if ( rights.get ( IMScreen.MANUAL_PURCHASERECEIPTJPANEL ) == null )
    {
      screenRights.put ( IMScreen.MANUAL_PURCHASERECEIPTJPANEL , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PURCHASERECEIPTJPANEL ,rights.get ( IMScreen.MANUAL_PURCHASERECEIPTJPANEL ) ) ;
    }
    if ( rights.get ( IMScreen.MANUAL_PURCHASERETURNJPANEL ) == null )
    {
      screenRights.put ( IMScreen.MANUAL_PURCHASERETURNJPANEL , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PURCHASERETURNJPANEL ,rights.get ( IMScreen.MANUAL_PURCHASERETURNJPANEL ) ) ;
    }
    
    if ( rights.get ( IMScreen.MANUAL_INVTRANSFORMATIONJPANEL ) == null )
    {
      screenRights.put ( IMScreen.MANUAL_INVTRANSFORMATIONJPANEL , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_INVTRANSFORMATIONJPANEL ,rights.get ( IMScreen.MANUAL_INVTRANSFORMATIONJPANEL ) ) ;
    }
    
    if ( rights.get ( IMScreen.MANUAL_PHYSICALINVENTORYJPANEL ) == null )
    {
      screenRights.put ( IMScreen.MANUAL_PHYSICALINVENTORYJPANEL , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.MANUAL_PHYSICALINVENTORYJPANEL ,rights.get ( IMScreen.MANUAL_PHYSICALINVENTORYJPANEL ) ) ;
    }
    
    //finished for GenUtils#56
    
    //Added for GenUtils#57
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST ) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST ) ) ;
    }
    
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST ) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST ) ) ;
    }
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST_PER_ITEM ) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST_PER_ITEM , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST_PER_ITEM ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_DSDVANS_WITH_COST_PER_ITEM ) ) ;
    }
    
    if ( rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST_PER_ITEM ) == null )
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST_PER_ITEM , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST_PER_ITEM ,rights.get ( IMScreen.GREECE_PENDING_INTRANSIT_TRANSACTION_REPORT_BETWEEN_LOCATION_EXCLUDEDSDVANS_WITH_COST_PER_ITEM ) ) ;
    }
    
    //finsihed for GenUtils#57 
    
    //Added for GenUtils#60
    if ( rights.get ( IMScreen.EXPECTED_PUC_PRICE_VALUE ) == null )
    {
      screenRights.put ( IMScreen.EXPECTED_PUC_PRICE_VALUE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.EXPECTED_PUC_PRICE_VALUE ,rights.get ( IMScreen.EXPECTED_PUC_PRICE_VALUE ) ) ;
    }
    if ( rights.get ( IMScreen.EXPECTED_PUT_PRICE_VALUE ) == null )
    {
      screenRights.put ( IMScreen.EXPECTED_PUT_PRICE_VALUE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.EXPECTED_PUT_PRICE_VALUE ,rights.get ( IMScreen.EXPECTED_PUT_PRICE_VALUE ) ) ;
    }
    //finished for GenUtils#60
    
    
    //Added for GenUtils#67
    if ( rights.get ( IMScreen.PRODUCTION_COST_FILE ) == null )
    {
      screenRights.put ( IMScreen.PRODUCTION_COST_FILE , "N" ) ;
    }
    else
    {
      screenRights.put ( IMScreen.PRODUCTION_COST_FILE ,rights.get ( IMScreen.PRODUCTION_COST_FILE ) ) ;
    }
    //finished for GenUtils#67
    
    //Added for GenUtils#84
    if ( rights.get ( IMScreen.HHC_IM_ERROR_MANAGEMENT ) == null )
    {
      screenRights.put ( IMScreen.HHC_IM_ERROR_MANAGEMENT , "N" ) ;
    }
    else
    {  
        screenRights.put ( IMScreen.HHC_IM_ERROR_MANAGEMENT ,rights.get ( IMScreen.HHC_IM_ERROR_MANAGEMENT ) ) ;
    	
    }
  //Finished for GenUtils#84
    if ( rights.get ( IMScreen.LEGAL_DOCUMENT_PRINT ) == null )
    {
      screenRights.put ( IMScreen.LEGAL_DOCUMENT_PRINT , "N" ) ;
    }
    else
    {  
        screenRights.put ( IMScreen.LEGAL_DOCUMENT_PRINT ,rights.get ( IMScreen.LEGAL_DOCUMENT_PRINT ) ) ;
    	
    }
    
    log.debug ( "getScreenUOAHashtable  :: " + screenRights ) ;
    return screenRights ;
  }

  public static Hashtable getDesktopBundle ()
  {
    Hashtable hBundle = null ;
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
    XMLBundleReader xmlBundleReader = new XMLBundleReader ( XMLBundle.
        DESKTOP_PANEL_XMLBUNDLE ) ;
    hBundle = xmlBundleReader.getObjectKeyValue ( IMModule.DESKTOP_IM ,
                                                  IMScreen.DESKTOP_IM ,
                                                  sessionInfo.getLanguage () ) ;
    return hBundle ;
  }

  public static void changeDeskTopPanel ()
  {
    Hashtable hBundle = getDesktopBundle () ;
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().jpnlMenu.removeAll () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().jpnlToolBar.removeAll () ;
    DeskTopFrame.getInstance ().repaint () ;
    DeskTopFrame.getInstance ().validate () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().setComponentsNull () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().setNewDeskTopPanel () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().repaint () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().validate () ;
    DeskTopFrame.getInstance ().repaint () ;
    DeskTopFrame.getInstance ().validate () ;
    DeskTopFrame.getInstance ().getDesktopPanel ().getStatusPanel ().setLoginText ( hBundle.get ( "jlblLanguage" ).toString ()
    		+GeneralUtils.getDesktopBundle ().get ( "language" ).toString ());
    		/*Commented for GenUtils#36
    		 * + "    " 
    		+hBundle.get ( "jlblLocation" ).toString ()
    		+sessionInfo.getLocation () + "-" 
    		+sessionInfo.getLocationDesc () ) ;*/
    //Change GenUtils#2
    //DeskTopFrame.getInstance().setTitle(sessionInfo.getUserId() + "  " + hBundle.get("jlblTitle").toString() + "  " + SessionInfo.getInstance().getVersion());
    /*Commented for GenUtils#36
     * DeskTopFrame.getInstance ().setTitle ( sessionInfo.getUserId () + "  " +
                                           hBundle.get ( "jlblTitle" ).toString () +
                                           "  " +
                                           Version.getInstance ().toString () ) ;    */
    //End GenUtils#2
    
    //added for GenUtils#36
    DeskTopFrame.getInstance().setTitle(GeneralUtils.getDesktopBundle().get("jlblTitle").toString()
			+ "  "
			+ Version.getInstance().toString()
			+ "   ( "
			+ GeneralUtils.getDesktopBundle().get("jlblUserId").toString()+" "
			+ sessionInfo.getUserId()
			+ " )   ( "
			+ GeneralUtils.getDesktopBundle().get("jlblLocation").toString()
			+ sessionInfo.getCountry()+"-"
			+ sessionInfo.getCompany()+"-"
			+ sessionInfo.getLocation()+":"
			+ sessionInfo.getLocationDesc()+" )"
			+ " ( "+GeneralUtils.getDesktopBundle().get("jlblDBInstance").toString()
			+" "+ getDBInstance() +" )"//Added for GenUtils#82
			);
     //finished for  GenUtils#36
    
  }

  
  public static ArrayList getItemsFromOpmLoc ( String uom , String code )
  {
    ArrayList productList = SessionInfo.getInstance ().getAProductList () ;
    ArrayList aProductsInfo = new ArrayList () ;
    for ( int i = 0 , length = productList.size () ; i < length ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) productList.get ( i ) ;
      if ( pInfo.getUOM ().equals ( uom ) && pInfo.getAtt1 ().equals ( code ) )
      {
        aProductsInfo.add ( pInfo ) ;
      }
    }
    return aProductsInfo ;
  }

  public static ArrayList getItemsFromOpmLoc ( String uom , String code ,
                                               String screen )
  {
    ArrayList productList ;
    if ( screen.equals ( IMConstants.ROUTESHIP ) )
    {
      productList = SessionInfo.getInstance ().getAProductListRS () ;
    }
    else
    {
      if ( screen.equals ( IMConstants.REVERSEROUTESHIP ) )
      {
        productList = SessionInfo.getInstance ().getAProductListRRS () ;
      }
      else
      {
        if ( screen.equals ( IMConstants.MISCELLANEOUS ) )
        {
          productList = SessionInfo.getInstance ().getAProductListMisc () ;
        }
        else
        {
          if ( screen.equals ( IMConstants.TRANSFER_OUT ) )
          {
            productList = SessionInfo.getInstance ().getAProductListTO () ;
          }
          else
          {
            if ( screen.equals ( IMConstants.TRANSFER_IN ) )
            {
              productList = SessionInfo.getInstance ().getAProductListTI () ;
            }
            else
            {
              if ( screen.equals ( IMConstants.PHYSICALINVENTORY ) )
              {
                productList = SessionInfo.getInstance ().getAProductListPhy () ;
              }
              else
              {
                if ( screen.equals ( IMConstants.PURCHASERETURN ) )
                {
                  productList = SessionInfo.getInstance ().getAProductListCTO () ;
                }
                else
                {
                  if ( screen.equals ( IMConstants.PURCHASERECEIPT ) )
                  {
                    productList = SessionInfo.getInstance ().getAProductListCTI () ;
                  }
                  else
                  {
                	 if ( screen.equals ( IMConstants.InventoryTransformation ) )
                     {
                      productList = SessionInfo.getInstance ().getAProductListITF () ;
                     }
                   	else
                	 	{
                	 		productList = SessionInfo.getInstance ().getAProductList () ;
                	 	}
                  }
                }   
              }
            }
          }
        }
      }
    }
    ArrayList aProductsInfo = new ArrayList () ;
    for ( int i = 0 , length = productList.size () ; i < length ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) productList.get ( i ) ;
      if ( pInfo.getUOM ().equals ( uom ) && pInfo.getAtt1 ().equals ( code ) )
      {
        aProductsInfo.add ( pInfo ) ;
      }
    }
    return aProductsInfo ;
  }

///////////////////////////
  //added by Ruchi
  public static ArrayList compareUOM ( Hashtable hUOM , String prodType ,
                                       String uomMF , String uomSource )
  {
    ArrayList uomList = new ArrayList () ;
    uomList.add ( uomMF ) ;
    String uomDest = ( String ) hUOM.get ( prodType ) ;
    String uomDestCode = ( String ) SessionInfo.getInstance ().getUOMDescCode ().
        get ( uomDest ) ;
    log.debug ( uomSource ) ;
    String uomSrcCode = ( String ) SessionInfo.getInstance ().getUOMDescCode ().
        get ( uomSource ) ;

    if ( uomSrcCode.equals ( uomDestCode ) )
    {
      if ( !uomMF.equals ( uomDest ) )
      {
        uomList.add ( uomDest ) ;
      }
    }
    else
    {
      String maxUOM = getMAXUOM ( uomSrcCode , uomDestCode ) ;
      maxUOM = SessionInfo.getInstance ().getUOMCodeDesc ().get ( maxUOM ).
          toString () ;
      if ( !uomMF.equals ( maxUOM ) )
      {
        uomList.add ( maxUOM ) ;
      }
    }
    return uomList ;
  }

  
   
 public static ArrayList setDefUOM(String defUOM,ArrayList tempuomList,String SourceType ,String DescType) {
	 ArrayList Tempuom=tempuomList;
	 boolean setUOMFlag=false;
	 try{
		 if(SourceType.length()>0 && DescType.length()>0 ){
			 if((SourceType.equals("03")&& DescType.equals("03"))||
				 (SourceType.equals("03")&&DescType.equals("92"))||
				 (SourceType.equals("03")&&DescType.equals("18"))|| 		 
				 (SourceType.equals("18")&&DescType.equals("03"))	 
				){
				 setUOMFlag=true;
			 }			 
		 }		 
		 if(setUOMFlag){
			 
			 int idx=0;
			  for(int i=0;i<Tempuom.size();i++){
				  if(Tempuom.get(i).equals(defUOM)){
					  idx=i;
					  break;
				  }		  
			  }
			  if(idx!=0){
				 String uom= (String)tempuomList.get(0);
				 Tempuom.set(0,defUOM);	
				 Tempuom.set(idx, uom);
			  }
		 }	
	 }catch(Exception ex){
		 log.debug("Error while setting default UOM.....");
		 ex.printStackTrace();
	 }
	 return Tempuom;
 }
 
 public static ArrayList getAllUOMArrayListRSH ( String uomDesc )
 {
   ArrayList arlAllUOMList = new ArrayList () ;
   String uom = ( String ) SessionInfo.getInstance ().getUOMDescCode ().get (
       uomDesc ) ;

   if ( uom == null )
   {
     return new ArrayList () ;
   }
  
     if ( uom.equals ( IMConstants.EACH_CODE )||uom.equals ( IMConstants.CASE_CODE ) )
     {
       arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                           getUOMCodeDesc ().get ( IMConstants.CASE_CODE ) ) ;
       arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                           getUOMCodeDesc ().get ( IMConstants.EACH_CODE ) ) ;
     }
     else
     {
       if ( uom.equals ( IMConstants.CADD_CODE ) )
       {
         arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                             getUOMCodeDesc ().get ( IMConstants.CASE_CODE ) ) ;
         arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                             getUOMCodeDesc ().get ( IMConstants.CADD_CODE ) ) ;
       }
     }
   
   return arlAllUOMList ;
 }
 //Finish by jiten 
  public static String getMAXUOM ( String _uomA , String _uomB )
  {
    if ( _uomA.equals ( "004" ) || _uomB.equals ( "004" ) )
    {
      return "004" ;
    }
    else
    {
      return "005" ;//Changed by manish for Changing caddy UOM as 005 from 018
    }
  }

///////////////////////////////////////////

  //Added by Kanhaiya Samariya for UOM changes
  public static ArrayList getAllUOMArrayList ( String uomDesc )
  {
    ArrayList arlAllUOMList = new ArrayList () ;
    String uom = ( String ) SessionInfo.getInstance ().getUOMDescCode ().get (
        uomDesc ) ;

    if ( uom == null )
    {
      return new ArrayList () ;
    }
    if ( uom.equals ( IMConstants.CASE_CODE ) )
    {
      arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().getUOMCodeDesc ().
                          get ( IMConstants.CASE_CODE ) ) ;
    }
    else
    {
      if ( uom.equals ( IMConstants.EACH_CODE ) )
      {
        arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                            getUOMCodeDesc ().get ( IMConstants.CASE_CODE ) ) ;
        arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                            getUOMCodeDesc ().get ( IMConstants.EACH_CODE ) ) ;
      }
      else
      {
        if ( uom.equals ( IMConstants.CADD_CODE ) )
        {
          arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                              getUOMCodeDesc ().get ( IMConstants.CASE_CODE ) ) ;
          arlAllUOMList.add ( ( String ) SessionInfo.getInstance ().
                              getUOMCodeDesc ().get ( IMConstants.CADD_CODE ) ) ;
        }
      }
    }
    return arlAllUOMList ;
  }

  public static ArrayList getUOMArrayLists ( String location , String invType ,
                                             String prodHierarchy )
  {
    ArrayList finalList = SessionInfo.getInstance ().getUOMRelatedLists () ;
    ArrayList uomList = new ArrayList () ;
    ArrayList arlLocation ;
    ArrayList arlLocationDesc ;
    ArrayList arlAttribute5 ;
    ArrayList arlAttribute6 ;
    ArrayList arlAttribute7 ;
    ArrayList arlAttribute8 ;
    arlLocation = ( ArrayList ) finalList.get ( 0 ) ;
    arlLocationDesc = ( ArrayList ) finalList.get ( 2 ) ;
    arlAttribute5 = ( ArrayList ) finalList.get ( 3 ) ;
    arlAttribute6 = ( ArrayList ) finalList.get ( 4 ) ;
    arlAttribute7 = ( ArrayList ) finalList.get ( 5 ) ;
    arlAttribute8 = ( ArrayList ) finalList.get ( 6 ) ;

    int index = arlLocation.indexOf ( location ) ;

    //String uom1 = (String)arlAttribute5.get(index);

    String uom2 = "" ;
    String value = invType.trim () + prodHierarchy.trim () ;
    log.debug ( "GeneralUtils :: value = " + value ) ;
    if ( value.equals ( IMConstants.FG3N ) )
    {
      uom2 = ( String ) arlAttribute6.get ( index ) ;

    }
    else
    {
      if ( value.equals ( IMConstants.FG3Y ) )
      {
        uom2 = ( String ) arlAttribute7.get ( index ) ;

      }
      else
      {
        if ( value.equals ( IMConstants.EQ3N ) )
        {
          uom2 = ( String ) arlAttribute8.get ( index ) ;
          log.debug ( "GeneralUtils :: uom2 = " + uom2 ) ;
        }
      }
    }
    log.debug ( "GeneralUtils :: uom2 = " + uom2 ) ;

    //final List

    //uomList.add(uom1);

    /*
         if (!uom1.trim().equals(uom2.trim()) && uom2!=null && !uom2.equals("")) {
             uomList.add(uom2);
             }*/
    //Commented by jiten for UOM    
    //uomList = getAllUOMArrayList ( uom2 ) ;
    
    //Added by jiten for uom
    uomList=getAllUOMArrayListRSH(uom2);
    
    return uomList ;
  }

  public static Hashtable getUOMArrayLists ()
  {
    ArrayList finalList = SessionInfo.getInstance ().getUOMRelatedLists () ;
    Hashtable uomList = new Hashtable () ;
    ArrayList arlAttribute5 ;
    ArrayList arlLocation ;
    arlLocation = ( ArrayList ) finalList.get ( 0 ) ;
    arlAttribute5 = ( ArrayList ) finalList.get ( 3 ) ;
    log.debug ( "GeneralUtils :: arlLocation = " + arlLocation.size () ) ;
    log.debug ( "GeneralUtils :: arlAttribute5 = " + arlAttribute5.size () ) ;
    int length = arlLocation.size () ;
    for ( int i = 0 ; i < length ; i++ )
    {
      log.debug ( "arlLocation.get(i).toString().trim()..." +arlLocation.get ( i ).toString ().trim () ) ;
      log.debug ( "arlAttribute5.get(i).toString().trim()..." +arlAttribute5.get ( i ).toString ().trim () ) ;
      uomList.put ( arlLocation.get ( i ).toString ().trim () ,
                    arlAttribute5.get ( i ).toString ().trim () ) ;
    }
    return uomList ;
  }

  public static String lpad ( String toBePad , int length , String toPadWith )
  {
    int toBePadLength = toBePad.trim ().length () ;
    String padString = "" ;
    String lPadString = "" ;
    for ( int i = 0 , padStringLength = length - toBePadLength ;
          i < padStringLength ; i++ )
    {
      padString += toPadWith.trim () ;
    }
    lPadString = padString.trim () + toBePad.trim () ;

    return lPadString ;
  }

  public static void setScreenRights ( Hashtable hRights )
  {
    htable = hRights ;
    log.debug ( "GeneralUtils :: htable = " + htable.size () ) ;
  }

  public static Hashtable getScreenRights ()
  {
    return htable ;
  }

  public static int rightAllign ( int totSpace , int maxChar , int sLength )
  {
    int pos = 0 , pixels = 0 ;
    if ( sLength == 0 )
    {
      return 0 ;
    }
    pixels = totSpace / maxChar ;
    pos = 4 * ( maxChar - sLength ) ;
    return pos ;
  }

  public static int rightAllign1 ( int totSpace , int pixelPerChar ,
                                   int sLength )
  {
    int pos = 0 ; //pixels=0;
    if ( sLength == 0 )
    {
      return 0 ;
    }
    //pixels = totSpace / maxChar;
    pos = pixelPerChar * sLength ;
    return ( totSpace - pos ) ;
  }

   
  public static boolean beforeTime ( String firstTime , String secondTime )
  {
    boolean flag = false ;
    /*for first time value*/
    StringTokenizer st1 = new StringTokenizer ( firstTime , ":" ) ;
    int hr1 = Integer.parseInt ( st1.nextToken () ) ;
    int min1 = Integer.parseInt ( st1.nextToken () ) ;
    int sec1 = Integer.parseInt ( st1.nextToken () ) ;

    /*for second time value*/
    StringTokenizer st2 = new StringTokenizer ( secondTime , ":" ) ;
    int hr2 = Integer.parseInt ( st2.nextToken () ) ;
    int min2 = Integer.parseInt ( st2.nextToken () ) ;
    int sec2 = Integer.parseInt ( st2.nextToken () ) ;

    /*Comparing values*/
    if ( hr1 < hr2 )
    {
      flag = true ;
    }
    else if ( hr1 == hr2 )
    {
      if ( min1 < min2 )
      {
        flag = true ;
      }
      else if ( min1 == min2 )
      {
        if ( sec1 <= sec2 )
        {
          flag = true ;
        }
      }
    }
    return flag ;
  }

  public static boolean afterTime ( String firstTime , String secondTime )
  {
    boolean flag = false ;
    /*for first time value*/
    StringTokenizer st1 = new StringTokenizer ( firstTime , ":" ) ;
    int hr1 = Integer.parseInt ( st1.nextToken () ) ;
    int min1 = Integer.parseInt ( st1.nextToken () ) ;
    int sec1 = Integer.parseInt ( st1.nextToken () ) ;

    /*for second time value*/
    StringTokenizer st2 = new StringTokenizer ( secondTime , ":" ) ;
    int hr2 = Integer.parseInt ( st2.nextToken () ) ;
    int min2 = Integer.parseInt ( st2.nextToken () ) ;
    int sec2 = Integer.parseInt ( st2.nextToken () ) ;

    /*Comparing values*/
    if ( hr1 > hr2 )
    {
      flag = true ;
    }
    else if ( hr1 == hr2 )
    {
      if ( min1 > min2 )
      {
        flag = true ;
      }
      else if ( min1 == min2 )
      {
        if ( sec1 > sec2 )
        {
          flag = true ;
        }
      }
    }
    return flag ;
  }

  public static boolean equalTime ( String firstTime , String secondTime )
  {
    boolean flag = false ;
    /*for first time value*/
    StringTokenizer st1 = new StringTokenizer ( firstTime , ":" ) ;
    int hr1 = Integer.parseInt ( st1.nextToken () ) ;
    int min1 = Integer.parseInt ( st1.nextToken () ) ;
    int sec1 = Integer.parseInt ( st1.nextToken () ) ;

    /*for second time value*/
    StringTokenizer st2 = new StringTokenizer ( secondTime , ":" ) ;
    int hr2 = Integer.parseInt ( st2.nextToken () ) ;
    int min2 = Integer.parseInt ( st2.nextToken () ) ;
    int sec2 = Integer.parseInt ( st2.nextToken () ) ;

    /*Comparing values*/
    if ( hr1 == hr2 && min1 == min2 && sec1 == sec2 )
    {
      flag = true ;
    }
    return flag ;
  }

  public static String addColonToTime ( String strTime )
  {
    String SEPERATOR = ":" ;
    String returnTime = "" ;
    String hour = strTime.substring ( 0 , 2 ) ;
    String min = strTime.substring ( 2 , 4 ) ;
    String secs = strTime.substring ( 4 ) ;

    returnTime = hour + SEPERATOR + min + SEPERATOR + secs ;

    return returnTime ;
  }

  public static String removeColonFromTime ( String strTime )
  {
    String returnTime = "" ;
    String hour = strTime.substring ( 0 , 2 ) ;
    String min = strTime.substring ( 3 , 5 ) ;
    String secs = strTime.substring ( 6 ) ;
    returnTime = hour + min + secs ;
    return returnTime ;
  }

  public static ArrayList getEffProducts ( ArrayList arlProducts ,
                                           String docDate , String uom ,
                                           String code )
  {
    ArrayList arlEffProducts = new ArrayList ( 0 ) ;
    log.debug ( "arlProducts.size()....." + arlProducts.size () ) ;
    for ( int i = 0 ; i < arlProducts.size () ; i++ )
    {
      int startDate = ( new Integer ( ( ( ProductInfo ) arlProducts.get ( i ) ).
                                      getStartdate () ) ).intValue () ;
      int endDate = ( new Integer ( ( ( ProductInfo ) arlProducts.get ( i ) ).
                                    getEnddate () ) ).intValue () ;

      int docDateint = ( new Integer ( docDate ) ).intValue () ;
      if ( startDate <= docDateint && docDateint <= endDate )
      {
        arlEffProducts.add ( arlProducts.get ( i ) ) ;
      }
    }
    ArrayList aProductsInfo = new ArrayList () ;
    for ( int i = 0 , length = arlEffProducts.size () ; i < length ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) arlEffProducts.get ( i ) ;
      if ( pInfo.getUOM ().equals ( uom ) && pInfo.getAtt1 ().equals ( code ) )
      {
        aProductsInfo.add ( pInfo ) ;
      }
    }
    return aProductsInfo ;
  }

  public static ArrayList getEffProducts ( ArrayList arlProducts ,
                                           String docDate , String code )
  {
    ArrayList arlEffProducts = new ArrayList ( 0 ) ;
    log.debug ( "arlProducts.size()....." + arlProducts.size () ) ;
    for ( int i = 0 ; i < arlProducts.size () ; i++ )
    {
      int startDate = ( new Integer ( ( ( ProductInfo ) arlProducts.get ( i ) ).
                                      getStartdate () ) ).intValue () ;
      int endDate = ( new Integer ( ( ( ProductInfo ) arlProducts.get ( i ) ).
                                    getEnddate () ) ).intValue () ;

      int docDateint = ( new Integer ( docDate ) ).intValue () ;
      if ( startDate <= docDateint && docDateint <= endDate )
      {
        arlEffProducts.add ( arlProducts.get ( i ) ) ;
      }
    }
    ArrayList aProductsInfo = new ArrayList () ;
    for ( int i = 0 , length = arlEffProducts.size () ; i < length ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) arlEffProducts.get ( i ) ;
      if ( pInfo.getAtt1 ().equals ( code ) )
      {
        aProductsInfo.add ( pInfo ) ;
      }
    }
    return aProductsInfo ;
  }

// To add Restrictions that only produced products come in PRC & PRT
// This function used by PRC & PRT
// You can use this function for all other Documents also
  public static ArrayList getEffProducts ( String docType ,
                                           ArrayList arlProducts ,
                                           String docDate , String code )
  {
    ArrayList arlEffProducts = new ArrayList ( 0 ) ;
    log.debug ( "arlProducts.size()....." + arlProducts.size () ) ;
    for ( int i = 0 ; i < arlProducts.size () ; i++ )
    {
      int startDate = ( new Integer ( ( ( ProductInfo ) arlProducts.get ( i ) ).
                                      getStartdate () ) ).intValue () ;
      int endDate = ( new Integer ( ( ( ProductInfo ) arlProducts.get ( i ) ).
                                    getEnddate () ) ).intValue () ;

      int docDateint = ( new Integer ( docDate ) ).intValue () ;
      if ( startDate <= docDateint && docDateint <= endDate )
      {
        if ( docType.equals ( "PRC" ) || docType.equals ( "PRT" ) )
        {
          if ( ( ( ProductInfo ) arlProducts.get ( i ) ).getProduceFlag ().
               equals ( "1" )
               ||
               ( ( ProductInfo ) arlProducts.get ( i ) ).getProduceFlag ().
               equals ( "2" ) )
          {
            arlEffProducts.add ( arlProducts.get ( i ) ) ;
          }
        }
        else
        {
          arlEffProducts.add ( arlProducts.get ( i ) ) ;
        }
      }

    }
    ArrayList aProductsInfo = new ArrayList () ;
    for ( int i = 0 , length = arlEffProducts.size () ; i < length ; i++ )
    {
      ProductInfo pInfo = ( ProductInfo ) arlEffProducts.get ( i ) ;
      if ( pInfo.getAtt1 ().equals ( code ) )
      {
        aProductsInfo.add ( pInfo ) ;
      }
    }
    return aProductsInfo ;
  }

 

  public static String getShiftDate ( Calendar newDateClone , String time )
  {
    Calendar newDate = ( Calendar ) newDateClone.clone () ;
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
    return "" ;
  }

  public static String getShiftTime ( String time )
  {
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
    
    return "";
  }

  public static int getProdShift ( String time )
  {
    SessionInfo sessionInfo = SessionInfo.getInstance () ;
    return 0 ;
  }

  public static String convertFromCalendar ( Calendar cal )
  {
    return lpad ( String.valueOf ( cal.get ( cal.DATE ) ) , 2 , "0" ) + "/" +
        lpad ( String.valueOf ( cal.get ( cal.MONTH ) + 1 ) , 2 , "0" ) + "/" +
        cal.get ( cal.YEAR ) ;

  }
  
  //Added by jiten for freezing doc
  public static boolean setCancelEnable(String nextdate , String currdate){
	  boolean EnableCancel=false;
	  
	 
	  Calendar nextdateCal =convertTOCalendar(nextdate);
	  Calendar currdateCal= convertTOCalendar(currdate);
	  
	  if(nextdateCal.equals(currdateCal) || nextdateCal.after(currdateCal)){
		  EnableCancel=true;
	  }
	  
	  return EnableCancel;
  }
  public static String convertMMDDYYYY_YYYYMMDD ( String strDate )
  {
    String SEPERATOR = "/" ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
//        int monSepIndex = 0;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    month = inputStrDate.substring ( 3 , 5 ) ;
    date = inputStrDate.substring ( 0 , 2 ) ;
    year = inputStrDate.substring ( 6 , 10 ) ;
    String returnDate = year + SEPERATOR + month + SEPERATOR + date ;

    return returnDate ;
  }
 
  //Finish by jiten for freezing doc
  
  //Added by jiten for costing--to get number with specific decimal
  static public double getDecimalPosition(double number,int digits) {
	  NumberFormat formatter =null;
	  
	  /*if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.GREEK_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("el", "EL"));
	  }else if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.ROMANIAN_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("ro", "RO"));
	  }else{
		   formatter = NumberFormat.getNumberInstance(Locale.US);
	  }*/
	  number=Double.parseDouble((new BigDecimal(Double.toString(number))).setScale(digits,BigDecimal.ROUND_HALF_UP).toString()); //Added for GenUtils#87
	  
	  formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(false);
      return Double.parseDouble(formatter.format(number));
  }
  //Added by jiten for greece changes
  //Added for GenUtils#30
  static public double getDecimalPositionOnCountry(double number,String Currency) {
	  NumberFormat formatter =null;
	  int digits=2;
	  
	  if(SessionInfo.getInstance().getCountry().equals(SessionInfo.getInstance().getRomania_country_code())){
		  if(Currency.equals("RON")){
			  digits=2;
		  }else{
			  digits=2;
		  }
	  }
	  
	  formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(false);
      return Double.parseDouble(formatter.format(number));
  }
  //finished for GenUtils#30
  //Added for GenUtils#83
  static public double getDecimalPositionOnCountryValue(double number,String Currency) {
	  NumberFormat formatter =null;
	  int digits=2;
	  number=Double.parseDouble((new BigDecimal(Double.toString(number))).setScale(digits,BigDecimal.ROUND_HALF_UP).toString());//Added for GenUtils#87
	  formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(false);
      return Double.parseDouble(formatter.format(number));
  }
  //finished for GenUtils#83
  
  //Added for GenUtils#71
  static public double getDecimalPositionOnCountryPrice(double number,String Currency) {
	  NumberFormat formatter =null;
	  int digits=4;
	  
	  formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(false);
      return Double.parseDouble(formatter.format(number));
  }
  //finished for GenUtils#71
  
  static public String getDecimalPositionString(double number,int digits) {
	  NumberFormat formatter =null;
	 
	  formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);      
      return formatter.format(number);
  }
//finishe by jiten for greece changes
  static public String getDecimalPositionUS(double number,int digits) {
	  NumberFormat formatter =null;
	  
	  /*if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.GREEK_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("el", "EL"));
	  }else if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.ROMANIAN_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("ro", "RO"));
	  }else{
		   formatter = NumberFormat.getNumberInstance(Locale.US);
	  }*/
	  
	  formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(false);
      return formatter.format(number);
  }
//Added by jiten for costing
  
//Added by manish for stock inquiry
  static public String getDecimalPosition1(double number,int digits) {
	  NumberFormat formatter =null;
	  
	  if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.GREEK_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("el", "GR"));
	  }else if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.ROMANIAN_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("ro", "RO"));
	  }else{
		   formatter = NumberFormat.getNumberInstance(Locale.US);
	  }
	  
	  //formatter = NumberFormat.getNumberInstance(Locale.US);
      formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(false);
      return formatter.format(number);
  }
  //start GenUtils#26
  static public String getDecimalPosition2(double number,int digits) {
	  NumberFormat formatter =null;
	  
	  if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.GREEK_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("el", "GR"));
	  }else if(SessionInfo.getInstance().getLanguage().equals(ApplicationConstants.ROMANIAN_LANGUAGE)){
		   formatter = NumberFormat.getNumberInstance(new java.util.Locale("ro", "RO"));
	  }else{
		   formatter = NumberFormat.getNumberInstance(Locale.US);
	  }
	  
	  formatter.setMaximumFractionDigits(digits);
      formatter.setGroupingUsed(true);
      return formatter.format(number);
  }
  

//Added by manish to show warning message if document date is previous date then system date
  public static boolean IsPrevioudDateallowed (Component view,String Fieldname)
  {
    boolean returnValue = false ;
      String arStr[] = new String[ 2 ] ;
      arStr[ 0 ] = GeneralUtils.getDesktopBundle ().get ( "jlblYES" ).toString () ;
      arStr[ 1 ] = GeneralUtils.getDesktopBundle ().get ( "jlblNO" ).toString () ;
      int answer = JOptionPane.showOptionDialog ( view ,
    		  									  Fieldname+GeneralUtils.getDesktopBundle ().get ( "jmsgDifferentDate" ).toString () ,
                                                  GeneralUtils.getDesktopBundle ().get ("jmsgDifferentDateHeader" ).toString () ,
                                                  JOptionPane.YES_NO_OPTION ,
                                                  JOptionPane.WARNING_MESSAGE , null ,arStr , arStr ) ;
      switch ( answer )
      {
        case JOptionPane.YES_OPTION:
         returnValue = true ;
          break ;

        case JOptionPane.NO_OPTION:
          returnValue = false ;
          break ;
          
        //added by manish for unit test issue#1841
        case JOptionPane.CLOSED_OPTION:
        returnValue = false ;//CHANGE FOR GenUtils#86
        break ;
        //Finish by manish for unit test issue#1841
        
        case JOptionPane.CANCEL_OPTION:
        return false ;
        
        default:
      }
    return returnValue ;
  }
  
  public static boolean IsPrevioudDateallowedGreece (Component view,String Fieldname)
  {
    boolean returnValue = false ;
      String arStr[] = new String[ 1 ] ;
      arStr[ 0 ] = GeneralUtils.getDesktopBundle ().get ( "jlblCANCEL" ).toString () ;
      int answer = JOptionPane.showOptionDialog ( view ,
    		  									  Fieldname+GeneralUtils.getDesktopBundle ().get ( "jmsgDifferentDategreece" ).toString () ,
                                                  GeneralUtils.getDesktopBundle ().get ("jmsgDifferentDateHeader" ).toString () ,
                                                  JOptionPane.CANCEL_OPTION ,
                                                  JOptionPane.ERROR_MESSAGE , null ,arStr,arStr) ;
     return returnValue ;
  }
//Finish by manish to show warning message if document date is previous date then system date

  //Added by jiten for convert document number text
  public static String rpad ( String toBePad , int length , String toPadWith )
  {
    int toBePadLength = toBePad.trim ().length () ;
    String padString = "" ;
    String rPadString = "" ;
    for ( int i = 0 , padStringLength = length - toBePadLength ;i < padStringLength ; i++ )
    {
      padString += toPadWith.trim () ;
    }
    rPadString = toBePad.trim () + padString.trim () ;
    log.debug("rPadString-----"+rPadString);
    return rPadString ;
  }
  
  public static String convertToBackEndDocNo ( String strDocNo )
  {
    String returnDocNo=strDocNo;
    
    String docType="";
    String locationSeries="";
    String DocNo="";
    
    if(strDocNo.length()>0 && strDocNo.length()<14){
       DocNo=strDocNo.substring(strDocNo.length()-8);
//       log.debug("DocNo-----"+DocNo);
       
       docType=strDocNo.substring(0,2);
//       log.debug("docType-----"+docType);
    	
       locationSeries=rpad(strDocNo.substring(2,2+(strDocNo.length()-10)),4,"-");
//       log.debug("locationSeries-----"+locationSeries);
       
       returnDocNo=docType + locationSeries + DocNo;
//       log.debug("convertBackEndDocNo-----"+returnDocNo);
    }
    
     
    return returnDocNo ;
  }
  
  public static String convertToFrontEndDocNo ( String strDocNo )
  {
    String returnDocNo=strDocNo;
    
    String docType="";
    String locationSeries="";
    String DocNo="";
    
    if(strDocNo.length()==14 && strDocNo.indexOf("-")>=0){
       DocNo=strDocNo.substring(6);
//       log.debug("DocNo-----"+DocNo);
       
       docType=strDocNo.substring(0,2);
//       log.debug("docType-----"+docType);
    	
       locationSeries=strDocNo.substring(2, strDocNo.indexOf("-"));
//       log.debug("locationSeries-----"+locationSeries);
       
       returnDocNo=docType + locationSeries + DocNo;
//       log.debug("convertFrontEndDocNo-----"+returnDocNo);
    }
    
     
    return returnDocNo ;
  }
  
  //Added by jiten for 
  public static String devideDocNo ( String strDocNo )
  {
    String returnDocNo=strDocNo;
    
    String docType="";
    String locationSeries="";
    String DocNo="";
    
    if(strDocNo.length()==14 && strDocNo.indexOf("-")>=0){
       DocNo=strDocNo.substring(6);
//       log.debug("DocNo-----"+DocNo);
       
       docType=strDocNo.substring(0,2);
//       log.debug("docType-----"+docType);
    
       locationSeries=strDocNo.substring(2, strDocNo.indexOf("-"));
//       log.debug("locationSeries-----"+locationSeries);
       
       returnDocNo=docType +"##"+ locationSeries +"##"+ DocNo;
//       log.debug("devideDocNo-----"+returnDocNo);
    }else if(strDocNo.length()==14 && strDocNo.indexOf("-")<0){
    	
    	 DocNo=strDocNo.substring(6);
//         log.debug("DocNo-----"+DocNo);
         
         docType=strDocNo.substring(0,2);
//         log.debug("docType-----"+docType);
      
         locationSeries=strDocNo.substring(2, 6);
//         log.debug("locationSeries-----"+locationSeries);
         
         returnDocNo=docType +"##"+ locationSeries +"##"+ DocNo;
//         log.debug("devideDocNo-----"+returnDocNo);
    }
    
     
    return returnDocNo ;
  }
  //finished by jiten 
  
  //Method adding start by manish for Unit Test issue#5527
  public static Boolean IsValidDocumentNumber(String Documentnumber)
  {
		String docNo =Documentnumber;
		if(docNo.length ()==0)
	    {
	    	return false;
	    }
	    else if ( docNo.length () > 10 )
	    {
	       	return true;       
	    }
	    else
	    {
	    	ErrorMessage.displayMessage ('E',ErrorConstants.INVALID_DOCUMENT_NUMBER ) ;
	    	return false;
	    }
 }
  //Method adding finish by manish for Unit Test Issue#5527
  
  public static String getDocTypeCode ( String strDocNo )
  {  String docType="";
  	 if(strDocNo.length()>=10){
  		docType=strDocNo.substring(0,2);
  	  }
	  return docType;
  }
  public static String getLocationSeries ( String strDocNo )
  {  String LocationSeries="";
      if(strDocNo.length()>10){
    	  LocationSeries=strDocNo.substring(2,2+(strDocNo.length()-10));
      }
	  return LocationSeries;
  }
  public static String getDocNo( String strDocNo )
  {  String DocNo="";
     if(strDocNo.length()>=10){
    	 DocNo=strDocNo.substring(strDocNo.length()-8);
     }  
	  return DocNo;
  }
  
  public static boolean isDateEquals(Calendar fdate,Calendar sdate){
	  boolean dateFlag=false;
	  //Commented by jiten for QA Issue#6080
	  /*if(fdate.YEAR == sdate.YEAR){
		  if(fdate.MONTH == sdate.MONTH){
			  if(fdate.DATE == sdate.DATE){
				  dateFlag=true;
			  }
		  }
		  
	  }*/
	  //finished by jiten for QA Issue#6080
	  
	  //Added by jiten for QA Issue#6080
	  if(fdate.get(1) == sdate.get(1)){
		  if(fdate.get(2) == sdate.get(2)){
			  if(fdate.get(5) == sdate.get(5)){
				  dateFlag=true;
			  }
		  }	  
	   }
   	//finished by jiten for QA Issue#6080
		  
	  return dateFlag; 
  }
  //finish by jiten for convert document number text
  
  //start GenUtils#16
  public static Calendar convertTOCalendar_withoutMonthDeduction ( String strDate )
  {
    String SEPERATOR = "/" ;
    Calendar calendarObj = Calendar.getInstance () ;
    String inputStrDate = strDate.trim () ;
    String date = new String () ;
    String month = new String () ;
    String year = new String () ;
    int dateSepIndex = 0 ;
    int monSepIndex = 0 ;
    int dateInt = 0 ;
    int monthInt = 0 ;
    int yearInt = 0 ;
    dateSepIndex = inputStrDate.indexOf ( SEPERATOR ) ;
    date = inputStrDate.substring ( 0 , dateSepIndex ) ;
    monSepIndex = inputStrDate.indexOf ( SEPERATOR , dateSepIndex + 1 ) ;
    month = inputStrDate.substring ( dateSepIndex + 1 , monSepIndex ) ;
    year = inputStrDate.substring ( monSepIndex + 1 , inputStrDate.length () ) ;
    dateInt = Integer.parseInt ( date ) ;
    monthInt = Integer.parseInt ( month ) ;
    yearInt = Integer.parseInt ( year ) ;
    calendarObj.set ( yearInt , monthInt , dateInt ) ;
    
  //Added for GenUtils#76 
    calendarObj.set(Calendar.HOUR, 0);
    calendarObj.set(Calendar.MINUTE, 0);
    calendarObj.set(Calendar.SECOND, 0);
    calendarObj.set(Calendar.MILLISECOND, 0);

    //finished for GenUtils#76
    return calendarObj ;
  }
  //end GenUtils#16
  
  //Aadded for 4444
//new 4444
  public static PrintService[] getPrintService()
	{
	PrintService[] printServices = null;
	try{
	//System.out.println("Sankit inside getPrintService method"); 
	//DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT; //commented for GenUtils#80 
	//PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet(); //commented for GenUtils#80
	//aset.add(MediaSizeName.ISO_A4); //commented for GenUtils#80
	printServices = PrintServiceLookup.lookupPrintServices(null, null);//PrintServiceLookup.lookupDefaultPrintService();
	System.out.println("\n ----------------- \nAvailable Printers:\n ----------------\n");
	for(int i=0;i<printServices.length;i++){ 
	System.out.println(i + " - "+ printServices[i].getName()); 
	} 
	}catch(Exception e)
	{e.printStackTrace();}
	return printServices;
	}
  //finished 4444
  
  public static boolean isPrinterAvailable(){
	  String printer=""; 
	  PrintService prnService=null;
	  
	  if(SessionInfo.getInstance().getLegal_printer_check().equals("NO")){
		return true;  
	  }
	  
	  try { 
	   
		  
	   try
		{
			
		}
		catch(Exception e)
		{
			 log.debug("Error Occurs While Calling isPrinterAvailable in generalUtils" + e);
		}
	  
	   System.out.println("======================");
	   System.out.println("PRINTER PATH FOUND FOR USER IS : " + printer);
	   System.out.println("======================");
	   //DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT; //commented for GenUtils#80
	   //AttributeSet aset = new HashAttributeSet();//commented for GenUtils#80
	   if(printer==null||printer.trim().equals("")){
			System.out.println("PRINTER NOT FOUND.........");
			ErrorMessage.displayMessage ('E',ErrorConstants.LEGAL_PRINTER_NOT_FOUND ) ;
			return false;
		}
	   //aset.add(new PrinterName(printer, null));   //commented for GenUtils#80
	   //PrintService[] pen = PrintServiceLookup.lookupPrintServices(null, aset);
	   PrintService[] pen = getPrintService();
	   
	   System.out.println("PRINTERS FOUND LENGTH : " + pen.length);
	   System.out.println("--------------");
	   
	   boolean isAvail = false;
	   
	   for(int i=0;i<pen.length;i++){
	    System.out.println("PRINTER "+ i + " - " + pen[i].getName());
	    //Added for 
	    //if(pen[i].getName().toUpperCase().contains(printer.toUpperCase()))
	    if(checkPrinterName(printer.toUpperCase(),pen[i].getName().toUpperCase()))
		{
			System.out.println("PrintService Found - "+ i + " - " + pen[i].getName());						
			isAvail=true;
			break;
		}
	    //finished for 4444
	    
	   }
		if(!isAvail)
		{
			for(int i=0;i<pen.length;i++){
				System.out.println(" checkPrinterNameOnSameMachine PRINTER "+ i + " - " + pen[i].getName());
				   if(checkPrinterNameOnSameMachine(printer.toUpperCase(),pen[i].getName().toUpperCase()))
					{
						System.out.println(" checkPrinterNameOnSameMachine PrintService Found - "+ i + " - " + pen[i].getName());						
						isAvail=true;
						break;
					}
			}
		}
	   
	   if(pen==null || pen.length<=0 || !isAvail){
		   ErrorMessage.displayMessage ('E',ErrorConstants.LEGAL_PRINTER_NOT_FOUND ) ;
	    return false;    
	   }
	   else
	    return isAvail; //change for 4444
	  }catch(Exception ex){
	   ex.printStackTrace();
	  }  
	  return false;
	 }
  public static boolean checkPrinterName(String printerNameInMF,String printernNameCitrix)
	{
		
		String machineName = "";
		String printerName  ="";
		int firstIndex = 0;
		int secondIndex = 0;
		int lastIndex = 0;
		for (int i=0; i<printerNameInMF.length(); i++){
			char chval = printerNameInMF.charAt(i);
			if (chval=='\\'){
				if(i ==0)
					firstIndex = i;
				else if(i==1)
					secondIndex = i;
				else	
					lastIndex = i;
			}
			
		}
		machineName = printerNameInMF.substring(secondIndex+1,lastIndex).toLowerCase();
		printerName = printerNameInMF.substring(lastIndex+1,printerNameInMF.length()).toLowerCase();
		
		printernNameCitrix = printernNameCitrix.toLowerCase();
		
		if(printernNameCitrix.contains(machineName) && printernNameCitrix.contains(printerName))
		{			
			return true;
		}
		return false;
		
	}
  public static boolean checkPrinterNameOnSameMachine(String printerNameInMF,String printernNameCitrix)
	{
		
		String machineName = "";
		String printerName  ="";
		int firstIndex = 0;
		int secondIndex = 0;
		int lastIndex = 0;
		for (int i=0; i<printerNameInMF.length(); i++){
			char chval = printerNameInMF.charAt(i);
			if (chval=='\\'){
				if(i ==0)
					firstIndex = i;
				else if(i==1)
					secondIndex = i;
				else	
					lastIndex = i;
			}
			
		}
		machineName = printerNameInMF.substring(secondIndex+1,lastIndex).toLowerCase();
		printerName = printerNameInMF.substring(lastIndex+1,printerNameInMF.length()).toLowerCase();
		
		printernNameCitrix = printernNameCitrix.toLowerCase();
		
		if(printernNameCitrix.contains(printerName))
		{			
			return true;
		}
		return false;
		
	}
  //added for Change GenUtils#72
  public static boolean isValidCheckDigit(String inputString)  
  {
	  boolean validCheckDigit = false;

	  if(inputString !=null && inputString.length()==18)
	  {
		  if(calculateCheckDigit(inputString) == new Short(inputString.substring(inputString.length()-1)).byteValue()) 
		  {
			  validCheckDigit = true;
		  }
	  } 
	  log.debug("Valid Check Digit:"+ validCheckDigit);
	  return validCheckDigit;
  }
  //Finish for Change GenUtils#72
  
  //added for Change GenUtils#72
  public static  byte calculateCheckDigit(String inputString)   
  {
		
	    int  calculatedSum = 0;
		byte checkDigit = 0;
		
		for(short pos=0;pos<inputString.length()-1;pos++) 
		{
			if(pos%2==0) 
			{
				calculatedSum = calculatedSum + (new Integer(inputString.substring(pos,pos+1)).shortValue()*3);
			}
			else 
			{
				calculatedSum = calculatedSum + (new Integer(inputString.substring(pos,pos+1)).shortValue());
			}
		}

		log.debug("Calcualted Sum:"+ calculatedSum);
			
		while(true)
		{
			if(calculatedSum%10==0) 
			{
				break;
			}
			else 
			{
				calculatedSum++;
				checkDigit++;
			}
		}
			
		log.debug("Calculated Check Digit......:"+ checkDigit);
		
		return checkDigit;
  }
  
 
	
	//added for Change GenUtils#77
	public static String getCurrentDateInDDMMYYYYFormat() {
		// This method returns the current method in following pattern
		// 01/02/2001
		String currentDate = "";
		String dateFormat = "dd/MM/yyyy";
		// Get The Currnet Date:
		Calendar cal = Calendar.getInstance();
		// Apply the format to Simple Date Format Object
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		// Formate the Current Date
		currentDate = sdf.format(cal.getTime());

		return currentDate;
	}
	
	
	//added for Change GenUtils#78
	
	public static String getCurrentDateInDDMMYYYYHHMMSSFormat() {
		
		String currentDateAndTime = "";
		String dateFormat = "dd/MM/yyyy HH:mm:ss";
		// Get The Currnet Date:
		Calendar cal = Calendar.getInstance();
		// Apply the format to Simple Date Format Object
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		// Formate the Current Date
		currentDateAndTime = sdf.format(cal.getTime());

		return currentDateAndTime;
	}
	
	//end for GenUtils#78

	
	public static String getSelectedFileName() {
		// File Chooser Dailog BOx
		JFileChooser fileChooser = null;

		// String File Name
		String filename = null;

		// This Variable checks whether cancel button is pressed or valid file
		// name
		// Enterd by user and Pressed Ok Button
		boolean fileNameIsvalidOrCancelButtonPressed = false;

		// Looping Until user presss Cancel Button or Insert Valid File Name
		while (fileNameIsvalidOrCancelButtonPressed == false) {
			// Initiale the File Name to Null if User had pressed The No button
			// In Case of Override
			filename = null;

			if (fileChooser == null) {
				fileChooser = new JFileChooser("c:\\");
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setFileSelectionMode(fileChooser.FILES_ONLY);

				// Setting the Fiter to .xls so save dailog box should show only
				// .xls file
				fileChooser.setFileFilter(new FileFilter() {
					public String getDescription() {
						return "Excel files(.xls)";
					}

					public boolean accept(File f) {
						if (f.isDirectory())
							return true;
						if (f.getName().endsWith(".xls"))
							return true;
						return false;
					}
				});
			}

			// variable added for user choice which button used had pressed
			int selectedButton = fileChooser.showSaveDialog(null);

			// If User Pressed Ok Button of Save Dailog
			if (selectedButton == JFileChooser.APPROVE_OPTION) {
				filename = fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println("FC file Name : " + filename);
				/*
				 * If condition is put to validate the file name as following
				 * characters are not allowed as the part of file name.
				 */
				if ((filename.indexOf("*") != -1)
						|| (filename.indexOf("|") != -1)) {
					ErrorMessage.displayMessage('I',
							ErrorConstants.FILE_VALID_NAME);
				} else {
					if (filename.indexOf(".xls") == -1) {
						filename = filename + ".xls";
					}

					File file = new File(filename);

					// If File Allready Exist Ask for Override of the File
					if (file.exists()) {

						int userSelectedButton = ErrorMessage.displayMessage(
								'Q', ErrorConstants.FILE_OVERWRITE);
						// If User agree to Overrite the File
						if (userSelectedButton == JOptionPane.OK_OPTION) {
							fileNameIsvalidOrCancelButtonPressed = true;
						}
					} else {
						fileNameIsvalidOrCancelButtonPressed = true;

					}
				}
			}
			// If Usef Pressed Cancel Button
			else {
				fileNameIsvalidOrCancelButtonPressed = true;
			}
		}

		return filename;
	}
	
	
  //Added f GenUtils#88 
	public static String getFileNameFromUser(final String fileExtension,final String description)
	{
		//File Choose Dailog Box 
		JFileChooser fileChooser = null;
		// Name of the File
		String filename = null;
		// Check for valid file name and cancel button presse
		boolean finameIsValid_CancelButtonPressed = false;
		// loop until user enter valid file name or press cancel button
		while (finameIsValid_CancelButtonPressed == false)
		{
			// Initilize file name to null in case user don't want overrite the existing file // In Case of Override
			filename = null;
			// Check IF the file Chooser is Null if found null initize all its data
			if (fileChooser == null) 
			{
				// Set default path to c:\\
				fileChooser = new JFileChooser("c:\\");
				//do not allowe to select multiple file
				fileChooser.setMultiSelectionEnabled(false);
				//sets the filter to search only file
				fileChooser.setFileSelectionMode(fileChooser.FILES_ONLY);
				// Setting the Fiter to .mdb so save dailog box should show only
				fileChooser.setFileFilter(new FileFilter() 
				{
					public String getDescription() {
						return description+"("+fileExtension+")";
						//return "MDB files(.mdb)";
					}
					
					public boolean accept(File f) {
						if (f.isDirectory())
							return true;
						if (f.getName().endsWith(fileExtension))
							return true;
						return false;
					}
				});
			}

			//added to idetified which button pressed by user
			int selectedButton = fileChooser.showSaveDialog(null);
			// If User Pressed Ok Button of Save Dailog
			if (selectedButton == JFileChooser.APPROVE_OPTION) 
			{
				filename = fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println("FC file Name : " + filename);
				/*
				 * If condition is put to validate the file name as following
				 * characters are not allowed as the part of file name.
				 */
				if ((filename.indexOf("*") != -1) || (filename.indexOf("|") != -1)) 
				{
					ErrorMessage.displayMessage('I',ErrorConstants.FILE_VALID_NAME);
				}
				else 
				{
					if (filename.indexOf(fileExtension) == -1) 
					{
						filename = filename + fileExtension.substring(1);
					}
					//Generating File 
					File file = new File(filename);
					//If File Allready Exist Ask for Override of the File
					if (file.exists()) 
					{
						int overWriteFile = ErrorMessage.displayMessage('Q', ErrorConstants.FILE_OVERWRITE);
						//If User agree to Overrite the File
						if (overWriteFile == JOptionPane.OK_OPTION) 
						{
							finameIsValid_CancelButtonPressed = true;
						}
					} 
					else 
					{
						finameIsValid_CancelButtonPressed = true;
					}
				}
			}
			// If use press the cancel Button
			else 
			{
				finameIsValid_CancelButtonPressed = true;
			}
		}

		return filename;
	}
	
	
	//Added for GenUtils#88
	public static boolean downloadFileFromFtpServer(String ftpHostId,String ftpUserName,String ftpPassword,String filetodownload,String fileToSave)
	{
		// Flag to decide whethere file is downloaded from FTP or Not.....
		boolean fileDownLoaded = false;
		try
		 {
			 
		 }
		 catch(Exception ex) 
		 {
			 ErrorMessage.displayMessage('E',ErrorConstants.INTERNAL_ERROR);
		 }
		 
		 return	fileDownLoaded; 			
	}
	//Added for GenUtils#88
	
	//Added for GenUtils#90
	public static boolean convertTextFileToPdfFile(String textFile,String pdfFile)
	{
		log.debug("TextToPDF :: convertTextFileToPdfFile :: Called.............");
		//File Created is Wrong
		boolean converted = true;
		try
		{
			log.debug("Conversion Finished...."+new Date().toString());
		} 
		catch(Exception ex)
	    {
			converted = false;
			ex.printStackTrace();
			
	    }
		log.debug("TextToPDF :: convertTextFileToPdfFile :: Finished.............");
		
		return converted;
	}
	//End Addition for GenUtils#90
}