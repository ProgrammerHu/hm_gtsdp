package com.hemaapp.hm_gtsdp.model;

import com.amap.api.services.core.PoiItem;

import xtom.frame.XtomObject;

/**
 * 详细地址列表中显示的模型
 * @author Wen
 * @author HuFanglin
 *
 */
public class AddressModel extends XtomObject
{
	
	private String AddressName;
	private String Snippet;
	private String CityName;
	private String ProvinceName;
	private String AdName;
	private double Latitude;
	private double Longitude;
	
	public boolean IsChecked;//标记是否选择
	
	public AddressModel(String ClickAddress, String ClickAddressSimple)
	{
		AddressName = ClickAddressSimple;
		Snippet = ClickAddress;
		CityName = "";
		ProvinceName = "";
		AdName = "";
		Latitude = 0;
		Longitude = 0;
		IsChecked = true;
	}
	
	public AddressModel(PoiItem poiItem)
	{
		AddressName = poiItem.toString();
		Snippet = poiItem.getSnippet();
		CityName = poiItem.getCityName();
		ProvinceName = poiItem.getProvinceName();
		AdName = poiItem.getAdName();
		Latitude = poiItem.getLatLonPoint().getLatitude();
		Longitude = poiItem.getLatLonPoint().getLongitude();
		IsChecked = false;
	}
	
	public String getAddressName() {
		return AddressName;
	}

	public String getSnippet() {
		return Snippet;
	}

	public String getCityName() {
		return CityName;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public String getAdName() {
		return AdName;
	}

	public double getLatitude() {
		return Latitude;
	}

	public double getLongitude() {
		return Longitude;
	}
}
