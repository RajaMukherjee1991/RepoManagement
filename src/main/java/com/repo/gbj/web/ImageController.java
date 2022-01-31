package com.repo.gbj.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repo.gbj.service.StockService;
import com.repo.gbj.utils.ZXingHelper;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	StockService stockService;

	@RequestMapping(value = "barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 400, 40));
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping(value = "/fetchStockPhoto/{stockBarcode}",method = RequestMethod.GET)
	@ResponseBody
	public String getStudentPhoto(HttpServletResponse response, @PathVariable("stockBarcode") String stockBarcode) throws Exception {
		String ph = stockService.getPhotoById(stockBarcode);
		System.out.println("length of blob " +ph.length());
/*
		response.setContentType(MediaType.TEXT_PLAIN_VALUE);
		byte[] bytes = ph.getBytes();
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());*/
		
		return ph;
	}
	
}
