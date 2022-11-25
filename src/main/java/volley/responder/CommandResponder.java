package volley.responder;

import discord4j.core.object.entity.Message;
import volley.response.EmptyResponse;
import volley.response.Response;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

/**
 * Abstracts responders that have set command syntax in user messages. Assumes the command names
 * are in lowercase.
 */
public abstract class CommandResponder implements Responder {
    private final Map<String, Function<String, Response>> commands;

    protected CommandResponder() {
        this.commands = new HashMap<>();
    }

    protected void addCommand(String command, Function<String, Response> process) {
        if (commands.containsKey(command)) {
            throw new IllegalArgumentException("Command already exists");
        }

        commands.put(command, process);
    }

    @Override
    public Response respondToMessage(Message message, String text) {
        String command = (!text.contains(" ") ? text : text.substring(0, text.indexOf(" "))).toLowerCase();
        String params = !text.matches(".+ .+") ? "" : text.substring(text.indexOf(" ") + 1);

        // TODO: do we care about case
        if (commands.containsKey(command)) {
            return commands.get(command).apply(params);
        } else {
            return new EmptyResponse();
        }
    }
}
