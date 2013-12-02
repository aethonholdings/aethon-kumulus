package com.scannerapp.common;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import com.scannerapp.apps.application.ApplicationConstants;
import com.scannerapp.apps.utils.GeneralUtils;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kanhaiya Samariya
 * @author Shuchi Sharma
 * @version 1.0
 */
/*
 *======================Modification History======================
 *
 * Date      	Author            	Code #        	Description of Modifications
 * --------  	----------------  	--------  			--------------------------------------
 * 17/08/2005	Pradeep Sharma		SessionInfo#1   Show Version Info same as OMS(Issue#2974)
 * 30/08/2005	Ruchi Shah			SessionInfo#2   Showing all active products based on Selection Criteria parameter(Ref. Issue#2979 and #2980)
 * 10/01/2007   Jimit Joshi         SessionInfo#3   Set query time out for duplicate document(Issue #4022)		
 * 18-09-2007   Manish Vithlani     SessionInfo#4   Issue 4905(making Transporter name and address textbox enable or disable based on parameter)
 * 29-11-2007   Manish Vithlani     SessionInfo#5   Allow Unauthorized and End Dated Product to Load and also allow to change it's quantity.(Issue 4761).	
 * 20-11-2008   Manish Vithlani     SessionInfo#6   Add ID column in T_Application_Paramter table.(Unit Test Issue#2302)
 * 02-02-2009   Jiten Patel			SessionInfo#7   Purchase receipt\return greece changes	
 * 04-03-2009   Jiten Patel			SessionInfo#8   Unit Test#2489
 * 13-03-2009   Jiten Patel			SessionInfo#9   Unit Test#2813
 * 22-04-2009   Jiten Patel			SessionInfo#10  Client Test Issye#232
 * 19-06-2009   Manish Vithlani     SessionInfo#11  Unit Test Issue#3508
 * 15-07-2009   Jiten Patel			SessionInfo#12  Unit Test Issue#3698
 * 15/07/2009   Manish Vithlani     SessionInfo#13  Unit Test Issue#3696
 * 04/08/2009   Manish Vithlani     SessionInfo#14  Qa issue#9492
 * 09/10/2009   Jiten Patel		    SessionInfo#15  Unit Test issue#4259
 * 14/10/2009   Jiten Patel		    SessionInfo#16  QA issue#100037
 * 18/11/2009   Jiten Patel		    SessionInfo#17  Unit Test issue#4430
 * 24/11/2009   Jiten Patel		    SessionInfo#18  Client Test issue#805
 * 30/11/2009   Jiten Patel		    SessionInfo#19  Unit Test issue#4519
 * 06/01/2010   Jiten Patel		    SessionInfo#20  Unit Test issue#4713
 * 19/02/2010   Jiten Patel		    SessionInfo#21  Client Test issue#937
 * 23/03/2010   Jiten Patel		    SessionInfo#22  Client Test issue#989
 * 31/03/2010   Jiten Patel		    SessionInfo#23  Client Test issue#1033
 * 20/04/2010   Jiten Patel		    SessionInfo#24  Client Test issue#1108
 * 28/04/2010   Jiten Patel		    SessionInfo#25  Client Test issue#628
 * 26/05/2010   Manish Vithlani     SessionInfo#26  Client Test Change#1231
 * 29/05/2010   Jiten Patel		    SessionInfo#27  Allow multipack product in production receipt and return.
 * 29/06/2010   Jiten Patel		    SessionInfo#28  Unit Test issue#5683
 * 24/12/2010   Jiten Patel		    SessionInfo#29  Unit Test issue#12980
 * 30/12/2010   Manish Vithlani   	SessionInfo#30  Password
 * 25/01/2011   Jiten Patel		    SessionInfo#31  Client issue#1741
*/
public class SessionInfo extends java.lang.Object implements ApplicationConstants,Cloneable,Comparable,Serializable {
    private static SessionInfo sessionInfo;
    private static boolean forceLogout = false;
    
    
    public SessionInfo() {
        super();
        setUserId(ApplicationConstants.DEFAULT_USER);

    //setLanguage(ApplicationConstants.DEFAULT_LANGUAGE);
    }
    //<!-- Start SessionInfo#5-->
    private boolean productisauthorized=true;
    public final void setProductStatus(boolean a)
    {
    	this.productisauthorized=a;
    	
    }
    
    public final boolean getProductStatus()
    {
    	
    	return productisauthorized;
    }
    ////<!-- End SessionInfo#5-->
    private String userId = ApplicationConstants.DEFAULT_USER;
    public final void setUserId(String userId) {
        this.userId = userId;
    }
    public final String getUserId() {
        return userId;
    }
    
    //start SessionInfo#11
    private String userPwd = "";
    public final void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public final String getUserPwd() {
        return userPwd;
    }
    //end SessionInfo#11
    
    
    //Added for SessionInfo#18
    private String reasonCodeConstant;
    public final String getReasonCodeConstant()
    {
      return this.reasonCodeConstant;
    }
    public final void setReasonCodeConstant(String reasonCodeConstant)
    {
      this.reasonCodeConstant = reasonCodeConstant;
    }
    //finished for SessionInfo#18
    
    //start SessionInfo#13
    private ArrayList arlMiscellaneousDocumentdata;
    public ArrayList getMiscellaneousDocumentdata()
    {
    	return arlMiscellaneousDocumentdata;
    }
    public void setMiscellaneousDocumentdata(ArrayList arlMisdocdata)
    {
    	this.arlMiscellaneousDocumentdata = arlMisdocdata;
    }
    //end SessionInfo#11
    
