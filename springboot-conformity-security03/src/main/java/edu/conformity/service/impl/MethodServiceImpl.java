package edu.conformity.service.impl;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.conformity.service.MethodService;

@Service
public class MethodServiceImpl implements MethodService {

	@PreAuthorize("hasRole('ROLE_dba')")
	@Override
	public String dba() {
		return "hello method dba!";
	}

	@PreAuthorize("hasAnyRole('ROLE_dba', 'ROLE_admin')")
	@Override
	public String admin() {
		return "hello method admin!";
	}

	@Secured("ROLE_user")
	@Override
	public String user() {
		return "hello method user!";
	}

}
