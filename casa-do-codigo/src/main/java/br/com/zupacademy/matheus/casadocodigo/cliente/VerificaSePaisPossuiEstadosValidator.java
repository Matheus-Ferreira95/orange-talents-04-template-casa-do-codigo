package br.com.zupacademy.matheus.casadocodigo.cliente;

import br.com.zupacademy.matheus.casadocodigo.estado.Estado;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class VerificaSePaisPossuiEstadosValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteRequest.class.isAssignableFrom(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        ClienteRequest clienteRequest = (ClienteRequest) o;
        TypedQuery<Estado> query = manager.createQuery("SELECT e FROM Estado e where e.pais.id =:paisId", Estado.class);
        query.setParameter("paisId", clienteRequest.getPaisId());
        List<Estado> estados = query.getResultList();
        if (!estados.isEmpty() && clienteRequest.getEstadoId() == null) {
            errors.rejectValue("estadoId", null, "Necessário informar o seu estado");
        }
    }
}
