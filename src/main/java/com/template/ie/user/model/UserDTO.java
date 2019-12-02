package com.template.ie.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "数据传输模型")
public class UserDTO {
	
	@ApiModelProperty(value = "用户唯一索引")
    private String userId;

	@ApiModelProperty(value = "终端id,用于服务器向终端推送信息")
    private String terminalId;

	@ApiModelProperty(value = "用户账号：手机号、微信号？")
    private String account;

	@ApiModelProperty(value = "昵称，如果没有默认为用户账号")
    private String nickName;
	
	@ApiModelProperty(value = "所属公司id")
	private String companyId;
	
	@ApiModelProperty(value = "所在公司名称")
	private String companyName;

	@ApiModelProperty(value = "账号级别  1-游客  2-普通会员 3-收费会员")
    private Integer level;

	@ApiModelProperty(value = "我的头像url，如果没有默认给一张")
    private String faceImageUrl;

	@ApiModelProperty(value = "我的头像大图url，如果没有默认给一张")
    private String faceImageBigUrl;

	@ApiModelProperty(value = "新用户注册后默认后台生成二维码")
    private String qrCode;

	@ApiModelProperty(value = "爱好")
    private String hobby;

	@ApiModelProperty(value = "手机号码")
    private String phone;

	@ApiModelProperty(value = "性别 0-女 1-男")
    private Integer sex;

	@ApiModelProperty(value = "电子邮箱")
    private String email;

	@ApiModelProperty(value = "实名验证标志，0-未验证 1-已验证")
    private Integer isReal;

	@ApiModelProperty(value = "登录状态 0-在线 1-离线")
	private Integer loginStatus;
}