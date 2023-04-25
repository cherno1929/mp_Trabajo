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


}
