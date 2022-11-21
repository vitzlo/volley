package volley.responder;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import volley.response.Response;

public interface Responder {
    boolean requiresPrefix();

    int defaultHexCode();

    Response respondToMessage(Message message, String text);

    Response respondToAddReaction(ReactionEmoji emoji, User user, Message message);

    Response respondToRemoveReaction(ReactionEmoji emoji, User user, Message message);
}
