package com.forstudy.board.config;

import com.forstudy.board.domain.Article;
import com.forstudy.board.domain.ArticleComment;
import com.forstudy.board.domain.Hashtag;
import com.forstudy.board.domain.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig((config, cors) ->
                config
                        .exposeIdsFor(UserAccount.class)
                        .exposeIdsFor(Article.class)
                        .exposeIdsFor(ArticleComment.class)
                        .exposeIdsFor(Hashtag.class)
        );
    }

}
