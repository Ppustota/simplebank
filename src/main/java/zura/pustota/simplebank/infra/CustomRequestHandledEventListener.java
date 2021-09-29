package zura.pustota.simplebank.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component @Slf4j
public class CustomRequestHandledEventListener {
    @EventListener()
    public void handledRequestRemainder(RequestHandledEvent event){
        log.info("Event handled {}", event.toString());
    }
}
