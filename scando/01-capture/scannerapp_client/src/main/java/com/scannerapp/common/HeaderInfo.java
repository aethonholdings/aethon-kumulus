package com.scannerapp.common;
import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Shuchi Sharma
 * @version 1.0
 */
/*======================Modification History======================
*
* Date            ID             Code#         Issue        Description of Modifications
* ----------  -------------  	--------      -------       -----------------------------
*
* 16/09/2005   Ruchi Shah       HInfo#1        771          For setting Reference Number Value, a new Field added
* 07/05/2008   Jiten Patel      HInfo#2                     For gap2 freezing doc setting delete flag 
* 07/11/2008   Manish Vithlani  HInfo#3                     Document Search screen take more time while fetching the records (qa issue#6448)
* 30/01/2009   Manish Vithlani  HInfo#4                     If user takes Print of the Physical Inventory document, it does not show the Physical Quantity and its Price in the report for the product as attached. 
* 17/02/2009   Jiten Patel      HInfo#5                     Added for Period closing Greece changes(IS_US_BOOK)
* 22/04/2009   Jiten Patel      HInfo#6                     Client Test Issue#225
* 16/07/2009   Jiten Patel      HInfo#7                     Unit Test Issue#3700
* 16/07/2009   Jiten Patel      HInfo#8                     Unit Test Issue#3701
* 06/08/2009   Manish Vithlani  HInfo#9                     Physical Inventory Change(Client Test Change#(609,610,611,612,613,614)
* 12/11/2009   Jiten Patel      HInfo#10                     Unit Test Issue#4375
* 1/12/2009   Jiten Patel       HInfo#11                     Unit Test Issue#4519
* 04/03/2010   Jiten Patel       HInfo#12                    Client Issue#933,934
* 30/04/2010   Jiten Patel       HInfo#13                    Client Issue#954
* 04/06/2010   Jiten Patel       HInfo#14                    Client Issue#1295
* 27/12/2010   Shraddha Panchamiya	HInfo#15				 Client Issue#1628
* 31/10/2011   Shalin Shah		HInfo#16				 	Client Issue#359 - Addition of two columns (Invoice Number & Vendor Location) while searching CRJC/DRJC document.
*/

public class HeaderInfo implements Cloneable,Comparable,Serializable {
    private String documentNo = "";
    private String documentDate = "";
    private String sSystem = "";
    private String documentType = "";
    private String documentSubType = "";
    private String sourceCtry = "";
    private String sourceCompany = "";
    private String sourceLocation = "";
    private String destinyCtry = "";
    private String destinyCompany = "";
    private String destinyLocation = "";
    private String location = "";
    private String shipmentStatus = "";
    private String shipmentDate = "";
    private String shift = "";
    private String copacker = "";
    private String recvDate = "";
    private String sourceDesc = "";
    private String transaction = "";
    private String invClosed = "";
    private String stime ="";
    private String comment = "";
    private String languageCode = "";
    private String connProc = "";
    private String tranStatus = "";
    private String delim = "";
    /*added by Ruchi*/
    private String createdate = "";
    private String createtime= "";
    private String createuser = "";
    private String updatedate = "";
    private String updatetime = "";
    private String updateuser = "";
    /*ends*/
    // Starts: HInfo#1
    private String refDocumentNumber = "";
    // Ends: HInfo#1   
    
    // Starts: HInfo#2
    private String deleteflag = "";
    // Ends: HInfo#2
    
    //Added by jiten for Costing
    private String recvTime="";
    private String poNumber="";
    private String invoiceDate="";
    private String currency="";
    private double conversionRate;
    private String expectedDocNo="";     
    private String documentReviewflag="";
    private double transcationCost;
    private double totalCost;
    //finish by jiten for costing
    
    //Added by jiten 
    //changed for HInfo#6
    private String PeriodNo="";
	private String PeriodYear="";
	private String PeriodStartDate="";
	private String PeriodEndDate="";
	private String LastClosingPeriod="";
	private String LastClosingYear="";
	private String isInventoryCloseFlag="";
	private String periodClosingDate= "";
	private String periodClosingTime= "";
    //finish by jiten 
	
	//start HInfo#3
	 private String documentTypeDescription = "";
	 private String StatusKey ="";
	 //end HInfo#3
    
	 //start HInfo#4
	 private String RomaniaCountryCode="";
	 private String RomaniaCompanyCode="";
	 private String GreeceCountryCode="";
	 private String GreeceCompanyCode="";
	 
	 //end HInfo#4
	 
	//Start HInfo#5
	 private String isUsBook="";
	//end HInfo#5
	 
