package com.hemaapp.hm_gtsdp.model;

import java.util.ArrayList;
import java.util.List;

public class TempStationModel {
	public static List<Province> getList()
	{
		List<Province> list =new ArrayList<Province>();
		for(int i=0; i< 10; i++)
		{
			Province pro = new Province();
			pro.position = i;
			pro.ProvinceName = "ʡ��" + i;
			List<City> citylist = new ArrayList<City>();
			for(int j = 0; i< 9; j++)
			{
				City city = new City();
				city.CityName = pro.ProvinceName + " ����"+j;
				city.position = j;
				citylist.add(city);
			}
			pro.Cities = citylist;
			list.add(pro);
		}
		
		return list;
	}
	
}
