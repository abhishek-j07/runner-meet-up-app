package com.app.runnermeet.service.impl;

import com.app.runnermeet.dto.ClubDTO;
import com.app.runnermeet.models.Club;
import com.app.runnermeet.repository.ClubRepository;
import com.app.runnermeet.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    @Autowired
    ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDTO> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .map((club) -> mapToClubDTO(club))
                .collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDTO club) {
        return clubRepository.save(mapToClub(club));
    }

    @Override
    public ClubDTO findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDTO(club);
    }

    @Override
    public void updateClub(ClubDTO club) {
        Club c = mapToClub(club);
        clubRepository.save(c);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    private Club mapToClub(ClubDTO clubDTO) {
        Club club = Club.builder()
                .id(clubDTO.getId())
                .title(clubDTO.getTitle())
                .photoUrl(clubDTO.getPhotoUrl())
                .content(clubDTO.getContent())
                .createdOn(clubDTO.getCreatedOn())
                .updatedOn(clubDTO.getUpdatedOn())
                .build();

        return club;
    }

    private ClubDTO mapToClubDTO(Club club) {

        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();

        return clubDTO;
    }
}
