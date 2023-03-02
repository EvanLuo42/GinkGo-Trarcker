package com.phakel.ginkgo.tracker.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "video_slice_db")
@Data
@NoArgsConstructor
public class VideoSlice extends PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    private Video video;

    @Column
    private String hash;

    @OneToMany
    private List<Node> nodes;
}
