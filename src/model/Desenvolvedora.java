package model;

public class Desenvolvedora implements java.io.Serializable{
    
    private static final long serialVersionUID = 1234567;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return the foundation
     */
    public String getFoundation() {
        return foundation;
    }

    /**
     * @param foundation the foundation to set
     */
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    /**
     * @return the bibliotecas
     */
    public String getBibliotecas() {
        return bibliotecas;
    }

    /**
     * @param bibliotecas the bibliotecas to set
     */
    public void setBibliotecas(String bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    /**
     * @return the frameworks
     */
    public String getFrameworks() {
        return frameworks;
    }

    /**
     * @param frameworks the frameworks to set
     */
    public void setFrameworks(String frameworks) {
        this.frameworks = frameworks;
    }
    
    private String name;
    private String origin;
    private String foundation;
    private String bibliotecas;
    private String frameworks;
    
    public String toString(){
        return(getName() + getOrigin());
    }
    
}
