package com.ManchesterInside.ManchesterInside.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.Role;
import com.ManchesterInside.ManchesterInside.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public long count() {
		return roleRepository.count();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

}
