package br.com.webflux;

import br.com.webflux.document.Playlist;
import br.com.webflux.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

    private PlaylistRepository repository;

    @Autowired
    public DummyData(PlaylistRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        String [] strings = { "API REST Spring Boot", "Java in IBM Cloud", "Java 8", "Github" };

        repository.deleteAll().thenMany(Flux.just(strings).map(name -> new Playlist(UUID.randomUUID().toString(), name)).flatMap(repository::save)).subscribe(System.out::println);

    }

}
