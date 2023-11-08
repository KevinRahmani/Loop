package utils;

import jakarta.servlet.http.HttpServletRequest;
import model.beans.ClientEntity;
import model.beans.VendeurEntity;
import model.dto.UserDTO;
import model.service.UserService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyData {
    public static boolean verifyParameters(String nom, String marque,String type, String couleur,String description, String categorie) {
        boolean isValid = true;
        String errorMessage = "Les champs suivants sont invalides : ";

        // Vérification des paramètres de texte
        if (nom == null || nom.isEmpty()) {
            isValid = false;
            errorMessage += "Nom, ";
        }

        if (marque == null || marque.isEmpty()) {
            isValid = false;
            errorMessage += "Marque, ";
        }

        if (type == null || type.isEmpty()) {
            isValid = false;
            errorMessage += "Type, ";
        }

        if (couleur == null || couleur.isEmpty()) {
            isValid = false;
            errorMessage += "Couleur, ";
        }

        if (description == null || description.isEmpty()) {
            isValid = false;
            errorMessage += "Description, ";
        }

        if (categorie == null || categorie.isEmpty()) {
            isValid = false;
            errorMessage += "Categorie, ";
        }

        if (!isValid) {
            System.out.println(errorMessage);
        }

        return isValid;
    }

    public static <T> boolean validateEntity(T entity) throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value == null || value.toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHashMapEmpty(HashMap<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static int getParameterAsInt(HttpServletRequest request, String param) {
        String value = request.getParameter(param);
        int result = 0;
        if (value != null) {
            try {
                result = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean isValidMail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isFreeMailClient(String mail, UserService<ClientEntity> clientService, ClientEntity client) {
        UserDTO user = new UserDTO();
        user.setMail(mail);

        List<ClientEntity> clients = clientService.findAllByFilters(user);
        clients.remove(client);

        return clients.isEmpty();
    }

    public static boolean isFreeMailVendeur(String mail, UserService<VendeurEntity> vendeurService, VendeurEntity vendeur) {
        UserDTO user = new UserDTO();
        user.setMail(mail);

        List<VendeurEntity> vendeurs = vendeurService.findAllByFilters(user);
        vendeurs.remove(vendeur);

        return vendeurs.isEmpty();
    }


}
