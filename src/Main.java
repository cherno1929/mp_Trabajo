// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Usuario user_1 = new Usuario();
        user_1.setNombre("Usuario_0");

        FileController controller = new FileController();

        System.out.println(controller.existeUsuario(user_1));

    }
}