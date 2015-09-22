package com.hemaapp.hm_gtsdp.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.hemaapp.hm_gtsdp.model.DistrictModel;

//import com.hemaapp.hm_rrg.model.CityModel;
//import com.hemaapp.hm_rrg.model.ProvinceModel;


public class XmlParserHandler extends DefaultHandler {

	/**
	 * 存储所有的解析对象
	 */
	private List<DistrictModel> provinceList = new ArrayList<DistrictModel>();
	 	  
	public XmlParserHandler() {
		
	}

	public List<DistrictModel> getDataList() {
		return provinceList;
	}

	@Override
	public void startDocument() throws SAXException {
		// 当读到第一个开始标签的时候，会触发这个方法
	}

	DistrictModel provinceModel = new DistrictModel();
	DistrictModel cityModel = new DistrictModel();
	DistrictModel districtModel = new DistrictModel();
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 当遇到开始标记的时候，调用这个方法
		if (qName.equals("province")) {
			provinceModel = new DistrictModel();
			provinceModel.setName(attributes.getValue(0));
			provinceModel.setChildList(new ArrayList<DistrictModel>());
		} else if (qName.equals("city")) {
			cityModel = new DistrictModel();
			cityModel.setName(attributes.getValue(0));
			cityModel.setChildList(new ArrayList<DistrictModel>());
		} else if (qName.equals("district")) {
			districtModel = new DistrictModel();
			districtModel.setName(attributes.getValue(0));
			districtModel.setZipcode(attributes.getValue(1));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 遇到结束标记的时候，会调用这个方法
		if (qName.equals("district")) {
			cityModel.getChildList().add(districtModel);
        } else if (qName.equals("city")) {
        	provinceModel.getChildList().add(cityModel);
        } else if (qName.equals("province")) {
        	provinceList.add(provinceModel);
        }
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
