package com.scannerapp.common;
import java.io.Serializable;

import com.scannerapp.apps.framework.view.IMConstants;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Shuchi Sharma
 * @version 1.0
 */


/*
          MODIFICATION CODE         MODIFICATION DATE     ISSUE NO               AUTHOR              PURPOSE
          prodInfo#1                 11/02/2005            CHANGE              Ruchi Shah          to ADD PRICE PROPERTY OF PRODUCT IN PRODUCT INFO
          prodInfo#2                 18/02/2009                                Jiten Patel	       convert closing qty from double to int    
          prodInfo#3                 01/05/2009            CHANGE              Manish Vithlani     Client Test Issue#247
          prodInfo#4                 24/07/2009            CHANGE              Jiten Patel         Client Test Issue#327
          prodInfo#5                 09/08/2009            CHANGE              Manish VIthlani     Physical Inventory Change(Client Test Change#(609,610,611,612,613,614)
          prodInfo#6                 01/10/2009                                Jiten Patel         Client Test Issue#583
          prodInfo#7                 12/11/2009                                Jiten Patel         Unit Test Issue#4375
          prodInfo#8                 19/11/2009                                Manish Vithlani     Client Test Change#811
          prodInfo#9                 24/12/2009                                Jiten Patel         Client Issue#813
          prodInfo#10                03/06/2009                                Manish Vithlani     QA Issue # 11644
          prodInfo#11				 08/07/2010								  Shraddha Panchamiya  Unit Test Issue#5713
          prodInfo#12				 28/10/2010								  Jiten Patel          Unit Test Issue#6120
*/

public class ProductInfo implements Cloneable,Comparable,Serializable {
  private Integer sellingId = new Integer(0); // need to pass to Backend
  private String itemId = ""; // need to pass to Backend
  private String productDesc = "";
  private String uom = "";
  private String defUom = "";
  private String volume = "";
  private Double weight = new Double(0);
  private Double printWeight = new Double(0);//Added for prodInfo#7
  private Double docPrintWeight = new Double(0);//Added for prodInfo#7
  private double unitCost = 0.0;

  /* prodInfo#1 */

  private double price = 0.0;

    /* prodInfo#1 */
  private Integer qty = new Integer(0); // use it as received qty also

  // need to pass to Backend
  private Integer shipQty = new Integer(0); // need to pass to Backend
  private String reasonCode = ""; // need to pass to Backend
  private String eanCode = ""; // need to pass to Backend
  private Integer lotId = new Integer(1); // need to pass to Backend
  private Integer docLineId = new Integer(0); // need to pass to Backend
  private String status = "O"; // need to pass to Backend
  private String linetype=""; // need to pass to Backend
  private String docType = "";
  private String att1 = "";
  private String att21 = "";
  private String invType = "";
  private String startdate = "";
  private String enddate = "";
  //added 2 new fields on 12/22/2003 11:36AM by kunal
  private String bagsUnit = "";
  private String caseType = "";
  private String pallete = "";
  //added by dhwanil
  private Integer diffAck;
  // For PRC & PRT
  // To check that whether product is produced at Plant or NOT
  private String produce_flg;
  // Used FOR TOT / TIN when Unauthorized products are inserted
  private String unauthProd_flg = "";

  private Integer ackBefInvCls;   // Acknowledge Before Inventory Close
  private Integer ackAftInvCls;   // Acknowledge After Inventory Close
   
  //Added by jiten for Gap43 Romania
  private String mappedItemId="";
  //finish by jiten for Gap43 Romania
  
//added by manish for qa issue#5595
  private Long shipQty1 = new Long(0); 
  private Long qty1 = new Long(0); 
//finish by manish for qa issue#5595

  //Added by jitren for costing
  private Double purchasePrice;  
  private Integer expectedQty;  
  private Integer remainingQty;
  private Double expectedPrice;
  private Double remainingPrice;
  private Double initialPrice;
  //finish by jitren for costing
  
