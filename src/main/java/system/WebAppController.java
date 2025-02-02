package system;

import system.PubSubApplication.PubsubOutboundGateway;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class WebAppController {

  private static final Log LOGGER = LogFactory.getLog(WebAppController.class);

  @Autowired
  private PubsubOutboundGateway messagingGateway;

  @PostMapping("/publish")
  public RedirectView publishMessage(@RequestParam("message") String message) {
    LOGGER.info("Mensagem será publicada. Payload: " + message);
	messagingGateway.sendToPubsub(message);
	return new RedirectView("/");
  }

}
