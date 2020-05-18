package com.fsd2020.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fsd2020.data.entity.PriceInfoEntity;
import com.fsd2020.data.mapper.PriceMapper;

@RestController
@CrossOrigin("*")
public class UploadController {

	private List<PriceInfoEntity> priceInfoList = new ArrayList<>();
	private PriceMapper mapper;
	
	@Autowired
	private UploadController(PriceMapper mapper) {
		this.mapper = mapper;
	}
	
	@PostMapping("upload")
	public List<PriceInfoEntity> importEmp(@RequestParam("file") MultipartFile file) throws IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Jump out of Title 
		// fpreach rows
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			
			XSSFRow row = sheet.getRow(i);
			PriceInfoEntity priceInfo = new PriceInfoEntity(
					
					// company code
					row.getCell(0).getStringCellValue().trim(), 
					row.getCell(1).getStringCellValue().trim(), 
					row.getCell(2).getNumericCellValue(), 
					format.format(row.getCell(3).getDateCellValue()));
			System.out.println(priceInfo.toString());
			priceInfoList.add(priceInfo);
		}
		
		// start business
		mapper.insertPrice(priceInfoList);
		System.out.println("insert done");
		
		workbook.close();
		
		
		return priceInfoList;
		
	}
	
}
