
package ValidateInput;

import java.util.regex.Pattern;

public class ValidateName {
     private Pattern pattern;
        private static final String USERNAME_PATTERN = "^[a-z ]{3,150}$";

        public ValidateName() {
            pattern = Pattern.compile(USERNAME_PATTERN);
        }

        public boolean validate(final String username) {
            return pattern.matcher(username).matches();
        }
}
