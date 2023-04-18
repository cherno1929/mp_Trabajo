import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class FileController_Operator extends FileController{

    //Atributes
    private String locationDesafios = "Ficheros_app/Desafios";
    private String locationDataPersonaje = "Ficheros_app/Personajes";
    public List<Desafio> listaDesafios = this.getDesafios();

    //Metodods
    public void banear(String idUsuario){
        Usuario userToBan = this.getUsuario(idUsuario);
        userToBan.setRol(Rol.baneado);
        this.modificarUsuario(userToBan);
    }

    public void desBanear(String idUsuario){
        Usuario userToDesBan = this.getUsuario(idUsuario);
        if (userToDesBan.getClass() == Operador.class){
            userToDesBan.setRol(Rol.operador);
        }
        this.modificarUsuario(userToDesBan);
    }

    private List<Desafio> getDesafios() {
        List<Desafio> desafios = new ArrayList<Desafio>();
        File fileDesafios = new File(this.locationDesafios);
        File[] files = fileDesafios.listFiles();
        if (files != null) {
            for (File fil : files){
                try {
                    Desafio desaf = new Desafio();
                    BufferedReader desafioReader = new BufferedReader(new FileReader(fil));
                    String line;
                    while ((line = desafioReader.readLine()) != null) {
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Oro")){
                                desaf.setOro(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("J1")) {
                                desaf.setJ1(getUsuario(arrData));
                            } else if (arrIdx.equals("J2")) {
                                desaf.setJ2(getUsuario(arrData));
                            } else if (arrIdx.equals("Mod_J1")) {
                                desaf.setMod_j1(buscarMods(arrData.split(" - ")));
                            } else if (arrIdx.equals("Mod_J2")) {
                                desaf.setMod_j2(buscarMods(arrData.split(" - ")));
                                }
                        }
                    }
                    desafios.add(desaf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return desafios;
    }

    public void borrarDesafio(String idDesafio){ //El id del deafio esta pensado para ser el nombre de los dos usuarios que se esnfrentarán
        String location = this.locationDesafios + "/" + idDesafio + ".txt";
        File fileDelete = new File(location);
        if (fileDelete.delete()){
            System.out.println("El desafio fue exitosamente borrado");
        }else {
            System.out.println("Error : El archivo no pudo ser borrado");
        }
    }

    //ALTERAR ESBIRRO ???

    public void alterarAtributosPersonaje(){

    }

}
