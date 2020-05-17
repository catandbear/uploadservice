package com.fsd2020.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fsd2020.data.entity.PriceInfoEntity;

@RestController
@CrossOrigin("*")
public class UploadController {

	private List<PriceInfoEntity> priceInfoList = new ArrayList<>();
	
	@PostMapping("upload")
	public void importEmp(@RequestParam("file") MultipartFile file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Jump out of Title 
		// fpreach rows
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			System.out.println("row number: " + i);
			
			PriceInfoEntity priceInfo = new PriceInfoEntity(
					row.getCell(0).getStringCellValue().trim(), 
					row.getCell(1).getStringCellValue().trim(), 
					row.getCell(2).getNumericCellValue(), 
					format.format(row.getCell(3).getDateCellValue()));
			System.out.println(priceInfo.toString());
			priceInfoList.add(priceInfo);
		}
		
		
		
	}
	
}
