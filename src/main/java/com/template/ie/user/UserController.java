package com.template.ie.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.ie.framework.Exception.CustomException;
import com.template.ie.framework.base.ResponseMessage;
import com.template.ie.user.domain.UserInfo;
import com.template.ie.user.model.UserDTO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userInfo")
public class UserController {
	
	/** 用户服务 */
	@Autowired private UserService userService;
	
	/** 注册/登录 */
	@ApiOperation(value = "注册/登录", notes = "使用账号和密码获得用户信息")
	@ApiImplicitParams({
    @ApiImplicitParam(name = "phone", required = true, dataType = "String", value = "手机号"),
    @ApiImplicitParam(name = "verificationCode", required = true, dataType = "String", value = "验证码"),
    @ApiImplicitParam(name = "terminalId", required = true, dataType = "String", value = "用于IM推送信息的设备唯一id")
	})
	@PostMapping("/registOrLogin")
	public String registOrLogin(String phone, String verificationCode, String terminalId) {
		/* 定义返回值 */
		String result = "";
		try {
			/* 校验数据 
			if (StringUtil.isNull(phone)) {
				throw new CustomException("USER001", "手机号不能为空");
			}*/
			/* 手机号码验证 
			String[] check = ValidatorUtil.validatePhoneNum(phone, true, "手机号码", "");
			if("false".equals(check[0])) {
				throw new CustomException("USER001", check[1]);
			}
			if (StringUtil.isNull(verificationCode)) {
				throw new CustomException("USER001", "验证码不能为空");
			}*/
			/* 校验验证码 
			boolean b = sMService.getSend(phone,verificationCode);
			if( !b ) {
				throw new CustomException("USER009", "验证码验证失败");
			}*/
			/* 业务调用 */
			UserInfo userInfo = userService.registOrLogin(phone, terminalId);
			/* 把user赋值给userDTO */
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(userInfo, userDTO);
			// TODO
			//String token = tokenService.getToken(userDTO.getUserId(), null);

			result = ResponseMessage.createOkMsg("用户登录成功", "token", userDTO);
			
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}
/*
	*//** 注销登录 *//*
	@ApiOperation(value = "用户注销登录", notes = "使用账号和密码获得用户信息")
	@PostMapping("/userloggeroutloggerin")
	public String userloggeroutloggerin(@RequestParam String userId, @RequestParam String token) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (StringUtil.isNull(userId)) {
				throw new CustomException("USER004", "用户id必传");
			}
			if (StringUtil.isNull(token)) {
				throw new CustomException("USER004", "token必传");
			}
			 更新用户最后登录时间 
			userService.updateLastloggerinTime(userId);
			 注销token 
			tokenService.cancellationToken(token);
			result = ResponseMessage.createOkMsg("退出登录成功");
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}

	*//** 修改用户信息 *//*
	@ApiOperation(value = "修改用户信息", notes = "用户快速注册后，补全信息后，游客身份会提升为会员")
	@PostMapping("/updateUserInfo")
	public String updateUserInfo(UserQuery query) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (StringUtil.isNull(query.getUserId())) {
				throw new CustomException("USER005", "用户id必传");
			}
			UserDTO userDTO = userService.updateUserInfo(query);
			result = ResponseMessage.createOkMsg("用户注销登录成功", userDTO);
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}

	*//** 上传用户头像 *//*
	@ApiOperation(value = "上传用户头像", notes = "上传用户头像")
	@PostMapping("/uploadFace")
	public String uploadFace(@RequestParam String userId, @RequestParam("file") MultipartFile file) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (StringUtil.isNull(userId)) {
				throw new CustomException("USER002", "用户id必传");
			}
			if (file.isEmpty()) {
				throw new CustomException("USER002", "请选择文件");
			}

			 调用业务逻辑 
			String faceImageUrl = faceImg + userId + file.getOriginalFilename();
			UserInfo userInfo = userService.queryUserInfoById(userId);
			if (null == userInfo) {
				throw new CustomException("USER002", "无效的用户信息");
			}
			userInfo.setFaceImageUrl("face_img/" + file.getOriginalFilename());
			userInfo.setFaceImageBigUrl("face_img/" + file.getOriginalFilename());
			int i = userService.updateUserInfo(userInfo);
			if (1 != i) {
				throw new CustomException("USER002", "用户图片上传失败");
			}
			 存储图片，视情况使用 file.getOriginalFilename() 
			file.transferTo(new File(faceImageUrl));
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(userInfo, userDTO);
			result = ResponseMessage.createOkMsg("上传用户头像成功", userDTO);
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}

	*//** 查询用户信息 *//*
	@ApiOperation(value = "查询用户信息")
	@PostMapping("/queryUserInfo")
	public String queryUserInfo(@RequestParam String userId) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (StringUtil.isNull(userId)) {
				throw new CustomException("USER007", "用户id必传");
			}
			UserDTO userDTO = userService.queryUser(userId);
			result = ResponseMessage.createOkMsg("用户信息成功", userDTO);
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}
	
	*//** 上传认证信息 *//*
	@ApiOperation(value = "公司认证", notes = "上传营业执照，认证经营者")
	@PostMapping("/authCompany")
	public String authCompany(@Valid UserAuthQuery query, @RequestParam("file") MultipartFile file) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (file.isEmpty()) {
				throw new CustomException("USER008", "请选择文件");
			}

			String userId = query.getUserId();
			 调用业务逻辑 
			UserInfo userInfo = userService.queryUserInfoById(userId);
			if (null == userInfo) {
				throw new CustomException("USER008", "无效的用户信息");
			}
			UserAuth userAuth = new UserAuth();
			BeanUtils.copyProperties(query, userAuth);
			Date date = new Date();
			userAuth.setBusinessLicense("license_img/" + file.getOriginalFilename());
			userAuth.setSubTime(date);
			userAuth.setDateCreate(date);
			userAuth.setDateUpdate(date);
			userAuth.setUserCreate(userId);
			userAuth.setUserUpdate(userId);
			int i = userService.addUserAuth(userAuth);
			if (1 != i) {
				throw new CustomException("USER008", "上传认证信息失败");
			}
			 存储图片 
			String licenseImgUrl = licenseImg + file.getOriginalFilename();
			file.transferTo(new File(licenseImgUrl));
			result = ResponseMessage.createOkMsg("上传认证信息成功");
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}
	
	*//** 查询认证信息 *//*
	@ApiOperation(value = "查询认证信息", notes = "查到信息，认证经营者")
	@PostMapping("/queryAuthInfo")
	public String queryAuthInfo(@RequestParam String userId) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (StringUtil.isNull(userId)) {
				throw new CustomException("USER007", "用户id必传");
			}
			UserInfo userInfo = userService.queryUserInfoById(userId);
			if (null == userInfo) {
				throw new CustomException("USER008", "无效的用户信息");
			}
			UserAuth userAuth = userService.queryUserAuth(userId);
			if(null == userAuth) {
				userAuth = new UserAuth();
			}
			result = ResponseMessage.createOkMsg("查询认证信息成功", userAuth);
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}
	
	*//** 修改用户手机号*//*
	@ApiOperation(value = "修改手机号", notes = "修改手机号")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "phone", required = true, dataType = "String", value = "手机号"),
	@ApiImplicitParam(name = "verificationCode", required = true, dataType = "String", value = "验证码"),
	@ApiImplicitParam(name = "userId", required = true, dataType = "String", value = "用户id")})
	@PostMapping("/updatePhone")
	public String updatePhone(String phone, String verificationCode, String userId) {
		 定义返回值 
		String result = "";
		try {
			 校验数据 
			if (StringUtil.isNull(userId)) {
				throw new CustomException("USER007", "用户id必传");
			}
			 校验数据 
			if (StringUtil.isNull(phone)) {
				throw new CustomException("USER001", "手机号不能为空");
			}
			 手机号码验证 
			String[] check = ValidatorUtil.validatePhoneNum(phone, true, "手机号码", "");
			if("false".equals(check[0])) {
				throw new CustomException("USER001", check[1]);
			}
			if (StringUtil.isNull(verificationCode)) {
				throw new CustomException("USER001", "验证码不能为空");
			}
			 TODO	校验短信验证码 
			boolean b = sMService.getSend(phone,verificationCode);
			if( !b ) {
				throw new CustomException("USER009", "验证码验证失败");
			}
			userService.updatePhone(phone,userId);
			result = ResponseMessage.createOkMsg("修改手机号成功");
		} catch (CustomException e) {
			result = ResponseMessage.createErrMsg(e.getMessage(), e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseMessage.createErrMsg("系统错误，请联系管理员", "SYS999");
		}
		return result;
	}
*/
}