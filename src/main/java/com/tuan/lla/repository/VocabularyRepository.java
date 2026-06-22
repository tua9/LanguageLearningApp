package com.tuan.lla.repository;

import com.tuan.lla.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, UUID> {

    List<Vocabulary> findAllByTopicId(UUID topicId);
}
