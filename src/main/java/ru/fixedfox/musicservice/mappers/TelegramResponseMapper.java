package ru.fixedfox.musicservice.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.fixedfox.musicservice.dto.TelegramNameTelgramId;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TelegramResponseMapper {
    ObjectMapper mapper = new ObjectMapper();

    public Long findTelegramIdByTelegramNameMapper(String entity, String telegramName) throws JsonProcessingException {
        var users = new ArrayList<TelegramNameTelgramId>();
        JsonNode root = mapper.readTree(entity);
        users = root.get("result").findValues("message").stream()
                .filter(m -> m.get("from").has("username"))
                .map(m -> {
                    var user = new TelegramNameTelgramId();
                    user.setTelegramName(m.get("from").findValue("username").asText());
                    user.setTelegramId(m.get("from").findValue("id").asLong());
                    return user;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        var findedUser = users.stream()
                .filter(o -> o.getTelegramName().equals(telegramName))
                .findFirst();
        return findedUser.map(TelegramNameTelgramId::getTelegramId).orElse(null);
    }
}
