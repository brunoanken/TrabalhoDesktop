package controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Desenvolvedora;

public class DesenvolvedoraDAO {
    
    //seção de gravação de arquivo texto    
    
    private ArrayList<Desenvolvedora> devs = new ArrayList<Desenvolvedora>();
    private File fileDev = new File("desenvolvedoras.txt");
    
    private Connection connection = null;
    private PreparedStatement pstdados = null;
    
    //cria, verifica e grava os dados no arquivo
    //função utilizada pra criar uma nova entrada
    public void criarArquivo(Desenvolvedora dev) throws IOException{
        
        //realiza a leitura do conteúdo do arquivo
        this.ler();
        
        try {
            for(int i = 0; i < devs.size(); i++){
                if(devs.get(i).getName().equals(dev.getName())){
                    //limpa o arraylist
                    devs.clear();
                    //se há erro, joga a exceção
                    throw new IOException(); 
                }
            }
            //adiciona o objeto dev (caso não haja erro) no array devs
            devs.add(dev);
            //grava o array no arquivo texto
            gravar(devs);            
        } catch(IOException erro) {
            System.out.println(erro);
        }
    }
    
    //Grava os dados no arquivo texto
    public void gravar(ArrayList<Desenvolvedora> devs) throws IOException{
       
       FileWriter escritor = new FileWriter(fileDev, false);
       BufferedWriter escritorBuff = new BufferedWriter(escritor);
       
       for(int i = 0; i < devs.size(); i++){
            escritorBuff.write(
                devs.get(i).getName()+","+
                devs.get(i).getOrigin()+","+
                devs.get(i).getFoundation()+","+
                devs.get(i).getBibliotecas()+","+
                devs.get(i).getFrameworks()+"\n");
       }
        escritorBuff.flush();
        escritorBuff.close();
    }
    
    //lê (DÃ) os dados no arquivo
    public int ler() throws FileNotFoundException, IOException{

        File arquivo = new File("desenvolvedoras.txt");
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
        FileReader leitor = new FileReader(arquivo);
        BufferedReader leitorBuff = new BufferedReader(leitor);
        String line;
        
        while((line = leitorBuff.readLine())!=null){
            String [] devs = line.split(",");            
            Desenvolvedora dev = new Desenvolvedora();
            dev.setName(devs[0]);
            dev.setOrigin(devs[1]);
            dev.setFoundation(devs[2]);
            dev.setBibliotecas(devs[3]);
            dev.setFrameworks(devs[4]);
            this.devs.add(dev);
        }
        leitorBuff.close();
        
        return this.devs.size();
    }
    
    //retorna os dados gravados em forma de um object string
    public String[][] retornaTexto(int tam) throws FileNotFoundException, IOException{
        
        File arquivo = new File("desenvolvedoras.txt");
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
    
    
   
   // --**--
    
   //binário
    
    public void testeBinario(){
        FileOutputStream fos;
        Desenvolvedora dev = new Desenvolvedora();
        dev.setName("A");
        dev.setBibliotecas("Bib");
        dev.setFoundation("Foundation");
        dev.setFrameworks("Frame");
        dev.setOrigin("Amanhã");
        try {
            fos = new FileOutputStream(new File("devs.obj"));
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(dev);
            
            ObjectInputStream ois = null;
            try{
                FileInputStream fis = new FileInputStream(new File("devs.obj"));
                ois = new ObjectInputStream(fis);
                Desenvolvedora aux = (Desenvolvedora)ois.readObject();
                System.out.println(aux.getFrameworks());
            } catch(FileNotFoundException ex) {
                System.out.println("deu ruim ao ler o arquivo");
            } catch (ClassNotFoundException ex) {
                System.out.println("deu ruim ao ler o objeto");
                Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //  --**--
    //BD
    
    //realiza a conexão com o BD
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
    
    //realiza a leitura de dados do BD
    public String[][] lerBD(){
        
        acessaBD();
                
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        String[][] stringArray = null;
                
        try{
            pst = connection.prepareStatement("SELECT * FROM desenvolvedoras");
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
                Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringArray;
    }
    
    public void inserir(Desenvolvedora dev){
        try {
            
            acessaBD();
            
            String sql = "INSERT INTO desenvolvedoras (name, origin, foundation, bibliotecas, frameworks) VALUES (?,?,?,?,?)";
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sql, tipo, concorrencia);            
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            pstdados.setString(1, dev.getName());
            pstdados.setString(2, dev.getOrigin());
            pstdados.setString(3, dev.getFoundation());
            pstdados.setString(4, dev.getBibliotecas());
            pstdados.setString(5, dev.getFrameworks());
            
            
            int rowsInserted = pstdados.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar(int id){
        try{
            acessaBD();
            
            String sql = "DELETE FROM desenvolvedoras WHERE id=?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizar(String[] dado){
        try {
            acessaBD();
            
            String sql = "UPDATE desenvolvedoras SET name=?, origin=?, foundation=?, bibliotecas=?, frameworks=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dado[0]);
            statement.setString(2, dado[1]);
            statement.setString(3, dado[2]);
            statement.setString(4, dado[3]);
            statement.setString(5, dado[4]);
            statement.setInt(6, Integer.parseInt(dado[5]));
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
