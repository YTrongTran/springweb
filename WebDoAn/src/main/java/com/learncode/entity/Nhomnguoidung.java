package com.learncode.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_nhomnguoidung")
public class Nhomnguoidung {
	@Id
	@GeneratedValue(generator = "bigidn")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long id;

	@Column(name = "manhom")
	@NotBlank(message = "Bắt buộc nhập mã nhóm")
	private String manhom;

	@Column(name = "tennhom")
	@NotBlank(message = "Bắt buộc nhập tên nhóm")
	private String tennhom;

	@Column(name = "nguoitao")
	@NotBlank(message = "Bắt buộc nhập người tạo nhóm")
	private String nguoitao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createday;

	@Column(name = "nguoiupdate")
	private String nguoiupdate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date updateday;

	@Column(name = "isdelete")
	private int isdelete = 0;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "qtht_nhomnguoidungchucnang", joinColumns = {
			@JoinColumn(name = "idnhom", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idchucnang", referencedColumnName = "id") })
	private Set<Chucnang> chucnang = new HashSet<>();
	
	
	public Set<Chucnang> getChucnang() {
		return chucnang;
	}

	public void setChucnang(Set<Chucnang> chucnang) {
		this.chucnang = chucnang;
	}

	public Nhomnguoidung() {
		super();
	}

	
	public Nhomnguoidung(long id, @NotBlank(message = "Bắt buộc nhập mã nhóm") String manhom,
			@NotBlank(message = "Bắt buộc nhập tên nhóm") String tennhom,
			@NotBlank(message = "Bắt buộc nhập người tạo nhóm") String nguoitao, Date createday, String nguoiupdate,
			Date updateday, int isdelete, Set<Chucnang> chucnang) {
		super();
		this.id = id;
		this.manhom = manhom;
		this.tennhom = tennhom;
		this.nguoitao = nguoitao;
		this.createday = createday;
		this.nguoiupdate = nguoiupdate;
		this.updateday = updateday;
		this.isdelete = isdelete;
		this.chucnang = chucnang;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getManhom() {
		return manhom;
	}

	public void setManhom(String manhom) {
		this.manhom = manhom;
	}

	public String getTennhom() {
		return tennhom;
	}

	public void setTennhom(String tennhom) {
		this.tennhom = tennhom;
	}

	public String getNguoitao() {
		return nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public Date getCreateday() {
		return createday;
	}

	public void setCreateday(Date createday) {
		this.createday = createday;
	}

	public String getNguoiupdate() {
		return nguoiupdate;
	}

	public void setNguoiupdate(String nguoiupdate) {
		this.nguoiupdate = nguoiupdate;
	}

	public Date getUpdateday() {
		return updateday;
	}

	public void setUpdateday(Date updateday) {
		this.updateday = updateday;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}


}
