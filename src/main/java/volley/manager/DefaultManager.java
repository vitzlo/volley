package volley.manager;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import volley.model.retcon.RetconResponder;
import volley.responder.Responder;
import volley.response.EmptyResponse;
import volley.response.Response;

import java.util.List;

public class DefaultManager implements Manager {
    private final List<Responder> responders;

    public DefaultManager() {
        this.responders = List.of(
                new RetconResponder()
        );
    }

    @Override
    public Response respondToMessage(Message message) {
        String prefix = "^"; // TODO: data access into server prefixes
        String text = message.getContent();
        if (text.isBlank()) {
            return new EmptyResponse();
        }

        for (Responder responder : responders) {
            System.out.println("with responder");
            Response response = new EmptyResponse();
            if (!responder.requiresPrefix()) {
                response = responder.respondToMessage(message, text);
            } else if (text.startsWith(prefix)) {
                String prunedText = prefix.length() == text.length() ? "" : text.substring(prefix.length());
                System.out.println("manager found prefix in msg, pruned = " + prunedText);
                response = responder.respondToMessage(message, prunedText);
            }

            if (!response.isEmpty()) {
                System.out.println("branch bad");
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
