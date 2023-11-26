package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.vm.ci.meta.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
//클라이언트와 맞다아 있는 곳
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "이게 어디에 뜰까??", notes = "이것은 노트") // 해당 api에대한 설명을 붙일 수 있음
    @PostMapping("/create/diary")
        // 값을 저장 할 때 postMapping 사용
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody String text) {
        diaryService.createDiary(date, text);
    }
    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간중의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  @ApiParam(value = "조회할 기간의 첫번째 날", example = "2020-02-02") LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조호할 기간의 마지막 날", example = "2020-02-02") LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
        // 수정을 위한
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }

}
