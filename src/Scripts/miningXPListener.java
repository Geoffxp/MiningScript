package Scripts;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;

public class miningXPListener implements ChatMessageListener, AutoCloseable {

    public miningXPListener() {
        Game.getEventDispatcher().register(this);
    }

    @Override
    public void notify(ChatMessageEvent e) {
        if (e.getMessage().contains("You manage to mine")) {
            Main.incrementOreCount(e.getMessage());
        }
    }

    @Override
    public void close() {
        Game.getEventDispatcher().deregister(this);
    }
}
