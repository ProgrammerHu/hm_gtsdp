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
 * 检索周边详细地址
 * @author Wen
 * @author HuFanglin
 *
 */
public class SelectAddressActivity extends GtsdpActivity implements LocationSource,
AMapLocationListener, OnMarkerClickListener, InfoWindowAdapter, OnClickListener, 
OnInfoWindowClickListener, OnMapClickListener, OnGeocodeSearchListener, OnMyLocationChangeListener, OnPoiSearchListener
{
	private final int FIND_ADDRESS = 100;
	private final String selectString= "住宅小区|楼宇相关|产业园区|风景名胜|住宿服务|医疗保健服务|生活服务|商场|知名企业|普通地名|道路名|路口名|紧急避难场所";
	
	//定位和地图相关
	private MapView mapView;
	private AMap aMap;
	private LocationManagerProxy mAMapLocationManager;
	private OnLocationChangedListener mListener;
	private GeocodeSearch geocodeSearch;
	private LatLng latLng;//标点的位置
	private Marker marker;
	private PoiSearch.Query query;// Poi查询条件类
	private PoiSearch poiSearch;
	private List<PoiItem> poiItems;// poi数据

	private String lng;
	private String lat;
	private String address;
	private boolean simpleAddress;
	
	//界面布局相关
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private ListView listviewAddress;
	private EditText editAddress;
	private LinearLayout layoutFind;
	//数据相关
	private List<AddressModel> addressList;
	private SelectAddressAdapter addressAdapter;
	private String ClickAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_set_address);
		super.onCreate(savedInstanceState);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		geocodeSearch = new GeocodeSearch(getApplication());
		geocodeSearch.setOnGeocodeSearchListener(this);
		initMap();
	}
	


	private void initMap() {
		if (aMap == null) {
			aMap = mapView.getMap();
			registerListener();
			aMap.setLocationSource(this);// 设置定位监听
			aMap.getUiSettings().setMyLocationButtonEnabled(false);
			aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
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
	 * 注册监听
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
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
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
		txtNext.setText("提交");
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("详细地址");
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
				{//确保List中有数据
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
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
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
			mListener.onLocationChanged(location);// 显示系统小蓝点
			float bearing = aMap.getCameraPosition().bearing;
			aMap.setMyLocationRotateAngle(bearing);// 设置小蓝点旋转角度
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
	 * 点击屏幕时
	 */
	@Override
	public void onMapClick(LatLng latLng) {
		this.latLng = latLng;
		getAddress(latLng.latitude, latLng.longitude);
	}

	// 逆地理编码
	private void getAddress(Double mlat, Double mLon) {
		lng = mLon.toString();
		lat = mlat.toString();
		LatLonPoint latLonPoint = new LatLonPoint(mlat, mLon);
		RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
				GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
		geocodeSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
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
		doSearchQuery(result.getRegeocodeAddress().getCity(), Double.parseDouble(lat), Double.parseDouble(lng));//查询当前点击点周边的地址
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
	 * 位置发生移动时就标记当前位置
	 */
	@Override
	public void onMyLocationChange(Location arg0) {
		getAddress(arg0.getLatitude(), arg0.getLongitude());
	}


	/**
	 * 开始进行poi搜索
	 */
	protected void doSearchQuery(String CityName, double lat, double lng) {
		aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件currentPage = 0;
		query = new PoiSearch.Query("", selectString, CityName);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
		query.setPageSize(20);// 设置每页最多返回多少条poiitem
		query.setPageNum(0);// 设置查第一页
		query.setLimitDiscount(false);// 所有poi
		query.setLimitGroupbuy(false);
		
		if(lng != 0 && lat != 0)
		{
			showProgressDialog("正在搜索周边");
			poiSearch = new PoiSearch(this, query);
			poiSearch.setOnPoiSearchListener(this);
			LatLonPoint lalo = new LatLonPoint(lat, lng);
			poiSearch.setBound(new SearchBound(lalo, 2000, true));//
			// 设置搜索区域为以lp点为圆心，其周围2000米范围
			/*
			 * List<LatLonPoint> list = new ArrayList<LatLonPoint>();
			 * list.add(lp);
			 * list.add(AMapUtil.convertToLatLonPoint(Constants.BEIJING));
			 * poiSearch.setBound(new SearchBound(list));// 设置多边形poi搜索范围
			 */
			poiSearch.searchPOIAsyn();// 异步搜索
		}
	}
	

	@Override
	public void onPoiItemDetailSearched(PoiItemDetail arg0, int arg1) {
	}
	/**
	 * 执行poi搜索之后
	 */
	@Override
	public void onPoiSearched(PoiResult result, int rCode) {
		aMap.setOnMapClickListener(this);
		cancelProgressDialog();
		if(rCode != 0)
		{
			showTextDialog("高德地图异常：" + GtsdpUtil.getAMapErrorString(rCode));
			return;
		}
		poiItems = result.getPois();
		if(poiItems == null || poiItems.size() == 0)
		{
			showTextDialog("没有找到您要想的结果");
		}
		addressList = new ArrayList<AddressModel>();
		addressList.add(new AddressModel(ClickAddress, "当前位置"));
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
