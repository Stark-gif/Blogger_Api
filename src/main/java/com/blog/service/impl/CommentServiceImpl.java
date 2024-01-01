package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

private PostRepository postRepo;
private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(" post not found with id:" + postId)
        );

        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());



        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id:" + commentId)
        );

        commentRepo.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);

        List<CommentDto> commentDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
return commentDtos;
    }

    @Override
    public CommentDto updateCommentById(long commentId, CommentDto commentDto) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment not found with id:" + commentId)
        );

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());


        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());


        return dtos;
    }


    CommentDto mapToDto (Comment comment){

        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

return dto;
    }

}
