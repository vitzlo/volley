package volley;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import volley.controller.D4JController;

public class VolleyMain {
    public static void main(String[] args) {
        DiscordClient client = DiscordClientBuilder.create(args[0]).build();
        D4JController controller = new D4JController(client);
        controller.run();
    }
}
