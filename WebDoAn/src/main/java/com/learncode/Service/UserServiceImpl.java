//package com.learncode.Service;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.learncode.Repository.RoleRepository;
//import com.learncode.Repository.UserRepository;
//import com.learncode.entity.Role;
//import com.learncode.entity.User;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	BCryptPasswordEncoder encoder;
//	@Autowired
//	RoleRepository roleRepository;
//	@Autowired
//	UserRepository userRepository;
//	
//	@Override
//	public void saveUser(User user) {
//		user.setPassword(encoder.encode(user.getPassword()));
//		Role userRole = roleRepository.findByName("USER");
//		user.setRole(new HashSet<Role>(Arrays.asList(userRole)));
//		userRepository.save(user);
//		
//	}
//
//	@Override
//	public boolean isUserAlreadyPresent(User user) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		userRepository.deleteById(id);
//		
//	}
//
//	@Override
//	public User findById(Long id) {
//		
//		return userRepository.findById(id).get();
//	}
//
//	@Override
//	public List<User> findAll() {
//		// TODO Auto-generated method stub
//		return (List<User>)userRepository.findAll();
//	}
//
//	@Override
//	public Page<User> findPaginated(int pageNo, int pageSize) {
//		Pageable pageble = PageRequest.of(pageNo -1, pageSize);
//		return this.userRepository.findAll(pageble);
//	}
//
//}
