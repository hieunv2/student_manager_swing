/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidateInput;

import java.util.regex.Pattern;

/**
 *
 * @author hp
 */
public class ValidateEmail {
     private Pattern pattern;
        private static final String USERNAME_PATTERN = "^[A-Z0-9+_.-]+@[A-Z0-9.-]+$";

        public ValidateEmail() {
            pattern = Pattern.compile(USERNAME_PATTERN);
        }

        public boolean validate(final String username) {
            return pattern.matcher(username).matches();
        }
}
