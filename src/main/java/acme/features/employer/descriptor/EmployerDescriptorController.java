
package acme.features.employer.descriptor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.descriptors.Descriptor;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/descriptor/")
public class EmployerDescriptorController extends AbstractController<Employer, Descriptor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerDescriptorShowService	showService;

	@Autowired
	private EmployerDescriptorUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
