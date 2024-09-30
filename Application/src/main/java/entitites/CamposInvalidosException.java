package entitites;

import javax.swing.JOptionPane;

public class CamposInvalidosException extends Exception {

    public CamposInvalidosException(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
