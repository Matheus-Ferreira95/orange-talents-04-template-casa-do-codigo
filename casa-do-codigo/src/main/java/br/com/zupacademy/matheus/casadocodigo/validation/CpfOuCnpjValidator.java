package br.com.zupacademy.matheus.casadocodigo.validation;

import br.com.zupacademy.matheus.casadocodigo.validation.util.ValidacCpfOuCnpj;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOuCnpjValidator implements ConstraintValidator<CpfOuCnpj, String> {

    private String domainAttribute;

    @Override
    public void initialize(CpfOuCnpj params) {
        domainAttribute = params.fieldName();
    }

    @Override
    public boolean isValid(String cpfOuCnpj, ConstraintValidatorContext context) {
        // não teve jeito, tive que fazer essa verificação, por que mesmo se colocar @NotBlank lá no dto, se não
        // for informado um documento, vai cair aqui, e tava dando nullpointer ao tentar fazer o replaceAll
        if (cpfOuCnpj == null) {
            return false;
        }
        // necessário deixar o cpfOuCnpj apenas com os digitos, se não vai dar pal na validação
        cpfOuCnpj = cpfOuCnpj.replaceAll("\\D", "");

        // se ambos forem falso é por que ambos não são validos
        if (!ValidacCpfOuCnpj.isValidCNPJ(cpfOuCnpj) && !ValidacCpfOuCnpj.isValidCPF(cpfOuCnpj)) {
            return false;
        }
        return true;
    }
}