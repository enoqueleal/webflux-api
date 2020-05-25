package br.com.webflux.service;

import static org.junit.Assert.*;

import br.com.webflux.document.Playlist;
import br.com.webflux.repository.PlaylistRepository;
import org.junit.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

public class PlaylistServiceTest {

    private PlaylistRepository repository;
    private PlaylistServiceImpl service;
    private String uuid;

    @Before
    public void init(){
        repository = mock(PlaylistRepository.class);
        service = new PlaylistServiceImpl(repository);
        uuid = "911689cf-f0d0-49f6-a618-7198b74ef2eb";
    }

    @Test
    public void test_find_all_with_success(){

        when(repository.findAll()).thenReturn(null);

        Flux<Playlist> playlistFlux = service.findAll();

        assertNull(playlistFlux);

    }

    @Test
    public void test_find_by_id_with_success(){

        when(repository.findById(anyString())).thenReturn(null);

        Mono<Playlist> playlistMono = service.findById(uuid);

        assertNull(playlistMono);

    }

    @Test
    public void test_save_with_success(){

        when(repository.save(any())).thenReturn(null);

        Mono<Playlist> playlistMono = service.save(new Playlist(uuid, "Java"));

        assertNull(playlistMono);

    }

}
