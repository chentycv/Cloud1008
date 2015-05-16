package tk.Cloud1008.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="SHARE")
public class Share {

	private long id;	
	public enum Type{PUBLIC,PRIVATE};
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	private Date startTime;
	private Date endTime;
	private long foldid;
	private long fromUser;
	private long toUser;
	private long toGroup;	
	private String url;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "STARTTIME")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "ENDTIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "FOLDID")
	public long getFoldid() {
		return foldid;
	}
	public void setFoldid(long foldid) {
		this.foldid = foldid;
	}
	
	@Column(name = "FROMUSER")
	public long getFromUser() {
		return fromUser;
	}
	public void setFromUser(long fromUser) {
		this.fromUser = fromUser;
	}
	
	@Column(name= "TOUSER")
	public long getToUser() {
		return toUser;
	}
	public void setToUser(long toUser) {
		this.toUser = toUser;
	}
	
	@Column(name = "TOGROUP")
	public long getToGroup() {
		return toGroup;
	}
	public void setToGroup(long toGroup) {
		this.toGroup = toGroup;
	}

	
}
