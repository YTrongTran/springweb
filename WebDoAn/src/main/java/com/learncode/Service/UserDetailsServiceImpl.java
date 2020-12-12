package com.learncode.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learncode.entity.Nguoidung;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	NguoidungService nguoidungService;

	
	
	@Override
	public UserDetails loadUserByUsername(String tennguoidung) throws UsernameNotFoundException {

		Nguoidung nguoidung = this.nguoidungService.findByTen(tennguoidung);
		if (nguoidung == null) {
			throw new UsernameNotFoundException("Could not find!!!");
		};
		
		return new MyuserDetails(nguoidung);
	}
}
