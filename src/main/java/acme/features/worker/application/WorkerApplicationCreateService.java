
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors/* , *"moment", "status", "worker", "job" */);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal = request.getPrincipal();
		int workerId = principal.getAccountId();
		Worker worker = this.repository.findWorkerById(workerId);
		model.setAttribute("worker", worker);

		int idJob = request.getModel().getInteger("id");
		Job job = this.repository.findJobById(idJob);
		model.setAttribute("job", job);

		model.setAttribute("status", "pending");

		request.unbind(entity, model, "reference");
		request.unbind(entity, model, "statement", "skills", "qualifications");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		result = new Application();

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		Principal principal = request.getPrincipal();
		int workerId = principal.getAccountId();
		Worker worker = this.repository.findWorkerById(workerId);
		entity.setWorker(worker);

		int idJob = request.getModel().getInteger("id");
		Job job = this.repository.findJobById(idJob);
		entity.setJob(job);

		entity.setStatus("pending");

		this.repository.save(entity);
	}

}
