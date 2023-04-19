package com.forstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
//@EntityListeners(AuditingEntityListener.class) // auditing > 자동 값 생성 => AuditingFields.class 로 변경
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @Setter @Column(nullable = false, length = 500) private String content;

//    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
//    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    //기본 생성자
    protected ArticleComment() {}

    //고정값 제외 생성자
    private ArticleComment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    //guide
    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
        return new ArticleComment(article,userAccount,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
