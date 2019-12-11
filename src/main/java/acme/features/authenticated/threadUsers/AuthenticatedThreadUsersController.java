
package acme.features.authenticated.threadUsers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.threads.Thread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/threadUsers/")
public class AuthenticatedThreadUsersController extends AbstractController<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedThreadUsersListService			listService;

	@Autowired
	private AuthenticatedThreadUsersShowService			showService;

	@Autowired
	private AuthenticatedThreadUsersAddUserService		addUserService;

	@Autowired
	private AuthenticatedThreadUsersDeleteUserService	deleteUserService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.ADD_USER, BasicCommand.UPDATE, this.addUserService);
		super.addCustomCommand(CustomCommand.DELETE_USER, BasicCommand.UPDATE, this.deleteUserService);
	}
}
