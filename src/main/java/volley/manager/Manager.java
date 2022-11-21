package volley.manager;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import volley.response.Response;

public interface Manager {
    Response respondToMessage(Message message);

    Response respondToAddReaction(ReactionEmoji emoji, User user, Message message);

    Response respondToRemoveReaction(ReactionEmoji emoji, User user, Message message);
}
