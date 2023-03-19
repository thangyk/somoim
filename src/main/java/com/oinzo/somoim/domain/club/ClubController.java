package com.oinzo.somoim.domain.club;

import com.oinzo.somoim.common.exception.ErrorCode;
import com.oinzo.somoim.domain.club.entity.Club;
import com.oinzo.somoim.domain.club.service.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;

    @ResponseBody
    @PostMapping()
    public Object addClub(@RequestBody Club request){
        return clubService.addClub(request);
    }

    @GetMapping("/search")
    public Object readClubByName(@RequestBody Club request) {
        return clubService.readClubByName(request);
    }

    @GetMapping("/favorite")
    public Object readClubByFavorite(@RequestBody Club request){
        return clubService.readClubByFavorite(request.getFavorite(),request.getArea());
    }

    @GetMapping("/{clubId}")
    public Object readClubById(@PathVariable("clubId") Long clubId, HttpServletResponse response,
                               @CookieValue(value="count", required=false) Cookie countCookie){
        return clubService.readClubById(clubId,response,countCookie);
    }

    @GetMapping("/random")
    public Object readClubByArea(@RequestBody Club request){
        return clubService.readClubByArea(request);
    }


}
