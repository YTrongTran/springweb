package com.learncode.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;

import com.learncode.entity.Chucnang;
import com.learncode.entity.Vaitro;

public interface VaitroService  {

	List<Long> findChucnangVaitro(Long idvaitro);

	List<Vaitro> findByTenvaitro(String tenvaitro);

	List<Vaitro> findByMavaitro(String mavaitro);

	Optional<Vaitro> findByVaitroId(Long id);

	void updateVaitro(Vaitro vt);

	List<Vaitro> listVaiTro();

	void insertVaitro(Vaitro vt);

	List<Chucnang> finAllChucNang();

	

	

	


}
