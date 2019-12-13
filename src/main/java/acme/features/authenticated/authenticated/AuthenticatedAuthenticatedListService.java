
package acme.features.authenticated.authenticated;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuthenticatedListService implements AbstractListService<Authenticated, Authenticated> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedAuthenticatedRepository repository;


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

		int threadid = request.getModel().getInteger("threadid");
		model.setAttribute("thread", threadid);
		model.setAttribute("usuario", entity.getUserAccount().getUsername());
		request.unbind(entity, model/* , "userAccount" */);
	}

	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;
		Collection<Authenticated> result;

		Integer idThread = request.getModel().getInteger("threadid");
		Collection<Authenticated> usuarios = this.repository.findManyUsers();
		Collection<Authenticated> usuariosThread = this.repository.findUsersByThread(idThread);
		result = usuarios.stream().filter(u -> !usuariosThread.contains(u)).collect(Collectors.toList());
		List<Integer> size = result.stream().map(u -> u.getUserAccount().getRoles().size()).collect(Collectors.toList());
		return result;
	}
}
