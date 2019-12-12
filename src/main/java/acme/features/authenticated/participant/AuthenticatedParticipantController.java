
package acme.features.authenticated.participant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;

@Controller
@RequestMapping("/authenticated/participant/")
public class AuthenticatedParticipantController extends AbstractController<Authenticated, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedParticipantListService	listService;

	@Autowired
	private AuthenticatedParticipantShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		//super.addCustomCommand(CustomCommand.LIST_NEW_USER, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