 //Added by bipin for Period Closing
  private String lu_desc="";
  private String product_Flag="";
  private Integer openningQty=new Integer(0);
  private double monthly_Qty=0.0;
  //[Changed data type by jinesh 
  private Integer closingQty=new Integer(0);;//Modify for prodInfo#2
  //Ended by Jinesh]
  private Double openningCost=new Double(0);
  private Double totalCost=0.0;
  //finish by bipin for Period Closing
  //added By Jinesh
  private double standardCost=0.0;
  private String delete_flag="";
  private String multipack_Flag="";
  //ended By Jinesh
  
  //start prodInfo#3
  private String caseTypeDesc = "";
  //end prodInfo#3
  
  //Added for prodInfo#4
  private Integer purchaseQty=0;  
  private Integer productionQty=0;
  private Double purchaseCost=0.0;
  private Double productionCost=0.0;
  //finished for prodInfo#4
  
  //Added for prodInfo#9
  private String openningQtyString="0";
  private String openningCostString="0.0";
  private String purchaseQtyString="0";  
  private String productionQtyString="0";
  private String purchaseCostString="0.0";
  private String productionCostString="0.0";  
  private String standardCostString="0.0";
  //finished for prodInfo#9
  
  //Added for prodInfo#6
  private Double stockOnHand=0.0;
  private Double available=0.0;
  //finished for prodInfo#6
  
  //start prodInfo#8
  private Integer theoQuanityEach;
  private String phyQuantityEach="";
  //end prodInfo#8
  
  
  //start prodInfo#11
   private String priceStr="";
   private String stdCostStr="";
   private String lastStdCostStr="";
  //end prodInfo#11
   //start  prodInfo#12
   private String theoQuanityEachStr;
   //end  prodInfo#12
   
   private int bagPerCase=0;//Added for prodInfo#12
/////////////////////////////////////////////////////////////////////////////////////////  
  //Added by jiten for Costing
  public Double getExpectedPrice(){
	  return this.expectedPrice;
  }
  public void setExpectedPrice(Double expectedPrice){
	  this.expectedPrice=expectedPrice;
  }
  
  public Double getRemainingPrice(){
	  return this.remainingPrice;
  }
  public void setRemainingPrice(Double remainingPrice){
	  this.remainingPrice=remainingPrice;
  }
  
  public Double getInitialPrice(){
	  return this.initialPrice;
  }
  public void setInitialPrice(Double initialPrice){
	  this.initialPrice=initialPrice;
  }
  
  
  
  public Double getPurchaseprice(){
	  return this.purchasePrice;
  }
  public void setPurchasePrice(Double Purchaseprice){
	  this.purchasePrice=Purchaseprice;
  }
  public void setExpectedQty(Integer expectedQty) {
	    this.expectedQty = expectedQty;
	  }
  public Integer getExpectedQty() {
    return expectedQty;
  }
  public void setRemainingQty(Integer remainingQty) {
	    this.remainingQty = remainingQty;
	  }
	public Integer getRemainingQty() {
	  return remainingQty;
	}
  //finish by jiten for costing
 //Added by jiten for Gap43 Romania
  public String getMappedItemId(){
	  return this.mappedItemId;
  }
  public void setMappedItemId(String mappedItemId){
	  this.mappedItemId=mappedItemId;
  }
//finish by jiten for Gap43 Romania
	  
  public void setPallete(String pallete)
  {
    this.pallete = pallete != null ? pallete.trim() : "";
  }
  public String getPallete()
  {
    return pallete;
  }
  public ProductInfo() {
    super();
  }
  public String getReasonCode() {
    return reasonCode;
  }
  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode != null ? reasonCode.trim() : "";
  }
  public String getEanCode() {
    return eanCode;
  }
  public void setEanCode(String eanCode) {
    this.eanCode = eanCode != null ? eanCode.trim() : "";
  }
  public Integer getSellingId() {
    return sellingId;
  }
  public void setShipQty(Integer shipQty) {
    this.shipQty = shipQty;
  }
  public Integer getShipQty() {
    return shipQty;
  }
//added by manish for qa issue#5595
  public void setShipQty1(Long shipQty)
  {
	    this.shipQty1 = shipQty;
  }
  public Long getShipQty1()
  {
	    return shipQty1;
  }
