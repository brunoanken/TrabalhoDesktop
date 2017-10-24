package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Desenvolvedora;

public class DesenvolvedoraDAO {
    
    //gravação de arquivo texto    
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
    
    //lê (DÃ) os dados no arquivo e imprime no console
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
        
//        System.out.println("Leitura das empresas desenvolvedoras já gravadas.\n");
//        for(int i = 0; i < this.devs.size(); i++){
//            System.out.println(                    
//                    "Nome: " + this.devs.get(i).getName()+
//                    ", País de origem: " + this.devs.get(i).getOrigin()+
//                    ", Data de fundação: " + this.devs.get(i).getFoundation()+
//                    ", Bibliotecas produzidas: " + this.devs.get(i).getBibliotecas()+
//                    ", Frameworks produzidos: " + this.devs.get(i).getFrameworks());
//        }        
        return this.devs.size();
    }
    
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
   
   
   //binário
   
//       public static ObjectOutputStream CriaEscritorBinario(File arquivo, boolean append) {
//        ObjectOutputStream out = null;
//
//        try {
//            //FileOutputStream fos = new FileOutputStream(arquivo, append);
//            //out = new ObjectOutputStream(fos);
//            
//            if (arquivo.exists()) {
//                FileOutputStream fos = new FileOutputStream(arquivo, append);
//                out = new ObjectOutputStream(fos) {
//                    @Override
//                    protected void writeStreamHeader() {
//                        // do not write a header
//                    }
//                };
//            } else {
//                FileOutputStream fos = new FileOutputStream(arquivo, append);
//                out = new ObjectOutputStream(fos);
//            }
//             
//        } catch (IOException erro) {
//            System.out.println("Erro ao criar arquivo. " + erro);
//        }
//        return out;
//       }
//       
//        public static ObjectInputStream CriaLeitorBinario(File arquivo) {
//            ObjectInputStream ois = null;
//            try {
//                FileInputStream fis = new FileInputStream(arquivo);
//                ois = new ObjectInputStream(fis);
//            } catch (IOException erro) {
//                System.out.println("Erro ao ler arquivo. " + erro);
//            }
//            return ois;
//        }
//        
//        public static void EscreveObjeto(ObjectOutputStream oos, Object obj, boolean flush) {
//        try {
//            oos.writeObject(obj);
//            if (flush) {
//                oos.flush();
//            }
//            System.out.println("Escrevendo objeto: " + obj.toString());
//            System.out.println("");
//        } catch (IOException erro) {
//            System.out.println("Erro na escrita. " + erro);
//        }
//    }
//        
//        public static Object LeObjeto(ObjectInputStream ois) {
//        Object objlido = null;
//        try {
//            objlido = ois.readObject();
//            System.out.println("Lendo objeto");
//        } catch (ClassNotFoundException erro) {
//            System.out.println("Classe nao encontrada. " + erro);
//        } catch (java.io.EOFException erro) {
//            System.out.println("Final de arquivo. " + erro);
//        } catch (IOException erro) {
//            System.out.println("Erro na leitura do objeto. " + erro);
//        } finally {
//            return objlido;
//        }
//    }
//        
//    public static void imprimeDev(Desenvolvedora dev) {
//        if (dev != null) {
//            System.out.println("Imprimindo dados.");
//            System.out.println(dev.toString());
//        } else {
//            System.out.println("Cliente nulo.");
//        }
//    }
//    
//    public static void principal(Desenvolvedora dev){
//        File arquivo = new File("e2.obj");
//        ObjectOutputStream escritor = CriaEscritorBinario(arquivo, true);
//        EscreveObjeto(escritor, dev, true);
//
//        ObjectInputStream leitor = CriaLeitorBinario(arquivo);
//        Desenvolvedora c4 = (Desenvolvedora) LeObjeto(leitor);
//        imprimeDev(c4);
//        
//        Desenvolvedora c5 = (Desenvolvedora) LeObjeto(leitor);
//        imprimeDev(c5);
//        
//        Desenvolvedora c6 = (Desenvolvedora) LeObjeto(leitor);
//        imprimeDev(c6);
//
//
//    }
}
