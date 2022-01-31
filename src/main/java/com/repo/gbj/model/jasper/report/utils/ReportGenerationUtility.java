package com.repo.gbj.model.jasper.report.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repo.gbj.model.jasper.report.view.ParentJasperViewObject;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportGenerationUtility{ 
//implements Runnable {

	private ParentJasperViewObject objectList;
	private List<String> pagelist;
	private ArrayList<JasperPrint> jasperPrints = null;
	private HttpServletResponse response;

	private final static Logger logger = LoggerFactory.getLogger(ReportGenerationUtility.class);

	//private static ReportGenerationUtility reportGenerationUtility = null;

	/*private ReportGenerationUtility(ParentJasperViewObject objectList, List<String> pagelist,
			HttpServletResponse response) {
		this.objectList = objectList;
		this.pagelist = pagelist;
		this.response = response;
	}

	public static ReportGenerationUtility getInstance(ParentJasperViewObject objectList, List<String> pagelist,
			HttpServletResponse response) {
		if (reportGenerationUtility == null) {
			reportGenerationUtility = new ReportGenerationUtility(objectList, pagelist, response);
		}
		return reportGenerationUtility;
	}*/

	public JasperPrint jasperToPDF(ParentJasperViewObject objectList, String jrxmlPath) {

		Collection collection = new ArrayList();
		collection.add(objectList);
		JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(collection);
		JasperPrint jasperPrint = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CollectionBeanParam", collectionDataSource);
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jasperreports/" + jrxmlPath);
			if (inputStream != null) {
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				jasperPrint = JasperFillManager.fillReport(jasperReport, params, collectionDataSource);
			} else {
				logger.info("Input Stream is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}

	/*@Override
	public void run() {

		jasperPrints = new ArrayList<JasperPrint>();
		if (this.pagelist.size() > 0) {
			for (String jrxmlpath : getPagelist()) {
				Collection collection = new ArrayList();
				collection.add(getObjectList());
				JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(collection);
				JasperPrint jasperPrint = null;
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("CollectionBeanParam", collectionDataSource);
				try {
					logger.info("Before preparing input stream");
					InputStream inputStream = getClass().getClassLoader()
							.getResourceAsStream("jasperreports/" + jrxmlpath);
					logger.info("Before preparing jasper design " + jrxmlpath);

					if (inputStream != null) {
						JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
						logger.info("Before preparing jasper report " + jasperDesign.getName());
						JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
						jasperPrint = JasperFillManager.fillReport(jasperReport, params, collectionDataSource);
						jasperPrints.add(jasperPrint);
					} else {
						logger.info("Input Stream is null");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			export();
		}
	}

	private void export() {
		if (!getJasperPrints().isEmpty() && getJasperPrints().size() > 1) {
			for (int i = 0; i < getJasperPrints().size();) {
				getJasperPrints().get(i).addPage(getJasperPrints().get(i + 1).getPages().get(0));
				i++;
			}
		}
		getResponse().setContentType("application/x-download");
		getResponse().addHeader("Content-disposition",
				"attachment; filename=" + DateUtility.todaysDateinDDMMYYYYHHMMSS() + ".pdf");
		try {
			
			 * JRPdfExporter exporter = new JRPdfExporter();
			 * exporter.setExporterInput(SimpleExporterInput.getInstance(
			 * jasperPrints)); //Set as export input
			 * exporter.setExporterOutput(new
			 * SimpleOutputStreamExporterOutput(response.getOutputStream()));
			 * //Set output stream SimplePdfExporterConfiguration configuration
			 * = new SimplePdfExporterConfiguration(); //set your configuration
			 * exporter.setConfiguration(configuration);
			 * exporter.exportReport();
			 
			// JasperExportManager.exportReportToPdfFile(getJasperPrints().get(0),"D:\\"+DateUtility.todaysDateinDDMMYYYYHHMMSS()
			// + ".pdf");
			JasperExportManager.exportReportToPdfStream(getJasperPrints().get(0), getResponse().getOutputStream());
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}
*/
	public ParentJasperViewObject getObjectList() {
		return objectList;
	}

	public void setObjectList(ParentJasperViewObject objectList) {
		this.objectList = objectList;
	}

	public List<String> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<String> pagelist) {
		this.pagelist = pagelist;
	}

	public ArrayList<JasperPrint> getJasperPrints() {
		return jasperPrints;
	}

	public void setJasperPrints(ArrayList<JasperPrint> jasperPrints) {
		this.jasperPrints = jasperPrints;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
