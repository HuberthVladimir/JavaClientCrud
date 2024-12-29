package com.huberthvladimir.JavaClient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huberthvladimir.JavaClient.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
