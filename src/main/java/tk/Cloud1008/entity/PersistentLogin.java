package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSISTENTLOGIN")
public class PersistentLogin {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private long id;

	@Column(name="USERID")
    private long userId;
    
    @Column(name="SERIES")
    private String series;
	
    @Column(name="TOKEN")
    private String token;
    
    @Column(name="LASTUSED")
    private String LastUsed;    
    
    
	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
    public long getUserID() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLastUsed() {
		return LastUsed;
	}

	public void setLastUsed(String LastUsed) {
		this.LastUsed = LastUsed;
	}

}
