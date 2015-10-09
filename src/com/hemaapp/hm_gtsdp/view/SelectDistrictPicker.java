package com.hemaapp.hm_gtsdp.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_gtsdp.activity.MainActivity;
import com.hemaapp.hm_gtsdp.model.DistrictModel;
import com.hemaapp.hm_gtsdp.service.XmlParserHandler;
import com.hemaapp.hm_gtsdp.view.widget.OnWheelChangedListener;
import com.hemaapp.hm_gtsdp.view.widget.WheelView;
import com.hemaapp.hm_gtsdp.view.widget.adapters.ArrayWheelAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class SelectDistrictPicker extends PopupWindow implements
		OnWheelChangedListener {
	private WheelView id_province, id_city, id_district;
	private Button btnDistrictPickerCancel, btnDistrictPickerClose;
	private View mMenuView;
	private AssetManager asset;
	private WheelView mViewProvince, mViewCity, mViewDistrict;
	private Context context;

	/**
	 * 所有省
	 */
	protected String[] mProvinceDatas;
	/**
	 * key - 省 value - 市
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区
	 */
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

	/**
	 * key - 区 values - 邮编
	 */
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

	/**
	 * 当前省的名称
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	protected String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	protected String mCurrentDistrictName = "";

	/**
	 * 当前区的邮政编码
	 */
	protected String mCurrentZipCode = "";

	public SelectDistrictPicker(Activity context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.dialog_district_picker, null);
		asset = context.getAssets();
		id_province = (WheelView) mMenuView.findViewById(R.id.id_province);
		id_city = (WheelView) mMenuView.findViewById(R.id.id_city);
		id_district = (WheelView) mMenuView.findViewById(R.id.id_district);
		btnDistrictPickerCancel = (Button) mMenuView.findViewById(R.id.btnDistrictPickerCancel);
		btnDistrictPickerClose = (Button) mMenuView.findViewById(R.id.btnDistrictPickerClose);

		mViewProvince = (WheelView) mMenuView.findViewById(R.id.id_province);
		mViewCity = (WheelView) mMenuView.findViewById(R.id.id_city);
		mViewDistrict = (WheelView) mMenuView.findViewById(R.id.id_district);
		mViewProvince.addChangingListener(this);
		mViewCity.addChangingListener(this);
		mViewDistrict.addChangingListener(this);
		
//		btnDistrictPickerCancel.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dismiss();
//			}
//		});
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationBottomDialog);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x90000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		setUpData();
	}
	
	public void setOnClickListener(OnClickListener onClickListener)
	{
		btnDistrictPickerCancel.setOnClickListener(onClickListener);
		btnDistrictPickerClose.setOnClickListener(onClickListener);
	}
	

	/**
	 * 解析省市区的XML数据
	 */
	protected void initProvinceJsonDatas() {
		try {
			mProvinceDatas = getProvinces();
			initCitisDatasMap();
			mCurrentProviceName = "北京市";
			mCurrentCityName = "北京市";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析省市区的XML数据
	 */
	protected void initProvinceDatas() {
		List<DistrictModel> provinceList = null;
		try 
		{
			InputStream input = asset.open("province_data.xml");
			// 创建一个解析xml的工厂对象
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// 解析xml
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();
			// 获取解析出来的数据
			provinceList = handler.getDataList();
			// */ 初始化默认选中的省、市、区
			if (provinceList != null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<DistrictModel> cityList = provinceList.get(0)
						.getChildList();
				if (cityList != null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0)
							.getChildList();
					mCurrentDistrictName = districtList.get(0).getName();
					mCurrentZipCode = districtList.get(0).getZipcode();
				}
			}
			// */
			mProvinceDatas = new String[provinceList.size()];
			for (int i = 0; i < provinceList.size(); i++) {
				// 遍历所有省的数据
				mProvinceDatas[i] = provinceList.get(i).getName();
				List<DistrictModel> cityList = provinceList.get(i)
						.getChildList();
				String[] cityNames = new String[cityList.size()];
				for (int j = 0; j < cityList.size(); j++) {
					// 遍历省下面的所有市的数据
					cityNames[j] = cityList.get(j).getName();
					List<DistrictModel> districtList = cityList.get(j)
							.getChildList();
					String[] distrinctNameArray = new String[districtList
							.size()];
					DistrictModel[] distrinctArray = new DistrictModel[districtList
							.size()];
					for (int k = 0; k < districtList.size(); k++) {
						// 遍历市下面所有区/县的数据
						DistrictModel districtModel = new DistrictModel(
								districtList.get(k).getName(), districtList
										.get(k).getZipcode());
						// 区/县对于的邮编，保存到mZipcodeDatasMap
						mZipcodeDatasMap.put(districtList.get(k).getName(),
								districtList.get(k).getZipcode());
						distrinctArray[k] = districtModel;
						distrinctNameArray[k] = districtModel.getName();
					}
					// 市-区/县的数据，保存到mDistrictDatasMap
					mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
				}
				// 省-市的数据，保存到mCitisDatasMap
				mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateDistricts() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(context,
				areas));
		mViewDistrict.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity
				.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
		mViewCity.setCurrentItem(0);
		updateDistricts();
	}

	private void setUpData() {
		//TODO  initProvinceDatas();
		initProvinceJsonDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context,
				mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateDistricts();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(context,
				areas));
		mViewDistrict.setCurrentItem(0);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		} else if (wheel == mViewCity) {
			updateAreas();
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
	}
	
	/**
	 * 获取行政区域的最终结果
	 * @return
	 */
	public String getDistrcictString()
	{
//		return mCurrentProviceName + mCurrentCityName + mCurrentDistrictName;
		if(mCurrentProviceName.equals(mCurrentCityName))
			return mCurrentProviceName;
		return mCurrentProviceName + mCurrentCityName;
	}
	
	private String[] getProvinces() throws Exception
	{
		String provinceStr = getAssetsJsonStr("province.json");
		JSONArray json = new JSONObject(provinceStr).getJSONArray("listItems");
		if(json == null)
		{
			return null;
		}
		else
		{
			int size = json.length();
			String []  provinces = new String [size];
			for(int i = 0; i < size; i++)
			{
				provinces[i] = json.getJSONObject(i).getString("name");
			}
			return provinces;
		}
	}
	
	/**
	 * 获取在assets中的json数据
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private String getAssetsJsonStr(String fileName) throws Exception
	{
		try 
		{
			AssetManager asset =context.getAssets();
			InputStream input = asset.open(fileName);
			int length = input.available();
			byte[] buffer = new byte[length];
			input.read(buffer);
			String jsonStr = EncodingUtils.getString(buffer, "error");  
			return jsonStr;
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
	}
	
	private void initCitisDatasMap() throws Exception
	{
		String jsonStr = getAssetsJsonStr("city.json");
		JSONArray jsonArray = new JSONObject(jsonStr).getJSONArray("listItems");
		for(int i = 0; i< mProvinceDatas.length; i++)
		{
			List<String> cityList = new ArrayList<String>();
			String provinceName = mProvinceDatas[i];//省份名
			String provinceId = String.valueOf(i+1);//省份id，要连续排列
			for(int j = 0; j < jsonArray.length(); j++)
			{//第一次遍历
				String Name = jsonArray.getJSONObject(j).getString("name");//城市名
				String parentId = jsonArray.getJSONObject(j).getString("parentid");//父节点id
				if(parentId.equals("0") && provinceName.equals(Name))
				{//这是直辖市
					cityList.add(provinceName);
					break;
				}
				else if(parentId.equals(provinceId))
				{//这是地级市
					cityList.add(Name);
				}
			}
			if(cityList.size() == 0)
			{//港澳海外
				String[] CitisDatas = new String[1];
				CitisDatas[0] = provinceName;
				mCitisDatasMap.put(provinceName, CitisDatas);
				mDistrictDatasMap.put(provinceName, CitisDatas);
			}
			else
			{//正常的城市
				String[] CitisDatas = new String[cityList.size()];
				for(int j = 0; j < cityList.size(); j++)
				{//第二次遍历，充实字符串数组
					CitisDatas[j] = cityList.get(j);
					String[] aa = new String[1];
					aa[0] = CitisDatas[j];
					mDistrictDatasMap.put(CitisDatas[j], aa);
				}
				mCitisDatasMap.put(provinceName, CitisDatas);
				
			}
		}
	}
}
