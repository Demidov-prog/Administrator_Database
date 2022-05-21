package com.admin.database.repository;

import com.admin.database.entity.Client;
import com.admin.database.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Transactional
    void deleteAllByTrainer(Trainer trainer);

    @Query(value = "select client.* from client join trainer on trainer.id = client.trainer_id " +
            "where ('' = :firstName or first_name = :firstName) and " +
            "('' = :telephone or telephone = :telephone) and " +
            "('' = :lastName or last_name = :lastName) and " +
            "('' = :trainerName or trainer.name = :trainerName)", nativeQuery = true)
    List<Client> findAllClientsByFirstNameAndLastNameAndTelephoneAndTrainer(
            String firstName, String lastName, String telephone, String trainerName);
}
