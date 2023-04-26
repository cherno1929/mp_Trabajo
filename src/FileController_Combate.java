import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileController_Combate extends FileController_Operator{
    private String locationPersistencia = "Ficheros_app/Persistencia";

    public boolean es_Baneable(Date actualDate,Usuario retado) {
        List<Persistencia> obliterado = this.getAllPersistencias();
        boolean baneable = false;
        for (Persistencia pers : obliterado) {
            baneable = (Math.abs(actualDate.getTime() - pers.getFecha_Combate().getTime()) <= 0) && (pers.getJ2().getNum_Registro().equals(retado.getNum_Registro()));
            if (baneable) {
                break;
            }
        }
        return baneable;
    }

    private List<Persistencia> getAllPersistencias() {
        List<Persistencia> persistencias = new ArrayList<Persistencia>();
        File locationPerst = new File(this.locationPersistencia);
        File[] allLocations = locationPerst.listFiles();
        for (File fil : allLocations) {
            Persistencia newPers = this.getPersistencia(fil);
            persistencias.add(newPers);
        }
        return persistencias;
    }

    public void addPersistencia(Persistencia perst) {
        if (perst != null) {
            File pestFileLoacation = new File(this.locationPersistencia + "/" + perst.getJ1().getNombre() + "-"+perst.getJ2().getNombre() + perst.getFecha_Combate() + ".txt");
            try {
                if (pestFileLoacation.createNewFile()) {
                    BufferedWriter fileWritter = new BufferedWriter(new FileWriter(pestFileLoacation));
                    fileWritter.write("J1 : "+perst.getJ1().getNum_Registro()+"\n");
                    fileWritter.write("J2 : "+perst.getJ2().getNum_Registro()+"\n");
                    fileWritter.write("Turnos : "+perst.getN_Turnos()+"\n");
                    fileWritter.write("Ganador : "+perst.getGanador().getNum_Registro()+"\n");
                    fileWritter.write("Oro : "+perst.getOroGanado()+"\n");
                    fileWritter.write("Fecha : "+perst.getFecha_Combate()+"\n");
                    fileWritter.write("Esbirros"+perst.getStringEsbirros_Vivos()+"\n");
                }else {
                    System.out.println("Oups, algo fue mal");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Ha habido algun problema con la persistencia");
        }
    }

    private Persistencia getPersistencia(File fil) {
        Persistencia pers = new Persistencia();
        if (fil != null) {
            try {
                BufferedReader persReader = new BufferedReader(new FileReader(fil));
                String line;
                while ((line = persReader.readLine())!=null) {
                    String[] arr = line.split(" : ");
                    if (arr.length > 1) {
                        String arrIdx = arr[0];
                        String arrData = arr[1];
                        if (arrIdx.equals("J1")){
                            pers.setJ1(getUsuario(arrData));
                        } else if (arrIdx.equals("J2")) {
                            pers.setJ2(getUsuario(arrData));
                        } else if (arrIdx.equals("Turnos")) {
                            pers.setN_Turnos(Integer.parseInt(arrData));
                        } else if (arrIdx.equals("Ganador")) {
                            pers.setGanador(getUsuario(arrData));
                        } else if (arrIdx.equals("Oro")) {
                            pers.setOroGanado(Integer.parseInt(arrData));
                        } else if (arrIdx.equals("Fecha")) {
                            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(arrData);
                            pers.setFecha_Combate(date1);
                        } else if (arrIdx.equals("Esbirros")) {
                            String[] esbirros = arrData.split(" - ");
                            pers.setEsbirros_Vivos((List<Esbirro>) buscarEsbirros(esbirros));
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return pers;
    }


    /*
    public void destruirDesafio(Desafio desafio) { // Por algun motivo falla
        String location_Desafio = this.locatinDesafios+"/"+desafio.getJ1().getNum_Registro()+"-"+desafio.getJ2().getNum_Registro()+".txt";
        System.out.println("Localización de ficherlo :: "+location_Desafio);
        File filDestyoyer = new File(location_Desafio);
        if (filDestyoyer.delete()) {
            System.out.println("Desafio rechazado correctamente");
        } else {
            System.out.println("Ha habido algun problema borrando el desafio");
        }
    }

     */
}
