package volley.controller;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import volley.Ut;
import volley.manager.DefaultManager;
import volley.manager.Manager;
import volley.response.Response;

public class D4JController implements VolleyController {
    private final DiscordClient client;
    private final Manager manager;

    public D4JController(DiscordClient client) {
        this.client = client;
        this.manager = new DefaultManager();
    }

    @Override
    public void run() {
        Mono<Void> connection = client.withGateway((GatewayDiscordClient gateway) -> {
            Mono<Void> printLogin = gateway.on(ReadyEvent.class, event -> printLogin(event)).then();
            Mono<Void> readMessage = gateway.on(MessageCreateEvent.class, event -> readMessage(event)).then();
            Mono<Void> processReactAdd = gateway.on(ReactionAddEvent.class, event -> processReactAdd(event)).then();

            return printLogin.and(readMessage).and(processReactAdd);
        });

        connection.block();
    }

    private Publisher<Object> printLogin(ReadyEvent event) {
        return Mono.fromRunnable(() -> {
            User self = event.getSelf();
            System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
        });
    }

    private Publisher<Object> readMessage(MessageCreateEvent event) {
        Message message = event.getMessage();

        Response response = manager.respondToMessage(message);
        return response.getPublisher(message);
    }

    private Publisher<Object> processReactAdd(ReactionAddEvent event) {
        ReactionEmoji emoji = event.getEmoji();
        User user = event.getUser().block(Ut.BLOCK_WAIT_TIME);
        Message message = event.getMessage().block(Ut.BLOCK_WAIT_TIME);

        Response response = manager.respondToAddReaction(emoji, user, message); // TODO: implement

        return response.getPublisher(emoji, user, message, false);
    }

    private Publisher<Object> processReactRemove(ReactionRemoveEvent event) {
        ReactionEmoji emoji = event.getEmoji();
        User user = event.getUser().block(Ut.BLOCK_WAIT_TIME);
        Message message = event.getMessage().block(Ut.BLOCK_WAIT_TIME);

        Response response = manager.respondToRemoveReaction(emoji, user, message); // TODO: implement

        return response.getPublisher(emoji, user, message, true);
    }
}
