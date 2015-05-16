package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_GROUP")
public class Group {

	private long id;
	private String name;
	private long spacesize;
	private long usedsize;
	private long remainingsize;
	
	@Id
	@GeneratedValue
	@Column(name ="ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "SPACESIZE")
	public long getSpacesize() {
		return spacesize;
	}
	public void setSpacesize(long spacesize) {
		this.spacesize = spacesize;
	}
	@Column(name = "USEDSIZE")
	public long getUsedsize() {
		return usedsize;
	}
	public void setUsedsize(long usedsize) {
		this.usedsize = usedsize;
	}
	
	@Column(name ="REMAININGSIZE")
	public long getRemainingsize() {
		return remainingsize;
	}
	public void setRemainingsize(long remainingsize) {
		this.remainingsize = remainingsize;
	}
}
