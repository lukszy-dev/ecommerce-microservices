package com.proggramik.user.repository

import com.proggramik.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.REQUIRED)
interface UserRepository : JpaRepository<User, Long>
