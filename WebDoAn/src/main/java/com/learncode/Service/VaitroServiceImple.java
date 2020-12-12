package com.learncode.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learncode.Repository.ChucNangRepository;
import com.learncode.Repository.VaitroRepository;
import com.learncode.entity.Chucnang;
import com.learncode.entity.Vaitro;


@Service
@Transactional
public class VaitroServiceImple implements VaitroService {

	@Autowired
	VaitroRepository vaiTroRepository;
	
	@Autowired
	ChucNangService chucNangService;
	
	@Override
	public List<Chucnang> finAllChucNang(){
		return this.chucNangService.findAllChucNang();
	}
	
	@Override
	public void insertVaitro(Vaitro vt) {
		this.vaiTroRepository.save(vt);

	}

	@Override
	public List<Vaitro> listVaiTro() {
		return this.vaiTroRepository.listVaiTro();
	}

	@Override
	@Cacheable(value = "vaitro", key = "#vt.nguoiupdate")
	public void updateVaitro(Vaitro vt) {
		this.vaiTroRepository.save(vt);

	}

	@Override
	@Cacheable(value = "vaitro", key = "#id")
	public Optional<Vaitro> findByVaitroId(Long id) {
		return this.vaiTroRepository.findByVaitroId(id);
	}

	@Override
	public List<Vaitro> findByMavaitro(String mavaitro) {
		return this.vaiTroRepository.findByMavaitro(mavaitro);
	}

	@Override
	public List<Vaitro> findByTenvaitro(String tenvaitro) {
		return this.vaiTroRepository.findByTenvaitro(tenvaitro);
	}

	@Override
	public List<Long> findChucnangVaitro(Long idvaitro) {
		return this.vaiTroRepository.findChucnangVaitro(idvaitro);

	
	}
}
