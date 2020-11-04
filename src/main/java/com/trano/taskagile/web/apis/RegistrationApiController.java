package com.trano.taskagile.web.apis;

import com.trano.taskagile.domain.application.UserService;
import com.trano.taskagile.domain.model.user.EmailAddressExistsException;
import com.trano.taskagile.domain.model.user.RegistrationException;
import com.trano.taskagile.domain.model.user.UsernameExistsException;
import com.trano.taskagile.web.payload.RegistrationPayload;
import com.trano.taskagile.web.results.ApiResult;
import com.trano.taskagile.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegistrationApiController {

  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {
    try {
      service.register(payload.toCommand());
      return Result.created(); //http status : 201
    } catch (RegistrationException e) {
      String errorMessage = "Registration failed";
      if (e instanceof UsernameExistsException) {
        errorMessage = "Username already exists";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "Email address already exists";
      }
      return Result.failure(errorMessage); //http status : 400
    }
  }
}
