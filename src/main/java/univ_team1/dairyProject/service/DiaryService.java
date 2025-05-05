package univ_team1.dairyProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ_team1.dairyProject.DairyProjectApplication;
import univ_team1.dairyProject.domain.Diary;
import univ_team1.dairyProject.dto.AddDiaryRequest;
import univ_team1.dairyProject.repository.DiaryRepository;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    // 일기 추가 메서드
    public Diary save(AddDiaryRequest request){
        return diaryRepository.save(request.toEntity());
    }


}
