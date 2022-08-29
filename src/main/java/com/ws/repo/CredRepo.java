package com.ws.repo;

import com.ws.model.credential.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredRepo extends JpaRepository<CredentialEntity, Long> {
}
