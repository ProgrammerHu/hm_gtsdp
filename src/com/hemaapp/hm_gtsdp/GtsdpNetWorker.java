package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_gtsdp.nettask.AlipayTradeTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginoutTask;
import com.hemaapp.hm_gtsdp.nettask.DeviceSaveTask;
import com.hemaapp.hm_gtsdp.nettask.InitTask;

/**
 * 网络请求工具类
 * @author Wen
 * @author HuFanglin
 *
 */
public class GtsdpNetWorker extends HemaNetWorker {
	private Context mContext;

	public GtsdpNetWorker(Context mContext) {
		super(mContext);
		this.mContext = mContext;
	}

	@Override
	public void clientLogin() {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * 登录
	 */
	public void clientLogin(String username, String password) {
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_LOGIN;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// 用户登录名 手机号或邮箱
		params.put("password", password); // 登陆密码 服务器端存储的是32位的MD5加密串
		params.put("devicetype", "2"); // 用户登录所用手机类型 1：苹果 2：安卓（方便服务器运维统计）
		String version = GtsdpUtil.getAppVersion(mContext);
		params.put("lastloginversion", version);// 登陆所用的系统版本号
		params.put("submit", "提交");

		GtsdpNetTask task = new ClientLoginTask(information, params);

		executeTask(task);
	}
	

	/**
	 * 退出登录
	 */
	public void clientLoginout(String token) {
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_LOGINOUT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌

		GtsdpNetTask task = new ClientLoginoutTask(information, params);
		executeTask(task);
	}

	@Override
	public boolean thirdSave() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 系统初始化
	 */
	public void init() {
		GtsdpHttpInformation information = GtsdpHttpInformation.INIT;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lastloginversion", GtsdpUtil.getAppVersion(mContext));// 版本号码(默认：1.0.0)
		params.put("devicetype", String.valueOf(GtsdpConfig.DEVICETYPE));// 登陆所用的系统版本号
		params.put("device_sn", XtomDeviceUuidFactory.get(mContext));// 客户端硬件串号
		GtsdpNetTask task = new InitTask(information, params);
		executeTask(task);
	}
	
	/**
	 * 硬件注册保存
	 */
	public void deviceSave(String token, String deviceid, String devicetype,
			String channelid) {
		GtsdpHttpInformation information = GtsdpHttpInformation.DEVICE_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌
		params.put("deviceid", deviceid);// 登陆手机硬件码 对应百度推送userid
		params.put("devicetype", devicetype);// 登陆手机类型 1:苹果 2:安卓
		params.put("channelid", channelid);// 百度推送渠道id 方便直接从百度后台进行推送测试

		GtsdpNetTask task = new DeviceSaveTask(information, params);
		executeTask(task);
	}
	

	
	/**
	 * 获取支付宝交易签名串
	 * @param token 登陆令牌
	 * @param keytype 业务类型，1：账户余额充值，2：商品立即购买
	 * @param keyid 业务相关,id当keytype=1时,keyid=0当keytype=2时,keyid=blog_id
	 * @param total_fee 支付交易金额,单位：元(测试时统一传递0.01元)
	 */
	public void alipay(String token, String keytype, String keyid, String total_fee) {
		GtsdpHttpInformation information = GtsdpHttpInformation.ALIPAY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌
//		params.put("paytype", paytype);// 支付类型 固定传1
		params.put("keytype", keytype);// 业务类型,1：账户余额充值2：商品立即购买
		params.put("keyid", keyid);// 业务相关,id当keytype=1时,keyid=0当keytype=2时,keyid=blog_id
//		params.put("total_fee", total_fee);// 支付交易金额,单位：元(测试时统一传递0.01元)

		GtsdpNetTask task = new AlipayTradeTask(information, params);
		executeTask(task);
	}


}
