package br.com.zupacademy.matheus.casadocodigo.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDuplicadoParaOMesmoPais implements Validator {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return EstadoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        EstadoRequest estadoRequest = (EstadoRequest) o;
        Optional<Estado> possivelEstado = estadoRepository.findByNomeAndPaisId(estadoRequest.getNome(), estadoRequest.getIdPais());
        if (possivelEstado.isPresent()) {
            errors.rejectValue("nome", null, "Já existe um estado com nome " + estadoRequest.getNome()
                                           + " para o pais " + possivelEstado.get().getNomePais());
        }
    }
}
