package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LinguagemProgramacao;
import model.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ProdutoDAO {
    private ArrayList<Produto> lings = new ArrayList<Produto>();
    private File fileDev = new File("produtos.txt");
    
    private Connection connection = null;
    private PreparedStatement pstdados = null;
    
    int bibframe;
    
    public ProdutoDAO(int bibframe){
        this.bibframe = bibframe;
    }
    
    public void criarArquivo(Produto ling) throws IOException{
        this.ler();
        
        try {
            for(int i = 0; i < lings.size(); i++){
                if(lings.get(i).getName().equals(ling.getName())){
                    //limpa o arraylist
                    lings.clear();
                    //se há erro, joga a exceção
                    throw new IOException(); 
                }
            }
            //adiciona o objeto dev (caso não haja erro) no array devs
            lings.add(ling);
            //grava o array no arquivo texto
            gravar(lings);            
        } catch(IOException erro) {
            System.out.println(erro);
        }
    }
    
    public void gravar(ArrayList<Produto> devs) throws IOException{
       
       FileWriter escritor = new FileWriter(fileDev, false);
       BufferedWriter escritorBuff = new BufferedWriter(escritor);
       
       for(int i = 0; i < devs.size(); i++){
            escritorBuff.write(
                devs.get(i).getName()+","+
                devs.get(i).getRelease()+","+
                devs.get(i).getStable()+","+
                devs.get(i).getLanguages()+","+
                devs.get(i).getTipo()+"\n");
       }
        escritorBuff.flush();
        escritorBuff.close();
    }
    
    public int ler() throws FileNotFoundException, IOException{

        File arquivo = new File("produtos.txt");
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
        FileReader leitor = new FileReader(arquivo);
        BufferedReader leitorBuff = new BufferedReader(leitor);
        String line;
        
        while((line = leitorBuff.readLine())!=null){
            String [] devs = line.split(",");            
            Produto dev = new Produto();
            dev.setName(devs[0]);
            dev.setRelease(devs[1]);
            dev.setStable(devs[2]);
            dev.setLanguages(devs[3]);
            dev.setTipo(devs[4]);
            this.lings.add(dev);
        }
        leitorBuff.close();
        
        return this.lings.size();
    }
    
    public String[][] retornaTexto(int tam) throws FileNotFoundException, IOException{
        
        File arquivo = new File("produtos.txt");
        FileReader leitor = new FileReader(arquivo);
        BufferedReader leitorBuff = new BufferedReader(leitor);
        String line;
        String[][] temp = new String[tam][];
        
        int i = 0;
        while((line = leitorBuff.readLine())!=null){
             String [] devs = line.split(",");             
             temp[i] = devs;
             i++;
        }
        
        return temp;
    }
    
    public boolean acessaBD(){
        try{
            String usuario = "postgres";
            String senha = "admin";
            
            Class.forName("org.postgresql.Driver");
            String urlconexao = "jdbc:postgresql://127.0.0.1/trabalhodesk";
            
            connection = DriverManager.getConnection(urlconexao, usuario, senha);
            //connection.setAutoCommit(false);
//            DatabaseMetaData dbmt = connection.getMetaData();
//            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
//            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
//            System.out.println("URL: " + dbmt.getURL());
//            System.out.println("Driver: " + dbmt.getDriverName());
//            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
//            System.out.println("Usuario: " + dbmt.getUserName());
            
        } catch(ClassNotFoundException erro){
            System.out.println("Falha ao carregar o driver JDBC/ODBC." + erro);
            return false;            
        } catch(SQLException erro){
            System.out.println("Falha na conexao, comando sql = " + erro);
            return false;
        }
        return true;
    }
    
    //1 = framework
    //resto = biblioteca
    public String[][] lerBD(){
        
        System.out.println("lerbd tipo = " + bibframe);
        
        acessaBD();
                
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        String[][] stringArray = null;
                
        try{
            if(bibframe == 1){
                pst = connection.prepareStatement("SELECT * FROM produtos WHERE tipo='Framework'");
            } else {
                pst = connection.prepareStatement("SELECT * FROM produtos WHERE tipo='Biblioteca'");
            }
            
            rs = pst.executeQuery();
            List<String[]> list = new ArrayList<String[]>();
            
            while (rs.next()) {
                String[] aux = {rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(1)};
                list.add(aux);
                count++;
            }
            
            stringArray = list.toArray(new String[0][]);
            
        } catch (SQLException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringArray;
    }
    
    public void inserir(Produto dev){
        try {
            
            acessaBD();
            
            String sql = "INSERT INTO produtos (name, release, stable, language, tipo) VALUES (?,?,?,?,?)";
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sql, tipo, concorrencia);            
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            pstdados.setString(1, dev.getName());
            pstdados.setString(2, dev.getRelease());
            pstdados.setString(3, dev.getStable());
            pstdados.setString(4, dev.getLanguages());
            pstdados.setString(5, dev.getTipo());
            
            
            int rowsInserted = pstdados.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar(int id){
        try{
            acessaBD();
            
            String sql = "DELETE FROM produtos WHERE id=?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A product was deleted successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizar(String[] dado){
        try {
            acessaBD();
            
            String sql = "UPDATE produtos SET name=?, release=?, stable=?, language=?, tipo=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dado[0]);
            statement.setString(2, dado[1]);
            statement.setString(3, dado[2]);
            statement.setString(4, dado[3]);
            statement.setString(5, dado[4]);
            statement.setInt(6, Integer.parseInt(dado[5]));
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing product was updated successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //cria um PDF geral
    // 1 = framework
    // resto = biblioteca
    public void createPDF(){
        
        acessaBD();
        
//        Map params = new HashMap();
//        params.put("query_nome", new String("%Python%"));        
        
        String src = null;
        
        System.out.println("tipo = " + bibframe);
        if(bibframe == 1){
            src = "src/reports/framework_geral.jasper";
        } else {
            src = "src/reports/biblioteca_geral.jasper";
        }
        
        
        JasperPrint jasperPrint = null;
        
        try {
            jasperPrint = JasperFillManager.fillReport(src, null, connection);
        } catch (JRException ex) {
            System.out.println("Erro jasperPrint\n");
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JasperViewer view = new JasperViewer(jasperPrint, false);
        
        view.setVisible(true);
        
    }
    
    //cria um PDF com busca por nome
    public void createPDFnome(String nome){
        
        acessaBD();
        
        Map params = new HashMap();
        params.put("query_name", new String("%" + nome + "%"));
        
        if(bibframe == 1){
            params.put("query_tipo", new String("Framework"));
        } else {
            params.put("query_tipo", new String("Biblioteca"));
        }
        
        String src = "src/reports/produto_nome.jasper";
        
        JasperPrint jasperPrint = null;
        
        try {
            jasperPrint = JasperFillManager.fillReport(src, params, connection);
        } catch (JRException ex) {
            System.out.println("Erro jasperPrint\n");
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JasperViewer view = new JasperViewer(jasperPrint, false);
        
        view.setVisible(true);        
    }
}
