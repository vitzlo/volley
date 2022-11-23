package volley.model.retcon;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import volley.responder.Responder;
import volley.response.EmptyResponse;
import volley.response.Response;

public class RetconResponder implements Responder {
    public RetconResponder() {

    }

    @Override
    public boolean requiresPrefix() {
        return true;
    }

    @Override
    public int defaultHexCode() {
        return 0x00FF00;
    }

    @Override
    public Response respondToMessage(Message message, String text) {
        return null;
    }

    @Override
    public Response respondToAddReaction(ReactionEmoji emoji, User user, Message message) {
        return new EmptyResponse();
    }

    @Override
    public Response respondToRemoveReaction(ReactionEmoji emoji, User user, Message message) {
        return new EmptyResponse();
    }
}
