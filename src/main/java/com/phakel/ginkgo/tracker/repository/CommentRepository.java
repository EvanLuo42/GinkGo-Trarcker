package com.phakel.ginkgo.tracker.repository;

import com.phakel.ginkgo.tracker.dto.CommentDto;
import com.phakel.ginkgo.tracker.dto.ReplyDto;
import com.phakel.ginkgo.tracker.entity.Comment;
import com.phakel.ginkgo.tracker.entity.Reply;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentRepository implements PanacheRepository<Comment> {
    public List<CommentDto> findByVideoIdToDto(String videoId) {
        return find("id", videoId)
                .list()
                .stream()
                .map(Comment::toDto)
                .toList();
    }

    public List<ReplyDto> findRepliesByCommentIdToDto(String commentId) {
        return find("id", commentId)
                .firstResult()
                .getReplies()
                .stream()
                .map(Reply::toDto)
                .toList();
    }

    public boolean isCommentExistByCommentId(String commentId) {
        return find("id", commentId).firstResultOptional().isPresent();
    }
}
