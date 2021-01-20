package pl.brzezinski.redditclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.brzezinski.redditclone.dto.PostRequest;
import pl.brzezinski.redditclone.dto.PostResponse;
import pl.brzezinski.redditclone.model.Post;
import pl.brzezinski.redditclone.model.Subreddit;
import pl.brzezinski.redditclone.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.userName")
    PostResponse mapToDto(Post post);
}
