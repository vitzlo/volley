package volley.response;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class EmptyResponse implements Response {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Publisher<Object> getPublisher(Message message) {
        return Mono.empty();
    }

    @Override
    public Publisher<Object> getPublisher(ReactionEmoji emoji, User user, Message message, boolean removed) {
        return Mono.empty();
    }
}
