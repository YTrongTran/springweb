package com.learncode.Service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import com.learncode.Repository.ChucNangRepository;
import com.learncode.Repository.NguoidungRepository;
import com.learncode.Repository.NhomnguoidungRepository;
import com.learncode.Repository.VaitroRepository;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Nguoidung;
import com.learncode.entity.Nhomnguoidung;
import com.learncode.entity.Vaitro;


@Service
public class NguoiDungImpl implements NguoidungService{
	
	@Autowired
	NguoidungRepository nguoiDungRepository;
	
	@Autowired
	NhomnguoidungRepository nhomNguoiDungRepository;
	@Autowired
	ChucNangService chucnangService;
	@Autowired
	ChucNangRepository chucNangRepository;
	
	@Autowired
	VaitroRepository vaiTroRepository;
	
	@Override
	public List<Nhomnguoidung> findAllNhom(){
		return this.nhomNguoiDungRepository.findAllNhomNguoiDung();
	}
	
	@Override
	public List<Chucnang> finAllChucNang(){
		return this.chucnangService.findAllChucNang();
	}

	@Override
	public List<Vaitro> finAllVaiTro(){
		return this.vaiTroRepository.listVaiTro();
	}

	@Override
	public void insertNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.save(nd);


	}

	@Override
	@Cacheable(value = "nguoidung", key = "#nguoidung.nguoiupdate")
	public void updateNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.save(nd);


	}
	
	@Override
	public Nguoidung findByTen(String tennguoidung) {
		return this.nguoiDungRepository.findByTen(tennguoidung);
	}

	@Override
	public void deleteNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.updateNguoidung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(), nd.getId());
	}
	
	
	@Override
	@Cacheable(value = "nguoidung", key = "#id")
	public Optional<Nguoidung> findNguoidungById(Long id) {
		return this.nguoiDungRepository.findNguoidungById(id);
	}

	@Override
	public List<Nguoidung> getAllNguoiDung() {
		return this.nguoiDungRepository.getAllNguoiDung();
	}
	
	
	@Override
	public List<Nguoidung> findByTennguoidung(String tennguoidung){
		return this.nguoiDungRepository.findByTennguoidung(tennguoidung);
	}
	
	@Override
	public Nguoidung findUrlChucNang(String tennguoidung){
		Nguoidung nd = this.findByTen(tennguoidung);
		
		return nd;
	}
	


	
	@Override
	public List<Long> findByIdnhom(Long idnguoidung) {
		return this.nguoiDungRepository.findByIdnhom(idnguoidung);
	}

	@Override
	public List<Long> findByIdvaitro(Long idnguoidung) {
		return this.nguoiDungRepository.findByIdvaitro(idnguoidung);
	}

	
	@Override
	public Nguoidung findById1(Long id) {
		return this.nguoiDungRepository.findById1(id);
	}



	@Override
	public Nguoidung findUrl(String tennguoidung) {
		return nguoiDungRepository.findUrl(tennguoidung, tennguoidung);
	}

	@Override
	public List<String> findUrlNd(String tennguoidung) {
		return nguoiDungRepository.findUrlNd(tennguoidung);
	}

}
