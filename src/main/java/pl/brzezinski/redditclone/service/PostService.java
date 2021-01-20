package pl.brzezinski.redditclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.brzezinski.redditclone.dto.PostRequest;
import pl.brzezinski.redditclone.dto.PostResponse;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    public void save(PostRequest postRequest) {

    }

    public PostResponse getPost(Long id) {
        return null;
    }

    public List<PostResponse> getAllPosts() {
        return null;
    }

    public List<PostResponse> getPostsByUserName(String userName) {
        return null;
    }

    public List<PostResponse> getPostsBySubreddit(Long id) {
        return null;
    }
}
