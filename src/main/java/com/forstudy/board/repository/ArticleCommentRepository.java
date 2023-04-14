package com.forstudy.board.repository;

import com.forstudy.board.domain.ArticleComment;
import com.forstudy.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>, // 모든필드의 기본 검색기능 추가
        QuerydslBinderCustomizer<QArticleComment> {

    List<ArticleComment> findByArticle_Id(Long articleId);
    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {

        bindings.excludeUnlistedProperties(true);   //listing 하지않은 property 는 검색에서 제외
        // 원하는 파라미터 field 추가
        bindings.including(root.content, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first(( path, value) -> path.eq(value) );
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);  like ' ${ v } '
        // 원하는 파라미터 field 검색 조건
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);  // like '% ${ v } %' 제목 검색 방법
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);

    }
}
