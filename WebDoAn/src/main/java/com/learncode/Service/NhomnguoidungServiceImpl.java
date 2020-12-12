package com.learncode.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learncode.Repository.ChucNangRepository;
import com.learncode.Repository.NhomnguoidungRepository;
import com.learncode.Repository.VaitroRepository;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Nhomnguoidung;
import com.learncode.entity.Vaitro;

@Service
public class NhomnguoidungServiceImpl implements NhomnguoidungService{

	@Autowired
	NhomnguoidungRepository nhomNguoiDungRepository;

	@Autowired
	ChucNangRepository chucNangRepository;
	
	@Autowired
	VaitroRepository vaiTroRepository;
	
	
	@Override
	public void insertNhomNguoiDung(Nhomnguoidung nnd) {
		this.nhomNguoiDungRepository.save(nnd);

	}

	@Override
	@Cacheable(value = "nhom", key = "#ndd.nguoiupdate")
	public void updateNhomNguoiDung(Nhomnguoidung ndd) {
		this.nhomNguoiDungRepository.save(ndd);

	}

	
	@Override
	public List<Nhomnguoidung> findByTennhom(String tennhom) {
		return this.nhomNguoiDungRepository.findByTennhom(tennhom);
	}

	@Override
	public void deleteNhomNguoiDung(Nhomnguoidung ndd) {
		ndd.setUpdateday(new Timestamp(new Date().getTime()));
		this.nhomNguoiDungRepository.updateNhomNguoiDung(ndd.getManhom(), ndd.getTennhom(), ndd.getUpdateday(), ndd.getNguoiupdate(), ndd.getId());
	}

	@Override
	public List<Nhomnguoidung> findByManhom(String manhom) {
		return this.nhomNguoiDungRepository.findByManhom(manhom);
	}

	@Override
	public List<Nhomnguoidung> findAllNhomNguoiDung() {
		return this.nhomNguoiDungRepository.findAllNhomNguoiDung();
	}

	@Override
	@Cacheable(value = "nhom", key = "#id")
	public Optional<Nhomnguoidung> findByLongId(Long id) {
		return this.nhomNguoiDungRepository.findByLongId(id);
	}

	@Override
	public List<Chucnang> findAllChucNang(){
		return (List<Chucnang>)this.chucNangRepository.findAllChucNang();
	}
	
	@Override
	public List<Vaitro> findAllVaiTro(){
		return (List<Vaitro>)this.vaiTroRepository.findAll();
	}

	@Override
	public List<Long> findChucnangNhom(Long idnhom) {
		return this.nhomNguoiDungRepository.findChucnangNhom(idnhom);
	}
}
