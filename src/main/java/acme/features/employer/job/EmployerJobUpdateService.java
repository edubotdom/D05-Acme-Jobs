
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

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

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Comprueba si el job está ya publicado
		errors.state(request, !entity.isFinalMode(), "status", "employer.job.isAlreadyPublished");

		//Comprueba que los duties sumen un 100%
		Collection<Duty> d = this.repository.findDutyByDescriptor(entity.getDescriptor().getId());
		Double suma = d.stream().mapToDouble(x -> x.getTimeAmount()).sum();
		Boolean sumUp = true;
		if (request.getModel().getAttribute("status").equals("Published")) {
			sumUp = suma == 100;
		}
		errors.state(request, sumUp, "status", "employer.job.dutiesNotSumUp");

		//Comprueba que status es "Published" o "Draft"
		Boolean statusCorrect = request.getModel().getAttribute("status").equals("Published") || request.getModel().getAttribute("status").equals("Draft");
		errors.state(request, statusCorrect, "status", "employer.job.statusNotCorrect");
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		if (request.getModel().getAttribute("status").equals("Published")) {
			entity.setFinalMode(true);
		} else {
			entity.setFinalMode(false);
		}
		this.repository.save(entity);
	}

}
