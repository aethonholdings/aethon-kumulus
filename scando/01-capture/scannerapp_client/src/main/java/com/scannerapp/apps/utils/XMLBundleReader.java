package com.scannerapp.apps.utils ;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>Title: XMLParsing </p>
 * <p>Description: This code is for parsing the XML documnet</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Systems Plus Pvt. Ltd.</p>
 * @author Hardik Shah
 * @version 1.0
 */
public class XMLBundleReader
{
  private static Document doc ;
  private Element root ;
  /* Code Clean-up : Dhwanil */
//    private Hashtable childHashTable = null;
  /**/
  private static String rootElement = "Application-IM" ;
  public XMLBundleReader ( String xmlFilePath )
  {
    try
    {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance () ;
      docFactory.setValidating ( false ) ;
      DocumentBuilder db = docFactory.newDocumentBuilder () ;
      File xml = new File ( xmlFilePath ) ;
      doc = db.parse ( xml ) ;
      root = doc.getDocumentElement () ;
    }
    catch ( ParserConfigurationException pce )
    {
      pce.printStackTrace () ;
    }
    catch ( SAXException se )
    {
      se.printStackTrace () ;
    }
    catch ( IOException ioe )
    {
      ioe.printStackTrace () ;
    }
  }

  public Element getRootElement ()
  {
    return root ;
  }

  public Document getDocument ()
  {
    return doc ;
  }

  public NodeList findChildNodes ( Node parent )
  {
    NodeList children = parent.getChildNodes () ;
    if ( children != null )
    {
      return children ;
    }
    else
    {
      return null ;
    }
  }

