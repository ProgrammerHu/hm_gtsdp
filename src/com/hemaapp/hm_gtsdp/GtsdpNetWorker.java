package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import xtom.frame.util.XtomDeviceUuidFactory;
import android.content.Context;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.hm_FrameWork.HemaNetWorker;
import com.hemaapp.hm_gtsdp.nettask.AlipayTradeTask;
import com.hemaapp.hm_gtsdp.nettask.ChangePwdTask;
import com.hemaapp.hm_gtsdp.nettask.ClientAddTask;
import com.hemaapp.hm_gtsdp.nettask.ClientGetTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginTask;
import com.hemaapp.hm_gtsdp.nettask.ClientLoginoutTask;
import com.hemaapp.hm_gtsdp.nettask.CodeVerifyTask;
import com.hemaapp.hm_gtsdp.nettask.DeviceSaveTask;
import com.hemaapp.hm_gtsdp.nettask.FileUploadTask;
import com.hemaapp.hm_gtsdp.nettask.GetTemplateTask;
import com.hemaapp.hm_gtsdp.nettask.InitTask;
import com.hemaapp.hm_gtsdp.nettask.NoticeListTask;
import com.hemaapp.hm_gtsdp.nettask.CurrentTask;
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
	 * 获取随机码
	 * @param username
	 * @param code 测试阶段固定向服务器提交“1234”
	 */
	public void getCodeVerify(String username, String code)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.CODE_VERIFY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// 用户登录名 手机号或邮箱
		params.put("code", code);
		GtsdpNetTask task = new CodeVerifyTask(information, params);
		executeTask(task);
	}
	/**
	 * 用户注册 
	 * @param temp_token 临时令牌
	 * @param username 用户注册名
	 * @param password 登陆密码 测试环境一律填写“123456”
	 * @param nickname 用户昵称
	 * @param address 详细地址
	 * @param sex 性别
	 * @param lng 经度
	 * @param lat 维度
	 */
	public void clientAdd(String temp_token, String username, String password, String nickname, String address, String sex,
			String lng, String lat)
	{
		if(GtsdpConfig.IS_DEVELOPMENT)
		{
			password = "123456";
		}
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_ADD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("temp_token", temp_token);
		params.put("username", username);
		params.put("password", password);
		params.put("nickname", nickname);
		params.put("address", address);
		params.put("sex", sex);
		params.put("lng", lng);
		params.put("lat", lat);
		GtsdpNetTask task = new ClientAddTask(information, params);
		executeTask(task);
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
	public void clientVerify(String username)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_VERIFY;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	
	/**
	 * 修改密码
	 * @param token 登录令牌
	 * @param keytype 密码类型 1：登陆密码 2：支付密码
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
	 * 重设密码
	 * @param temp_token 临时令牌 
	 * @param keytype 密码类型 1：登陆密码 2：支付密码
	 * @param new_password 新密码
	 */
	public void resetPwd(String temp_token, String keytype, String new_password)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_RESET;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("temp_token", temp_token);
		params.put("keytype", keytype);
		params.put("new_password", new_password);
		
		GtsdpNetTask task = new CurrentTask(information, params);
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
	/**
	 * 获取用户信息
	 * @param token 登录令牌
	 * @param id 通过被访问用户主键获取
	 */
	public void clientGet(String token, String id)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.CLIENT_GET;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("id", id); 
		
		GtsdpNetTask task = new ClientGetTask(information, params);
		executeTask(task);
	}
	/**
	 * 获取通知列表
	 * @param token 登录令牌
	 * @param keytype 业务类型 1：评论回复 2：好友申请 3：系统通知 
	 * @param page 页数
	 */
	public void getNoticeList(String token, String keytype, String page)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.NOTICE_LIST;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("keytype", keytype); 
		params.put("page", page); 
		
		GtsdpNetTask task = new NoticeListTask(information, params);
		executeTask(task);
	}
	/**
	 * 添加模板接口
	 * @param token 登录令牌
	 * @param keytype 业务类型 1：发件人模板；2：收件人模板
	 * @param name 姓名
	 * @param address 地址
	 * @param telphone 联系电话
	 */
	public void addTemplate(String token, String keytype, String name, String address, String telphone)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.TEMPLATE_ADD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌
		params.put("keytype", keytype);
		params.put("name", name);
		params.put("address", address);
		params.put("telphone", telphone);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 修改模板
	 * @param token 登录令牌
	 * @param id ID
	 * @param keytype 业务类型 1：发件人模板；2：收件人模板
	 * @param name 姓名
	 * @param address 地址
	 * @param telphone 联系电话
	 */
	public void saveTemplate(String token,String id, String keytype, String name, String address, String telphone)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.TEMPLATE_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌
		params.put("id", id);
		params.put("keytype", keytype);
		params.put("name", name);
		params.put("address", address);
		params.put("telphone", telphone);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 获取模板列表
	 * @param token 登录令牌
	 * @param keytype 业务类型 1：发件人模板；2：收件人模板
	 * @param page 第几页 从0开始
	 */
	public void getTemplateList(String token, String keytype, int page)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.TEMPLATE_LIST;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);// 登陆令牌
		params.put("keytype", keytype);
		params.put("page", String.valueOf(page));
		GtsdpNetTask task = new GetTemplateTask(information, params);
		executeTask(task);
	}
	/**
	 * 验证密保接口
	 * @param username 待修改密码的手机号
	 * @param ask1_id 密保1问题id
	 * @param answer1 密保1问题的答案
	 * @param ask2_id 密保2问题id
	 * @param answer2 密保2问题的答案
	 * @param ask3_id 密保3问题id
	 * @param answer3 密保3问题的答案
	 */
	public void checkAsk(String username, String ask1_id, String answer1
			, String ask2_id, String answer2
			, String ask3_id, String answer3)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_ASK_CHECK;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);// 登陆令牌
		params.put("ask1_id", ask1_id);
		params.put("answer1", answer1);
		params.put("ask2_id", ask2_id);
		params.put("answer2", answer2);
		params.put("ask3_id", ask3_id);
		params.put("answer3", answer3);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 保存密保接口
	 * @param token 登录令牌
	 * @param ask1_id 密保1问题id
	 * @param answer1 密保1问题的答案
	 * @param ask2_id 密保2问题id
	 * @param answer2 密保2问题的答案
	 * @param ask3_id 密保3问题id
	 * @param answer3 密保3问题的答案
	 */
	public void saveAsk(String token, String ask1_id, String answer1
			, String ask2_id, String answer2
			, String ask3_id, String answer3)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.PASSWORD_ASK_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("ask1_id", ask1_id);
		params.put("answer1", answer1);
		params.put("ask2_id", ask2_id);
		params.put("answer2", answer2);
		params.put("ask3_id", ask3_id);
		params.put("answer3", answer3);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 申请配送员
	 * @param token 登录令牌
	 * @param realname 真实姓名
	 * @param telphone 联系电话
	 * @param address 家庭住址
	 */
	public void deliveryAdd(String token, String realname, String telphone, String address)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.DELIVERY_ADD;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("realname", realname);
		params.put("telphone", telphone);
		params.put("address", address);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 保存银行卡
	 * @param token 登录令牌
	 * @param bankuser 开户名
	 * @param bankname 银行名称
	 * @param bankcard 卡号
	 * @param bankaddress 开户行地址
	 */
	public void bankSave(String token, String bankuser, String bankname, String bankcard, String bankaddress)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.BANK_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("bankuser", bankuser);
		params.put("bankname", bankname);
		params.put("bankcard", bankcard);
		params.put("bankaddress", bankaddress);
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
	/**
	 * 支付宝信息保存
	 * @param token 登录令牌
	 * @param aliuser 支付宝账号
	 */
	public void aliSave(String token, String aliuser)
	{
		GtsdpHttpInformation information = GtsdpHttpInformation.ALI_SAVE;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("aliuser", aliuser);	
		GtsdpNetTask task = new CurrentTask(information, params);
		executeTask(task);
	}
}