//finish by manish for qa issue#5595
  public void setLotId(Integer lotId) {
    this.lotId = lotId;
  }
  public Integer getLotId() {
    return lotId;
  }
  public void setSellingId(Integer sellingId) {
    this.sellingId = sellingId;
  }
  public String getItemId() {
    return itemId;
  }
  public void setItemId(String itemId) {
    this.itemId = itemId != null ? itemId.trim() : "";
  }
  public String getProductDesc() {
    return productDesc;
  }
  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc != null ? productDesc.trim() : "";
  }
  public String getUOM() {
    return uom;
  }
  public String getDefUom() {
    return defUom;
  }
  public void setUOM(String uom) {

        /*if(uom.trim().equals(IMConstants.CASE_CODE))
        this.uom = IMConstants.CASE;
        else if(uom.trim().equals(IMConstants.CADD_CODE))
        this.uom = IMConstants.CADD;
        else if(uom.trim().equals(IMConstants.EACH_CODE))
        this.uom = IMConstants.EACH;*/
    this.uom = uom != null ? uom.trim() : "";
  }
  public void setDefUom(String defUom) {
    this.defUom = defUom != null ? defUom.trim() : "";

  }
  public void setUOMCode(String uom) {
    if(uom.trim().equals(IMConstants.CASE)) {
      this.uom = IMConstants.CASE_CODE;
    }
    else {
      if(uom.trim().equals(IMConstants.CADD)) {
        this.uom = IMConstants.CADD_CODE;
      }
      else {
        if(uom.trim().equals(IMConstants.EACH)) {
          this.uom = IMConstants.EACH_CODE;
        }
      }
    }
  }
  public void setDefUOMCode(String defUom) {
    if(defUom.trim().equals(IMConstants.CASE)) {
      this.defUom = IMConstants.CASE_CODE;
    }
    else {
      if(defUom.trim().equals(IMConstants.CADD)) {
        this.defUom = IMConstants.CADD_CODE;
      }
      else {
        if(defUom.trim().equals(IMConstants.EACH)) {
          this.defUom = IMConstants.EACH_CODE;
        }
      }
    }
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status != null ? status.trim() : "";
  }
  public String getAtt1() {
    return att1;
  }
  public void setAtt1(String att1) {
    this.att1 = att1 != null ? att1.trim() : "";
  }
  public String getInvType() {
    return invType;
  }
  public void setInvType(String invType) {
    this.invType = invType != null ? invType.trim() : "";
  }
  public String getAtt21() {
    return att21;
  }
  public void setAtt21(String att21) {
    this.att21 = att21 != null ? att21.trim() : "";
  }
  public String getDocType() {
    return docType;
  }
  public void setDocType(String docType) {
    this.docType = docType != null ? docType.trim() : "";
  }
  public String getVolume() {
    return volume;
  }
  public void setVolume(String volume) {
    this.volume = volume != null ? volume.trim() : "";
  }
  public Double getWeight() {
    return weight;
  }
  public void setWeight(Double weight) {
    this.weight = weight;
  }
  public Integer getQty() {
    return this.qty;
  }
  public void setQty(Integer qty) {
    this.qty = qty;
  }
//added by manish for qa issue#5595
  public Long getQty1()
  {
	    return this.qty1;
  }
  public void setQty1(Long qty)
  {
	    this.qty1 = qty;
  }
//finish by manish for qa issue#5595
  
  public Integer getDocLineId() {
    return this.docLineId;
  }
  public void setDocLineId(Integer docLineId) {
    this.docLineId = docLineId;
  }
  public double getUnitCost() {
    return this.unitCost;
  }
  public void setUnitCost(double unitCost) {
    this.unitCost = unitCost;
  }
  public String getStartdate() {
    return startdate;
  }
  public void setStartdate(String startdate) {
    this.startdate = startdate != null ? startdate.trim() : "";
  }
  public String getEnddate() {
    return enddate;
  }
  public void setEnddate(String enddate) {
    this.enddate = enddate != null ? enddate.trim() : "";
  }
  public String getBagsUnit(){
    return bagsUnit;
  }
  public void setBagsUnit(String bagsUnit){
    this.bagsUnit = bagsUnit != null ? bagsUnit.trim() : "";
  }
  public String getCaseType(){
    return caseType;
  }
  public void setCaseType(String caseType){
    this.caseType = caseType != null ? caseType.trim() : "";
  }

  //added by dhwanil

  public Integer getDiffAckQty()	{
    return diffAck;
  }

  public void setDiffAckQty(Integer diffAck)	{
    this.diffAck = diffAck;
  }
