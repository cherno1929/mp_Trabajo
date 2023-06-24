public class Usuario {

    //Atributos
    protected String nombre;
    public String nick;
    protected String password;
    private String num_Registro;
    public Rol rol;
    public Personaje personajeActivo;
    public AppController app;

    //Metodos
    ////TBD...

    //Constructor

    public Usuario(String nombre, String nick, String password, String num_Registro, Rol rol, Personaje personajeActivo, AppController app) {
        this.nombre = nombre;
        this.nick = nick;
        this.password = password;
        this.num_Registro = num_Registro;
        this.rol = rol;
        this.personajeActivo = personajeActivo;
        this.app = app;
    }

    public Usuario() {
    }


    //Get-Set


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNum_Registro() {
        return num_Registro;
    }

    public void setNum_Registro(String num_Registro) {
        this.num_Registro = num_Registro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Personaje getPersonajeActivo() {
        return personajeActivo;
    }

    public void setPersonajeActivo(Personaje personajeActivo) {
        this.personajeActivo = personajeActivo;
    }

    public AppController getApp() {
        return app;
    }

    public void setApp(AppController app) {
        this.app = app;
    }
}
