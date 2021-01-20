package pl.brzezinski.redditclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.brzezinski.redditclone.dto.SubredditDto;
import pl.brzezinski.redditclone.exceptions.SpringRedditException;
import pl.brzezinski.redditclone.mapper.SubredditMapper;
import pl.brzezinski.redditclone.model.Subreddit;
import pl.brzezinski.redditclone.repository.SubredditRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubRedditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No subreddit found with."));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
