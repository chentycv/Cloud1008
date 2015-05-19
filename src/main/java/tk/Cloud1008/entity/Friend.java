package tk.Cloud1008.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FRIEND")
public class Friend {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Long id;

	@Column(name="USERAID")
    private Long userAId;
	
	@Column(name="USERBID")
    private Long userBId;

	public Long getId() {
		return id;
	}

	public Long getUserAId() {
		return userAId;
	}

	public Long getUserBId() {
		return userBId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserAId(Long userAId) {
		this.userAId = userAId;
	}

	public void setUserBId(Long userBId) {
		this.userBId = userBId;
	}
}