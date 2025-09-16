package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PraiseController {

	private final PraiseService praiseService; //의존성 주입
	 
// 단순 "praises/list"_html 보여주는 로직
//	@GetMapping("/praises")
//	public String praises() {
//		return "praises/list";
//	}
	


//	@GetMapping("/praises")
//	public String praises(Model model) {
//		List<Praise> praises = praiseService.getList();
//		model.addAttribute("praises",praises);
//		
//		return "praises/list";
//	}
	
	// 작성한 글 목록 불러오는 로직
	//이 로직은 praiseService.getList()를 호출하여 데이터베이스에서 모든 글(칭찬 목록)을 가져오고 
	//model.addAttribute()를 통해 praises/list.html 템플릿에 전달
	@GetMapping("/praises")
	public String praises(Model model) {
		List<Praise> praises = praiseService.getList();
		model.addAttribute("praises",praises);
		// "praises"(보낼 곳_html),praises(보낼데이터_목록))
		return "praises/list";
	}
	
	// 글 작성하는 html 보여주는 로직
	// 이 메서드는 글을 작성하는 폼(new-praise-form.html)을 사용자에게 보여줌.
	// model.addAttribute("praise", new PraiseDto());는 빈 폼 객체를 템플릿에 미리 전달하여,
	// 타임리프(th:object)가 폼과 데이터를 연결할 수 있게 한다.
	@GetMapping("/praises/new")
	public String praises_new(Model model) {
		model.addAttribute("praise", new PraiseDto());
	
			return "praises/new-praise-form";
		}
	
	// 글 작성해서 db로 넘어갈 수 있게하는 로직
	//이 메서드는 사용자가 폼에 입력한 데이터를 받아서 PraiseDto 객체에 바인딩한다.
//if(bindingResult.hasErrors())는 폼에 문제가 있을 경우(@Valid로 검증된 내용과 다를 때) 다시 폼으로 돌려보내는 역할을 한다.
//praiseService.savePraise(praiseDto);는 서비스 계층의 메서드를 호출하여 데이터베이스에 최종적으로 데이터를 저장한다.
//return "redirect:/praises";는 글 저장이 성공하면 /praises URL로 사용자를 다시 보내어, 글 목록 페이지를 보여주도록 한다.
	
	@PostMapping("/praises/new")
	public String createPraise(@Valid PraiseDto praiseDto,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "praises/new-praise-form";
		}
		
		this.praiseService.savePraise(praiseDto);
		return "redirect:/praises";
	}
	
	//수정페이지(게시글 누르면 내용까지 확인할 수 있게)
	@GetMapping("/praises/{id}/edit")
	public String edit(@PathVariable("id")Long id, Model model) {
		Praise praise = this.praiseService.getPraise(id);
		model.addAttribute("praise",praise);
		
		return "praises/edit-form";
		
	}
	
	//게시글 수정 후 업데이트 하는 부분
	@PostMapping(value="/praises/{id}/edit")
	public String updatePraise(@PathVariable("id") Long id, PraiseDto dto) {
		
		this.praiseService.update(id, dto);
		return "redirect:/praises";
	}
	
	//게시글 삭제 부분
	@PostMapping(value="/praises/{id}/delete")
	public String deletePraise(@PathVariable("id") Long id) {
		
		this.praiseService.delete(id);
		return "redirect:/praises";
		
	}
	
	
	
	
	
	
}
