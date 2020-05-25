package br.com.webflux.flux;

import br.com.webflux.document.Playlist;
import br.com.webflux.service.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class PlaylistHandler {

    private static final MediaType json = MediaType.APPLICATION_JSON;
    private PlaylistService service;

    public PlaylistHandler(PlaylistService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {

        return ok().contentType(json).body(service.findAll(), Playlist.class);

    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        final String id = request.pathVariable("id");

        return ok().contentType(json).body(service.findById(id), Playlist.class);

    }

    public Mono<ServerResponse> save(ServerRequest request) {

        final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);

        return ok().contentType(json).body(fromPublisher(playlist.flatMap(service::save), Playlist.class));

    }

}
