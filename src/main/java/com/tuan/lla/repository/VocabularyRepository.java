package com.tuan.lla.repository;

import com.tuan.lla.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    List<Vocabulary> findAllByTopicId(Long topicId);
=======
import java.util.UUID;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, UUID> {

    List<Vocabulary> findAllByTopicId(UUID topicId);
>>>>>>> dev
}