//
///////////////
 public String getProduceFlag(){
    return produce_flg;
  }
  public void setProduceFlag(String produceFlg){
    this.produce_flg = produceFlg != null ? produceFlg.trim() : "";
  }

  public String getUnauthProdFlag(){
     return unauthProd_flg;
   }
   public void setUnauthProdFlag(String unauthProdFLAG){
     this.unauthProd_flg = unauthProdFLAG != null ? unauthProdFLAG.trim() : "";
   }
   private String _division, _divisionDesc;
   public String getDivision() {
     return _division;
   }
   public String getDivisionDesc() {
     return _divisionDesc;
   }
   public void setDivision(String division) {
     this._division = division;
   }
   public void setDivisionDesc(String divisionDesc) {
     this._divisionDesc = divisionDesc;
   }
   //added by manish for gap18
   private String Typecode;
   public String getTypecode()
   {
	     return Typecode;
   }
   public void setTypecode(String typecode)
   {
	     this.Typecode = typecode;
   }
   //finish by mansih for gap18
   private String _catagory,_catagoryDesc;
   public String getCatagory() {
     return _catagory;
   }
   public String getCatagoryDesc() {
     return _catagoryDesc;
   }
   public void setCatagory(String catagory) {
     this._catagory = catagory;
   }
   public void setCatagoryDesc(String catagoryDesc) {
     this._catagoryDesc = catagoryDesc;
   }
   private String _family,_familyDesc;
   public String getFamily() {
     return _family;
   }
   public String getFamilyDesc() {
     return _familyDesc;
   }
   public void setFamily(String family) {
     this._family = family;
   }
   public void setFamilyDesc(String familyDesc) {
     this._familyDesc = familyDesc;
   }
   private String _subfamily,_subfamilyDesc;
   public String getSubFamily() {
     return _subfamily;
   }
   public String getSubFamilyDesc() {
     return _subfamilyDesc;
   }
   public void setSubFamily(String subfamily) {
     this._subfamily = subfamily;
   }
   public void setSubFamilyDesc(String subfamilyDesc) {
     this._subfamilyDesc = subfamilyDesc;
   }
   private String _flavor,_flavorDesc;
   public String getFlavor() {
     return _flavor;
   }
   public String getFlavorDesc() {
     return _flavorDesc;
   }
   public void setFlavor(String flavor) {
     this._flavor = flavor;
   }
   public void setFlavorDesc(String flavorDesc) {
     this._flavorDesc = flavorDesc;
   }
   
   //Added by bipin
   public void setOpenningQty(Integer openningQty) {
	     this.openningQty = openningQty;
	   }
   
   public Integer getOpenningQty() {
	    return openningQty;
	   }
   
   public void setOpenningCost(Double openningCost) {
	     this.openningCost = openningCost;
	   }   
   
   public Double getOpenningCost() {
	     return openningCost; 
	   }
   
   //[##Added BY Jinesh
   public void setClosingQty(Integer closingQty) {
	     this.closingQty = closingQty;
	   }      
   
   public Integer gettClosingQty() {
	    return closingQty;
	   }  
  //Ended By Jinesh] 
   public void setTotalCost(Double totalCost) {
	     this.totalCost = totalCost;
	   }
   
   public Double getTotalCost() {
	     return totalCost;
	   }
   
   //Finish by bipin
/////////////////////////////////////
   public Integer getAckBefInvCls()
   {
     return this.ackBefInvCls;
   }
   public void setAckBefInvCls(Integer AckBefore)
   {
     this.ackBefInvCls = AckBefore;
   }

   public Integer getAckAftInvCls()
   {
     return this.ackAftInvCls;
   }
   public void setAckAftInvCls(Integer AckAfter)
   {
     this.ackAftInvCls = AckAfter;
   }
/* prodInfo#1 */
   public double getPrice()
    {
      return this.price;
    }
    public void setPrice(double price)
    {
      this.price = price;
    }

/* prodInfo#1 */



