package com.hemaapp.hm_gtsdp.activity;

import java.util.ArrayList;
import java.util.List;

import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.hemaapp.hm_gtsdp.R;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.GtsdpUtil;
import com.hemaapp.hm_gtsdp.adapter.SelectAddressAdapter;
import com.hemaapp.hm_gtsdp.model.AddressModel;

/**
 * �����ܱ���ϸ��ַ
 * @author Wen
 * @author HuFanglin
 *
 */
public class SelectAddressActivity extends GtsdpActivity implements LocationSource,
AMapLocationListener, OnMarkerClickListener, InfoWindowAdapter, OnClickListener, 
OnInfoWindowClickListener, OnMapClickListener, OnGeocodeSearchListener, OnMyLocationChangeListener, OnPoiSearchListener
{
	private final int FIND_ADDRESS = 100;
	private final String selectString= "סլС��|¥�����|��ҵ԰��|�羰��ʤ|ס�޷���|ҽ�Ʊ�������|�������|�̳�|֪����ҵ|��ͨ����|��·��|·����|�������ѳ���";
	
	//��λ�͵�ͼ���
	private MapView mapView;
	private AMap aMap;
	private LocationManagerProxy mAMapLocationManager;
	private OnLocationChangedListener mListener;
	private GeocodeSearch geocodeSearch;
	private LatLng latLng;//����λ��
	private Marker marker;
	private PoiSearch.Query query;// Poi��ѯ������
	private PoiSearch poiSearch;
	private List<PoiItem> poiItems;// poi����

	private String lng;
	private String lat;
	private String address;
	private boolean simpleAddress;
	
	//���沼�����
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private ListView listviewAddress;
	private EditText editAddress;
	private LinearLayout layoutFind;
	//�������
	private List<AddressModel> addressList;
	private SelectAddressAdapter addressAdapter;
	private String ClickAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_set_address);
		super.onCreate(savedInstanceState);
		mapView.onCreate(savedInstanceState);// �˷���������д
		geocodeSearch = new GeocodeSearch(getApplication());
		geocodeSearch.setOnGeocodeSearchListener(this);
		initMap();
	}
	


	private void initMap() {
		if (aMap == null) {
			aMap = mapView.getMap();
			registerListener();
			aMap.setLocationSource(this);// ���ö�λ����
			aMap.getUiSettings().setMyLocationButtonEnabled(false);
			aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
			try {
				String slat = XtomSharedPreferencesUtil.get(mContext, "lat");
				String slng = XtomSharedPreferencesUtil.get(mContext, "lng");
				double lat = Double.parseDouble(slat);
				double lng = Double.parseDouble(slng);
				LatLng latLng = new LatLng(lat, lng);
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,
						15);
				aMap.moveCamera(update);
			} catch (Exception e) {
			}
		}
	}


	/**
	 * ע�����
	 */
	private void registerListener() {
		aMap.setOnMapClickListener(this);
		aMap.setOnMarkerClickListener(this);
		aMap.setOnInfoWindowClickListener(this);
		aMap.setInfoWindowAdapter(this);
		aMap.setOnMyLocationChangeListener(this);
//		aMap.setOnCameraChangeListener(this);
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask arg0, HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		mapView = (MapView) findViewById(R.id.mapView);
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("�ύ");
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("��ϸ��ַ");
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		listviewAddress = (ListView)findViewById(R.id.listviewAddress);
		editAddress = (EditText)findViewById(R.id.editAddress);
		layoutFind = (LinearLayout)findViewById(R.id.layoutFind1);
	}

	@Override
	protected void getExras() {
	}

	@Override
	protected void setListener() {
		txtNext.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		layoutFind.setOnClickListener(this);
		listviewAddress.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(addressList != null && addressList.size() > position)
				{//ȷ��List��������
					String Address = addressList.get(position).getCityName()
							+addressList.get(position).getAdName()
							+addressList.get(position).getSnippet()
							+addressList.get(position).getAddressName();
					editAddress.setText(Address);
					addressAdapter.setCheckedItem(position);
					addressAdapter.notifyDataSetChanged();
				}
			}
		});
	}


	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.txtNext:
			Intent result = new Intent();
			result.putExtra("Address", editAddress.getEditableText().toString());
			setResult(0, result);
			finish();
			break;
		case R.id.imageQuitActivity:
			finish();
			break;
		case R.id.layoutFind1:
			Intent intent = new Intent(SelectAddressActivity.this, FindAddressActivity.class);
			startActivityForResult(intent, FIND_ADDRESS);
			overridePendingTransition(R.anim.bottom_in, R.anim.none);
			break;
		}
	}
	
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2�汾��������������true��ʾ��϶�λ�а���gps��λ��false��ʾ�����綨λ��Ĭ����true Location
			 * API��λ����GPS�������϶�λ��ʽ
			 * ����һ�������Ƕ�λprovider���ڶ�������ʱ�������2000���룬������������������λ���ף����ĸ������Ƕ�λ������
			 */
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, -1, 10, this);
		}
	}

	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if (mListener != null && location != null) {
			mListener.onLocationChanged(location);// ��ʾϵͳС����
			float bearing = aMap.getCameraPosition().bearing;
			aMap.setMyLocationRotateAngle(bearing);// ����С������ת�Ƕ�
		}
		deactivate();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (!marker.isInfoWindowShown())
			marker.showInfoWindow();
		else
			marker.hideInfoWindow();
		return false;
	}

	@Override
	public View getInfoContents(Marker marker) {
		log_i("getInfoContents");
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		log_i("getInfoWindow");
		return null;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		marker.hideInfoWindow();
	}

	/**
	 * �����Ļʱ
	 */
	@Override
	public void onMapClick(LatLng latLng) {
		this.latLng = latLng;
		getAddress(latLng.latitude, latLng.longitude);
	}

	// ��������
	private void getAddress(Double mlat, Double mLon) {
		lng = mLon.toString();
		lat = mlat.toString();
		LatLonPoint latLonPoint = new LatLonPoint(mlat, mLon);
		RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
				GeocodeSearch.AMAP);// ��һ��������ʾһ��Latlng���ڶ�������ʾ��Χ�����ף�������������ʾ�ǻ�ϵ����ϵ����GPSԭ������ϵ
		geocodeSearch.getFromLocationAsyn(query);// ����ͬ��������������
	}

	@Override
	public void onGeocodeSearched(GeocodeResult arg0, int arg1) {
	}

	@Override
	public void onRegeocodeSearched(RegeocodeResult result, int arg1) {
		if (result != null && result.getRegeocodeAddress() != null
				&& result.getRegeocodeAddress().getFormatAddress() != null) 
		{
			address = result.getRegeocodeAddress().getFormatAddress();
			if (simpleAddress)
				try {
					String township = result.getRegeocodeAddress()
							.getTownship();
					address = address.split(township)[1];
				} catch (Exception e) {
					// ignore
				}
			showMarker();
		} else {
			// nothing
		}
		doSearchQuery(result.getRegeocodeAddress().getCity(), Double.parseDouble(lat), Double.parseDouble(lng));//��ѯ��ǰ������ܱߵĵ�ַ
//		locationName.setText(address);
		ClickAddress = result.getRegeocodeAddress().getProvince()+
				result.getRegeocodeAddress().getCity()+
				result.getRegeocodeAddress().getDistrict() +
				result.getRegeocodeAddress().getStreetNumber().getStreet()+ 
				result.getRegeocodeAddress().getStreetNumber().getNumber();
//		locationDetails.setText();
		
	}

	/**
	 * 
	 */
	private void showMarker() {
		if (marker == null) {
			marker = aMap.addMarker(new MarkerOptions()
					.position(latLng)
					.title(address)
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
		} else {
			marker.remove();
			marker = aMap.addMarker(new MarkerOptions()
					.position(latLng)
					.title(address)
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
		}
	}

	/**
	 * λ�÷����ƶ�ʱ�ͱ�ǵ�ǰλ��
	 */
	@Override
	public void onMyLocationChange(Location arg0) {
		getAddress(arg0.getLatitude(), arg0.getLongitude());
	}


	/**
	 * ��ʼ����poi����
	 */
	protected void doSearchQuery(String CityName, double lat, double lng) {
		aMap.setOnMapClickListener(null);// ����poi����ʱ�������ͼ����¼�currentPage = 0;
		query = new PoiSearch.Query("", selectString, CityName);// ��һ��������ʾ�����ַ������ڶ���������ʾpoi�������ͣ�������������ʾpoi�������򣨿��ַ�������ȫ����
		query.setPageSize(20);// ����ÿҳ��෵�ض�����poiitem
		query.setPageNum(0);// ���ò��һҳ
		query.setLimitDiscount(false);// ����poi
		query.setLimitGroupbuy(false);
		
		if(lng != 0 && lat != 0)
		{
			showProgressDialog("���������ܱ�");
			poiSearch = new PoiSearch(this, query);
			poiSearch.setOnPoiSearchListener(this);
			LatLonPoint lalo = new LatLonPoint(lat, lng);
			poiSearch.setBound(new SearchBound(lalo, 2000, true));//
			// ������������Ϊ��lp��ΪԲ�ģ�����Χ2000�׷�Χ
			/*
			 * List<LatLonPoint> list = new ArrayList<LatLonPoint>();
			 * list.add(lp);
			 * list.add(AMapUtil.convertToLatLonPoint(Constants.BEIJING));
			 * poiSearch.setBound(new SearchBound(list));// ���ö����poi������Χ
			 */
			poiSearch.searchPOIAsyn();// �첽����
		}
	}
	

	@Override
	public void onPoiItemDetailSearched(PoiItemDetail arg0, int arg1) {
	}
	/**
	 * ִ��poi����֮��
	 */
	@Override
	public void onPoiSearched(PoiResult result, int rCode) {
		aMap.setOnMapClickListener(this);
		cancelProgressDialog();
		if(rCode != 0)
		{
			showTextDialog("�ߵµ�ͼ�쳣��" + GtsdpUtil.getAMapErrorString(rCode));
			return;
		}
		poiItems = result.getPois();
		if(poiItems == null || poiItems.size() == 0)
		{
			showTextDialog("û���ҵ���Ҫ��Ľ��");
		}
		addressList = new ArrayList<AddressModel>();
		addressList.add(new AddressModel(ClickAddress, "��ǰλ��"));
		for(PoiItem poiItem : poiItems)
		{
//			log_e(poiItem.toString() + poiItem.getLatLonPoint() + "," + poiItem.getCityName() + poiItem.getAdName() + poiItem.getTitle()+"\n"
//					+poiItem.getSnippet());
			AddressModel address = new AddressModel(poiItem);
			addressList.add(address);
		}
		addressAdapter = new SelectAddressAdapter(SelectAddressActivity.this, addressList);
		listviewAddress.setAdapter(addressAdapter);
		editAddress.setText(ClickAddress);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// TODO Auto-generated method stub
	}

}
