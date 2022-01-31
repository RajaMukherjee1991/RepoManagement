package com.repo.gbj.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class FileHelper {
	
	public String[] fetchResource(String filename) throws FileNotFoundException{
		InputStream resource = getClass().getClassLoader().getResourceAsStream(filename);
		
		JasperDesign jasperDesign=null;
		try {
			jasperDesign = JRXmlLoader.load(resource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return jasperDesign.getPropertyNames();
	}
}
