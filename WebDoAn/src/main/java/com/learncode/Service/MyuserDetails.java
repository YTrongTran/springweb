package com.learncode.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learncode.entity.Chucnang;
import com.learncode.entity.Nguoidung;

public class MyuserDetails implements UserDetails {
	
	private Nguoidung nguoidung;
	
	public MyuserDetails(Nguoidung nguoidung) {
		this.nguoidung = nguoidung;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();
		for (Chucnang privilege : nguoidung.getVaitro().get(0).getChucnang()) {
			authorites.add(new SimpleGrantedAuthority(privilege.getMachucnang()));
        }
		return authorites;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return nguoidung.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nguoidung.getTennguoidung();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
