package com.forstudy.board.repository;

import com.forstudy.board.domain.UserAccount;
import com.forstudy.board.domain.projection.UserAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
