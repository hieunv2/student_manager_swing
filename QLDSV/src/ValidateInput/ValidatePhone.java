
package ValidateInput;

import java.util.regex.Pattern;

public class ValidatePhone {
    private Pattern pattern;
    private static final String USERNAME_PATTERN = "^(09[2|6|8|9]|01[2|6|8|9]|03[2|6|8|9])+([0-9]{8})\\b$";

    public ValidatePhone() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    public boolean validate(final String phone) {
        return pattern.matcher(phone).matches();
    }
}
