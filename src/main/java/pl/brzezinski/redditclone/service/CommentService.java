package pl.brzezinski.redditclone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.brzezinski.redditclone.dto.CommentsDto;
import pl.brzezinski.redditclone.exceptions.PostNotFoundException;
import pl.brzezinski.redditclone.mapper.CommentMapper;
import pl.brzezinski.redditclone.model.Comment;
import pl.brzezinski.redditclone.model.NotificationEmail;
import pl.brzezinski.redditclone.model.Post;
import pl.brzezinski.redditclone.model.User;
import pl.brzezinski.redditclone.repository.CommentRepository;
import pl.brzezinski.redditclone.repository.PostRepository;
import pl.brzezinski.redditclone.repository.UserRepository;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUserName() + " posted a comment on your post." + POST_URL);
        sendCommentNotfication(message, post.getUser());
    }

    private void sendCommentNotfication(String message, User user) {
        mailService.sendEmail(new NotificationEmail(user.getUserName() + " Commented on your post", user.getEmail(), message));
    }
}
