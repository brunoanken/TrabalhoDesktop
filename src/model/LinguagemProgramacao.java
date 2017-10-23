package model;
import java.util.Date;

public class LinguagemProgramacao {

    /**
     * @return the release
     */
    public String getRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(String release) {
        this.release = release;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the stable
     */
    public String getStable() {
        return stable;
    }

    /**
     * @param stable the stable to set
     */
    public void setStable(String stable) {
        this.stable = stable;
    }

    /**
     * @return the libraries
     */
    public String getLibraries() {
        return libraries;
    }

    /**
     * @param libraries the libraries to set
     */
    public void setLibraries(String libraries) {
        this.libraries = libraries;
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
    
    private String nome;
    private String release;
    private String stable;
    private String libraries;
    private String frameworks;
}
