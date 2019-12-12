
package acme.features.authenticated.participant;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListService implements AbstractListService<Authenticated, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "username");
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		Collection<UserAccount> result;

		Integer idThread = request.getModel().getInteger("id");
		Collection<UserAccount> usuarios = this.repository.findManyUsers();
		Collection<Authenticated> usuariosThread = this.repository.findUsersByThread(idThread);
		Collection<UserAccount> accountsThread = usuariosThread.stream().map(a -> a.getUserAccount()).collect(Collectors.toList());
		result = usuarios.stream().filter(u -> !accountsThread.contains(u)).collect(Collectors.toList());

		return result;
	}
}
