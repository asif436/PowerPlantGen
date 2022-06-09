package com.uae.adnoc.backend.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.uae.adnoc.backend.model.Country;
import com.uae.adnoc.backend.model.PowerGenFigures;
import com.uae.adnoc.backend.model.PowerPlant;
import com.uae.adnoc.backend.model.State;

public class ExcelFileParser extends BaseFileParser {

	@Override
	public void parseFile(String inputFileName) throws IOException {
		
		try {

			boolean dataAvailable = true;
			ResourceFile resFile = new ResourceFile();
			Country country = new Country();
			PowerGenFigures powerGenFigures = new PowerGenFigures();

			File file = new File(getClass().getClassLoader().getResource(inputFileName).getFile());
			Workbook wb = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet sheet = null; 

			/* ****************************************
			 * Load Country data from the excell sheet 
			 * 
			 * ****************************************/
			sheet = wb.getSheetAt(3);
			Iterator rowsEmp = sheet.rowIterator(); 
			Properties prop = resFile.getResourceProperties("PGFigures.properties");

			while (rowsEmp.hasNext() && dataAvailable) {
				Row row = (Row) rowsEmp.next();

				// Ignore first header row
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {

					continue; // just skip the rows if row number is 0 or 1

				} else {

					// Country data year
					Double counttry_Data_Year = new Double(
							row.getCell(Integer.parseInt(prop.getProperty("CountryYear"))).getNumericCellValue());

					// Country Nameplate Capacity
					Double country_Nameplate_Capacity = new Double(
							row.getCell(Integer.parseInt(prop.getProperty("Country_Nameplate_Capacity")))
									.getNumericCellValue());

					country.setData_Year(counttry_Data_Year.intValue());
					country.setNameplate_Capacity(country_Nameplate_Capacity.intValue());

					// Set country powerGeneration figures
					powerGenFigures = resFile.loadPowerGenFigures("USNGEN", "USGEN", "US", row);
					country.setPowerGenFigures(powerGenFigures);

					dataAvailable = false;
				}

			}

			sheet = null;
			sheet = wb.getSheetAt(2);
			Iterator rowState = sheet.rowIterator(); // Now we have rows ready from the sheet
			dataAvailable = true;
			int counter = 0;
			while (rowState.hasNext() && dataAvailable) {

				Row row = (Row) rowState.next();
				
				// Ignore first header row
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {

					continue; // just skip the rows if row number is 0 or 1

				} else {

					State state = new State();
					powerGenFigures = new PowerGenFigures();

					Iterator cells = row.cellIterator();

					while (cells.hasNext()) {
						Cell cell = (Cell) cells.next();
						CellType cellType = cell.getCellTypeEnum();

						if (cellType == cellType.BLANK && cell.getColumnIndex() == 0) {
							dataAvailable = false;
							break;
						}
					}

					if (dataAvailable) {

						// State data year
						Double state_Data_Year = new Double(
								row.getCell(Integer.parseInt(prop.getProperty("StateYear"))).getNumericCellValue());

						// State abbreviation
						String state_Abbre_As_String = row
								.getCell(Integer.parseInt(prop.getProperty("StateAbbreviation")))
								.getRichStringCellValue().getString();

						// State code int value
						String state_Code = row.getCell(Integer.parseInt(prop.getProperty("StateCode")))
								.getRichStringCellValue().getString();

						state.setData_Year(state_Data_Year.intValue());
						state.setAbbreviation(state_Abbre_As_String);
						state.setCode(Integer.parseInt(state_Code));

						powerGenFigures = resFile.loadPowerGenFigures("STNGEN", "STGEN", "ST", row);
						state.setPowerGenFigures(powerGenFigures);

						country.getLstState().add(state);
						counter = counter + 1;
						System.out.println(counter);
					}
				}
			}
			
			sheet = null;
			sheet = wb.getSheetAt(1);
			Iterator rowPowerPlant = sheet.rowIterator(); // Now we have rows ready from the sheet
			dataAvailable = true;
			
			while (rowPowerPlant.hasNext() && dataAvailable) {
				
				Row row = (Row) rowPowerPlant.next();

				// Ignore first header row
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {

					continue; // just skip the rows if row number is 0 or 1

				} else {

					PowerPlant plant = new PowerPlant();
					powerGenFigures = new PowerGenFigures();
					
					Iterator cells = row.cellIterator();

					while (cells.hasNext()) {
						Cell cell = (Cell) cells.next();
						CellType cellType = cell.getCellTypeEnum();

						if (cellType == cellType.BLANK && cell.getColumnIndex() == 0) {
							dataAvailable = false;
							break;
						}
					}

					if (dataAvailable) {
						// State data year
						Double state_Data_Year = new Double(
								row.getCell(Integer.parseInt(prop.getProperty("PlantYear"))).getNumericCellValue());

						Double plant_LAT = new Double(
								row.getCell(Integer.parseInt(prop.getProperty("Plant_LAT"))).getNumericCellValue());
						
						Double plant_LON = new Double(
								row.getCell(Integer.parseInt(prop.getProperty("Plant_LON"))).getNumericCellValue());
						
						// State abbreviation
						String state_Abbre_As_String = row
								.getCell(Integer.parseInt(prop.getProperty("PlantStateAbbreviation")))
								.getRichStringCellValue().getString();

						// State code int value
						String plant_Name = row.getCell(Integer.parseInt(prop.getProperty("PNAME")))
								.getRichStringCellValue().getString();

						plant.setData_Year(state_Data_Year.intValue());
						plant.setState_Abbrevation(state_Abbre_As_String);
						plant.setName(plant_Name);
						plant.setLatitude(plant_LAT);
						plant.setLongitued(plant_LON);

						powerGenFigures = resFile.loadPowerGenFigures("PLNGEN", "PLGEN", "PL", row);
						plant.setPowerGenFigures(powerGenFigures);		
						
						for (State aState : country.getLstState()) {
							if (aState.getAbbreviation().equalsIgnoreCase(plant.getState_Abbrevation())) {
								aState.getPower_plant().add(plant);
							}
						}
					}
				}
			}
			
			this.setCountry(country);			
			wb.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException io) {
			// TODO Auto-generated catch block
			io.printStackTrace();
		}
	}
	
}
