package com.rest.template.controllers;

import javax.validation.Valid;

import com.rest.template.controllers.requests.BasicRequest;
import com.rest.template.controllers.responses.MessageResponse;
import com.rest.template.exceptions.SizeException;
import com.rest.template.exceptions.ValidationException;

import org.apache.log4j.Logger;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
	
	private static final Logger logger = Logger.getLogger(BasicController.class);
	
	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping("/")
    public String index() {
		logger.info("Rest Template Initialized.");
        return "Rest Template!";
    }
	
	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(value="/echo/{message}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public HttpEntity<MessageResponse> echo(@PathVariable(value="message") String input) throws SizeException {
		logger.info("/basic/echo/"+input);
		
		if (input.length() > 30) {
			throw new SizeException(30);
		}
		
		MessageResponse msg = new MessageResponse(input);
		msg.add(linkTo(methodOn(BasicController.class).echo(input)).withSelfRel());
		
		return new ResponseEntity<MessageResponse>(msg, HttpStatus.OK);
    }
	
	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(value="/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public HttpEntity<MessageResponse> insert(@RequestBody @Valid final BasicRequest content, final BindingResult result) throws ValidationException {
		logger.info("/basic/new/");
		
		this.validate(result);
		
		MessageResponse msg = new MessageResponse("Successfully Inserted.");
		msg.add(linkTo(methodOn(BasicController.class).insert(content, result)).withSelfRel());
		
        return new ResponseEntity<MessageResponse>(msg, HttpStatus.OK);
    }
	
	private void validate(final BindingResult result) throws ValidationException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
	}

}
