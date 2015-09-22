package com.hemaapp.hm_gtsdp;

import com.hemaapp.GtsdpConfig;
import com.hemaapp.HemaConfig;
import com.hemaapp.hm_FrameWork.HemaHttpInfomation;
import com.hemaapp.hm_gtsdp.model.SysInitInfo;

/**
 * 网络请求信息枚举类
 */
public enum GtsdpHttpInformation implements HemaHttpInfomation {
	/**
	 * 登录
	 */
	CLIENT_LOGIN(HemaConfig.ID_LOGIN, "client_login", "登录", false),
	// 注意登录接口id必须为HemaConfig.ID_LOGIN
	/**
	 * 后台服务接口根路径
	 */
	SYS_ROOT(0, GtsdpConfig.SYS_ROOT, "后台服务接口根路径", true),
	/**
	 * 系统初始化
	 */
	INIT(1, "index.php/webservice/index/init", "系统初始化", false),
	/**
	 * 验证用户名是否合法
	 */
	CLIENT_VERIFY(2, "client_verify", "验证用户名是否合法", false),
	/**
	 * 申请随机验证码
	 */
	CODE_GET(3, "code_get", "申请随机验证码", false),
	/**
	 * 验证随机码
	 */
	CODE_VERIFY(4, "code_verify", "验证随机码", false),
	/**
	 * 用户注册
	 */
	CLIENT_ADD(5, "client_add", "用户注册", false),
	/**
	 * 上传文件（图片，音频，视频）
	 */
	FILE_UPLOAD(6, "file_upload", "上传文件（图片，音频，视频）", false),
	/**
	 * 重设密码
	 */
	PASSWORD_RESET(7, "password_reset", "重设密码", false),
	/**
	 * 退出登录
	 */
	CLIENT_LOGINOUT(8, "client_loginout", "退出登录", false),
	/**
	 * 添加帖子
	 */
	BLOG_ADD(9, "blog_add", "添加帖子", false),
	/**
	 * 获取帖子列表
	 */
	BLOG_LIST(10, "blog_list", "获取帖子列表", false),
	/**
	 * 获取帖子详情信息
	 */
	BLOG_GET(11, "blog_get", "获取帖子详情信息", false),
	/**
	 * 获取行业类别
	 */
	TYPE_LIST(12, "type_list", "获取行业类别", false),
	/**
	 * 获取行业类别是否关注状态
	 */
	TYPELOVE_GET(13, "typelove_get", "获取行业类别是否关注状态", false),
	/**
	 * 添加收藏（关注）
	 */
	LOVE_ADD(14, "love_add", "添加收藏（关注）", false),
	/**
	 * 数据删除汇总
	 */
	DATA_REMOVE(15, "data_remove", "数据删除汇总", false),
	/**
	 * 获取帖子回复列表
	 */
	REPLY_LIST(16, "reply_list", "获取帖子回复列表", false),
	/**
	 * 添加评论
	 */
	REPLY_ADD(17, "reply_add", "添加评论", false),
	/**
	 * 获取名片详情
	 */
	CARD_GET(18, "card_get", "获取名片详情", false),
	/**
	 * 获取名片列表
	 */
	CARD_LIST(19, "card_list", "获取名片列表", false),
	/**
	 * 收藏名片接口（兼容更改分组）
	 */
	CARD_SAVE(20, "card_save", "收藏名片接口（兼容更改分组）", false),
	/**
	 * 保存名片
	 */
	CLIENTCARD_SAVE(21, "clientcard_save", "保存名片", false),
	/**
	 * 最近事件添加
	 */
	NEAR_ADD(22, "near_add", "最近事件添加", false),
	/**
	 * 获取名片收藏类别
	 */
	CARDTYPE_LIST(23, "cardtype_list", "获取名片收藏类别", false),
	/**
	 * 保存(修改)名片类别
	 */
	CARDTYPE_SAVE(24, "cardtype_save", "保存(修改)名片类别", false),
	/**
	 * 名片开始交换
	 */
	SWAP_START(25, "swap_start", "名片开始交换", false),
	/**
	 * 名片停止交换
	 */
	SWAP_STOP(26, "swap_stop", "名片停止交换", false),
	/**
	 * 获取用户个人资料
	 */
	CLIENT_GET(27, "client_get", "获取用户个人资料", false),
	/**
	 * 保存用户资料
	 */
	CLIENT_SAVE(28, "client_save", "保存用户资料", false),
	/**
	 * 获取用户通知列表
	 */
	NOTICE_LIST(29, "notice_list", "获取用户通知列表", false),
	/**
	 * 保存用户通知操作
	 */
	NOTICE_SAVEOPERATE(30, "notice_saveoperate", "保存用户通知操作", false),
	/**
	 * 意见反馈
	 */
	ADVICE_ADD(31, "advice_add", "意见反馈", false),
	/**
	 * 修改并保存密码
	 */
	PASSWORD_SAVE(32, "password_save", "修改并保存密码", false),
	/**
	 * 团队保存(新增或修改)
	 */
	TEAM_SAVE(33, "team_save", "团队保存(新增或修改)", false),
	/**
	 * 获取团队列表
	 */
	TEAM_LIST(34, "team_list", "获取团队列表", false),
	/**
	 * 获取团队资料详情
	 */
	TEAM_GET(35, "team_get", "获取团队资料详情", false),
	/**
	 * 团队解散或退出
	 */
	TEAM_REMOVE(36, "team_remove", "团队解散或退出", false),
	/**
	 * 获取成员列表
	 */
	CLIENT_LIST(37, "client_list", "获取成员列表", false),
	/**
	 * 团队成员操作
	 */
	GROUP_CLIENT_SAVE(38, "group_client_save", "团队成员操作", false),
	/**
	 * 获取部门列表
	 */
	GROUP_DEPT_LIST(39, "group_dept_list", "获取部门列表", false),
	/**
	 * 新增(修改)部门
	 */
	GROUP_DEPT_SAVE(40, "group_dept_save", "新增(修改)部门", false),
	/**
	 * 保存帖子操作
	 */
	BLOG_SAVEOPERATE(41, "blog_saveoperate", "保存帖子操作", false),
	/**
	 * 获取系统类别列表
	 */
	SYSTYPE_GET(42, "systype_get", "获取系统类别列表", false),
	/**
	 * 保存系统类别
	 */
	SYSTYPE_SAVE(43, "systype_save", "保存系统类别", false),
	/**
	 * 保存(修改)客户
	 */
	VIP_SAVE(44, "vip_save", "保存(修改)客户", false),
	/**
	 * 获取客户详情信息
	 */
	VIP_GET(45, "vip_get", "获取客户详情信息", false),
	/**
	 * 获取客户列表信息
	 */
	VIP_LIST(46, "vip_list", "获取客户列表信息", false),
	/**
	 * 新增(修改)客户类别
	 */
	VIPTYPE_SAVE(47, "viptype_save", "新增(修改)客户类别", false),
	/**
	 * 获取客户类别列表
	 */
	VIPTYPE_LIST(48, "viptype_list", "获取客户类别列表", false),
	/**
	 * 添加工作
	 */
	WORK_ADD(49, "work_add", "添加工作", false),
	/**
	 * 获取工作列表
	 */
	WORK_LIST(50, "work_list", "获取工作列表", false),
	/**
	 * 获取工作详情
	 */
	WORK_GET(51, "work_get", "获取工作详情", false),
	/**
	 * 保存工作操作
	 */
	WORK_SAVEOPERATE(52, "work_saveoperate", "保存工作操作", false),
	/**
	 * 添加记账或业绩
	 */
	ACCOUNT_ADD(53, "account_add", "添加记账或业绩", false),
	/**
	 * 获取记账或业绩列表
	 */
	ACCOUNT_LIST(54, "account_list", "获取记账或业绩列表", false),
	/**
	 * 添加考勤
	 */
	CHECK_ADD(55, "check_add", "添加考勤", false),
	/**
	 * 业绩统计
	 */
	ACCOUNT_SUM(56, "account_sum", "业绩统计", false),
	/**
	 * 保存当前用户坐标
	 */
	POSITION_SAVE(57, "position_save", "保存当前用户坐标", false),
	/**
	 * 按日获取考勤列表
	 */
	CHECK_LIST_BYDATE(58, "check_list_bydate", "按日获取考勤列表", false),
	/**
	 * 按月获取考勤列表
	 */
	CHECK_LIST_BYMONTH(59, "check_list_bymonth", "按月获取考勤列表", false),
	/**
	 * 更改手机号码
	 */
	MOBILE_SAVE(60, "mobile_save", "更改手机号码", false),
	/**
	 * 硬件注册保存
	 */
	DEVICE_SAVE(61, "device_save", "硬件注册保存", false),
	/**
	 * 获取轨迹列表
	 */
	POSITION_LIST(62, "position_list", "获取轨迹列表", false),
	/**
	 * 获取考勤状态
	 */
	CHECK_GET(63, "check_get", "获取考勤状态", false),
	/**
	 * 分享回调
	 */
	SHARE_ADD(64, "share_add", "分享回调", false),
	/**
	 * 工作编辑保存
	 */
	WORK_SAVE(65, "work_save", "工作编辑保存", false),
	/**
	 * 转安排工作
	 */
	WORK_CHANGESAVE(66, "work_changesave", "转安排工作", false),
	/**
	 * 真聊天消息推送
	 */
	CHATPUSH_ADD(67, "chatpush_add", "真聊天消息推送", false),

	;

	private int id;// 对应NetTask的id
	private String urlPath;// 请求地址
	private String description;// 请求描述
	private boolean isRootPath;// 是否是根路径

	private GtsdpHttpInformation(int id, String urlPath, String description,
			boolean isRootPath) {
		this.id = id;
		this.urlPath = urlPath;
		this.description = description;
		this.isRootPath = isRootPath;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getUrlPath() {
		if (isRootPath)
			return urlPath;

		String path = SYS_ROOT.urlPath + urlPath;

		if (this.equals(INIT))
			return path;

		GtsdpApplication application = GtsdpApplication.getInstance();
		SysInitInfo info = application.getSysInitInfo();
		path = info.getSys_web_service() + urlPath;

		// if (this.equals(ALIPAY))
		// path = info.getSys_plugins() + urlPath;
		//
		// if (this.equals(UNIONPAY))
		// path = info.getSys_plugins() + urlPath;

		return path;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isRootPath() {
		return isRootPath;
	}

}
