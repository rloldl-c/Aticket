package me.articket.server.shorts.controller;

import lombok.RequiredArgsConstructor;
import me.articket.server.common.response.SuccessResponse;
import me.articket.server.like.data.LikeStateRes;
import me.articket.server.like.data.ModifyLikeReq;
import me.articket.server.like.service.LikeService;
import me.articket.server.login.data.UserDetail;
import me.articket.server.shorts.data.AddShortsReq;
import me.articket.server.shorts.data.ModifyShortsReq;
import me.articket.server.shorts.data.ShortsInfoRes;
import me.articket.server.shorts.service.ShortsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorts")
@RequiredArgsConstructor
public class ShortsController {

    private final ShortsService shortsService;

    private final LikeService likeService;

    @GetMapping("/{id}")
    public SuccessResponse<ShortsInfoRes> getShortsInfo(@PathVariable Long id) {
        return new SuccessResponse<>(shortsService.getShortsInfo(id));
    }

    @PostMapping("/add")
    public SuccessResponse<ShortsInfoRes> addShorts(@RequestBody AddShortsReq req) {
        return new SuccessResponse<>(shortsService.addShorts(req));
    }

    @PutMapping("/{id}")
    public SuccessResponse<ShortsInfoRes> modifyShortsInfo(@PathVariable Long id, @RequestBody ModifyShortsReq req) {
        return new SuccessResponse<>(shortsService.modifyShortsInfo(id, req));
    }

    @GetMapping("/{id}/like")
    public SuccessResponse<LikeStateRes> getLikeStatus(@PathVariable Long id, @AuthenticationPrincipal UserDetail userDetail) {
        return new SuccessResponse<>(likeService.getLikeState(id, userDetail.getId()));
    }

    @PostMapping("/{id}/like")
    public SuccessResponse<LikeStateRes> setLikeStatus(@PathVariable Long id, @RequestBody ModifyLikeReq req, @AuthenticationPrincipal UserDetail userDetail) {
        return new SuccessResponse<>(likeService.setLikeState(id, userDetail.getId(), req));
    }
}
