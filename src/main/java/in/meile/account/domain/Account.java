package in.meile.account.domain;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Account  {
	private Long id    ; 
	private Integer type;
	private Long companyAccountId;
	private String companyName;
	private String name; 
	private String password;
	private String contactPerson; 
	private String contactWay;
	private Integer status;
	private Date created; 
	private Date lastLogin;
	private Date lastmodify;
	private String email;
	private String salt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getCompanyAccountId() {
		return companyAccountId;
	}
	public void setCompanyAccountId(Long companyAccountId) {
		this.companyAccountId = companyAccountId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getLastmodify() {
		return lastmodify;
	}
	public void setLastmodify(Date lastmodify) {
		this.lastmodify = lastmodify;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
