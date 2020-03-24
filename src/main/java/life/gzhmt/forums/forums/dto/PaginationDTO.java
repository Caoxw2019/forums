package life.gzhmt.forums.forums.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {


    public List<QuestionDTO> question;
    public List<CommentDTO> comment;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    public List<CommentDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentDTO> comment) {
        this.comment = comment;
    }



    private  Integer page;
    //private  List<Integer> pages;
    private  List<Integer> pages=new ArrayList<>();


    public List<QuestionDTO> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionDTO> question) {
        this.question = question;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        Integer totalPage;
        if (totalCount%size==0){
            totalPage=totalCount/size;
        }
        else{
            totalPage=totalCount/size+1;
        }


        if (page<1){
            page=1;
    }
        if (page>totalPage){
        page=totalPage;
    }
        this.page=page;


        //页码变化数据 简单算法
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if(page==1){
            showPrevious=false;
        }
        else{
            showPrevious=true;
        }
        //是否展示下一页

        if(page==totalPage){
            showNext=false;
        }
        else{
            showNext=true;
        }


        //以下功能不实现了

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }
        else {
            showFirstPage=true;
        }
        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }




    }
}

