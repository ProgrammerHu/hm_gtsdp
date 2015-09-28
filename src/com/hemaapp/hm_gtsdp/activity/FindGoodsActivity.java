package com.hemaapp.hm_gtsdp.activity;

import xtom.frame.util.XtomSharedPreferencesUtil;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;

public class FindGoodsActivity extends GtsdpActivity implements  LocationSource,
AMapLocationListener, OnMarkerClickListener, InfoWindowAdapter, OnClickListener, 
OnInfoWindowClickListener, OnMapClickListener, OnGeocodeSearchListener
{
	//地图相关
	private MapView mapView;
	private AMap aMap;
	private LocationManagerProxy mAMapLocationManager;
	private OnLocationChangedListener mListener;
	private GeocodeSearch geocodeSearch;
	private LatLng latLng;//标点的位置
	private String lng;
	private String lat;
	private Marker marker;
	private String address;
	
	
	//界面布局相关
	private TextView txtTitle, txtNext;
	private ImageView imageQuitActivity;
	private Button btnConfirm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_findgoods);
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
			aMap.getUiSettings().setMyLocationButtonEnabled(true);
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
	protected void findView() {
		mapView = (MapView) findViewById(R.id.mapView);
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("更改路线");
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("找货");
		imageQuitActivity = (ImageView)findViewById(R.id.imageQuitActivity);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		txtNext.setOnClickListener(this);
		imageQuitActivity.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.btnConfirm:
			intent = new Intent(this, PublishRouteActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		case R.id.txtTitle:
			intent = new Intent(this, PublishRouteActivity.class);
			startActivity(intent);
			//这里要加上当前的起止位置
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
			
		}
		
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
	public boolean onMarkerClick(Marker marker) {
		if (!marker.isInfoWindowShown())
			marker.showInfoWindow();
		else
			marker.hideInfoWindow();
		return false;
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
		Log.e(lng, lat);
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
				&& result.getRegeocodeAddress().getFormatAddress() != null) {
			address = result.getRegeocodeAddress().getFormatAddress();
			try {
				String township = result.getRegeocodeAddress().getTownship();
				address = address.split(township)[1];
			} catch (Exception e) {
				// ignore
			}
			showMarker();
		} else {
			// nothing
		}
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
		marker.showInfoWindow();
	}


	@Override
	public void onInfoWindowClick(Marker marker) {
		Intent intent = new Intent(this, GoodsDetailActivity.class);
		intent.putExtra("ActivityType", GtsdpConfig.USER_IDENTIFY_CURSOR);
		startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.none);
//		marker.hideInfoWindow();
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		View infoWindow = getLayoutInflater().inflate(
				R.layout.custom_info_window, null);
		return infoWindow;
	}

	/**
	 * 自定义infowinfow窗口
	 */
	public void render(Marker marker, View view) {
		
	}
}
