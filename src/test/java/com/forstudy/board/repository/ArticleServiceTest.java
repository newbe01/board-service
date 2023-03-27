package com.forstudy.board.repository;

import com.forstudy.board.domain.type.SearchType;
import com.forstudy.board.dto.ArticleDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("business logic - articles")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("searching articles")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticlesList() {
        //Given
//        SearchParameters param = SearchParameters.of(SearchType.TITLE, "search keyword");

        //When
        Page<ArticleDto> articles =  sut.searchArticles(SearchType.TITLE, "search keyword");   //title, content, ID, NickName, hashtag

        //Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("redirect article")
    @Test
    void givenId_whenSearchingArticle_thenReturnsArticle() {
        //Given
//        SearchParameters param = SearchParameters.of(SearchType.TITLE, "search keyword");

        //When
        ArticleDto article =  sut.searchArticle(1L);   //title, content, ID, NickName, hashtag

        //Then
        assertThat(article).isNotNull();
    }
}
