package come.mine.workbench.validationservice;

import org.springframework.stereotype.Service;

import com.mine.workbench.model.EventInvitation;

import come.mine.workbench.validationservice.exception.ValidatorServiceException;

/**
 * Validation service to map incoming json object to POJO
 * 
 * @author niranjandeshpande
 *
 */
@Service
public interface IValidator {

	EventInvitation validateEventCreation(String inputJson) throws ValidatorServiceException;

}
