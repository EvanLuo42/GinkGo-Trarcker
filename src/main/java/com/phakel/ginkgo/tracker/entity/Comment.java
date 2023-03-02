package com.phakel.ginkgo.tracker.entity;

import com.phakel.ginkgo.tracker.dto.CommentDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "video_comment_db")
@Data
@NoArgsConstructor
public class Comment extends PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToOne
    private User author;

    @Column(nullable = false)
    private String text;

    @OneToMany
    private List<Reply> replies;

    public CommentDto toDto() {
        return new CommentDto(id, author.toDto(), text);
    }
}
