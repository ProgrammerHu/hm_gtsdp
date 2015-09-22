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
			pro.ProvinceName = "省份" + i;
			List<City> citylist = new ArrayList<City>();
			for(int j = 0; i< 9; j++)
			{
				City city = new City();
				city.CityName = pro.ProvinceName + " 城市"+j;
				city.position = j;
				citylist.add(city);
			}
			pro.Cities = citylist;
			list.add(pro);
		}
		
		return list;
	}
	
}