	 private String TruckPlateNo="";//Added for HInfo#7
	 
	 private String DestinationRoute="";//Added for HInfo#8
	 
	 //start HInfo#9
	 private String greece_PHY_UOM="";
	 //end HInfo#9
	 //Added for HInfo#10
	 private double weight=0.0; 
	 private double printWeight=0.0;
	 private String reasonCode="";
	 //finished for HInfo#10
	 
	 
	 //Added for HInfo#12
	 private String qty_doc_number="";
	 private String accrual_doc_number="";
	 private String invoice_doc_number="";	 
	 //finsiehd for HInfo#12
	 
	 //Added for HInfo#13
	 private String void_tin_fun_key="";
         
         private String Concurrncy_update_date_time="";//Added for HInfo#14

	 private boolean finalized_Opening_Qty = false;
	 private String costFileName="";
	 
	 private String phy_Print_UOM="";//added for HInfo#15
	 
	 private String vendorLocation = "";	//ADDED By SHALIN SHAH For HInfo#16 --- CLIENT ISSUE 359
	 
	 
	 
	 public String getPhy_Print_UOM() {
		return phy_Print_UOM;
	}
	public void setPhy_Print_UOM(String phy_Print_UOM) {
		this.phy_Print_UOM = phy_Print_UOM;
	}
	public String getCostFileName() {
		return costFileName;
	}
	public void setCostFileName(String costFileName) {
		this.costFileName = costFileName;
	}
	public boolean isFinalized_Opening_Qty() {
		return finalized_Opening_Qty;
	}
	public void setFinalized_Opening_Qty(boolean finalizedOpeningQty) {
		finalized_Opening_Qty = finalizedOpeningQty;
	}
	private String locationSeries=SessionInfo.getInstance().getSelLocationSeries(); //Added for HInfo#11
	 
    public HeaderInfo() {
        super();
    }
    public String getDocumentNo() {
    	   return this.documentNo;
     }
    public void setRecvDate(String recvDate) {
        this.recvDate = recvDate != null ? recvDate.trim() : "";

    //this.recvDate = recvDate.trim();
    }
    public String getInvClosed() {
        return this.invClosed;
    }
    public void setInvClosed(String invClosed) {
        this.invClosed = invClosed != null ? invClosed.trim() : "";
    }
    public String getRecvDate() {
        return this.recvDate;
    }
    public String getDocumentDate() {
        return this.documentDate;
    }
    public void setDocumentDate(String date) {
        this.documentDate = date != null ? date.trim() : "";
    }
    public String getSSystem() {
        return this.sSystem;
    }
    public void setSSystem(String sSystem) {
        this.sSystem = sSystem != null ? sSystem.trim() : "";
    }
    public String getDelim() {
        return this.delim;
    }
    public void setDelim(String delim) {
        this.delim = delim != null ? delim.trim() : "";
    }
    public String getDocumentType() {
        return this.documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType != null ? documentType.trim() : "";
    }
    public String getDocumentSubType() {
        return this.documentSubType;
    }
    
    //start HInfo#3
    public void setDocumentTypeDescription(String documentTypedesc) {
        this.documentTypeDescription = documentTypedesc != null ? documentTypedesc.trim() : "";
    }
    public String getDocumentTypeDescription() {
        return this.documentTypeDescription;
    }
    
    public void setStatusKey(String statuskey)
    {
        this.StatusKey = statuskey != null ? statuskey.trim() : "";
    }
    public String getStatusKey()
    {
        return this.StatusKey;
    }
    
    //end HInfo#3
    
    public void setDocumentSubType(String documentSubType) {
        this.documentSubType = documentSubType != null ? documentSubType.trim() : "";

    //this.documentSubType = documentSubType;
    }

