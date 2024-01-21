package fastcampusmysql.application.controller;

import fastcampusmysql.application.usacase.CreateFollowMemberUsecase;
import fastcampusmysql.application.usacase.GetFollowingMembersUsecase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "팔로우 정보")
@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {
    final private CreateFollowMemberUsecase createFollowMemberUsecase;
    final private GetFollowingMembersUsecase getFollowingMembersUsacase;

    @Operation(summary = "팔로우 등록")
    @PostMapping("/{fromId}/{toId}")
    public List<MemberDto> register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
        return getFollowingMembersUsacase.execute(fromId);
    }

    @Operation(summary = "팔로워 조회")
    @GetMapping("/members/{fromId}")
    public List<MemberDto> getFollowers(@PathVariable Long fromId) {
        return getFollowingMembersUsacase.execute(fromId);
    }
}
