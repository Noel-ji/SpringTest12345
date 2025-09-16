package com.example.demo;

import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PraiseDto {

	 // 사용자가 입력할 데이터 값들
	    private String praiser; // 칭찬해 준 사람
	    
	    private String content; // 칭찬 내용
	
	    private LocalDate praisedAt; // 날짜
}

