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
}