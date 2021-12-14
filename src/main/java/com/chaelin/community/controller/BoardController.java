package com.chaelin.community.controller;

import com.chaelin.community.dto.BoardDTO;
import com.chaelin.community.dto.PageRequestDTO;
import com.chaelin.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/")
    public String index(){
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(@PageableDefault(sort = "bno", direction = Sort.Direction.DESC) Pageable pageable, Model model){

        int next = pageable.next().getPageNumber();
        log.info(next);

        model.addAttribute("boardList", boardService.getList(pageable));

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", next);

        return "/board/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public String register(Model model, Authentication authentication){
        log.info("regiser get...");
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        model.addAttribute("writer",userDetails.getUsername());
        return "/board/register";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String register(BoardDTO dto, RedirectAttributes redirectAttributes){

        Long bno = boardService.register(dto);
        redirectAttributes.addFlashAttribute("msg",bno);

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/board/list";
    }

    @GetMapping({"/read"})
    public void read(long bno, Model model,Authentication authentication){
        try{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("author",userDetails.getUsername());
        }catch(NullPointerException nullPointerException){
            //it mean user isn't authorized.
        }
        log.info("bno: " + bno);

        BoardDTO dto = boardService.read(bno);

        model.addAttribute("dto", dto);


    }

    @GetMapping("/modify")
    public void modify(long bno, Model model,Authentication authentication){
        try{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("author",userDetails.getUsername());
        }catch(NullPointerException nullPointerException){
            //it mean user isn't authorized.
        }
        log.info("bno: " + bno);

        BoardDTO dto = boardService.read(bno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());

        redirectAttributes.addAttribute("bno",dto.getBno());


        return "redirect:/board/read";

    }
}
