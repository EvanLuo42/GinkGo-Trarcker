package com.phakel.ginkgo.tracker.entity;

import com.phakel.ginkgo.tracker.dto.ReplyDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "reply_table")
@Data
@NoArgsConstructor
public class Reply extends PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private String text;

    @OneToOne
    private User author;

    public ReplyDto toDto() {
        return new ReplyDto(id, text, author.toDto());
    }
}
