package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//NoArgsConstructor는 개발자가 코드를 직접 작성하지 않아도 
//**'매개변수가 없는 생성자'**를 자동으로 만들어주는 롬복(Lombok) 기능
@Entity
@Getter
@Setter
public class Praise {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String praiser; // 칭찬해 준 사람

    @Column(nullable = false)
    private String content; // 칭찬 내용

    private LocalDate praisedAt; // 칭찬받은 날짜

    
	public Praise(String praiser, String content, LocalDate praisedAt) {
		
		this.praiser = praiser;
		this.content = content;
		this.praisedAt = praisedAt;
	}


	public void update(String praiser2, String content2,LocalDate praisedAt) {
		
		this.praiser = praiser2;
		this.content = content2;
		this.praisedAt = praisedAt;
		
		
	}


	

	
    
	
	
	
	
	
	
}
