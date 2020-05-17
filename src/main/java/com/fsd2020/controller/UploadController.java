package com.fsd2020.controller;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class UploadController {

	@PostMapping("upload")
	public void importEmp(@RequestParam("file") MultipartFile file) throws IOException {
	    //...
		XSS
		HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		// row numbers
		System.out.println(sheet.getPhysicalNumberOfRows());
	}
}
