package com.learncode.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.learncode.Repository.ChucNangRepository;
import com.learncode.entity.Chucnang;

@Service
public class ChucNangServiceImpl implements ChucNangService {

	@Autowired
	ChucNangRepository chucNangRepository;
	
	
	@Override
	public List<Chucnang> findAllChucNang() {
		return chucNangRepository.findAllChucNang();
	}

	
	@Override
	public List<Chucnang> getAllChucNang() {
		return chucNangRepository.getAllChucNang();
	}
	

	@Override
	public List<Chucnang> getAllChucNangParent(){
		return chucNangRepository.getAllChucNangParent();
	}
	
	
	
	@Override
	public Optional<Chucnang> findByChucNangEditId(Long id){
		System.out.println("tim đến id");
		return this.chucNangRepository.findByChucNangEditId(id);
	}


	@Override
	public Optional<Chucnang> findById(Long id) {
		return chucNangRepository.findById(id);
	}


	@Override
	public List<Chucnang> findByTenchucnang(String tenchucnang) {
		return chucNangRepository.findByTenchucnang(tenchucnang);
	}

	@Override
	public void insertChucNang(Chucnang cn) {
		this.chucNangRepository.save(cn);
	}


	@Override
	public void updateChucNang(Chucnang cn) {
		System.out.println("update day");
		this.chucNangRepository.save(cn);
	}

	@Override
	public long count(Long id) {
		return chucNangRepository.count(id);
	}

	@Override
	public List<Chucnang> findByMachucnang(String machucnang) {
		return chucNangRepository.findByMachucnang(machucnang);
	}

	@Override
	public List<Chucnang> findChucnangByTennguoidung(String tennguoidung) {
		return chucNangRepository.findChucnangByTennguoidung(tennguoidung, tennguoidung);
	}

	@Override
	public List<String> maapi() {
		return chucNangRepository.maapi();
	}
}
