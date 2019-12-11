
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("status", "Draft");

		request.unbind(entity, model, "referenceNumber", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description"); //Hay que poner status??
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Job result;

		Principal principal = request.getPrincipal();
		int employerId = principal.getAccountId();
		Employer employer = this.repository.findOneEmployerByUserAccountId(employerId);

		result = new Job();
		result.setEmployer(employer);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Comprueba que status es "Published" o "Draft" ????
		Boolean statusCorrect = request.getModel().getAttribute("status").equals("Draft");
		errors.state(request, statusCorrect, "status", "employer.job.statusMustBeDraft");
		//Comprueba el spam
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
