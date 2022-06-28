package dataa.eleger.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class ExceptionHadlerController {

	@ExceptionHandler(NaoEncontrado.class)
	public ResponseEntity<StandardError> notFoundException(NaoEncontrado e, ServletRequest request) {
		StandardError error = new StandardError (
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ViolacaoDeIntegridade.class)
	public ResponseEntity<StandardError> integratyViolationException(ViolacaoDeIntegridade e, ServletRequest request) {
		StandardError error = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}

	@ExceptionHandler(ValorDuplicado.class)
	public ResponseEntity<StandardError> valorDuplicado(ValorDuplicado e, ServletRequest request) {
		StandardError error = new StandardError (
				System.currentTimeMillis(),
				HttpStatus.NOT_ACCEPTABLE.value(),
				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
	}

	@ExceptionHandler(ViolacaoDeRegra.class)
	public ResponseEntity<StandardError> violacaoDeRegrra(ValorDuplicado e, ServletRequest request) {
		StandardError error = new StandardError (
				System.currentTimeMillis(),
				HttpStatus.NOT_ACCEPTABLE.value(),

				e.getMessage()
		);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
	}

}