    private String language = ApplicationConstants.DEFAULT_LANGUAGE;
    public final void setLanguage(String language) {
        this.language = language;
    }
    public String getLanguage() {
        return language;
    }
    private String languageCode = ApplicationConstants.DEFAULT_LANGUAGE_CODE;
    public final void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    public String getLanguageCode() {
        return languageCode;
    }
    private String version = "";
    public final void setVersion(String version) {
        this.version = version;
    }
    public final String getVersion() {
        return version;
    }
    //Start : SessionInfo#1 : Added for set and get System's Version Information
    private ArrayList aVersionInfo = new ArrayList();
    public final void setVersionInfo(ArrayList aVersionInfo) {
        this.aVersionInfo = aVersionInfo;
    }
    public final ArrayList getVersionInfo() {
        return aVersionInfo;
    }
    //End : SessionInfo#1
    private boolean isProductSelectionDone = false;
    public final void setProuductSelectionDone(boolean isProductSelectionDone) {
        this.isProductSelectionDone = isProductSelectionDone;
    }
    public final boolean isProductSelectionDone() {
        return isProductSelectionDone;
    }
    private String loginDateTime;
    public final void setLoginDateTime(String loginDateTime) {
        this.loginDateTime = loginDateTime == null ? "" : loginDateTime;
    }
    public final String getLoginDateTime() {
        return loginDateTime;
    }
    private String country = "";
    public final void setCountry(String country) {
        this.country = country;
    }
    public final String getCountry() {
        return this.country;
    }
    private String company = "";
    public final void setCompany(String company) {
        this.company = company;
    }
    public final String getCompany() {
        return this.company;
    }
    private String location = "";
    public final void setLocation(String location) {
        this.location = location;
    }
    public final String getLocation() {
        return this.location;
    }

