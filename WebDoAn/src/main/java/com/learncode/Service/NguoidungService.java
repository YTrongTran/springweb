package com.learncode.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.learncode.entity.Chucnang;
import com.learncode.entity.Nguoidung;
import com.learncode.entity.Nhomnguoidung;
import com.learncode.entity.Vaitro;

public interface NguoidungService {

	

	List<String> findUrlNd(String tennguoidung);

	Nguoidung findUrl(String tennguoidung);

	Nguoidung findById1(Long id);

	List<Long> findByIdvaitro(Long idnguoidung);

	List<Long> findByIdnhom(Long idnguoidung);

	Nguoidung findUrlChucNang(String tennguoidung);

	List<Nguoidung> findByTennguoidung(String tennguoidung);

	List<Nguoidung> getAllNguoiDung();

	Optional<Nguoidung> findNguoidungById(Long id);

	void deleteNguoidung(Nguoidung nd);

	Nguoidung findByTen(String tennguoidung);

	void updateNguoidung(Nguoidung nd);

	void insertNguoidung(Nguoidung nd);

	List<Vaitro> finAllVaiTro();



	List<Nhomnguoidung> findAllNhom();

	List<Chucnang> finAllChucNang();

}
