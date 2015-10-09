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
	 * ����ʡ
	 */
	protected String[] mProvinceDatas;
	/**
	 * key - ʡ value - ��
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - �� values - ��
	 */
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

	/**
	 * key - �� values - �ʱ�
	 */
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

	/**
	 * ��ǰʡ������
	 */
	protected String mCurrentProviceName;
	/**
	 * ��ǰ�е�����
	 */
	protected String mCurrentCityName;
	/**
	 * ��ǰ��������
	 */
	protected String mCurrentDistrictName = "";

	/**
	 * ��ǰ������������
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
			mCurrentProviceName = "������";
			mCurrentCityName = "������";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ʡ������XML����
	 */
	protected void initProvinceDatas() {
		List<DistrictModel> provinceList = null;
		try 
		{
			InputStream input = asset.open("province_data.xml");
			// ����һ������xml�Ĺ�������
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// ����xml
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();
			// ��ȡ��������������
			provinceList = handler.getDataList();
			// */ ��ʼ��Ĭ��ѡ�е�ʡ���С���
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
				// ��������ʡ������
				mProvinceDatas[i] = provinceList.get(i).getName();
				List<DistrictModel> cityList = provinceList.get(i)
						.getChildList();
				String[] cityNames = new String[cityList.size()];
				for (int j = 0; j < cityList.size(); j++) {
					// ����ʡ����������е�����
					cityNames[j] = cityList.get(j).getName();
					List<DistrictModel> districtList = cityList.get(j)
							.getChildList();
					String[] distrinctNameArray = new String[districtList
							.size()];
					DistrictModel[] distrinctArray = new DistrictModel[districtList
							.size()];
					for (int k = 0; k < districtList.size(); k++) {
						// ����������������/�ص�����
						DistrictModel districtModel = new DistrictModel(
								districtList.get(k).getName(), districtList
										.get(k).getZipcode());
						// ��/�ض��ڵ��ʱ࣬���浽mZipcodeDatasMap
						mZipcodeDatasMap.put(districtList.get(k).getName(),
								districtList.get(k).getZipcode());
						distrinctArray[k] = districtModel;
						distrinctNameArray[k] = districtModel.getName();
					}
					// ��-��/�ص����ݣ����浽mDistrictDatasMap
					mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
				}
				// ʡ-�е����ݣ����浽mCitisDatasMap
				mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * ���ݵ�ǰ���У�������WheelView����Ϣ
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
		updateDistricts();
	}

	private void setUpData() {
		//TODO  initProvinceDatas();
		initProvinceJsonDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context,
				mProvinceDatas));
		// ���ÿɼ���Ŀ����
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateDistricts();
	}

	/**
	 * ���ݵ�ǰ���У�������WheelView����Ϣ
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
	 * ��ȡ������������ս��
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
	 * ��ȡ��assets�е�json����
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
			String provinceName = mProvinceDatas[i];//ʡ����
			String provinceId = String.valueOf(i+1);//ʡ��id��Ҫ��������
			for(int j = 0; j < jsonArray.length(); j++)
			{//��һ�α���
				String Name = jsonArray.getJSONObject(j).getString("name");//������
				String parentId = jsonArray.getJSONObject(j).getString("parentid");//���ڵ�id
				if(parentId.equals("0") && provinceName.equals(Name))
				{//����ֱϽ��
					cityList.add(provinceName);
					break;
				}
				else if(parentId.equals(provinceId))
				{//���ǵؼ���
					cityList.add(Name);
				}
			}
			if(cityList.size() == 0)
			{//�۰ĺ���
				String[] CitisDatas = new String[1];
				CitisDatas[0] = provinceName;
				mCitisDatasMap.put(provinceName, CitisDatas);
				mDistrictDatasMap.put(provinceName, CitisDatas);
			}
			else
			{//�����ĳ���
				String[] CitisDatas = new String[cityList.size()];
				for(int j = 0; j < cityList.size(); j++)
				{//�ڶ��α�������ʵ�ַ�������
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
