package volley.model.retcon;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;
import volley.Ut;
import volley.model.retcon.crosshair.Crosshair;
import volley.responder.CommandResponder;
import volley.response.EmptyResponse;
import volley.response.Response;
import volley.response.StringResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RetconResponder extends CommandResponder {
    private final Map<String, Crosshair> crosshairs;

    private static final String SEARCH_NOT_FOUND_MSG = "Crosshair not found. Check your spelling or try a more general query.";

    public RetconResponder() {
        super();
        addCommand("chget", params -> respondToCrosshairGet(params));
        addCommand("chrandom", params -> respondToCrosshairRandom(params));
        this.crosshairs = Map.of(); // TODO: import
    }

    @Override
    public boolean requiresPrefix() {
        return false;
    }

    @Override
    public int defaultHexCode() {
        return 0x00FF00;
    }

    private Response respondToCrosshairGet(String params) {
        if (params.isEmpty()) {
            return new StringResponse(Ut.ERR_MISSING_PARAMS);
        }

        List<String> matchingNames = new ArrayList<>();

        System.out.println("chget into method");
        for (String name : crosshairs.keySet()) {
            if (params.equalsIgnoreCase(name)) {
                return respondToCrosshairGetPrint(crosshairs.get(name));
            } else if (Ut.isValidSearchQuery(params, name)) {
                matchingNames.add(name);
            }
        }

        if (matchingNames.size() == 1) {
            System.out.println("chget 1");
            return respondToCrosshairGetPrint(crosshairs.get(matchingNames.get(0)));
        } else if (!matchingNames.isEmpty()) {
            System.out.println("chget 2");
            return respondToCrosshairGetMatches(matchingNames);
        } else {
            System.out.println("chget 3");
            return new StringResponse(SEARCH_NOT_FOUND_MSG);
        }
    }

    private Response respondToCrosshairGetPrint(Crosshair crosshair) {
        // TODO: embed with crosshair name, picture, and code
        return new EmptyResponse();
    }

    private Response respondToCrosshairGetMatches(List<String> matches) {
        // TODO: embed with possible results
        // TODO: cap at X values
        return new EmptyResponse();
    }

    private Response respondToCrosshairRandom(String params) {
        // TODO: warning embed if params given
        // TODO: print random crosshair
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
