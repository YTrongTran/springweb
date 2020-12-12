package com.learncode.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learncode.entity.Nhomnguoidung;

@Repository
@Transactional
public interface NhomnguoidungRepository extends CrudRepository<Nhomnguoidung, Long> {

	@Modifying
	@Query(value = "INSERT INTO public.qtht_nhomnguoidung(id, manhom, tennhom, createday, nguoitao, updateday, nguoiupdate, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
	void insertNhomNguoiDung(@Param("id") Long id, @Param("manhom") String manhom, @Param("tennhom") String tennhom, @Param("createday") Date createday, @Param("nguoitao") String nguoitao, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("isdelete") Integer isdelete);
	
	@Modifying
	@Query(value = "INSERT INTO public.qtht_nhomnguoidungchucnang(idnhom, idchucnang) VALUES (?, ?);", nativeQuery = true)
	void insertNhomNguoiDungChucNang(@Param("idnhom") Long idnhom, @Param("idchucnang") Long idchucnang);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_nhomnguoidung SET manhom=?, tennhom=?, updateday=?, nguoiupdate=? WHERE id=?;", nativeQuery = true)
	void updateNhomNguoiDung(@Param("manhom") String manhom, @Param("tennhom") String tennhom, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("id") Long id);
	
	
	@Modifying
	@Query(value = "DELETE FROM public.qtht_nhomnguoidungchucnang WHERE idnhom = ?;", nativeQuery = true)
	int deleteNhomNguoiDungChucNang(@Param("idnhom") Long idnhom);
	
	@Query(value = "SELECT id, manhom, tennhom, createday, nguoitao, updateday, nguoiupdate, isdelete FROM public.qtht_nhomnguoidung where id = ?;", nativeQuery = true)
	Optional<Nhomnguoidung> findByLongId(Long id);
	
	@Query(value = "SELECT id, manhom, tennhom, createday, nguoitao, updateday, nguoiupdate, isdelete FROM public.qtht_nhomnguoidung where isdelete = 0;", nativeQuery = true)
	List<Nhomnguoidung> findAllNhomNguoiDung();
	
	@Query(value = "SELECT * FROM qtht_nhomnguoidung WHERE tennhom @@ to_tsquery(?) and isdelete = 0", nativeQuery = true)
	List<Nhomnguoidung> findByTennhom(String tennhom);
	
	@Query(value = "SELECT * FROM qtht_nhomnguoidung WHERE manhom @@ to_tsquery(?1)", nativeQuery = true)
	List<Nhomnguoidung> findByManhom(String manhom);
	
	@Query(value = "SELECT idchucnang FROM qtht_nhomnguoidungchucnang WHERE idnhom = ?", nativeQuery = true)
	List<Long> findChucnangNhom(@Param("idnhom") Long idnhom);
	
}
