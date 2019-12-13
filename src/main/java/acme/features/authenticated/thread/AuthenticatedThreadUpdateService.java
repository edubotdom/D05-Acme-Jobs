
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedThreadUpdateService implements AbstractUpdateService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "users");
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneThreadById(id);
		result.getUsers().size();
		return result;
	}

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		int userid;
		Authenticated user;
		userid = request.getModel().getInteger("userid");
		user = this.repository.findOneAuthenticatedByUserAccountId(userid);

		int id = request.getModel().getInteger("threadid");
		Collection<Authenticated> usersMod = this.repository.findOneThreadById(id).getUsers();
		usersMod.add(user);
		entity.setUsers(usersMod);
		this.repository.save(entity);
	}

}