    //Added for SessionInfo#12
    private String locationType = "";
    public final void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    public final String getLocationType() {
        return this.locationType;
    }
    //finished for SessionInfo#12
    private Hashtable minUOM;
    public final void setMinUOM(Hashtable minUOM) {
        this.minUOM = minUOM;
    }
    public final Hashtable getMinUOM() {
        return this.minUOM;
    }
    //Added for SessionInfo#24
    private Hashtable uomCodeDescGreek;
    public final void setUOMCodeDescGreek(Hashtable uomCodeDescGreek) {
        this.uomCodeDescGreek = uomCodeDescGreek;
    }
    public final Hashtable getUOMCodeDescGreek() {
        return this.uomCodeDescGreek;
    }
    //finished for SessionInfo#24
    private Hashtable uomCodeDesc;
    public final void setUOMCodeDesc(Hashtable uomCodeDesc) {
        this.uomCodeDesc = uomCodeDesc;
    }
    public final Hashtable getUOMCodeDesc() {
        return this.uomCodeDesc;
    }
    private Hashtable uomDescCode;
    public final void setUOMDescCode(Hashtable uomDescCode) {
        this.uomDescCode = uomDescCode;
    }
    public final Hashtable getUOMDescCode() {
        return this.uomDescCode;
    }
    private ArrayList UOMRelatedLists = new ArrayList();
    public final void setUOMRelatedLists(ArrayList UOMRelatedLists) {
        this.UOMRelatedLists = UOMRelatedLists;
    }
    public final ArrayList getUOMRelatedLists() {
        return this.UOMRelatedLists;
    }
    private String locationDesc = "Burgos Warehouse";
    public final void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }
    public final String getLocationDesc() {
        return this.locationDesc;
    }

    //Added by jiten for Gap35 documentSearch
    private boolean documentSearchFlag=false;
    public final void setDocumentSearchFlag(boolean documentSearchFlag){
    	this.documentSearchFlag=documentSearchFlag;
    }
    public final boolean getDocumentSearchFlag(){
    	return documentSearchFlag;
    }    
    //Finish by jiten for Gap35
    
    //Added by jiten for gap43 romania
    private String locCoreDestributor="0";
    public final void setLocCoreDestributor(ArrayList locationData,String location){    	
    	for(int i=0;i<locationData.size();i++){    		
    		if(((String)((ArrayList)locationData.get(i)).get(2)).equals(location)){
    			this.locCoreDestributor=((String)((ArrayList)locationData.get(i)).get(5));
    			break;
    		}
    	}    	    	
    }
    public final String getLocCoreDestributor(){
    	return locCoreDestributor;
    }    
    

    private Hashtable MappedLuCode= new Hashtable();
    public void setMappedLuCode(Hashtable MappedLuCode){
 	   this.MappedLuCode=MappedLuCode;
    }
    public Hashtable getMappedLuCode(){
 	   return MappedLuCode;
    }    
   //Finish by jiten for  gap43 romania
    
    /////////added by kunalt for freight
    private ArrayList freight = new ArrayList();
    public final void setFreight(ArrayList freight) {
        this.freight = freight;
    }
    public final ArrayList getFreight() {
        return this.freight;
    }

    /////////added by kunalt for freight ends
    private String lastInventoryDate = "19/11/2002";
    public final void setLastInventoryDate(String lastInvDate) {
        this.lastInventoryDate = GeneralUtils.convertBackEndDate(lastInvDate);
    }
    public final String getLastInventoryDate() {
        return this.lastInventoryDate;
    }
    private ArrayList locationList;
    public final void setLocationList(ArrayList locationList) {
        this.locationList = locationList;
    }
    public final ArrayList getLocationList() {
        return this.locationList;
    }


    private TreeMap productList;
    public final void setProductList(TreeMap productList) {
        this.productList = productList;
    }
    public final TreeMap getProductList() {
        return this.productList;
    }
    private ArrayList aProductList;
    public final void setAProductList(ArrayList aProductList) {
        this.aProductList = aProductList;
    }
    public final ArrayList getAProductList() {
        return this.aProductList;
    }
    private ArrayList aProductListRS;
    public final void setAProductListRS(ArrayList aProductList) {
        this.aProductListRS = aProductList;
    }
    public final ArrayList getAProductListRS() {
        return this.aProductListRS;
    }
    private ArrayList aProductListRRS;
    public final void setAProductListRRS(ArrayList aProductList) {
        this.aProductListRRS = aProductList;
    }
    public final ArrayList getAProductListRRS() {
        return this.aProductListRRS;
    }
    private ArrayList aProductListMisc;
    public final void setAProductListMisc(ArrayList aProductList) {
        this.aProductListMisc = aProductList;
    }
    public final ArrayList getAProductListMisc() {
        return this.aProductListMisc;
    }
    private ArrayList aProductListPhy;
    public final void setAProductListPhy(ArrayList aProductList) {
        this.aProductListPhy = aProductList;
    }
    public final ArrayList getAProductListPhy() {
        return this.aProductListPhy;
    }
    private ArrayList aProductListTO;
    ProductInfo tempproduct;
    public final void setAProductListTO(ArrayList aProductList) {
    	aProductListTO = new ArrayList(); 	
    	  for(int i=0;i<aProductList.size();i++)
    	  {
    		  /*tempproduct=(ProductInfo)aProductList.get(i);
    		  if(!tempproduct.getTypecode().equals("5"))
    		  {
    			  aProductListTO.add(aProductList.get(i));
    		  }*/
    		  //code commented by manish to also show multipack products in those transaction which are using this method to get products.
    		  aProductListTO.add(aProductList.get(i));
    	  }
    	//this.aProductListTO = aProductList;
    }
    public final ArrayList getAProductListTO() {
        return this.aProductListTO;
    }
    
  //added by manish for Unit Test Issue#1866.
    
    //Added for 
    private ArrayList aProductListPRC;  
    ProductInfo tempproductPRC;
    public final void setAProductListPRC(ArrayList aProductList) {
    	aProductListPRC = new ArrayList(); 	
    	  for(int i=0;i<aProductList.size();i++)
    	  {
    		  /* commented for SessionInfo#27
    		   * tempproductPRC=(ProductInfo)aProductList.get(i);
    		  if(!tempproductPRC.getTypecode().equals("5"))
    		  {*/
    			  aProductListPRC.add(aProductList.get(i));
    		  //}
    	  }
    	//this.aProductListTO = aProductList;
    }
    public final ArrayList getAProductListPRC() {
        return this.aProductListPRC;
    }
    //finished for 
    
    
    private ArrayList aProductListTo_current;
    
    
    public final void setAProductListTO_current(ArrayList aProductList)
     {
    	  aProductListTo_current = new ArrayList(); 	
    	  for(int i=0;i<aProductList.size();i++)
    	  {
    		  
    		  aProductListTo_current.add(aProductList.get(i));
    	  }
    	
    }
    public final ArrayList getAProductListTO_current()
    {
    	
    	return this.aProductListTo_current;
    }
    public final void Empty_aProductListTo_current()
    {
    	if(this.aProductListTo_current!=null)
    	{
    	this.aProductListTo_current.clear();
    	}
    }
    
    private ArrayList aProductListTo_final;
    public final void setAProductListTO_final(ArrayList aProductList)
    {
   	  aProductListTo_final = new ArrayList(); 	
   	  for(int i=0;i<aProductList.size();i++)
   	  {
   		  aProductListTo_final.add(aProductList.get(i));
   	  }
   	
   }
   public final ArrayList getAProductListTO_final()
   {
   	  return this.aProductListTo_final;
   }
   public final void Empty_aProductListTo_final()
   {
	    if(this.aProductListTo_final!=null)
   		{
	     this.aProductListTo_final.clear();
   		} 
   }
    
  //Finish by manish for Unit Test Issue#1866.
    
    //added by manish for Document date right
    private String cDocumentdateright ="";
    public void setDocumentdateright(String docdateright)
    {
    	cDocumentdateright=docdateright;
    }
    public String getDocumentdateright()
    {
    	return cDocumentdateright;
    }
    //finish by manish for document date right
    
  //added by manish for Document date right
    private String cAutoDocumentdateright ="";
    public void setAutoDocumentdateright(String docdateright)
    {
    	cAutoDocumentdateright=docdateright;
    }
    public String getAutoDocumentdateright()
    {
    	return cAutoDocumentdateright;
    }
    //finish by manish for document date right
    
  //Added for SessionInfo#20
    private String auto_allowed_no_of_days="";
    private String manual_allowed_no_of_days="";
    
    public String getAuto_allowed_no_of_days() {
		return auto_allowed_no_of_days;
	}

	public void setAuto_allowed_no_of_days(String auto_allowed_no_of_days) {
		this.auto_allowed_no_of_days = auto_allowed_no_of_days;
	}

	public String getManual_allowed_no_of_days() {
		return manual_allowed_no_of_days;
	}

	public void setManual_allowed_no_of_days(String manual_allowed_no_of_days) {
		this.manual_allowed_no_of_days = manual_allowed_no_of_days;
	}
    
    //finished for SessionInfo#20
	
    //added by manish for manual document date validation
   
    private String allowed_no_of_days="";
   
      public void setNumberofdays(String s)
    {
    	//allowed_no_of_days=s;//commented for SessionInfo#20
    	//Added for SessionInfo#20  
    	 StringTokenizer st= new StringTokenizer(s,":");
    	 setAuto_allowed_no_of_days(st.nextToken());
    	 setManual_allowed_no_of_days(st.nextToken());
    	//Finsihed for SessionInfo#20
    }
    public String getNumberofdays()
    {
    	return allowed_no_of_days;
    }
   
    
    //finish by manish for manual document date validation
    
    //added by manish for delivery note
    private Hashtable htbbasesdv;
    public void setBASESDV(Hashtable h)
    {
    	htbbasesdv= ((Hashtable)copy(h));
    }
    public Hashtable getBASESDV()
    {
    	return (Hashtable)copy(htbbasesdv);
    }
    //Finish by manish for delivery note
    
    //added by manish for Transaction codes
    private Hashtable htbTransactionCodes;
    public void setTransactionCodes(Hashtable htbtcode)
    {
    	if(this.htbTransactionCodes!=null)
    	{
    	if(!this.htbTransactionCodes.isEmpty())
    	{
    		this.htbTransactionCodes.clear();
    	}
    	}
    	this.htbTransactionCodes= ((Hashtable)copy(htbtcode));
    }
    public Hashtable getTransactionsCodes()
    {
    	return (Hashtable)copy(this.htbTransactionCodes);
    }
    
    private Hashtable htbFunctionalityKeys;
    public void setFunctionalitykeys(Hashtable htbtkeys)
    {
    	if(this.htbFunctionalityKeys!=null)
    	{
    	if(!this.htbFunctionalityKeys.isEmpty())
    	{
    		this.htbFunctionalityKeys.clear();
    	}
    	}
    	this.htbFunctionalityKeys= ((Hashtable)copy(htbtkeys));
    }
    public Hashtable getFunctionalitykeys()
    {
    	return (Hashtable)copy(this.htbFunctionalityKeys);
    }
    
    
    //Finish by manish for Transaction codes
    
    
    //Added by manish for new Greece requirement
    private ArrayList aMiscdocs= new ArrayList();
    public ArrayList GetMisellaneousdocs()
    {
    	return aMiscdocs;
    }
    public void SetMisellaneousdocs(ArrayList aMiscellaneousdocument)
    {
    	
    	if(aMiscdocs.size()>0)
    	{
    		aMiscdocs.clear();
    	}
    	this.aMiscdocs=aMiscellaneousdocument;
    }
    //Finish by manish for new Greece requirement
    
    //added by manish for gap18
    private ArrayList aProductListITF;
    ProductInfo tempproduct1;
    public final void setAProductListITF(ArrayList aProductList) {
    	this.aProductListITF = new ArrayList(); 	
    	  for(int i=0;i<aProductList.size();i++)
    	  {
    		  this.tempproduct1=(ProductInfo)aProductList.get(i);
    		  if(tempproduct1.getTypecode().equals("5"))
    		  {
    			  this.aProductListITF.add(copy(aProductList.get(i)));//changed for SessionInfo#16 
    		  }
    	  }
    	  //this.aProductListTO = aProductList;
    }
    public final ArrayList getAProductListITF() {
        return this.aProductListITF;
    }
    
    //start SessionInfo#26
    private ArrayList aProductListITF_PALLET;
    ProductInfo tempproduct2;
    public final void setAProductListITF_PALLET_PRODUCTS(ArrayList aProductList) {
    	this.aProductListITF_PALLET = new ArrayList(); 	
    	  for(int i=0;i<aProductList.size();i++)
    	  {
    		  this.tempproduct2=(ProductInfo)aProductList.get(i);
    		  if(tempproduct2.getTypecode().equals("5") || tempproduct2.getTypecode().equals("6") )
    		  {
    			  this.aProductListITF_PALLET.add(copy(aProductList.get(i))); 
    		  }
    	  }
    	      	
    }
    public final ArrayList getAProductListITF_PALLET_PRODUCTS() {
        return this.aProductListITF_PALLET;
    }
    //end SessionInfo#26
    
    public Hashtable packproducts;
    private Hashtable originalpackproducts;
    public Hashtable getPackProducts()
    {
    	return (Hashtable)copy(packproducts);
    }
    public Hashtable getoriginalpackproducts()
    {
    	return (Hashtable)copy(originalpackproducts);
    }
    public void setPackProducts(Hashtable htable)
    {
    	if(originalpackproducts==null ||originalpackproducts.isEmpty())
    	{
    		originalpackproducts=((Hashtable)copy(htable));
    	}
    	packproducts= ((Hashtable)copy(htable));
    	
    }
  //added by manish for unit test issue#1807
    public void EmptyOriginalPackProducts()
    {
    	originalpackproducts.clear();
    }
  //finish by manish for unit test issue#1807
    public Object copy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
    
    public Hashtable exist_packproducts;
    
    public final Hashtable getexistPackProducts()
    {
    	return (Hashtable)copy(exist_packproducts);
    }
    public final void setexistPackProducts(Hashtable hexisttable)
    {
    	if(exist_packproducts!=null)
    	{
    	if(!exist_packproducts.isEmpty())
    	{
    		exist_packproducts.clear();
    	}
    	}
    	exist_packproducts=((Hashtable)copy(hexisttable));
    	
    }
    public Hashtable hproductqtyok=new Hashtable();
    public final Hashtable getProductqtyok()
    {
    	return this.hproductqtyok;
    }
    public final void setProductqtyok(String key,boolean value)
    {
    	if(hproductqtyok.containsKey(key))
    	{
    		hproductqtyok.remove(new String(key));
    		hproductqtyok.put(new String(key),new Boolean(value));
    	}
    	else
    	{
    	hproductqtyok.put(new String(key),new Boolean(value));
    	}
    }
    
    public Hashtable requiredqty=new Hashtable();
    
    public final Hashtable getRequiredqty()
    {
    	return (Hashtable)this.requiredqty;
    }
    
    public final void setRequiredqty(String key,Hashtable hrequiredqty)
    {
    	if(requiredqty.containsKey(key))
    	{
    		requiredqty.remove(new String(key));
    		requiredqty.put(new String(key),new Hashtable((Hashtable)hrequiredqty.clone()));
    	}
    	else
    	{
    		requiredqty.put(new String(key),new Hashtable((Hashtable)hrequiredqty.clone()));
    	}
    	
    }
    
    
    //Finish by manish for gap18
    
    //added by manish for for qa issue#5956
    public final void RemoveRequiredqtyLucodefromRequiredqty(String key)
    {
    	if(requiredqty.containsKey(key))
    	{
    		requiredqty.remove(key);
    	}
    }
   //Finish by manish for qa issue#5956
    
    //added by manish for gap2 & gap4
    private boolean Itxselected=false;
    private boolean CoPackerSelected=false;
    private boolean StaleSelected=false;
    public final boolean isStaleSelected()
    {
    	return StaleSelected;
    }
    public final void setstaleselected(boolean value)
    {
    	this.StaleSelected=value;
    }
    public final boolean isItxSelected()
    {
    	return Itxselected;
    }
    public final boolean isCopackerSelected()
    {
    	return CoPackerSelected;
    }
    public final void setitxselected(boolean value)
    {
    	this.Itxselected=value;
    }
    public final void setCopackerselected(boolean value)
    {
    	this.CoPackerSelected=value;
    }
    //finish by manish for gap2 & gap4
    
    //Added for SessionInfo#22
    private boolean ExternalNumber=false;
    public final boolean isExternalNumber()
    {
    	return ExternalNumber;
    }
    public final void setExternalNumber(boolean value)
    {
    	this.ExternalNumber=value;
    }
    //finished for SessionInfo#22
    
    private ArrayList aProductListTI;
    public final void setAProductListTI(ArrayList aProductList) {
        this.aProductListTI = aProductList;
    }
    public final ArrayList getAProductListTI() {
        return this.aProductListTI;
    }
    //added for detail report 11/14/2003 10:04AM
    private ArrayList aReportProducts;
    public final void setAReportProducts(ArrayList aReportProducts) {
        /*aReportProducts = new ArrayList();
        for(int i=0;i<aReportProducts.size();i++) {
        		aReportProducts.add(aReportProducts.get(i));
        }*/
        this.aReportProducts = aReportProducts;
    }
    public final ArrayList getAReportProducts() {
        return this.aReportProducts;
    }
    //added for detail report 11/14/2003 10:04AM end
    private ArrayList aProductListCTO;
    public final void setAProductListCTO(ArrayList aProductList) {
        this.aProductListCTO = aProductList;
    }
    public final ArrayList getAProductListCTO() {
        return this.aProductListCTO;
    }
    private ArrayList aProductListCTI;
    public final void setAProductListCTI(ArrayList aProductList) {
        this.aProductListCTI = aProductList;
    }
    public final ArrayList getAProductListCTI() {
        return this.aProductListCTI;
    }
    private String connectionProc;
    public final void setConnectionProc(String connectionProc) {
        this.connectionProc = connectionProc;
    }
    public final String getconnectionProc() {
        return this.connectionProc;
    }
    private String timeOut;
    public final void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
    public final String getTimeOut() {
        return this.timeOut;
    }
    // Starts: SessionInfo#3
    private String sPrdTimeOut;
    public void setPrdTimeOut(String sPrdTimeOut) {
      this.sPrdTimeOut = sPrdTimeOut != null ? sPrdTimeOut.trim() : "";
    }
    public String getPrdTimeOut() {
        return this.sPrdTimeOut;
    }
    // Ends: SessionInfo#3
    private String delim;
    public final void setDelim(String delim) {
        this.delim = delim;
    }
    public final String getDelim() {
        return this.delim;
    }
    private ArrayList opmLocation;
    public final void setOpmLocation(ArrayList opmLocation) {
        this.opmLocation = opmLocation;
    }
    public final ArrayList getOpmLocation() {
        return this.opmLocation;
    }

    //Start SessionInfo#4
    private ArrayList arrTransporterRights;
    public ArrayList getTranporterRights()
    {
    	return this.arrTransporterRights;
    }
    
    public void setTranporterRights(ArrayList arrTransporterRights)
    {
    	this.arrTransporterRights = arrTransporterRights;    	
    }
    
    //End SessionInfo#4

