package ru.fixedfox.musicservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fixedfox.musicservice.dto.TracklistInfoDto;
import ru.fixedfox.musicservice.dto.UserWithTelgramId;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.mappers.TelegramResponseMapper;

import java.util.Set;

@Service
public class TelegramIntegrationService {

    private final TelegramResponseMapper telegramMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String botURL = "https://api.telegram.org/bot6926246907:AAGCQ-CkORgbPMdEqn0PrkSzdrAFZqsIEmo";

    public TelegramIntegrationService(TelegramResponseMapper telegramMapper) {
        this.telegramMapper = telegramMapper;
    }

    public UserWithTelgramId takeChatIdByTelegramName(String telegramName) throws JsonProcessingException {
        ResponseEntity<String> response= restTemplate
                .getForEntity(botURL + "/getUpdates",String.class);
        return telegramMapper.findUserByTelegramNameMapper(response.getBody(), telegramName);
    }

    public void NotificationNewTracklistPublished (Set<User> users, TracklistInfoDto tracklist) {
        for (User user : users) {
        var response = restTemplate.getForEntity(botURL +
                "/sendMessage?chat_id=" + user.getTelegramid() + "&text=" +
                "Вышел альбом: " +
                tracklist.getTracklistName() + " - " +
                tracklist.getTracklistType() + " - " +
                tracklist.getTracklistCreators().toString() + " - " +
                tracklist.getTracklistGenre(),String.class);
        }
    }
}