  public String findObjectValue ( String ModuleName , String ScreenName ,
                                  String key , String selectedLanguage )
  {

    String keyValue = null ;
    try
    {
      NodeList listOfParents = getDocument ().getElementsByTagName (
          rootElement ) ;
//            int totalParents = listOfParents.getLength();
      for ( int s = 0 , j = listOfParents.getLength () ; s < j ; s++ )
      {
        Node RootNode = listOfParents.item ( s ) ;
        if ( RootNode.getNodeType () == Node.ELEMENT_NODE )
        {
          Element rootElement = ( Element ) RootNode ;

          //------- Fetch the requested Module Element Tag from File
          NodeList firstModuleList = rootElement.getElementsByTagName (
              ModuleName.trim () ) ;
          Element firstModuleElement = ( Element ) firstModuleList.item ( 0 ) ;

          //------- Fetch the requested Screen Element Tag from File
          NodeList firstScreenList = firstModuleElement.getElementsByTagName (
              ScreenName.trim () ) ;
          Element firstScreenElement = ( Element ) firstScreenList.item ( 0 ) ;

          //------- Fetch KEYVALUE child node Element Tag from File for Module-Screen
          NodeList objectList = firstScreenElement.getElementsByTagName ( key.
              trim () ) ;
          Element firstScreenChildElement = ( Element ) objectList.item ( 0 ) ;
          Attr Language = firstScreenChildElement.getAttributeNode (
              selectedLanguage ) ;

          keyValue = Language.getValue () ;
        } //end of if
      }
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
    if ( keyValue != null )
    {
      return keyValue ;
    }
    else
    {
      return " " ;
    }
  }

  public String findObjectValue ( String key )
  {

    //String keyValue = childHashTable.get(new String(key));
    return " " ;
  }

  public Vector findModuleList ()
  {
    Vector moduleVector = new Vector () ;
    try
    {
      NodeList listOfParents = getDocument ().getElementsByTagName (
          rootElement ) ;
      Element ModuleElement = ( Element ) listOfParents.item ( 0 ) ;
      NodeList ModuleList = ModuleElement.getChildNodes () ;
      for ( int s = 0 ; s < ModuleList.getLength () ; s++ )
      {
        Node ModuleNode = ModuleList.item ( s ) ;
        if ( ModuleNode.getNodeType () == Node.ELEMENT_NODE )
        {
          Element ModuleChlidElement = ( Element ) ModuleNode ;
          moduleVector.add ( ModuleChlidElement.getTagName () ) ;
        } //end of if
      } //end of for
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
    return moduleVector ;
  }

  public Vector findScreenList ( String ModuleName )
  {
    Vector screenVector = new Vector () ;
    try
    {
      NodeList listOfParents = getDocument ().getElementsByTagName (
          rootElement ) ;
      for ( int s = 0 ; s < listOfParents.getLength () ; s++ )
      {
        Node RootNode = listOfParents.item ( s ) ;
        if ( RootNode.getNodeType () == Node.ELEMENT_NODE )
        {
          Element rootElement = ( Element ) RootNode ;

          //------- Fetch the requested Module Element Tag from File
          NodeList firstModuleList = rootElement.getElementsByTagName (
              ModuleName.trim () ) ;
          Element firstModuleElement = ( Element ) firstModuleList.item ( 0 ) ;
          NodeList firstScreenList = firstModuleElement.getChildNodes () ;
//                    int totalScreens = firstScreenList.getLength();

          //------- Fetch the requested Screen Element Tag from File
          for ( int c = 0 ; c < firstScreenList.getLength () ; c++ )
          {
            Node firstScreenChildNode = firstScreenList.item ( c ) ;
            if ( firstScreenChildNode.getNodeType () == Node.ELEMENT_NODE )
            {

              Element firstScreenChildElement = ( Element )
                  firstScreenChildNode ;

              screenVector.add ( firstScreenChildElement.getTagName () ) ;
            }
          } //end of for
        } //end of if
      }
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
    return screenVector ;
  }

  //    public Hashtable getObjectKeyValue(String ModuleName,String ScreenName,String selectedLanguage) throws IMServerException{
  public Hashtable getObjectKeyValue ( String ModuleName , String ScreenName ,
                                       String selectedLanguage )
  {
    Hashtable childHashTable = new Hashtable () ;
//        if(ModuleName == null || ScreenName == null || selectedLanguage == null) {

    //throw new IMServerException("Parameters should not be blank");
//        }
    selectedLanguage = selectedLanguage.toLowerCase () ;
    try
    {
      NodeList listOfParents = getDocument ().getElementsByTagName (
          rootElement ) ;
      for ( int s = 0 ; s < listOfParents.getLength () ; s++ )
      {
        Node RootNode = listOfParents.item ( s ) ;
        if ( RootNode.getNodeType () == Node.ELEMENT_NODE )
        {
          Element rootElement = ( Element ) RootNode ;

          //------- Fetch the requested Module Element Tag from File
          NodeList firstModuleList = rootElement.getElementsByTagName (
              ModuleName.trim () ) ;
          Element firstModuleElement = ( Element ) firstModuleList.item ( 0 ) ;

          //------- Fetch the requested Screen Element Tag from File
          NodeList firstScreenList = firstModuleElement.getElementsByTagName (
              ScreenName.trim () ) ;
          Element firstScreenElement = ( Element ) firstScreenList.item ( 0 ) ;

          //------- Fetch every child node Element Tag from File for Module-Screen
          NodeList objectList = firstScreenElement.getChildNodes () ;

          //------- Fetch Value from each object
          for ( int cs = 0 , ts = objectList.getLength () ; cs < ts ; cs++ )
          {
            Node firstScreenChildNode = objectList.item ( cs ) ;
            if ( firstScreenChildNode.getNodeType () == Node.ELEMENT_NODE )
            {

              Element firstScreenChildElement = ( Element )
                  firstScreenChildNode ;
              Attr Language = firstScreenChildElement.getAttributeNode (
                  selectedLanguage ) ;

              childHashTable.put ( firstScreenChildElement.getTagName () ,
                                   Language.getValue () ) ;
            } //end of if
          } //end of for
        } //end of if
      }
    }
    catch ( Exception e )
    {
      e.printStackTrace () ;
    }
    return childHashTable ;
  } //end of Method
} //end of class
