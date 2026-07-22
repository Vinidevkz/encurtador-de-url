package dtos;

public record TokenResponseDTO(String userName, String userEmail, String token, long expiration) {
}