package com.template.ie.user;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.template.ie.framework.util.MD5;
import com.template.ie.user.domain.UserInfo;

@Service
public class UserService {
	
	@Autowired private MongoTemplate mongoTemplate;
	
	/** 默认有8个头像文件名 */
	Integer[] defaultImg = {1,2,3,4,5,6,7,8};
	
	/** 注册/登录 TODO id 时间 */
	public UserInfo registOrLogin(String phone, String terminalId) throws Exception {
		/* 判断用户是否重复注册 */
		Criteria criatira = Criteria.where("phone").is(phone);
		UserInfo userInfo = mongoTemplate.findOne(new Query(criatira), UserInfo.class);
		/* 已注册就登录，未注册就注册 */
		if(null == userInfo) {
			userInfo = regist(phone, terminalId);
		} else {
			login(userInfo);
		}
		return userInfo;
	}
	private UserInfo regist(String phone, String terminalId) {
		/* 注册用户信息到数据库 */
		UserInfo userInfo = new UserInfo();
		Date date = new Date();
		String userId = MD5.md5(phone);
		userInfo.setUserId(userId);
		userInfo.setQrCode(userId);						// 默认后台生成二维码
		userInfo.setAccount(phone);
		userInfo.setPassWord("1");
		userInfo.setSalt("1");
		userInfo.setTerminalId(terminalId);				// 终端id
		userInfo.setNickName(phone);					// 昵称 - 注册时用账号代替
		userInfo.setPhone(phone);
		userInfo.setLevel(1);							// 账号级别  1-游客  2-普通会员 3-收费会员
		
		String defaultImgUrl = "face_img/" + getdefaultImgUrl() + ".png";
		userInfo.setFaceImageUrl(defaultImgUrl);		// 默认给一张头像地址
		userInfo.setFaceImageBigUrl(defaultImgUrl);		// 默认给一张头像大图地址
		
		userInfo.setDateCreate(date);
		userInfo.setDateUpdate(date);
		userInfo.setUserCreate(phone);
		userInfo.setUserUpdate(phone);
		
		mongoTemplate.save(userInfo);
		return userInfo;
	}
	@Transactional
	private UserInfo login(UserInfo userInfo) {
		/* 更新登录状态 */
		Date date = new Date();
		userInfo.setLoginStatus(0);
		userInfo.setFinalLogintime(date);
		userInfo.setDateUpdate(date);
		mongoTemplate.save(userInfo);
		/* 清理上一次的token 
		SystemAccessToken accessToken = tokenService.queryTokenInfoByUserId(userInfo.getUserId());
		if(null != accessToken) {
			i = tokenService.cancellationToken(accessToken.getToken());
	    	if(1 != i) {
	    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new CustomException("USER001", "其他端注销登录失败");
			}
		}*/
		return userInfo;
	}
	/* 随机抽一张图片作为默认头像 */
	public Integer getdefaultImgUrl() {
		int n = new Random().nextInt(defaultImg.length);
		return defaultImg[n];
	}
/*
	 更新用户最后登录时间 
	public void updateLastLoginTime(String userId) {
		UserInfo userInfo = queryUserInfoById(userId);
		if(null == userInfo) {
			throw new CustomException("USER004", "用户不存在");
		}
		Date date = new Date();
		userInfo.setLoginStatus(1);
		userInfo.setFinalLogintime(date);
		userInfo.setUserUpdate(userId);
		userInfo.setDateUpdate(date);
		int i = updateUserInfo(userInfo);
		if(1 != i) {
			throw new CustomException("USER004", "更新用户最后登录时间失败");
		}
	}
	
	*//** 修改用户信息 *//*
	public UserDTO updateUserInfo(UserQuery userQuery) throws Exception {
		UserInfo userInfo = queryUserInfoById(userQuery.getUserId());
		if(null == userInfo) {
			throw new CustomException("USER005", "用户信息获取失败");
		}
		BeanUtils.copyProperties(userQuery, userInfo);
		int i = updateUserInfo(userInfo);
		if(i == 0) {
			throw new CustomException("USER005", "用户信息修改失败");
		}
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userInfo, userDTO);
		return userDTO;
	}

	*//** 查询用户信息 *//*
	public UserDTO queryUser(String userId) throws Exception {
		UserInfo userInfo = queryUserInfoById(userId);
		if(null == userInfo) {
			throw new CustomException("USER007", "查询用户信息失败");
		}
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userInfo, userDTO);
		return userDTO;
	}
	
	public UserInfo queryUserInfoById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
	
	public Integer updateUserInfo(UserInfo userInfo) {
		return userMapper.updateByPrimaryKeySelective(userInfo);
	}
	
	*//** 用户提交认证公司 *//*
	public int addUserAuth(UserAuth userAuth) {
		return userAuthMapper.insertSelective(userAuth);
	}
	*//** 查询用户的认证信息 *//*
	public UserAuth queryUserAuth(String userId) {
		return userAuthMapper.selectByPrimaryKey(userId);
	}

	*//** 修改用户手机号*//*
	public void updatePhone(String phone, String userId) {
		UserInfo userInfo =  queryUserInfoById(userId);
		if(null == userInfo) {
			throw new CustomException("USER005", "用户信息获取失败");
		}
		Date date = new Date();
		userInfo.setPhone(phone);
		userInfo.setAccount(phone);
		userInfo.setUserId(userId);
		userInfo.setDateUpdate(date);
		userInfo.setUserUpdate(phone);
		int i = userMapper.updateByPrimaryKeySelective(userInfo);
		if(i == 0) {
			throw new CustomException("USER010", "用户手机号修改失败");
		}
	}

	*//** 用多个userId取出多个userInfo *//*
	public List<UserInfo> queryUserList(List<String> listUserID) {
		return userMapper.queryUserList(listUserID);

	}
*/
}