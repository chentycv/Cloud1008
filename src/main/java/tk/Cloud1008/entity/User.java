package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Long id;

	@Column(name="LOGINNAME")
    private String loginName;
	
	@Column(name="PASSWORD")
    private String password;
	
	@Column(name="EMAIL")
    private String email;
	
	@Column(name="MOBILE")
    private String mobile;
	
	@Column(name="NICKNAME")
    private String nickname;
	
	@Column(name="GENDER")
    private String gender;
	
	@Column(name="ONLINE")
    private Long online;
	
	@Column(name="SPACESIZE")
    private Long spaceSize;

	@Column(name="USEDSIZE")
    private Long usedSize;

	public Long getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public String getGender() {
		return gender;
	}

	public Long getOnline() {
		return online;
	}

	public Long getSpaceSize() {
		return spaceSize;
	}

	public Long getUsedSize() {
		return usedSize;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setOnline(Long online) {
		this.online = online;
	}

	public void setSpaceSize(Long spaceSize) {
		this.spaceSize = spaceSize;
	}

	public void setUsedSize(Long usedSize) {
		this.usedSize = usedSize;
	}	
}