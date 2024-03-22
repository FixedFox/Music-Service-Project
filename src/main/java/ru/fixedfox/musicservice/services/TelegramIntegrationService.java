package ru.fixedfox.musicservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fixedfox.musicservice.dto.TracklistInfoDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.mappers.TelegramResponseMapper;

import java.util.Set;

@Service
public class TelegramIntegrationService {

    private final TelegramResponseMapper telegramMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${token}")
    private String token;

    private static final String botURL = "https://api.telegram.org/bot";

    public TelegramIntegrationService(TelegramResponseMapper telegramMapper) {
        this.telegramMapper = telegramMapper;
    }

    public Long findTelegramIdByTelegramName(String telegramName) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate
                .getForEntity(botURL + token + "/getUpdates", String.class);
            return telegramMapper.findTelegramIdByTelegramNameMapper(response.getBody(), telegramName);
    }

    public void sendHelloByTelegramId(String name, Long telegramId) {
        ResponseEntity<String> response = restTemplate.getForEntity(botURL + token +
                "/sendMessage?chat_id=" + telegramId + "&text=" +
                "Привет, " + name + "!", String.class);
    }

    public void NotificationNewTracklistPublished(Set<User> users, TracklistInfoDto tracklist) {
        for (User user : users) {
            var response = restTemplate.getForEntity(botURL + token +
                    "/sendMessage?chat_id=" + user.getTelegramid() + "&text=" +
                    "Вышел альбом: " +
                    tracklist.getTracklistName() + " - " +
                    tracklist.getTracklistType() + "\n" +
                    tracklist.getTracklistCreators().toString() + "\n" +
                    tracklist.getTracklistGenre(), String.class);
        }
    }
}