    public void setSourceDesc(String sourceDesc) {
      this.sourceDesc = sourceDesc != null ? sourceDesc.trim() : "";
    }
    public String getSourceDesc() {
        return this.sourceDesc;
    }
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo != null ? documentNo.trim() : "";
    }
    public String getSourceCountry() {
        return this.sourceCtry;
    }
    public void setSourceCountry(String sourceCtry) {
        this.sourceCtry = sourceCtry != null ? sourceCtry.trim() : "";
    }
    public String getSourceCompany() {
        return this.sourceCompany;
    }
    public void setSourceCompany(String sourceCompany) {
        this.sourceCompany = sourceCompany != null ? sourceCompany.trim() : "";
    }
    public String getSourceLocation() {
        return this.sourceLocation;
    }
    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation != null ? sourceLocation.trim() : "";
    }
    public String getDestinyCountry() {
        return this.destinyCtry;
    }
    public void setDestinyCountry(String destinyCtry) {
        this.destinyCtry = destinyCtry != null ? destinyCtry.trim() : "";
    }
    public String getDestinyCompany() {
        return this.destinyCompany;
    }
    public void setDestinyCompany(String destinyCompany) {
        this.destinyCompany = destinyCompany != null ? destinyCompany.trim() : "";
    }
    public String getDestinyLocation() {
        return this.destinyLocation;
    }
    public void setDestinyLocation(String destinyLocation) {
        this.destinyLocation = destinyLocation != null ? destinyLocation.trim() : "";
    }
    public String getShipmentDate() {
        return this.shipmentDate;
    }
    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate != null ? shipmentDate.trim() : "";
    }
    public String getShipmentStatus() {
        return this.shipmentStatus;
    }
    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus != null ? shipmentStatus.trim() : "";
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location != null ? location.trim() : "";
    }
    public String getShift() {
        return this.shift;
    }
    public void setShift(String shift) {
        this.shift = shift != null ? shift.trim() : "";
    }
    public String getCopacker() {
        return this.copacker;
    }
    public void setCopacker(String copacker) {
        this.copacker = copacker != null ? copacker.trim() : "";
    }
    public String getTransaction() {
        return this.transaction;
    }
    public void setTransaction(String transaction) {
        this.transaction = transaction != null ? transaction.trim() : "";
    }
    public void setStime(String stime) {
      this.stime = stime != null ? stime.trim() : "";
    }
    public String getStime() {
        return this.stime;
    }
    public void setComment(String comment) {
      this.comment = comment != null ? comment.trim() : "";
    }
    public String getComment() {
        return this.comment;
    }
    public void setLanguageCode(String languageCode) {
      this.languageCode = languageCode != null ? languageCode.trim() : "";
    }
    public String getLanguageCode() {
        return this.languageCode;
    }
    public void setConnProc(String connProc) {
      this.connProc = connProc != null ? connProc.trim() : "";
    }
    public String getConnProc() {
        return this.connProc;
    }
    private Double freight = new Double(0);
    public void setFreight(String freight) {
      this.freight = new Double(NumberConverter.getStringToDouble(freight));
//          != null ? Double.valueOf(freight) : null;
    }


    public Double getFreight() {
      return this.freight;
    }
    private String carrier = new String();
    public void setCarrier(String carrier) {
      this.carrier = carrier != null ? carrier.trim() : "";
    }
    public String getCarrier() {
      return this.carrier;
    }

    public Object clone() throws CloneNotSupportedException {
        HeaderInfo clonedObj = null;
        try {
            clonedObj = (HeaderInfo)super.clone();
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clonedObj;
    }
    public final String toString() {
        return getSSystem() + "-" + getDestinyCompany() + "-" + getDestinyCountry() + "-" + getDestinyLocation() + "-" + getDocumentNo() + "-" + getShipmentDate() + "-" + getSourceCompany() + "-" + getSourceCountry() + "-" + getSourceLocation() + "-" + getLocation() + "-" + getShipmentStatus() + "-" + getShift() + "-" + getCopacker() + "-" + getDocumentType() + "-" + getDocumentSubType() + "-" + getStime() + "-" + getComment();
    	
    }
    public final boolean equals(Object obj) {
        if(obj instanceof HeaderInfo) {
            return (getDestinyCompany().equals(((HeaderInfo)obj).getDestinyCompany()) && getSSystem().equals(((HeaderInfo)obj).getDestinyCountry()) && getDestinyCountry().equals(((HeaderInfo)obj).getDestinyCountry()) && getDestinyLocation().equals(((HeaderInfo)obj).getDestinyLocation()) && getDocumentNo().equals(((HeaderInfo)obj).getDocumentNo()) && getShipmentDate().equals(((HeaderInfo)obj).getShipmentDate()) && getSourceCompany().equals(((HeaderInfo)obj).getSourceCompany()) && getSourceCountry().equals(((HeaderInfo)obj).getSourceCountry()) && getSourceLocation().equals(((HeaderInfo)obj).getSourceLocation()) && getLocation().equals(((HeaderInfo)obj).getLocation()) && getShipmentStatus().equals(((HeaderInfo)obj).getShipmentStatus()) && getShift().equals(((HeaderInfo)obj).getShift()) && getCopacker().equals(((HeaderInfo)obj).getCopacker()) && getDocumentType().equals(((HeaderInfo)obj).getDocumentType()) && getDocumentSubType().equals(((HeaderInfo)obj).getDocumentSubType()) ? true : false);
        }
        else {
            return false;
        }
    }
    public final int compareTo(java.lang.Object obj) {
        if(obj instanceof HeaderInfo) {
            return toString().compareTo(obj.toString());
        }
        else {
            return -1;
        }
    }
    public String getTranStatus() {
        return this.tranStatus;
    }
    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus != null ? tranStatus.trim() : "";
    }
    //////////////////////////////////////////////////////////
    /*
     ================================================
     Date        : 11.04.2004
     Description : Shift No Added to this Object
     By Zubair 'Å°r°c°h Å°ñ°g°ë°l' Saiyed
     ================================================
     */
    private int shift_No ;

    /**
     *  Returns Shift no of this Object
     * @return int
     */
    public final int getShiftNo ()
    {
      return this.shift_No ;
    }

    /**
     * set Shift No to this Object
     * @param _shiftNo int
     */
    public final void setShiftNo ( int _shiftNo)
    {
      this.shift_No = _shiftNo ;
    }
    //////////////////////////////////////////////////////////

    /*added by ruchi*/
   public void setCreateDate(String createdate) {
        this.createdate = createdate;
    }
    public String getCreateDate() {
        return this.createdate;
    }

       public void setCreateTime(String createtime) {
        this.createtime = createtime;
    }
    public String getCreateTime() {
        return this.createtime;
    }

       public void setCreateUser(String createuser) {
        this.createuser = createuser;
    }
    public String getCreateUser() {
        return this.createuser;
    }

       public void setUpdateDate(String updatedate) {
        this.updatedate = updatedate;
    }
    public String getUpdateDate() {
        return this.updatedate;
    }

       public void setUpdateTime(String updatetime) {
        this.updatetime = updatetime;
    }
    public String getUpdateTime() {
        return this.updatetime;
    }

       public void setUpdateUser(String updateuser) {
        this.updateuser = updateuser;
    }
    public String getUpdateUser() {
        return this.updateuser;
    }

    // Starts: HInfo#1
    public String getRefDocumentNumber() {
      return refDocumentNumber;
    }
    public void setRefDocumentNumber(String refDocumentNumber) {
      this.refDocumentNumber = refDocumentNumber;
    }
    // Ends: HInfo#1
    
    // Starts: HInfo#2
    public String getDeleteFlag(){
      return deleteflag;
    }
    
    public void setDeleteFlag(String deleteflag){
    	this.deleteflag=deleteflag;
    }    
    // Ends: HInfo#2
    
    //Added by jiten for costing
    public String getRecvTime() {
        return recvTime;
    }
    public void setRecvTime(String receivedTime) {
        this.recvTime = receivedTime != null ? receivedTime.trim() : "";
    }
    
    public String getPoNumber() {
        return poNumber;
    }
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber!= null ? poNumber.trim() : "";
    }
    
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate= invoiceDate != null ? invoiceDate.trim() : "";
    }
    
    public String getcurrency() {
        return currency;
    }
    public void setcurrency(String currency) {
        this.currency= currency != null ? currency.trim() : "";
    }
    public double getConversionRate() {
        return conversionRate;
    }
    public void setConversionRate(double conversionRate) {
        this.conversionRate= conversionRate; 
    }
      
    public String getExpectedDocNo() {
        return expectedDocNo;
    }
    public void setExpectedDocNo(String expectedDocNo) {
        this.expectedDocNo= expectedDocNo != null ? expectedDocNo.trim() : "";
    }
    
    public String getDocumentReviewflag() {
        return documentReviewflag;
    }
    public void setDocumentReviewflag(String documentReviewflag) {
        this.documentReviewflag= documentReviewflag != null ? documentReviewflag.trim() : "";
    }
    
    public double getTranscationCost() {
        return transcationCost;
    }
    public void setTranscationCost(double transcationCost) {
        this.transcationCost= transcationCost;
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost= totalCost ;
    }
    //finish by jiten for costing

    //Added by jiten for period Closing
    
    //Added by jiten
    
	public String getPeriodNo() {
		return PeriodNo;
	}

	public void setPeriodNo(String periodNo) {
		PeriodNo = periodNo;
	}

	public String getPeriodYear() {
		return PeriodYear;
	}

	public void setPeriodYear(String periodYear) {
		PeriodYear = periodYear;
	}

	public String getPeriodStartDate() {
		return PeriodStartDate;
	}

	public void setPeriodStartDate(String periodStartDate) {
		PeriodStartDate = periodStartDate;
	}

	public String getPeriodEndDate() {
		return PeriodEndDate;
	}

	public void setPeriodEndDate(String periodEndDate) {
		PeriodEndDate = periodEndDate;
	}

	public String getLastClosingPeriod() {
		return LastClosingPeriod;
	}

	public void setLastClosingPeriod(String lastClosingPeriod) {
		LastClosingPeriod = lastClosingPeriod;
	}

	public String getLastClosingYear() {
		return LastClosingYear;
	}

	public void setLastClosingYear(String lastClosingYear) {
		LastClosingYear = lastClosingYear;
	}

	public String getIsInventoryCloseFlag() {
		return isInventoryCloseFlag;
	}

	public void setIsInventoryCloseFlag(String isInventoryCloseFlag) {
		this.isInventoryCloseFlag = isInventoryCloseFlag;
	}
	
	public String getPeriodClosingDate() {
		return periodClosingDate;
	}

	public void setPeriodClosingDate(String periodClosingDate) {
		this.periodClosingDate = periodClosingDate;
	}

	public String getPeriodClosingTime() {
		return periodClosingTime;
	}

	public void setPeriodClosingTime(String periodClosingTime) {
		this.periodClosingTime = periodClosingTime;
	}
    //finish by jiten 

	//start HInfo#4
	
	public String getRomaniaCountryCode() {
		return RomaniaCountryCode;
	}
	public void setRomaniaCountryCode(String romaniaCountryCode) {
		RomaniaCountryCode = romaniaCountryCode;
	}
	public String getRomaniaCompanyCode() {
		return RomaniaCompanyCode;
	}
	public void setRomaniaCompanyCode(String romaniaCompanyCode) {
		RomaniaCompanyCode = romaniaCompanyCode;
	}
	public String getGreeceCountryCode() {
		return GreeceCountryCode;
	}
	public void setGreeceCountryCode(String greeceCountryCode) {
		GreeceCountryCode = greeceCountryCode;
	}
	public String getGreeceCompanyCode() {
		return GreeceCompanyCode;
	}
	public void setGreeceCompanyCode(String greeceCompanyCode) {
		GreeceCompanyCode = greeceCompanyCode;
	}
	// End HInfo#4
	//Start HInfo#5
	public String getIsUsBook() {
		return isUsBook;
	}
	public void setIsUsBook(String isUsBook) {
		this.isUsBook = isUsBook;
	}
    //End HInfo#5
	public String getTruckPlateNo() {
		return TruckPlateNo;
	}
	public void setTruckPlateNo(String truckPlateNo) {
		TruckPlateNo = truckPlateNo;
	}
	public String getDestinationRoute() {
		return DestinationRoute;
	}
	public void setDestinationRoute(String destinationRoute) {
		DestinationRoute = destinationRoute;
	}
	//start HInfo#9
	public String getGreece_PHY_UOM() {
		return greece_PHY_UOM;
	}
	public void setGreece_PHY_UOM(String greece_PHY_UOM) {
		this.greece_PHY_UOM = greece_PHY_UOM;
	}
	//end HInfo#9
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}	
	public double getPrintWeight() {
		return printWeight;
	}
	public void setPrintWeight(double printWeight) {
		this.printWeight = printWeight;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	public String getLocationSeries() {
		return locationSeries;
	}
	public void setLocationSeries(String locationSeries) {
		this.locationSeries = locationSeries;
	}
	public String getQty_doc_number() {
		return qty_doc_number;
	}
	public void setQty_doc_number(String qty_doc_number) {
		this.qty_doc_number = qty_doc_number;
	}
	public String getAccrual_doc_number() {
		return accrual_doc_number;
	}
	public void setAccrual_doc_number(String accrual_doc_number) {
		this.accrual_doc_number = accrual_doc_number;
	}
	public String getInvoice_doc_number() {
		return invoice_doc_number;
	}
	public void setInvoice_doc_number(String invoice_doc_number) {
		this.invoice_doc_number = invoice_doc_number;
	}
	public String getVoid_tin_fun_key() {
		return void_tin_fun_key;
	}
	public void setVoid_tin_fun_key(String void_tin_fun_key) {
		this.void_tin_fun_key = void_tin_fun_key;
	}
	public String getConcurrncy_update_date_time() {
		return Concurrncy_update_date_time;
	}
	public void setConcurrncy_update_date_time(String concurrncy_update_date_time) {
		Concurrncy_update_date_time = concurrncy_update_date_time;
	}
	
	
	
//ADDED By SHALIN SHAH For HInfo#16 --- CLIENT ISSUE 359
	
	public void setVendorLocation (String vendorLocation)
	{
		this.vendorLocation = vendorLocation;
	}
	
	public String getVendorLocation()
	{
		return vendorLocation;
	}
	
//FINISHED By SHALIN SHAH For HInfo#16 --- CLIENT ISSUE 359
	
	
	
	
}
