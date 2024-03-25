package ru.fixedfox.musicservice.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TelegramResponseMapperTest {

    @Autowired
    private TelegramResponseMapper mapper;

    @Test
    void findTelegramIdByTelegramNameMapper_success_test() throws JsonProcessingException {
        String telegramResponse = "{\n" +
                "\t\"ok\": true,\n" +
                "\t\"result\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\"update_id\": 631877399,\n" +
                "\t\t\t\"message\": {\n" +
                "\t\t\t\t\"message_id\": 25,\n" +
                "\t\t\t\t\"from\": {\n" +
                "\t\t\t\t\t\"id\": 88888888,\n" +
                "\t\t\t\t\t\"is_bot\": false,\n" +
                "\t\t\t\t\t\"first_name\": \"Иван\",\n" +
                "\t\t\t\t\t\"last_name\": \"Горшков\",\n" +
                "\t\t\t\t\t\"username\": \"user\",\n" +
                "\t\t\t\t\t\"language_code\": \"ru\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"chat\": {\n" +
                "\t\t\t\t\t\"id\": 88888888,\n" +
                "\t\t\t\t\t\"first_name\": \"Иван\",\n" +
                "\t\t\t\t\t\"last_name\": \"Горшков\",\n" +
                "\t\t\t\t\t\"username\": \"user\",\n" +
                "\t\t\t\t\t\"type\": \"private\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"date\": 1711046033,\n" +
                "\t\t\t\t\"text\": \"Ggg\\nFfff\"\n" +
                "\t\t\t}\n" +
                "\t\t} ] }";
        Long id = mapper.findTelegramIdByTelegramNameMapper(telegramResponse, "user");
        assertEquals(88888888, id);
    }

    @Test
    void findTelegramIdByTelegramNameMapper_fail_test() throws JsonProcessingException {
        String telegramResponse = "{\n" +
                "\t\"ok\": true,\n" +
                "\t\"result\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\"update_id\": 631877399,\n" +
                "\t\t\t\"message\": {\n" +
                "\t\t\t\t\"message_id\": 25,\n" +
                "\t\t\t\t\"from\": {\n" +
                "\t\t\t\t\t\"id\": 88888888,\n" +
                "\t\t\t\t\t\"is_bot\": false,\n" +
                "\t\t\t\t\t\"first_name\": \"Иван\",\n" +
                "\t\t\t\t\t\"last_name\": \"Горшков\",\n" +
                "\t\t\t\t\t\"language_code\": \"ru\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"chat\": {\n" +
                "\t\t\t\t\t\"id\": 88888888,\n" +
                "\t\t\t\t\t\"first_name\": \"Иван\",\n" +
                "\t\t\t\t\t\"last_name\": \"Горшков\",\n" +
                "\t\t\t\t\t\"type\": \"private\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"date\": 1711046033,\n" +
                "\t\t\t\t\"text\": \"Ggg\\nFfff\"\n" +
                "\t\t\t}\n" +
                "\t\t} ] }";
        Long id = mapper.findTelegramIdByTelegramNameMapper(telegramResponse, "user");
        assertEquals(null, id);
    }
}