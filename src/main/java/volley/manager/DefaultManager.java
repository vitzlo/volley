package volley.manager;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import volley.responder.Responder;
import volley.response.EmptyResponse;
import volley.response.Response;

import java.util.List;

public class DefaultManager implements Manager {
    private final List<Responder> responders;

    public DefaultManager() {
        this.responders = List.of();
    }

    @Override
    public Response respondToMessage(Message message) {
        String prefix = "^"; // TODO: data access into server prefixes
        String text = message.getContent();
        if (text.isBlank()) {
            return new EmptyResponse();
        }

        for (Responder responder : responders) {
            Response response = new EmptyResponse();
            if (!responder.requiresPrefix()) {
                response = responder.respondToMessage(message, text);
            } else if (text.startsWith(prefix)) {
                String prunedText = prefix.length() == text.length() ? "" : text.substring(prefix.length());
                response = responder.respondToMessage(message, prunedText);
            }

            if (!response.isEmpty()) {
                return response;
            }
        }

        return new EmptyResponse();
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
