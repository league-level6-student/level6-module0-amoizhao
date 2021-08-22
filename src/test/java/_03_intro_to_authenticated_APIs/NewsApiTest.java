package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;

import _01_intro_to_APIs.CheetahSearchApi;
import _01_intro_to_APIs.data_transfer_objects.Result;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;
    
    @Mock
    WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<ApiExampleWrapper> resultMonoMock;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);

    	newsApi = new NewsApi();
    	newsApi.setWebClient(webClientMock);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	String topic = "pigeons";

        ApiExampleWrapper expectedWrapper = new ApiExampleWrapper();
        
        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ApiExampleWrapper.class))
                .thenReturn(resultMonoMock);
        when(resultMonoMock.block())
                .thenReturn(expectedWrapper);

        //when
        ApiExampleWrapper actualWrapper = newsApi.getNewsStoryByTopic(topic);

        //then
        verify(webClientMock, times(1)).get();
        assertEquals(expectedWrapper, actualWrapper);
    }

    @Test
    void itShouldFindStory(){
        //given
    	String topic = "pigeons";
        ArrayList<Article> newsArticles = new ArrayList<Article>();
        
        Article article = new Article();
        article.setTitle("some pigeons");
        article.setContent("pigeons are irritating");
        article.setUrl("www.pigeons.com");
        
        newsArticles.add(article);

        ApiExampleWrapper expectedWrapper = new ApiExampleWrapper();
        expectedWrapper.setArticles(newsArticles);

        when(webClientMock.get())
        		.thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
		        .thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve())
		        .thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class))
		        .thenReturn(resultMonoMock);
		when(resultMonoMock.block())
		        .thenReturn(expectedWrapper);

		String expectedStory =
                article.getTitle() + " -\n"
                        + article.getContent()
                        + "\nFull article: " + article.getUrl();
        
        //when
        String actualStory = newsApi.findStory(topic);

        //then
        verify(webClientMock, times(1)).get();
        assertEquals(expectedStory, actualStory);
    }


}