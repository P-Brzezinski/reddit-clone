package pl.brzezinski.redditclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.brzezinski.redditclone.dto.CommentsDto;
import pl.brzezinski.redditclone.model.Comment;
import pl.brzezinski.redditclone.model.Post;
import pl.brzezinski.redditclone.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUserName())")
    CommentsDto mapToDto(Comment comment);

}