////////////////
  public Object clone() throws CloneNotSupportedException {
    ProductInfo clonedObj = null;
    try {
      clonedObj = (ProductInfo)super.clone();
    }
    catch(CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return clonedObj;
  }
  public final String toString() {
    return getItemId() + "-" + getProductDesc() + "-" + getSellingId() + "-" + getUOM() + "-" + getVolume() + "-" + getDocLineId() + "-" + getQty() + "-" + getStatus() + "-" + getReasonCode() + "-" + getDefUom() + "-" + getStartdate() + "-" + getEnddate() + "-" + getBagsUnit() + "-" + getCaseType();
  }
  public final boolean equals(Object obj) {
    if(obj instanceof ProductInfo) {
      return (getItemId() == ((ProductInfo)obj).getItemId() && getProductDesc() == ((ProductInfo)obj).getProductDesc() && getSellingId().equals(((ProductInfo)obj).getSellingId()) && getUOM().equals(((ProductInfo)obj).getUOM()) && getDefUom().equals(((ProductInfo)obj).getDefUom()) && getVolume().equals(((ProductInfo)obj).getVolume()) && getDocLineId() == ((ProductInfo)obj).getDocLineId() && getQty() == ((ProductInfo)obj).getQty() && getLotId() == ((ProductInfo)obj).getLotId() && getShipQty() == ((ProductInfo)obj).getShipQty() && getUnitCost() == ((ProductInfo)obj).getUnitCost() && getEanCode().equals(((ProductInfo)obj).getEanCode()) && getDocType().equals(((ProductInfo)obj).getDocType()) && getReasonCode().equals(((ProductInfo)obj).getReasonCode()) && getStatus().equals(((ProductInfo)obj).getStatus()) ? true : false);
    }
    else {
      return false;
    }
  }
  public final int compareTo(java.lang.Object obj) {
    if(obj instanceof ProductInfo) {
      return toString().compareTo(obj.toString());
    }
    else {
      return -1;
    }
  }
  //added by manish for gap18
  
  public String getLinetype()
  {
    return this.linetype;
  }
  public void setLinetype(String LINETYPE)
  {
	  this.linetype=LINETYPE;
  }
  //finish by manish for gap18
  //Added By Jinesh
public String getMultipack_Flag() {
	return multipack_Flag;
}
public void setMultipack_Flag(String multipack_Flag) {
	this.multipack_Flag = multipack_Flag;
}
public double getStandardCost() {
	return standardCost;
}
public void setStandardCost(double standardCost) {
	this.standardCost = standardCost;
}
//Ended By Jinesh
public String getLu_desc() {
	return lu_desc;
}
public void setLu_desc(String lu_desc) {
	this.lu_desc = lu_desc;
}
public String getProduct_Flag() {
	return product_Flag;
}
public void setProduct_Flag(String product_Flag) {
	this.product_Flag = product_Flag;
}
public double getMonthly_Qty() {
	return monthly_Qty;
}
public void setMonthly_Qty(double monthly_Qty) {
	this.monthly_Qty = monthly_Qty;
}
public String getDelete_flag() {
	return delete_flag;
}
public void setDelete_flag(String delete_flag) {
	this.delete_flag = delete_flag;
}

//start prodInfo#3
public String getCaseTypeDesc() {
	return caseTypeDesc;
}
public void setCaseTypeDesc(String caseTypeDesc) {
	this.caseTypeDesc = caseTypeDesc;
}
//end prodInfo#3
public Integer getPurchaseQty() {
	return purchaseQty;
}
public void setPurchaseQty(Integer purchaseQty) {
	this.purchaseQty = purchaseQty;
}
public Integer getProductionQty() {
	return productionQty;
}
public void setProductionQty(Integer productionQty) {
	this.productionQty = productionQty;
}
public Double getPurchaseCost() {
	return purchaseCost;
}
public void setPurchaseCost(Double purchaseCost) {
	this.purchaseCost = purchaseCost;
}
public Double getProductionCost() {
	return productionCost;
}
public void setProductionCost(Double productionCost) {
	this.productionCost = productionCost;
}

//start prodInfo#5
//private Double Theo_qty=0.0; //code commented for prodInfo#10
private boolean isTheoqtyindouble=false;
//private Double Phy_qty=0.0;

public boolean isTheoqtyindouble() {
	return isTheoqtyindouble;
}
public void setTheoqtyindouble(boolean isTheoqtyindouble) {
	this.isTheoqtyindouble = isTheoqtyindouble;
}
/*public Double getPhy_qty() {
	return Phy_qty;
}
public void setPhy_qty(Double phy_qty) {
	Phy_qty = phy_qty;
}*/

//start prodInfo#10

/*public Double getTheo_qty() {
	return Theo_qty;
}
public void setTheo_qty(Double theo_qty) {
	Theo_qty = theo_qty;
}*/

private String Theo_qty="0";

public String getTheo_qty() {
	return Theo_qty;
}
public void setTheo_qty(String theo_qty) {
	Theo_qty = theo_qty;
}

//end prodInfo#10

public Double getStockOnHand() {
	return stockOnHand;
}
public void setStockOnHand(Double stockOnHand) {
	this.stockOnHand = stockOnHand;
}
public Double getAvailable() {
	return available;
}
public void setAvailable(Double available) {
	this.available = available;
}
public Double getPrintWeight() {
	return printWeight;
}
public void setPrintWeight(Double printWeight) {
	this.printWeight = printWeight;
}
public Double getDocPrintWeight() {
	return docPrintWeight;
}
public void setDocPrintWeight(Double docPrintWeight) {
	this.docPrintWeight = docPrintWeight;
}

//end prodInfo#5

//start prodInfo#8
public Integer getTheoQuanityEach() {
	return theoQuanityEach;
}
public void setTheoQuanityEach(Integer theoQuanityEach) {
	this.theoQuanityEach = theoQuanityEach;
}
public String getPhyQuantityEach() {
	return phyQuantityEach;
}
public void setPhyQuantityEach(String phyQuantityEach) {
	this.phyQuantityEach = phyQuantityEach;
}
//end prodInfo#8
//Added for prodInfo#9
public String getOpenningQtyString() {
	return openningQtyString;
}
public void setOpenningQtyString(String openningQtyString) {
	this.openningQtyString = openningQtyString;
}
public String getOpenningCostString() {
	return openningCostString;
}
public void setOpenningCostString(String openningCostString) {
	this.openningCostString = openningCostString;
}
public String getPurchaseQtyString() {
	return purchaseQtyString;
}
public void setPurchaseQtyString(String purchaseQtyString) {
	this.purchaseQtyString = purchaseQtyString;
}
public String getProductionQtyString() {
	return productionQtyString;
}
public void setProductionQtyString(String productionQtyString) {
	this.productionQtyString = productionQtyString;
}
public String getPurchaseCostString() {
	return purchaseCostString;
}
public void setPurchaseCostString(String purchaseCostString) {
	this.purchaseCostString = purchaseCostString;
}
public String getProductionCostString() {
	return productionCostString;
}
public void setProductionCostString(String productionCostString) {
	this.productionCostString = productionCostString;
}
//finished for prodInfo#9
public String getStandardCostString() {
	return standardCostString;
}
public void setStandardCostString(String standardCostString) {
	this.standardCostString = standardCostString;
}
//start prodInfo#11
public String getPriceStr() {
	return priceStr;
}
public void setPriceStr(String priceStr) {
	this.priceStr = priceStr;
}
public String getStdCostStr() {
	return stdCostStr;
}
public void setStdCostStr(String stdCostStr) {
	this.stdCostStr = stdCostStr;
}
public String getLastStdCostStr() {
	return lastStdCostStr;
}
public void setLastStdCostStr(String lastStdCostStr) {
	this.lastStdCostStr = lastStdCostStr;
}
//end prodInfo#11
//start prodInfo#12
public String getTheoQuanityEachStr() {
	return theoQuanityEachStr;
}
public void setTheoQuanityEachStr(String theoQuanityEachStr) {
	this.theoQuanityEachStr = theoQuanityEachStr;
}
//end prodInfo#12
public int getBagPerCase() {
	return bagPerCase;
}
public void setBagPerCase(int bagPerCase) {
	this.bagPerCase = bagPerCase;
}
}