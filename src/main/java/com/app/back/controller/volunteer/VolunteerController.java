package com.app.back.controller.volunteer;

import com.app.back.domain.volunteer.Pagination;
import com.app.back.domain.volunteer.VolunteerDTO;
import com.app.back.service.volunteer.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // 이 클래스가 컨트롤러임을 나타냄
@RequestMapping("/volunteer/*") // QA 관련 요청을 처리
@RequiredArgsConstructor // 생성자 자동 생성
@Slf4j // 로깅 기능 추가
public class VolunteerController {

    private final VolunteerService volunteerService;

    @GetMapping("/volunteer-write")
    public String volunteerWrite(Model model) {
        return "volunteer/volunteer-write";
    }

    @GetMapping("/volunteer-list")
    public List<VolunteerDTO> admin(Pagination pagination, Model model) {
        return volunteerService.getList(pagination);
    }


    
    @GetMapping("/volunteer-info") // Q&A 게시글 목록 조회
    @ResponseBody
    public List<VolunteerDTO> getList(Pagination pagination, Model model, @RequestParam(defaultValue = "recent") String view) {
        log.info("Controller - getList() 호출됨");
        log.info("페이지네이션 정보: {}", pagination);

        pagination.setTotal(volunteerService.getTotal()); // 전체 게시글 수 설정
        pagination.progress(); // 페이지 진행 상태 업데이트

        // 정렬 기준에 따른 게시글 목록 조회
        List<VolunteerDTO> lists = volunteerService.getList(pagination);
        if ("recent".equals(view)) {
            lists = volunteerService.getListByRecent(pagination);
        } else if ("endingSoon".equals(view)) {
            lists = volunteerService.getListByEndingSoon(pagination);
        } else if ("viewCount".equals(view)) {
            lists = volunteerService.getListByViewCount(pagination);
        } else {
            lists = volunteerService.getList(pagination); // 기본 조회
        }


        // 각 DTO에 남은 일수를 계산
        for (VolunteerDTO volunteerDTO : lists) {
            volunteerDTO.calculateDaysLeft(); // 남은 일수 계산
        }

        // 모델에 페이징 및 게시글 목록 추가
        model.addAttribute("pagination", pagination);
        model.addAttribute("lists", lists); // 모델에 게시글 목록 추가

        return volunteerService.getList(pagination);
    }



}


