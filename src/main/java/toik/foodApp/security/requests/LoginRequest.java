package toik.foodApp.security.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public record LoginRequest(String username, String password) {
}