package com.tuan.lla.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

@Getter
@Setter
@Entity
@Table(name = "vocabularies")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
<<<<<<< HEAD
    private Long id;
=======
    private UUID id;
>>>>>>> dev

    @Size(max = 100)
    @NotNull
    @Column(name = "word", nullable = false, length = 100)
    private String word;

    @Size(max = 20)
    @NotNull
    @Column(name = "word_type", nullable = false, length = 20)
    private String wordType;

<<<<<<< HEAD
    @NotNull
    @Column(name = "definition", nullable = false, length = Integer.MAX_VALUE)
    private String definition;

=======
>>>>>>> dev
    @Column(name = "sample_sentence", length = Integer.MAX_VALUE)
    private String sampleSentence;

    @Column(name = "image_url", length = Integer.MAX_VALUE)
    private String imageUrl;

    @Column(name = "audio_url", length = Integer.MAX_VALUE)
    private String audioUrl;

<<<<<<< HEAD
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "topic_id", nullable = false)
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "topic_id", nullable = true)
>>>>>>> dev
    private Topic topic;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

<<<<<<< HEAD
=======
    @Size(max = 10)
    @Column(name = "level", length = 10)
    private String level;

    @Size(max = 255)
    @Column(name = "pronunciation")
    private String pronunciation;

>>>>>>> dev

}