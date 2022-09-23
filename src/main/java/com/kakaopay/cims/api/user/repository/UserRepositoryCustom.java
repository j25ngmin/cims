package com.kakaopay.cims.api.user.repository;
import com.kakaopay.cims.core.security.AuthorizedUser;

public interface UserRepositoryCustom {
    AuthorizedUser findAuthCustomByUserId(String userId);
}
