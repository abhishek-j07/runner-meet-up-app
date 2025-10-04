package com.app.runnermeet.controller;

import com.app.runnermeet.dto.ClubDTO;
import com.app.runnermeet.models.Club;
import com.app.runnermeet.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {

    private ClubService clubService;

    @Autowired
    ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String getClubs(Model model) {
        List<ClubDTO> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubsForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetails(@PathVariable("clubId") Long clubId,
                              Model model) {
        ClubDTO clubDTO = clubService.findClubById(clubId);
        model.addAttribute("club", clubDTO);
        return "club-details";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDTO clubDto,
                           BindingResult result,
                           Model model) {

        if(result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubsForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDTO club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClubs(@PathVariable("clubId") Long clubId, Model model) {
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDTO clubDTO,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "clubs-edit";
        }

        clubDTO.setId(clubId);
        clubService.updateClub(clubDTO);
        return "redirect:/clubs";
    }
}
