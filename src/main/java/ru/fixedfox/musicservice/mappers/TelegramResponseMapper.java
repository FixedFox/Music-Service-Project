package ru.fixedfox.musicservice.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.fixedfox.musicservice.dto.UserWithTelgramId;

import java.util.*;

@Component
public class TelegramResponseMapper {
    ObjectMapper mapper = new ObjectMapper();

    public UserWithTelgramId findUserByTelegramNameMapper(String entity, String telegramName) throws JsonProcessingException, NoSuchElementException {
        var newUsers = new LinkedHashSet<UserWithTelgramId>();
        JsonNode root = mapper.readTree(entity);
        JsonNode userFromTg = root.get("result")
                .findValues("message")
                .stream()
                .filter(o -> o.get("from").get("username").asText().equals(telegramName))
                .findFirst().orElse(null);
        if (userFromTg != null) {
            var user = new UserWithTelgramId();
            user.setTelegramName(userFromTg.get("from").get("username").asText());
            user.setTelegramId(userFromTg.get("from").get("id").asLong());
            return user;
        } else {
            return null;
        }
    }
}
