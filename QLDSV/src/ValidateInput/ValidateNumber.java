
package ValidateInput;

import java.util.regex.Pattern;

public class ValidateNumber {
     private Pattern pattern;
        private static final String USERNAME_PATTERN = "^(-?\\d+\\.\\d+)$|^(-?\\d+)$";

        public ValidateNumber() {
            pattern = Pattern.compile(USERNAME_PATTERN);
        }

        public boolean validate(final String username) {
            return pattern.matcher(username).matches();
        }
}
