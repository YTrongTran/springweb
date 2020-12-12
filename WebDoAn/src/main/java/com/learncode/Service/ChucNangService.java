package com.learncode.Service;


import java.util.List;
import java.util.Optional;


import com.learncode.entity.Chucnang;

public interface ChucNangService {

	List<String> maapi();

	List<Chucnang> findChucnangByTennguoidung(String tennguoidung);

	List<Chucnang> findByMachucnang(String machucnang);

	long count(Long id);

	void updateChucNang(Chucnang cn);

	void insertChucNang(Chucnang cn);

	List<Chucnang> findByTenchucnang(String tenchucnang);

	Optional<Chucnang> findById(Long id);

	Optional<Chucnang> findByChucNangEditId(Long id);

	List<Chucnang> getAllChucNangParent();

	List<Chucnang> getAllChucNang();

	List<Chucnang> findAllChucNang();

	


}
