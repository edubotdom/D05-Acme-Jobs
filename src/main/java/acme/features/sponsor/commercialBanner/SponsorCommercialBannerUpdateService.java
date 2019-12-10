
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercialBanners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {

		assert request != null;

		boolean result;
		int commercialBannerId;
		CommercialBanner commercialBanner;
		Sponsor sponsor;
		Principal principal;

		commercialBannerId = request.getModel().getInteger("id");
		commercialBanner = this.repository.findOneCommercialBannerById(commercialBannerId);
		sponsor = commercialBanner.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "picture", "slogan", "url", "creditCard");
	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCommercialBannerById(id);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		Sponsor sponsor;
		Principal principal;
		int userAccountId;
		//String creditCard;

		// primero vamos a encontrar el Sponsor
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repository.findOneSponsorByUserAccountId(userAccountId);

		// ahora vamos a extraer su tarjeta de crédito e insertarla en la nueva propiedad
		//creditCard = sponsor.getCreditCard();

		// ahora inicializamos sus propiedades
		entity.setSponsor(sponsor);
		//entity.setCreditCard(creditCard);

		this.repository.save(entity);

	}

}
