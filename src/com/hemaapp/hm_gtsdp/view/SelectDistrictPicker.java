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
	 * ����ʡ
	 */
	protected String[] mProvinceDatas;
	/**
	 * key - ʡ value - ��
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

	/**
	 * ��ǰʡ������
	 */
	protected String mCurrentProviceName;
	/**
	 * ��ǰ�е�����
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
		
		// ����SelectPicPopupWindow��View
		this.setContentView(mMenuView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(LayoutParams.MATCH_PARENT);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		// ����SelectPicPopupWindow�������嶯��Ч��
		this.setAnimationStyle(R.style.AnimationBottomDialog);
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0x90000000);
		// ����SelectPicPopupWindow��������ı���
		this.setBackgroundDrawable(dw);
		setUpData();
	}
	
	public void setOnClickListener(OnClickListener onClickListener)
	{
		btnDistrictPickerCancel.setOnClickListener(onClickListener);
		btnDistrictPickerClose.setOnClickListener(onClickListener);
	}
	

	/**
	 * ����ʡ������XML����
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
	 * ���ݵ�ǰ��ʡ��������WheelView����Ϣ
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
		// ���ÿɼ���Ŀ����
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		updateCities();
	}

	/**
	 * ���ݵ�ǰ���У�������WheelView����Ϣ
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
	 * ��ȡ������������ս��
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
			String provinceName = mProvinceDatas[i];//ʡ����
			String provinceId = provincesArray.getJSONObject(i).getString("id");
			for(int j = 0; j < jsonArray.length(); j++)
			{
				//��һ�α���
				String Name = jsonArray.getJSONObject(j).getString("name");//������
				String parentId = jsonArray.getJSONObject(j).getString("parentid");//���ڵ�id
				if(parentId.equals(provinceId))
				{//���ǵؼ���
					cityList.add(Name);
				}
			}
			String[] CitisDatas = new String[cityList.size()];
			for (int j = 0; j < cityList.size(); j++) {// �ڶ��α�������ʵ�ַ�������
				CitisDatas[j] = cityList.get(j);
				String[] aa = new String[1];
				aa[0] = CitisDatas[j];
			}
			mCitisDatasMap.put(provinceName, CitisDatas);
			
		}
	}
}
