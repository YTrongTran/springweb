package com.learncode.Service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class CustomPermissionEvaluator implements PermissionEvaluator {
	@Autowired
	NguoidungService nguoidungService;
	@PersistenceContext
	EntityManager em;
private final String nativeQueryFindPermission ="select cn.machucnang, cn.id from qtht_chucnang cn\r\n" + 
		"left join qtht_vaitrovachucnang vt_cn on vt_cn.idchucnang = cn.id\r\n" + 
		"left join qtht_vaitro vt on vt.id = vt_cn.idvaitro\r\n" + 
		"left join qtht_nguoidungvavaitro nd_vt on  nd_vt.idvaitro = vt.id\r\n" + 
		"left join qtht_nguoidung nd on nd_vt.idnguoidung = nd.id \r\n" + 
		"where nd.tennguoidung = ?\r\n" + 
		"group by cn.id";

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		// TODO Auto-generated method stub
		if ((authentication != null) && (permission instanceof String)) {
			List<Object[]> resultList = (List<Object[]>) this.em.createNativeQuery(this.nativeQueryFindPermission).setParameter(1, authentication.getName()).getResultList();
			List<Object[]> data = resultList;
			for(Object[] per: data) {
				if(per[0].toString().equals(String.valueOf(permission))) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		System.out.println("permission: "+permission);
		return false;
	}

}


