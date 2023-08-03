package ar.edu.undef.fie.arpyduel.interfaces.responses;

public record DuelResponse(Long id, Integer turnOf, String winner, String challenger) {
}
