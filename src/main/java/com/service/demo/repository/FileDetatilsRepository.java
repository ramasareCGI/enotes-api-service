package com.service.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.demo.entity.FileDetails;

@Repository
public interface FileDetatilsRepository extends JpaRepository<FileDetails,Integer> {

}
