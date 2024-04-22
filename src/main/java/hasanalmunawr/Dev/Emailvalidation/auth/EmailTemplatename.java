package hasanalmunawr.Dev.Emailvalidation.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplatename {

    ACTIVATE_ACCOUNT("activate_account")

    ;

    private final String value;
}
