package com.uae.adnoc.backend.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;

import com.uae.adnoc.backend.model.PowerGenFigures;

public class ResourceFile {

	public ResourceFile(){
		
	}
	
	public Properties getResourceProperties(String fileName){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
			input = new FileInputStream(file);
			
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	return prop;
	}

	
	
	@SuppressWarnings("deprecation")
	public PowerGenFigures loadPowerGenFigures(String netAnnualString, String prependString, String region, Row aRow) {
		
		Properties prap = getResourceProperties("PGFigures.properties");
		PowerGenFigures pgf = new PowerGenFigures();
		
		Double AN_As_Double = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(netAnnualString +"AN"))).getNumericCellValue());
		Double OZ_As_Double = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(netAnnualString +"OZ"))).getNumericCellValue());
		
		Double ACL_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ACL"))).getNumericCellValue());
		Double AOL_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AOL"))).getNumericCellValue());	
		Double AGS_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AGS"))).getNumericCellValue());
		Double ANC_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ANC"))).getNumericCellValue());		
		Double AHY_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AHY"))).getNumericCellValue());	
		Double ABM_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ABM"))).getNumericCellValue());		
		Double AWI_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AWI"))).getNumericCellValue());
		Double ASO_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ASO"))).getNumericCellValue());	
		Double AGT_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AGT"))).getNumericCellValue());
		Double AOF_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AOF"))).getNumericCellValue());
		Double AOP_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"AOP"))).getNumericCellValue());	
		Double ATN_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ATN"))).getNumericCellValue());
		Double ATR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ATR"))).getNumericCellValue());
		Double ATH_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ATH"))).getNumericCellValue());	
		Double ACY_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ACY"))).getNumericCellValue());		
		Double ACN_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(prependString +"ACN"))).getNumericCellValue());
		
		Double CLPR_As_String =  new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"CLPR"))).getNumericCellValue());	
		Double OLPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"OLPR"))).getNumericCellValue());
		Double GSPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"GSPR"))).getNumericCellValue());
		Double NCPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"NCPR"))).getNumericCellValue());	
		Double HYPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"HYPR"))).getNumericCellValue());
		Double BMPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"BMPR"))).getNumericCellValue());
		Double WIPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"WIPR"))).getNumericCellValue());	
		Double SOPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"SOPR"))).getNumericCellValue());
		Double GTPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"GTPR"))).getNumericCellValue());
		Double OFPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"OFPR"))).getNumericCellValue());	
		Double OPPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"OPPR"))).getNumericCellValue());
		Double TNPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"TNPR"))).getNumericCellValue());
		Double TRPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"TRPR"))).getNumericCellValue());	
		Double THPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"THPR"))).getNumericCellValue());
		Double CYPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"CYPR"))).getNumericCellValue());
	//	Double CNPR_As_String = new Double(aRow.getCell(Integer.parseInt(prap.getProperty(region +"CNPR"))).getNumericCellValue());	
		

		pgf.setNet_Generation(AN_As_Double.intValue());
		pgf.setOzone_Generation(OZ_As_Double.intValue());
		
		pgf.setCoal_Net_Generation(ACL_As_String.intValue());
		pgf.setOil_Net_Generation(AOL_As_String.intValue());
		pgf.setGas_Net_Generation(AGS_As_String.intValue());
		pgf.setNuclear_Net_Generation(ANC_As_String.intValue());
		pgf.setHydro_Net_Generation(AHY_As_String.intValue());		
		pgf.setBiomass_Net_Generation(ABM_As_String.intValue());
		pgf.setWind_Net_Generation(AWI_As_String.intValue());
		pgf.setSolar_Net_Generation(ASO_As_String.intValue());
		pgf.setGeothermal_Net_Generation(AGT_As_String.intValue());
		pgf.setFossil_Net_Generation(AOF_As_String.intValue());
		pgf.setUnknown_Net_Generation(AOP_As_String.intValue());		
		pgf.setTotal_Nonrenewables_Net_Generation(ATN_As_String.intValue());
		pgf.setTotal_Renewables_Net_Generation(ATR_As_String.intValue());
		pgf.setTotal_Nonhydro_Renewables_Net_Generation(ATH_As_String.intValue());
		pgf.setTotal_Combustion_Net_Generation(ACY_As_String.intValue());
		pgf.setTotal_Noncombustion_Net_Generation(ACN_As_String.intValue());
		
		pgf.setCoal_Generation_Percent(CLPR_As_String.toString());
		pgf.setOil_Generation_Percent(OLPR_As_String.toString());
		pgf.setGas_Generation_Percent(GSPR_As_String.toString());
		pgf.setNuclear_Generation_Percent(NCPR_As_String.toString());
		pgf.setHydro_Generation_Percent(HYPR_As_String.toString());
		pgf.setBiomass_Generation_Percent(BMPR_As_String.toString());
		pgf.setWind_Generation_Percent(WIPR_As_String.toString());
		pgf.setSolar_Generation_Percent(SOPR_As_String.toString());
		pgf.setGrothermal_Generation_Percent(GTPR_As_String.toString());
		pgf.setFossil_Generation_Percent(OFPR_As_String.toString());
		pgf.setUnknown_Generation_Percent(OPPR_As_String.toString());
		pgf.setNonrenewables_Generation_Percent(TNPR_As_String.toString());
		pgf.setRenewables_Generation_Percent(TRPR_As_String.toString());
		pgf.setNonhydro_Generation_Percent(THPR_As_String.toString());
		pgf.setCombustion_Generation_Percent(CYPR_As_String.toString());
	//	pgf.setNoncombustion_Generation_Percent(CNPR_As_String.toString());
			
		
		return pgf;
	}
	
	
}
