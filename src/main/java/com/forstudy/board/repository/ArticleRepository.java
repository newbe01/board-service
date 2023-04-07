package com.forstudy.board.repository;

import com.forstudy.board.domain.Article;
import com.forstudy.board.domain.QArticle;
import com.forstudy.board.repository.querydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>, // 모든필드의 기본 검색기능 추가
        QuerydslBinderCustomizer<QArticle> {

    Page<Article> findByTitleContaining(String title, Pageable pageable);

    Page<Article> findByContentContaining(String content, Pageable pageable);

    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);

    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);

    Page<Article> findByHashtag(String hashtag, Pageable pageable);

    @Override   //queryDSL
    default void customize(QuerydslBindings bindings, QArticle root) {

        bindings.excludeUnlistedProperties(true);   //listing 하지않은 property 는 검색에서 제외
        // 원하는 파라미터 field 추가
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first(( path, value) -> path.eq(value) );
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);  like ' ${ v } '
        // 원하는 파라미터 field 검색 조건
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  // like '% ${ v } %' 제목 검색 방법
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);  // like '% ${ v } %' 제목 검색 방법
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);  // like '% ${ v } %' 제목 검색 방법
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);

    }
}
