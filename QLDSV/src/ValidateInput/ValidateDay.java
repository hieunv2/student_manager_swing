
package ValidateInput;

import java.util.regex.Pattern;

public class ValidateDay {
     private Pattern pattern;
    private static final String USERNAME_PATTERN = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9]):([0-9]|[0-5][0-9])$";
    public ValidateDay() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    public boolean validate(final String day) {
        return pattern.matcher(day).matches();
    }
}
