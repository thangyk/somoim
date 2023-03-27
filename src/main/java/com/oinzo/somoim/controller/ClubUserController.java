package com.oinzo.somoim.controller;

import com.oinzo.somoim.common.response.ResponseUtil;
import com.oinzo.somoim.common.response.SuccessResponse;
import com.oinzo.somoim.domain.clubuser.service.ClubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ClubUserController {

	private final ClubUserService clubUserService;

	// 클럽 가입
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/clubs/{clubId}/join")
	public SuccessResponse<?> joinClub(
		@AuthenticationPrincipal Long userId,
		@PathVariable Long clubId) {
		clubUserService.joinClub(userId, clubId);
		return ResponseUtil.success();
	}

}