///////////   added for Reason Code of TOA ///////////////////////////////////////////////
    private Vector reasonCode;
    public final Vector getReasonCode()
    {
      return this.reasonCode;
    }
    public final void setReasonCode(Vector reasonCode)
    {
      this.reasonCode = reasonCode;
    }
///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
/* added for Shifts       */
    
    private String _title;
    public final String getDialogTitle()
    {
      return this._title;
    }
    public final void setDialogTitle(String title)
    {
      this._title= title;
    }
    private Hashtable _hData;
    public final Hashtable getDialogCodeValue()
    {
      return this._hData;
    }
    public final void setDialogCodeValue(Hashtable hData)
    {
      this._hData = hData;
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public Object clone() throws CloneNotSupportedException {
        SessionInfo clonedObj = null;
        try {
            clonedObj = (SessionInfo)super.clone();
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clonedObj;
    }
    public final String toString() {
        return getUserId() + "-" + getLanguage();
    }
    public final boolean equals(Object obj) {
        if(obj instanceof SessionInfo) {
            return (getLanguage().trim().equals(((SessionInfo)obj).getLanguage().trim()) && getUserId().equals(((SessionInfo)obj).getUserId()) ? true : false);
        }
        else {
            return false;
        }
    }
    public final int compareTo(java.lang.Object obj) {
        if(obj instanceof SessionInfo) {
            return toString().compareTo(obj.toString());
        }
        else {
            return -1;
        }
    }
    public static SessionInfo getInstance() {
        if(sessionInfo == null) {
            sessionInfo = new SessionInfo();
        }
        return sessionInfo;
    }
    
    // Starts: SessionInfo#2
    private boolean bDisplayFlag;
    
    public final void setDisplayFlag(boolean bDisplayFlag) {
        this.bDisplayFlag = bDisplayFlag;
    }
    public final boolean getDisplayFlag() {
        return bDisplayFlag;
    }
    // Ends: SessionInfo#2
   
    //Added by jiten for gap23 Greece
   private boolean prodRecpSummFlag;
    
    public final void setProdRecpSummFlag(boolean prodRecpSummFlag) {
        this.prodRecpSummFlag = prodRecpSummFlag;
    }
    public final boolean getProdRecpSummFlag() {
        return prodRecpSummFlag;
    }
    
  //Added by jiten for getting country code
    private String romania_country_code="";
    private String greece_country_code="";
    
    private String romania_wh="";
    private String greece_wh="";
    
    private String romania_company_code="";
    private String greece_company_code="";
    
    private String currency="";//Added by jiten for costing
    
    private String man_puc_qty_days ="";//Added for SessionInfo#21
    private String supl_del_note_days  ="";//Added for SessionInfo#21
    private String misc_back_transaction_day="";//Added for SessionInfo#23
    
    private String tin_back_transaction_day="";//Added for SessionInfo#31
    
    private String legal_printer_check="";//Added for SessionInfo#25
    
    private String export_to_excel_limit="";//Added for SessionInfo#28
    
    public final void setCountryCode(Hashtable hcountryCode){
    	
    	this.romania_country_code=(String)hcountryCode.get("ROMANIA");
    	this.greece_country_code=(String)hcountryCode.get("GREECE");
    	
    	this.romania_wh=(String)hcountryCode.get("ROMANIA_WH");
    	this.greece_wh=(String)hcountryCode.get("GREECE_WH");
    	
    	this.romania_company_code=(String)hcountryCode.get("ROMANIA_CMP");
    	this.greece_company_code=(String)hcountryCode.get("GREECE_CMP");
    	
    	//start SessionInfo#6
    	//this.currency=(String)hcountryCode.get("CURRENCY");//Added by jiten for costing
    	//end SessionInfo#6
    	//Added for SessionInfo#7
    	this.rmn_pur_allow_extra_qty_price=(String)hcountryCode.get("RMN_PUR_ALLOW_EXTRA_QTY_PRICE");//changed for SessionInfo#9
    	this.grc_pur_allow_extra_qty_price=(String)hcountryCode.get("GRC_PUR_ALLOW_EXTRA_QTY_PRICE");//Added for SessionInfo#9
    	//finished for SessionInfo#7
    	
    	this.reasonCodeConstant=(String)hcountryCode.get("PALLET_CONSTANT");//Added for SessionInfo#18
    	
    	this.man_puc_qty_days=(String)hcountryCode.get("MAN_PUC_QTY_DAYS");//Added for SessionInfo#18
    	this.supl_del_note_days=(String)hcountryCode.get("SUPL_DEL_NOTE_DAYS");//Added for SessionInfo#18
    	
    	this.misc_back_transaction_day=(String)hcountryCode.get("MISC_BACK_TRANSACTION_DAYS");//Added for SessionInfo#23
    	
    	this.legal_printer_check=(String)hcountryCode.get("LEGAL_PRINTER_CHECK");//Added for SessionInfo#25
    	
    	this.export_to_excel_limit=(String)hcountryCode.get("EXPORT_TO_EXCEL_LIMIT");//Added for SessionInfo#28
    	
    	this.tin_back_transaction_day=(String)hcountryCode.get("TIN_BACK_TRANSACTION_DAYS");//Added for SessionInfo#31
    	
    }
    //Added by jiten for costing
    public final String getCurrency(){
 	   return this.currency;
    }
    //Finish by jiten for costing
    
   public final String getRomania_country_code(){
	   return this.romania_country_code;
   }
   
   public final String getGreece_country_code(){
	   return this.greece_country_code;
   }
   
   public final String getRomania_wh(){
	   return this.romania_wh;
   }
   
   public final String getGreece_wh(){
	   return this.greece_wh;
   }
   
   public final String getRomania_company_code(){
	   return this.romania_company_code;
   }
   
   public final String getGreece_company_code(){
	   return this.greece_company_code;
   }
  //finihs by jiten for getting country code  
   
  //Added by jiten for purchase receipt costing 
   private Hashtable hpurchaseReceiptCommon;
   public void sethpurchaseReceiptCommon(Hashtable h)
   {
	   hpurchaseReceiptCommon= h;
   }
   public Hashtable gethpurchaseReceiptCommon()
   {
   	return (Hashtable)hpurchaseReceiptCommon;
   }
      
   /*private String status="";
   
   public final String getStatus(){
 	   return this.status;
    }
   public final void setStatus(String status){
 	   this.status=status;
    }*/
  //finish by jiten for costing 
   
   //added by manish for Greece Physical Inventory Implementation
   private String greecePhysicalDocumentExist="";
   private String greecePhysicalDocumentNumber="";
   private String greecePhysicalDocumentYear="";
   private String greecePhysicalDocumentDate="";
   private String greeceLocationImplementationDate="";
   private String greeceLocationImplementationYear="";
   private String greeceLastSavedPhysicalDocumentExist="";
   
   
		public String getGreecePhysicalDocumentExist() {
			return greecePhysicalDocumentExist;
		}
		
		public void setGreecePhysicalDocumentExist(String greecePhysicalDocumentExist) {
			this.greecePhysicalDocumentExist = greecePhysicalDocumentExist;
		}
		
		public String getGreecePhysicalDocumentNumber() {
			return greecePhysicalDocumentNumber;
		}
		
		public void setGreecePhysicalDocumentNumber(String greecePhysicalDocumentNumber) {
			this.greecePhysicalDocumentNumber = greecePhysicalDocumentNumber;
		}
		
		public String getGreecePhysicalDocumentYear() {
			return greecePhysicalDocumentYear;
		}
		
		public void setGreecePhysicalDocumentYear(String greecePhysicalDocumentYear) {
			this.greecePhysicalDocumentYear = greecePhysicalDocumentYear;
		}
		
		public String getGreeceLocationImplementationDate() {
			return greeceLocationImplementationDate;
		}
		
		public void setGreeceLocationImplementationDate(
				String greeceLocationImplementationDate) {
			this.greeceLocationImplementationDate = greeceLocationImplementationDate;
		}
		
		public String getGreeceLocationImplementationYear() {
			return greeceLocationImplementationYear;
		}
		
		public void setGreeceLocationImplementationYear(
				String greeceLocationImplementationYear) {
			this.greeceLocationImplementationYear = greeceLocationImplementationYear;
		}
		
		public String getGreecePhysicalDocumentDate() {
			return greecePhysicalDocumentDate;
		}

		public void setGreecePhysicalDocumentDate(String greecePhysicalDocumentDate) {
			this.greecePhysicalDocumentDate = greecePhysicalDocumentDate;
		}

		public String getGreeceLastSavedPhysicalDocumentExist() {
			return greeceLastSavedPhysicalDocumentExist;
		}

		public void setGreeceLastSavedPhysicalDocumentExist(String greeceLastSavedPhysicalDocumentExist) {
			this.greeceLastSavedPhysicalDocumentExist = greeceLastSavedPhysicalDocumentExist;
		}
		
		
		
		
   
   //Finish by manish for Greece Physical Inventory Implementation
   
  //Added for SessionInfo#7	
  //changed for SessionInfo#9		
  String rmn_pur_allow_extra_qty_price="";
  
	public String getRmn_Pur_allow_extra_qty_price() {
		return rmn_pur_allow_extra_qty_price;
	}
	
	public void setPur_allow_extra_qty_price(String rmn_pur_allow_extra_qty_price) {
		this.rmn_pur_allow_extra_qty_price = rmn_pur_allow_extra_qty_price;
	}
  //finsihed for SessionInfo#7
   
	//Added for SessionInfo#9
	 String grc_pur_allow_extra_qty_price="";
	  
		public String getGrc_Pur_allow_extra_qty_price() {
			return grc_pur_allow_extra_qty_price;
		}
		
		public void setGrc_Pur_allow_extra_qty_price(String grc_pur_allow_extra_qty_price) {
			this.grc_pur_allow_extra_qty_price = grc_pur_allow_extra_qty_price;
		}
   //Finished for SessionInfo#9
   
   //Added for SessionInfo#10
		private ArrayList arlBrandLists = new ArrayList();
	    public final void setBrandLists(ArrayList arlBrandLists) {
	        this.arlBrandLists = arlBrandLists;
	    }
	    public final ArrayList getarlBrandLists() {
	        return this.arlBrandLists;
	    }		
  //finished fpr SessionInfo#10 
	    
	  //start SessionInfo#14
		  private boolean isInventoryTransformationGoingon=false;
		  public boolean isInventoryTransformationGoingon()
		  {
				return isInventoryTransformationGoingon;
		  }

		  public void setInventoryTransformationGoingon(boolean isInventoryTransformationGoingon)
		  {
				this.isInventoryTransformationGoingon = isInventoryTransformationGoingon;
		  }
		  //end SessionInfo#14
		  //Added for SessionInfo#15
		  String screen="";		
		public String getScreen() {
			return screen;
		}

		public void setScreen(String screen) {
			this.screen = screen;
		}
		  //finished for SessionInfo#15	  
		
		//Added for SessionInfo#17
		  private Hashtable voidDocReasonCode;
		    public final void setVoidDocReasonCode(Hashtable voidDocReasonCode) {
		        this.voidDocReasonCode = voidDocReasonCode;
		    }
		    public final Hashtable getVoidDocReasonCode() {
		        return this.voidDocReasonCode;
		    }
		//finsihed for SessionInfo#17
		    
		    private Hashtable dlvNoteReasonCode;
		    public final void setDlvNoteReasonCode(Hashtable dlvNoteReasonCode) {
		        this.dlvNoteReasonCode = dlvNoteReasonCode;
		    }
		    public final Hashtable getDlvNoteReasonCode() {
		        return this.dlvNoteReasonCode;
		    }
		    
		    //Added for SessionInfo#19
		    private ArrayList locationSeries = null;
		    private String selLocationSeries = "";
		    private String defLocationSeries = "";
		    public ArrayList getLocationSeries() {
		    	return locationSeries;
		    }

		    public void setLocationSeries(ArrayList locationSeries) {
		    	this.locationSeries = locationSeries;
		    }

		    public String getSelLocationSeries() {
		    	return selLocationSeries;
		    }

		    public void setSelLocationSeries(String selLocationSeries) {
		    	this.selLocationSeries = selLocationSeries;
		    }

			public String getDefLocationSeries() {
				return defLocationSeries;
			}

			public void setDefLocationSeries(String defLocationSeries) {
				this.defLocationSeries = defLocationSeries;
			}

			public String getMan_puc_qty_days() {
				return man_puc_qty_days;
			}

			public String getSupl_del_note_days() {
				return supl_del_note_days;
			}

			public String getMisc_back_transaction_day() {
				return misc_back_transaction_day;
			}
			public String getLegal_printer_check() {
				return legal_printer_check;
			}

			public String getExport_to_excel_limit() {
				return export_to_excel_limit;
			}
			
			public String getTin_back_transaction_day() {
				return tin_back_transaction_day;
			}
		    //finished for SessionInfo#19
			//added by jiten for Unit test Issue#6275
			/*private String fromStalelocationStale="";
			public String getFromStalelocationStale() {
				return fromStalelocationStale;
			}

			public void setFromStalelocationStale(String fromStalelocationStale) {
				this.fromStalelocationStale = fromStalelocationStale;
			}*/
			
			//Added for SessionInfo#29
           private String fromTransferOutScreenFlag="";
			public String getFromTransferOutScreenFlag() {
				return fromTransferOutScreenFlag;
			}
	
			public void setFromTransferOutScreenFlag(String fromTransferOutScreenFlag) {
				this.fromTransferOutScreenFlag = fromTransferOutScreenFlag;
			}
           
			private String fromTransferInScreenFlag="";
			public String getFromTransferInScreenFlag() {
				return fromTransferInScreenFlag;
			}

			public void setFromTransferInScreenFlag(String fromTransferInScreenFlag) {
				this.fromTransferInScreenFlag = fromTransferInScreenFlag;
			}
			
			//finished for SessionInfo#29
	//end by jiten for Unit test Issue#6275
			
			
			 // Start SessionInfo#30
		    
			private String sUserActive;
			private String sUserFirstTime;
			private String sFailure;
			private int iFailureCnt;
			private String FailureUserId;
			private String FailureUserCountry;
		   
		    
		    public final void setUserActivate(String sUserActive)
		    {
		        this.sUserActive = sUserActive;
		    }
		    public final String getUserActivate()
		    {
		        return sUserActive;
		    }
		    
		    public final void setUserFirstTime(String sUserFirstTime)
		    {
		        this.sUserFirstTime = sUserFirstTime;
		    }
		    public final String getUserFirstTime()
		    {
		        return sUserFirstTime;
		    } 
		    
		    public final void setFailureCount(String sFailure)
		    {
		        this.sFailure = sFailure;
		    }
		    public final String getFailureCount()
		    {
		        return sFailure;
		    } 
		    
		    public final void setTotalFailure(int iFailureCnt)
		    {
		        this.iFailureCnt = iFailureCnt;
		    }
		    public final int getTotalFailure()
		    {
		        return iFailureCnt;
		    } 
		    
		    public final void setFailureUserId(String FailureUserId)
		    {
		        this.FailureUserId = FailureUserId;
		    }
		    public final String getFailureUserId()
		    {
		        return FailureUserId;
		    } 
		    
		    public final void setFailureUserCountry(String FailureUserCountry) 
		    {
		        this.FailureUserCountry = FailureUserCountry;
		    }
		    public final String getFailureUserCountry()
		    {
		        return FailureUserCountry;
		    }

			public static boolean isForceLogout() {
				return forceLogout;
			}

			public static void setForceLogout(boolean forceLogout) {
				SessionInfo.forceLogout = forceLogout;
			} 
		    
		    // End SessionInfo#30
			
}
