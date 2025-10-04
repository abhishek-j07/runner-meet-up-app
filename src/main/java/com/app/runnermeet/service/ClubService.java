package com.app.runnermeet.service;

import com.app.runnermeet.dto.ClubDTO;
import com.app.runnermeet.models.Club;

import java.util.List;

public interface ClubService {

    List<ClubDTO> findAllClubs();

    Club saveClub(ClubDTO club);

    ClubDTO findClubById(Long clubId);

    void updateClub(ClubDTO club);

    void deleteClub(Long clubId);
}
