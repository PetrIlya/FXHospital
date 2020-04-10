package network;

import lombok.Getter;
import lombok.Setter;
import model.Record;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Getter
@Setter
public class RequestProcessor {
    public static final String PACKS_URI = "/packs";
    public static final String DELETE_URI = "/delete";
    public static final String SEARCH_URI = "/search";
    public static final String NAME_PARAMETER = "name";
    public static final String SIZE_PARAMETER = "size";
    public static final String PAGE_PARAMETER = "page";
    private final WebClient webClient;
    private final String serverUri;

    private String currentPack;

    public RequestProcessor(String serverUri, String currentPack) {
        this.webClient = WebClient.
                builder().
                baseUrl(serverUri).
                build();
        this.serverUri = serverUri;
        setCurrentPack(currentPack);
    }

    public void deletePack(String packName) {
        this.webClient.
                delete().
                uri((uriBuilder -> uriBuilder.
                        path(PACKS_URI).
                        queryParam(NAME_PARAMETER, packName).
                        build())).
                exchange().
                block();
        if (currentPack.equals(packName)) {
            setCurrentPack("");
        }
    }

    public void postPack(String packName) {
        this.webClient.
                post().
                uri((uriBuilder -> uriBuilder.
                        path(PACKS_URI).
                        queryParam(NAME_PARAMETER, packName).
                        build())).
                exchange().
                block();
        setCurrentPack(packName);
    }

    public void postRecord(Record record) {
        this.webClient.
                post().
                uri(PACKS_URI + currentPack).
                contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(record), Record.class).
                exchange().
                block();
    }

    public List<Record> searchRecordByCondition(ConditionObject object) {
        return this.webClient.
                post().
                uri(PACKS_URI + currentPack + SEARCH_URI).
                contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(object), ConditionObject.class).
                exchange().
                flatMap(response -> response.bodyToMono(Record[].class)).
                map(List::of).
                block();
    }

    public List<Record> deleteRecordByCondition(ConditionObject object) {
        return this.webClient.
                post().
                uri(PACKS_URI + currentPack + DELETE_URI).
                contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(object), ConditionObject.class).
                exchange().
                flatMap(response -> response.bodyToMono(Record[].class)).
                map(List::of).
                block();
    }

    public void setCurrentPack(String currentPack) {
        this.currentPack = "/" + currentPack;
    }
}
