package com.scannerapp.common;
import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Kunal Thacker
 * @version 1.0
 */
 /*
 *======================Modification History======================
 *
 * Date      		Author       Code #        			Description of Modifications
 * ----------   ----------------     ----------------  			--------------------------------------
 * 
   23-08-2007   Nikunj Maniar        FreightHeader#1          		Adding 2 fields in freight screen and report of TOT,PUT,PRT,MIS(issue#4905)

*/
public class FreighHeader implements Cloneable,Comparable,Serializable {

    private String  documentNbr = "";
    private String  documentDate = "";
    private String  documentType = "";

    private String  carrierID = "";
    private String  freightTotal = "";
    private String  plate1 = "";
    private String  plate2 = "";
    private String  volume = "";
    private String  truckid = "";
    private String  comments = "";
	//FreightHeader#1 starts
	private String  sTname= "";
	private String  sAddress = "";
	//FreightHeader#1 ends
	//FreightHeader#1 starts
    public String getAddress() {
        return sAddress;
    }

    public String getTname() {
        return sTname;
    }
//FreightHeader#1 ends
    
  //Added by jiten for costing
	private String finalflag="";
	private String changedflag="F";
	private String  documentTime = "";
	//finish by jiten for costing
	
	//Added by jiten for costing
	
	public void setFinalflag(String finalflag ) {
        this.finalflag=finalflag;
    }

    public String getFinalflag() {
        return finalflag;
    }
    
    public void setChangedflag(String changedflag ) {
        this.changedflag=changedflag;
    }

    public String getChangedflag() {
        return changedflag;
    }
	//finish by jiten  

    public String getDocumentNbr() {
        return documentNbr;
    }
    public void setDocumentNbr(String documentNbr) {
        this.documentNbr = documentNbr.trim();
    }

    public String getVolume ()
    {
      return volume ;
    }

//FreightHeader#1 starts
    public void setAddress(String Address) {
        this.sAddress=Address.trim();
    }

    public void setTname(String Tname) {
        this.sTname=Tname.trim();
    }
//FreightHeader#1 ends 


    public void setVolume ( String Volume )
    {
      this.volume = Volume.trim () ;
    }

    public String getTruckID ()
    {
      return truckid ;
    }

    public void setTruckID ( String truckID )
    {
      this.truckid = truckID.trim () ;
    }

    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String _documentType) {
        this.documentType = _documentType.trim();
    }

    public String getDocumentDate () {
        return documentDate ;
    }
    public void setDocumentDate (String documentDate ) {
        this.documentDate  = documentDate.trim();
    }


    public String getCarrierID () {
        return carrierID ;
    }
    public void setCarrierID(String carrierID ) {
        this.carrierID  = carrierID.trim();
    }

    public String getFreightTotal() {
        return freightTotal ;
    }
    public void setFreightTotal(String freightTotal ) {
        this.freightTotal  = freightTotal.trim();
    }


    public String getPlate1() {
        return plate1 ;
    }
    public void setPlate1(String plate1 ) {
        this.plate1  = plate1.trim();
    }


    public String getPlate2() {
        return plate2 ;
    }
    public void setPlate2(String plate2 ) {
        this.plate2  = plate2.trim();
    }


    public String getComments() {
        return comments ;
    }
    public void setComments(String comments ) {
        this.comments  = comments.trim();
    }
    private String carrierName = "";
    public String getCarrierName() {
      return carrierName;
    }
    public void setCarrierName(String carrierName) {
      this.carrierName = carrierName;
    }
    public Object clone() throws CloneNotSupportedException {
        FreighHeader clonedObj = null;
        try {
            clonedObj = (FreighHeader)super.clone();
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clonedObj;
    }
    public final String toString()
    {
        return  "=> Doc No  - " + getDocumentNbr()  + "\n" +
                "=> Total   - " + getFreightTotal() + "\n" +
                "=> DocDate - " + getDocumentDate() + "\n" +
                "=> DocTime - " + getDocumentTime() + "\n" +
                "=> Carrier - " + getCarrierID()    + "\n" +
                "=> Plate 1 - " + getPlate1()       + "\n" +
                "=> Plate 2 - " + getPlate2()       + "\n" +
                "=> TruckID - " + getTruckID()      + "\n" +
                "=> Volume  - " + getVolume()       ;
    }

    public final boolean equals(Object obj)
    {
      if (obj instanceof FreighHeader)
      {
        return
          (
          ((FreighHeader)obj).getComments().equals(getComments()) &&
          ((FreighHeader)obj).getCarrierID().equals(getCarrierID()) &&
          ((FreighHeader)obj).getPlate1().equals(getPlate1()) &&
          ((FreighHeader)obj).getPlate2().equals(getPlate2()) &&
          ((FreighHeader)obj).getTruckID().equals(getTruckID()) &&
          ((FreighHeader)obj).getVolume().equals(getVolume())
          ) ? true : false ;
      }
      else
        return false ;
    }

    public final int compareTo(java.lang.Object obj) {
        if(obj instanceof FreighHeader) {
            return toString().compareTo(obj.toString());
        }
        else {
            return -1;
        }
    }

	public String getDocumentTime() {
		return documentTime;
	}

	public void setDocumentTime(String documentTime) {
		this.documentTime = documentTime;
	}
}
