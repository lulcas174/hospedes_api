package hospedeAPI.example.hospedeAPI.validators;

public class ValidatorTelefone {
    public static boolean isValidTelefone(String telefone) {

        if(!telefone.matches("\\d+")){
            return false;
        }

        if (telefone.length() != 11) {
            return false;
        }

        if (telefone.matches("(\\d)\\1{10}")) {
            return false;
        }

        return true;
    }
}
