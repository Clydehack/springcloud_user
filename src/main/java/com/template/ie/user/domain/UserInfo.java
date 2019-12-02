package com.template.ie.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "领域模型")
@Document(collection="UserInfo")
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 7754596083112033103L;
	
	@ApiModelProperty(value = "用户唯一索引")
	@Id
    private String userId;

	@ApiModelProperty(value = "终端id,用于服务器向终端推送信息")
    private String terminalId;

	@ApiModelProperty(value = "用户账号：手机号、微信号？")
    private String account;

	@ApiModelProperty(value = "密码")
    private String passWord;
	
	@ApiModelProperty(value = "所属公司id")
	private String companyId;
	
	@ApiModelProperty(value = "所在公司名称")
	private String companyName;

	@ApiModelProperty(value = "盐")
    private String salt;

	@ApiModelProperty(value = "昵称，如果没有默认为用户账号")
    private String nickName;

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

	@ApiModelProperty(value = "用户真实姓名，实名认证使用")
    private String userName;

	@ApiModelProperty(value = "手机号码")
    private String phone;

	@ApiModelProperty(value = "性别 0-女 1-男")
    private Integer sex;

	@ApiModelProperty(value = "电子邮箱")
    private String email;

	@ApiModelProperty(value = "身份证号")
    private String idCard;

	@ApiModelProperty(value = "国家，如中国为CN")
    private String country;

	@ApiModelProperty(value = "用户个人资料填写的省份")
    private String province;

	@ApiModelProperty(value = "用户个人资料填写的城市")
    private String city;

	@ApiModelProperty(value = "用户个人资料填写的详细地址")
    private String address;

	@ApiModelProperty(value = "实名验证标志，0-未验证 1-已验证")
    private Integer isReal;

	@ApiModelProperty(value = "登录状态 0-在线 1-离线")
	private Integer loginStatus;
	
	@ApiModelProperty(value = "用户最后一次登录日期，用于判断活跃用户")
    private Date finalLogintime;

	@ApiModelProperty(value = "微信公众号上，用户的唯一标识")
    private String openId;
	
	@ApiModelProperty(value = "微信平台上，用户的唯一标识")
    private String unionId;

	@ApiModelProperty(value = "用户登录标识，使用与否待定")
    private String sessionId;

	@ApiModelProperty(value = "通用 - 创建人")
    private String userCreate;

	@ApiModelProperty(value = "通用 - 创建时间")
    private Date dateCreate;

	@ApiModelProperty(value = "通用 - 修改人")
    private String userUpdate;

	@ApiModelProperty(value = "通用 - 修改时间")
    private Date dateUpdate;

	@ApiModelProperty(value = "通用 - 乐观锁")
    private Integer version;
}