
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipantShowService implements AbstractShowService<Authenticated, Authenticated> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("usuario", entity.getUserAccount().getUsername());
		request.unbind(entity, model/* , "userAccount" */);

	}

	@Override
	public Authenticated findOne(final Request<Authenticated> request) {
		assert request != null;

		Authenticated result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findUserById(id);

		return result;
	}
}
