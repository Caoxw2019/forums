package life.gzhmt.forums.forums.enums;


public  enum CommentTypeEnum {//同mapper
    QUESTION(1),
    COMMENT(2);
    private  Integer type;
    public Integer getType() {
        return type;
    }
    CommentTypeEnum(Integer type){
        this.type=type;
    }





}
