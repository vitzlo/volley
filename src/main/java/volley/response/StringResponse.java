package volley.response;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import org.reactivestreams.Publisher;

public class StringResponse implements Response {
    private final String content;

    public StringResponse(String content) {
        this.content = content;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Publisher<Object> getPublisher(Message message) {
        return message.getChannel().flatMap(channel -> channel.createMessage(content)); // TODO: ?
    }

    @Override
    public Publisher<Object> getPublisher(ReactionEmoji emoji, User user, Message message, boolean removed) {
        return message.getChannel().flatMap(channel -> channel.createMessage(content)); // TODO: ?
    }
}
