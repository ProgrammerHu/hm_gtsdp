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

import xtom.frame.util.XtomSharedPreferencesUtil;

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
	private WheelView id_province, id_site;
	private Button btnDistrictPickerCancel, btnDistrictPickerClose;
	private View mMenuView;
	private AssetManager asset;
	private WheelView mViewProvince, mViewCity;
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
	 * 当前省的名称
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	protected String mCurrentCityName;
	
	private JSONArray provincesArray;

	public SelectDistrictPicker(Activity context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popupwindow_district_picker, null);
		asset = context.getAssets();
		id_province = (WheelView) mMenuView.findViewById(R.id.id_province);
		id_site = (WheelView) mMenuView.findViewById(R.id.id_site);
		btnDistrictPickerCancel = (Button) mMenuView.findViewById(R.id.btnDistrictPickerCancel);
		btnDistrictPickerClose = (Button) mMenuView.findViewById(R.id.btnDistrictPickerClose);

		mViewProvince = (WheelView) mMenuView.findViewById(R.id.id_province);
		mViewCity = (WheelView) mMenuView.findViewById(R.id.id_site);
		mViewProvince.addChangingListener(this);
		mViewCity.addChangingListener(this);
		
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
			mCurrentProviceName = mProvinceDatas[0];
			mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
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
//		updateDistricts();
	}

	private void setUpData() {
		initProvinceJsonDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context,
				mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		updateCities();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} 
	}
	
	/**
	 * 获取行政区域的最终结果
	 * @return
	 */
	public String getDistrcictString()
	{
		if(mCurrentProviceName.equals(mCurrentCityName))
			return mCurrentProviceName;
		return mCurrentProviceName + mCurrentCityName;
	}
	
	private String[] getProvinces() throws Exception
	{
		String provinceStr = XtomSharedPreferencesUtil.get(context, "provinces");
		JSONArray json = new JSONObject(provinceStr).getJSONArray("listItems");
		provincesArray = json;
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
	

	
	private void initCitisDatasMap() throws Exception
	{
		String jsonStr = XtomSharedPreferencesUtil.get(context, "sites");
		JSONArray jsonArray = new JSONObject(jsonStr).getJSONArray("listItems");
		for(int i = 0; i< mProvinceDatas.length; i++)
		{
			List<String> cityList = new ArrayList<String>();
			String provinceName = mProvinceDatas[i];//省份名
			String provinceId = provincesArray.getJSONObject(i).getString("id");
			for(int j = 0; j < jsonArray.length(); j++)
			{
				//第一次遍历
				String Name = jsonArray.getJSONObject(j).getString("name");//城市名
				String parentId = jsonArray.getJSONObject(j).getString("parentid");//父节点id
				if(parentId.equals(provinceId))
				{//这是地级市
					cityList.add(Name);
				}
			}
			String[] CitisDatas = new String[cityList.size()];
			for (int j = 0; j < cityList.size(); j++) {// 第二次遍历，充实字符串数组
				CitisDatas[j] = cityList.get(j);
				String[] aa = new String[1];
				aa[0] = CitisDatas[j];
			}
			mCitisDatasMap.put(provinceName, CitisDatas);
			
		}
	}
}
