package com.viavi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.agile.api.AgileSessionFactory;
import com.agile.api.CommodityConstants;
import com.agile.api.ExceptionConstants;
import com.agile.api.IAgileList;
import com.agile.api.IAgileSession;
import com.agile.api.ICell;
import com.agile.api.ICommodity;
import com.agile.api.IManufacturerPart;
import com.agile.api.IPartGroup;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.ManufacturerPartConstants;
import com.agile.api.PartFamilyConstants;
import com.agile.api.PartGroupConstants;

public class ComplianceRollup {
	
	
	 
	 
	 public static void main(String args[])
	 {
		 ComplianceRollup cp1= new ComplianceRollup();
		cp1.rollUp();
		 
	 }
	 
	 void rollUp()
	{
		
	try
	{
		
	 ComplianceRollup cp= new ComplianceRollup();
	IAgileSession session  = cp.connectToAgile("agile-admin","Agile@123","http://denappagileuat2:7001/Agile");
	session.disableWarning(ExceptionConstants.APDM_UNRESPONDEDCHANGE_WARNING);
		Map params = new HashMap();
	 params.put(ManufacturerPartConstants.ATT_GENERAL_INFO_MANUFACTURER_NAME, "NIC COMPONENTS");
     params.put(ManufacturerPartConstants.ATT_GENERAL_INFO_MANUFACTURER_PART_NUMBER, "NRC12J472F");

     IManufacturerPart mfrPartObj =(IManufacturerPart) session.getObject(IManufacturerPart.OBJECT_TYPE, params);
     
     System.out.println(" MPN Loaded :"+mfrPartObj.getName());
     
     Object pfamily=(Object)mfrPartObj.getValue(ManufacturerPartConstants.ATT_GENERAL_INFO_PART_FAMILY);
     
     String family=pfamily.toString();
     System.out.println("Part Family Loaded : "+family);
     
     ICommodity partfamily=(ICommodity)session.getObject(ICommodity.OBJECT_TYPE, family);
     System.out.println(partfamily.toString());
     
     ITable parttable=partfamily.getTable(PartGroupConstants.TABLE_PARTS);
     System.out.println(parttable.size());
     Iterator itr=parttable.iterator();
    
     IRow rrow=null;
     IManufacturerPart mfpartadd=null;
    
     while(itr.hasNext())
     {
    	 
    	 IRow partsnow=(IRow)itr.next();    	 
    	 IManufacturerPart  mfpart=(IManufacturerPart)partsnow.getReferent();
    	 String number=(String)mfpart.getValue(ManufacturerPartConstants.ATT_GENERAL_INFO_MANUFACTURER_PART_NUMBER);
    	 String mfrname=(String)mfpart.getValue(ManufacturerPartConstants.ATT_GENERAL_INFO_MANUFACTURER_NAME);
    	 System.out.println("Manufactuer part Number :"+number+" Manufactuer Name :"+mfrname);
    	 
    	 if(number.equals("NRC12J472F") && mfrname.equals("NIC COMPONENTS"))
    	 {
    		 rrow=partsnow;
    		 parttable.removeRow(partsnow);
    		 System.out.println("Found :");
    		 break;
    	 }
    	 
    	 
    	 
     }
     
     parttable=partfamily.getTable(PartGroupConstants.TABLE_PARTS);
     mfrPartObj =(IManufacturerPart) session.getObject(IManufacturerPart.OBJECT_TYPE, params);
    System.out.println(mfrPartObj.getName());
   //parttable.createRow(mfrPartObj);
     
     ICell cell =mfrPartObj.getCell(ManufacturerPartConstants.ATT_GENERAL_INFO_PART_FAMILY);
     IAgileList values = cell.getAvailableValues();
   //ject obj[] = new Object {pfamily};
     
     System.out.println(cell.getValue()+ " VALUE IN Cell"+pfamily);
//     System.out.println(pfamily+" "+mfrPartObj.getName());
     
     values.setSelection(new Object[] { pfamily });
     
//  System.out.println("Line 83 ");
//     if(parttable!=null)
//     {
//    	 System.out.println("Line 87");
//     parttable.add(mfrPartObj);
//     System.out.println("Line 88");
//     }
     
     mfrPartObj.rollup();
    
     System.out.println();
    
}
catch(Exception e )
	{
	e.printStackTrace();
	}
	
}
	public IAgileSession connectToAgile(String username,String pass,String url) throws Exception

	 {
		IAgileSession session;
	 
	 // TODO Auto-generated method stub

	 System.out.println("While connecting Session");

	 System.setProperty("disable.agile.sessionID.generation", "true");

	 HashMap params = new HashMap();

	 params.put(AgileSessionFactory.USERNAME, username);

	 params.put(AgileSessionFactory.PASSWORD, pass);

System.out.println("Before Getting Session");
	 AgileSessionFactory factory = AgileSessionFactory.getInstance(url);

	 //System.out.println(factory.getVersion());

	 //System.out.println(factory.getServerVersion());

	 session = factory.createSession(params);
	 System.out.println("After Getting Session");

	 //session = AgileSessionFactory.createSessionEx(params);


	 System.out.println("Session Created ="+session);

return session;

	}
}