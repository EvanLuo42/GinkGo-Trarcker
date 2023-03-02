package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.dto.CommentDto;
import com.phakel.ginkgo.tracker.entity.Comment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CommentRepository implements PanacheRepository<Comment> {
    public List<CommentDto> findCommentsByVideoIdToDto(String videoId) {
        return find("id", videoId)
                .list()
                .stream()
                .map(Comment::toDto)
                .toList();
    }
}
