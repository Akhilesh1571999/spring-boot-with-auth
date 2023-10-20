package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {

	List<Credentials> findByEmail(String email);

}
