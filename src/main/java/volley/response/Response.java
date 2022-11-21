package volley.response;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import org.reactivestreams.Publisher;

public interface Response {
    boolean isEmpty();

    /**
     * Forms this response's Discord effect in response to the given message.
     * @param message the message that prompted this response
     * @return the publisher for this response
     */
    Publisher<Object> getPublisher(Message message);

    /**
     * Forms this response's Discord effect in response to the given reaction addition/removal.
     * @param message the message that prompted this response
     * @return the publisher for this response
     */
    Publisher<Object> getPublisher(ReactionEmoji emoji, User user, Message message, boolean removed);
}
