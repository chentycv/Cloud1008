package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILE")
public class File {

	private long id;
	private String name;
	private long size;
	private long parent;
	private long owner;
	private String path;
	private String thumbnail;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
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
	@Column(name = "SIZE")
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Column(name = "OWNER")
	public long getOwner() {
		return owner;
	}
	public long getParent() {
		return parent;
	}
	@Column(name = "PARENT")
	public void setParent(long parent) {
		this.parent = parent;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	@Column(name = "PATH")
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "THUMBNAIL")
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
