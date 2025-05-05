package univ_team1.dairyProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ_team1.dairyProject.DairyProjectApplication;
import univ_team1.dairyProject.domain.Diary;
import univ_team1.dairyProject.dto.AddDiaryRequest;
import univ_team1.dairyProject.repository.DiaryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    // 일기 추가 메서드
    public Diary save(AddDiaryRequest request){
        return diaryRepository.save(request.toEntity());
    }

    //일기 목록 조회 메서드
    public List<Diary> findAll(){
        return diaryRepository.findAll();
    }

}
