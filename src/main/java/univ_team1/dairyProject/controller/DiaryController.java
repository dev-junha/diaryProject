package univ_team1.dairyProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import univ_team1.dairyProject.domain.Diary;
import univ_team1.dairyProject.dto.AddDiaryRequest;
import univ_team1.dairyProject.service.DiaryService;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;


    @PostMapping("/api/diaries")
    public ResponseEntity<?> createDiary(@RequestBody AddDiaryRequest request){
        try {
            Diary savedDiary = diaryService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedDiary);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효하지 않은 감정(emotion) 또는 날씨(weather) 값입니다");
        }
    }


}
