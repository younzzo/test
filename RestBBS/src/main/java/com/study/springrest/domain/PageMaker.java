package com.study.springrest.domain;

public class PageMaker {

	int totalArticles=175;
	int startPage;
	int endPage;
	
	int pagePerBlock=10; //한 페이지에 10개의 아이콘 뜨게
	
	boolean next =false;
	boolean prev=false;
	
	Criteria criteria;
	//생성자
	public PageMaker(Criteria cri, int total){
		 criteria = cri;
		 totalArticles=total;
		 process();
	}
	public int getTotalArticles() {
		return totalArticles;
	}
	public void setTotalArticles(int totalArticles) {
		this.totalArticles = totalArticles;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	private void process(){
		//전체 페이지 개수                                 //10
		int totalPages=(int) Math.ceil(totalArticles / (double)pagePerBlock); //18
		
		//요청한 페이지
		int currentPage=criteria.getPage(); //11
		
		//페이지 번호가 끝나는 숫자
		endPage= (int)Math.ceil(currentPage/(double)pagePerBlock) *pagePerBlock; //10, 20(totalPages)
				
		//페이지 번호가 시작되는 숫자
		startPage=endPage-pagePerBlock +1 ; //1, 11, 21, 31
		
		//20     10     11
		//10      10    1

		if(endPage>totalPages){
			endPage=totalPages;
		}
		//이전 페이지 블록 버튼 유무
		if(startPage >1)
			prev=true;
		//다음 페이지 블록 버튼 유무
		 if(endPage < totalPages)
			next=true;
	}
}
