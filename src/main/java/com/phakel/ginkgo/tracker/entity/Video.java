package com.phakel.ginkgo.tracker.entity;

import com.phakel.ginkgo.tracker.dto.VideoDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "video_db")
@Data
@NoArgsConstructor
public class Video extends PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToOne
    private User author;

    @Column(unique = true, nullable = false)
    private String hash;

    @OneToMany
    private List<Comment> comments;

    public VideoDto toDto() {
        return new VideoDto(
                id,
                title,
                description,
                author.toDto(),
                hash
        );
    }
}
