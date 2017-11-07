package controller;

import java.io.*;
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
                System.out.println(aux.getBibliotecas());
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

}
