package com.learncode.Service;

import java.util.List;
import java.util.Optional;



import com.learncode.entity.Chucnang;
import com.learncode.entity.Nhomnguoidung;
import com.learncode.entity.Vaitro;

public interface NhomnguoidungService {

	List<Long> findChucnangNhom(Long idnhom);

	List<Vaitro> findAllVaiTro();

	List<Chucnang> findAllChucNang();

	Optional<Nhomnguoidung> findByLongId(Long id);

	List<Nhomnguoidung> findAllNhomNguoiDung();

	List<Nhomnguoidung> findByManhom(String manhom);

	void deleteNhomNguoiDung(Nhomnguoidung ndd);

	List<Nhomnguoidung> findByTennhom(String tennhom);

	void updateNhomNguoiDung(Nhomnguoidung ndd);

	void insertNhomNguoiDung(Nhomnguoidung nnd);

}
