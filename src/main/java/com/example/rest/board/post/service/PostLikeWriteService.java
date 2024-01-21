package com.example.rest.board.post.service;

import com.example.rest.fastcampusmysql.ex.member.dto.MemberDto;
import com.example.rest.board.post.entity.Post;
import com.example.rest.board.post.entity.PostLike;
import com.example.rest.board.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    final private PostLikeRepository postLikeRepository;

    public void create(Post post, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();
        postLikeRepository.save(postLike);
    }
}
