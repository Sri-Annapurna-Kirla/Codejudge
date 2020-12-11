package org.codejudge.sb.serviceImplementation;

import org.codejudge.sb.entity.LeadEntity;
import org.codejudge.sb.exceptions.NoLeadExistsException;
import org.codejudge.sb.exceptions.NoLeadFoundException;
import org.codejudge.sb.repository.LeadRepository;
import org.codejudge.sb.request.LeadRequest;
import org.codejudge.sb.response.Lead;
import org.codejudge.sb.response.RestResponse;
import org.codejudge.sb.service.LeadService;
import org.codejudge.sb.util.LocationType;
import org.codejudge.sb.util.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class LeadServiceImpl implements LeadService {

	private LeadRepository leadRepository;

	@Autowired
	public LeadServiceImpl(LeadRepository leadRepository1) {
		leadRepository = leadRepository1;
	}

	@Override
	public Lead getLead(Integer id) throws NoLeadExistsException {
		Lead lead = new Lead();
		LeadEntity leadEntity = leadRepository.findById(id);
		if (leadEntity == null) {
			throw new NoLeadExistsException("Unknown Lead Details");
		} else {
			BeanUtils.copyProperties(leadEntity, lead);
			Status status = Status.valueOf(leadEntity.getStatus());
			lead.setStatus(status.getDescription());
			lead.setLocationType(LocationType.valueOf(leadEntity.getLocationType()).getDescription());
		}
		return lead;
	}

	@Override
	public Lead createLead(LeadRequest leadRequest) throws NoLeadFoundException {

		LeadEntity leadEntity = new LeadEntity();
		Lead lead = new Lead();
		String locationType = leadRequest.getLocationType();
		try {
			BeanUtils.copyProperties(leadRequest, leadEntity);
			leadEntity.setStatus(Status.Created.getValue());
			leadEntity.setLocationType(LocationType.valueOf(locationType).getValue());
			leadEntity = leadRepository.save(leadEntity);
		} catch (Exception e) {
			throw new NoLeadFoundException("Please check the input");
		}
		BeanUtils.copyProperties(leadEntity, lead);
		lead.setLocationType(locationType);
		lead.setStatus("Created");
		return lead;
	}

	@Override
	public void updateLead(LeadRequest leadRequest, Integer id) throws NoLeadExistsException, NoLeadFoundException {
		LeadEntity leadEntity = leadRepository.findById(id);
		if (leadEntity == null) {
			throw new NoLeadExistsException("Unknown Lead Details");
		} else {
			try {
				BeanUtils.copyProperties(leadRequest, leadEntity);
				System.out.println(leadEntity.getLocationString());
				String locationType = leadRequest.getLocationType();
				leadEntity.setLocationType(LocationType.valueOf(locationType).getValue());
				leadRepository.save(leadEntity);
			} catch (Exception e) {
				throw new NoLeadFoundException("Please check the input");
			}
		}
	}

	@Override
	public void deleteLead(Integer id) throws NoLeadExistsException {
		LeadEntity leadEntity = leadRepository.findById(id);
		if (leadEntity == null) {
			throw new NoLeadExistsException("Unknown Lead Details");
		} else {
			leadRepository.delete(leadEntity);
		}
	}

	@Override
	public RestResponse markLead(Integer id, String communication) throws NoLeadExistsException {
		RestResponse restResponse = new RestResponse("Contacted", null, communication);
		LeadEntity leadEntity = leadRepository.findById(id);
		if (leadEntity == null) {
			throw new NoLeadExistsException("Unknown Lead Details");
		} else {
			leadEntity.setStatus(Status.Contacted.getValue());
			leadEntity.setCommunication(communication);
			leadRepository.save(leadEntity);
		}

		return restResponse;
	}
}
