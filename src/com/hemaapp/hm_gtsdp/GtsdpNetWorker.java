package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_gtsdp.nettask.AlipayTradeTask;
import com.hemaapp.hm_gtsdp.nettask.ChangePwdTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginoutTask;
import com.hemaapp.hm_gtsdp.nettask.DeviceSaveTask;
import com.hemaapp.hm_gtsdp.nettask.FileUploadTask;
import com.hemaapp.hm_gtsdp.nettask.InitTask;
import com.hemaapp.hm_gtsdp.nettask.UnionTradeTask;

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
	 * 修改密码
	 * @param token 登录令牌
	 * @param keytype 密码类型	1：登陆密码 2：支付密码
	 * @param old_password 旧密码
	 * @param new_password 新密码
	 */ 
	public void changePwd(String token, String keytype, String old_password, String new_password)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 版本号码(默认：1.0.0)
		params.put("keytype", keytype);// 登陆所用的系统版本号
		params.put("old_password", old_password);// 客户端硬件串号
		params.put("new_password", new_password);// 客户端硬件串号
		GtsdpNetTask task = new ChangePwdTask(information, params);
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
	/**
	 * 获取银联交易签名串
	 * @param token 登陆令牌
	 * @param paytype 支付类型 固定传2
	 * @param keytype 业务类型,1：账户余额充值2：商品立即购买
	 * @param keyid 业务相关,id当keytype=1时,keyid=0当keytype=2时,keyid=blog_id
	 * @param total_fee  支付交易金额,单位：元(测试时统一传递0.01元)
	 */
	public void unionpay(String token, String paytype, String keytype,
			String keyid, String total_fee) {
		GtsdpHttpInformation information = GtsdpHttpInformation.UNIONPAY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌
//		params.put("paytype", paytype);// 支付类型 固定传2
		params.put("keytype", keytype);// 业务类型,1：账户余额充值2：商品立即购买
		params.put("keyid", keyid);// 业务相关,id当keytype=1时,keyid=0当keytype=2时,keyid=blog_id
//		params.put("total_fee", total_fee);// 支付交易金额,单位：元(测试时统一传递0.01元)

		GtsdpNetTask task = new UnionTradeTask(information, params);
		executeTask(task);
	}
	/**
	 * 上传文件（图片，音频，视频）
	 * @param token 登录令牌
	 * @param keytype 上传操作类型 1：用户头像; 
	 * @param keyid 主键id 当keytype=1时，keyid=client_id；
	 * @param duration 播放时长 上传图片时，此值固定传"0"即可 单位：S(秒)
	 * @param orderby 排序上传多副图片时，传递上传次序  从0开始，依次递增
	 * @param content 内容描述  有的项目中，展示性图片需要附属一段文字说明信息。  默认传"无"
	 * @param temp_file 文件 临时需要上传的文件控件名称  对应表单type="file" 中的name值 ，相关文件请先在客户端压缩再上传（压缩尺寸宽度固定640）
	 */
	public void fileUpload(String token, String keytype, String keyid,
			String duration, String orderby, String content, String temp_file) {
		GtsdpHttpInformation information = GtsdpHttpInformation.FILE_UPLOAD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);//
		params.put("keytype", keytype); //
		params.put("keyid", keyid); //
		params.put("duration", duration); //
		params.put("orderby", orderby); //
		params.put("content", content);// 内容描述 有的项目中，展示性图片需要附属一段文字说明信息。默认传"无"
		HashMap<String, String> files = new HashMap<String, String>();
		files.put("temp_file", temp_file); //

		GtsdpNetTask task = new FileUploadTask(information, params, files);
		executeTask(task);
	}
}
