package com.admin.database.repository;

import com.admin.database.entity.Gym;
import com.admin.database.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {

    @Transactional
    void deleteAllByGym(Gym gym);

    List<Pass> findAllByPrice(Integer price);

}
