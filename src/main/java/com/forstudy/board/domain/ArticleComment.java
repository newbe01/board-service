package com.forstudy.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {

    private long id;
    private Article article;
    private String content;

    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;


}
