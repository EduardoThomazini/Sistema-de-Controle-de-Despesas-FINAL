import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
    private String login;
    private String senhaCriptografada;

    public Usuario(String login, String senha) throws NoSuchAlgorithmException {
        this.login = login;
        this.senhaCriptografada = criptografarSenha(senha);
    }

    private String criptografarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(senha.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public boolean verificarSenha(String senha) throws NoSuchAlgorithmException {
        return senhaCriptografada.equals(criptografarSenha(senha));
    }

    public static void salvarUsuario(String login, String senha) throws IOException, NoSuchAlgorithmException {
        Usuario usuario = new Usuario(login, senha);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write(login + "|" + usuario.senhaCriptografada + "\n");
        }
    }
}
