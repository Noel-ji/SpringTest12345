package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PraiseService {

	private final PraiseRepository praiseRepository;
	
	//DTO(=사용자가 입력한 값)값을 Praise클래스(엔티티)를 사용해서 객체 생성
	public void savePraise(PraiseDto praiseDto) {
		Praise praise = new Praise(praiseDto.getPraiser(),praiseDto.getContent(),praiseDto.getPraisedAt());
//		praise.setPraiser(praiseDto.getPraiser());
//		praise.setContent(praiseDto.getContent());
//		praise.setPraisedAt(praiseDto.getPraisedAt());
		
		// 생성한 객체를 Repo(DB)에 저장
		this.praiseRepository.save(praise);
		
	}

	// Praise엔티티의 목록을 불러옴.
	public List<Praise> getList() {
		// TODO Auto-generated method stub
		// findAll() => Praise엔티티 모든 row를 불러오는 것
		return this.praiseRepository.findAll();
	}
	
	//수정을 위해 id가 필요,
	// id를 가져오기 위한 로직
	public Praise getPraise(Long id) {
		
		Optional<Praise> optionalPraise = this.praiseRepository.findById(id);
		
		if(optionalPraise.isPresent()) {
			return optionalPraise.get();
		}else {
			throw new IllegalArgumentException("해당 게시글이 없습니다.");
		}
		
		
		
	}
	//update 사용해서 수정완료 버튼 누르면 수정이 완료되는 로직
	//update 메소드. 이 메소드는 칭찬(Praise) 게시글의 정보를 수정하는 역할
	//
	@Transactional
	public void update(Long id, PraiseDto dto) {
		
		// 1. praiseRepository에서 제공된 'id'를 사용하여 해당 칭찬 게시글을 찾음.
	    // findById 메소드는 게시글을 찾으면 Optional 객체에 담아 반환하고, 없으면 빈 Optional을 반환.
	    // Optional은 nullPointerException을 방지하기 위한 Java 8의 기능
		Optional<Praise> praise =this.praiseRepository.findById(id);
		// 2. Optional 객체에서 Praise 엔티티를 가져옴.
	    // get() 메소드는 Optional 객체 안에 있는 실제 Praise 인스턴스를 반환.
	    // 만약 객체가 비어있다면(게시글을 찾지 못했다면) NoSuchElementException이 발생
		Praise p1 = praise.get();
		// 3. Optional 객체에서 다시 Praise 엔티티를 가져와서 수정 메소드(update)를 호출
		p1.update(dto.getPraiser(),dto.getContent(),dto.getPraisedAt());
		this.praiseRepository.save(p1);
	}
	@Transactional
	public void delete(Long id) {
		Optional<Praise> praise =this.praiseRepository.findById(id);
		Praise p1 = praise.get();
		this.praiseRepository.delete(p1);
		
	}

	
}
