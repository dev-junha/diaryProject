package univ_team1.dairyProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ_team1.dairyProject.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
}
