/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Desenvolvedora;

/**
 *
 * @author anken
 */
public class Teste {
    
    public void deletarBinario(){
        File f = new File("devs.obj");
        f.delete();
    }
    
    public void salvarBinario(Desenvolvedora dev){
        FileOutputStream fos;
        ArrayList<Desenvolvedora> devs = lerBinario();
        //se o retorno de lerBinario() for null
        
        if(devs == null){
            try {
                System.out.println("aqui");
                fos = new FileOutputStream("devs.obj");
                ObjectOutputStream out = new ObjectOutputStream(fos);
                devs.add(dev);
                out.writeObject(devs);
                System.out.println("Dados salvos com sucesso");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //se tudo correr bem
            devs.add(dev);

            try{
                fos = new FileOutputStream("devs.obj");
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(devs);
                System.out.println("Dados salvos com sucesso");
                devs = lerBinario();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        
           
    }
    
    //lê o arquivo binário
    public ArrayList<Desenvolvedora> lerBinario(){
         ObjectInputStream ois = null;
         ArrayList<Desenvolvedora> devs = new ArrayList<Desenvolvedora>();
         
        try {
            File arq = new File("devs.obj");
            if(!arq.exists()){                
                return null;
            } else {
                FileInputStream fis = new FileInputStream("devs.obj");
                try{
                    devs = (ArrayList<Desenvolvedora>) ois.readObject();
                    return devs;
                } catch (IOException ex){
                    devs.add((Desenvolvedora) ois.readObject());
                    return null;
                }
            }            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DesenvolvedoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
        return null;
    }
    
    public String[][] binObj(ArrayList<Desenvolvedora> devs){
        if(devs == null){
            System.out.println("binObj devs == null");
        }
        String[][] dados = new String[devs.size()][];
        for(int i = 0; i < devs.size(); i++){
            String aux[] = {
                devs.get(i).getName(), devs.get(i).getOrigin(), devs.get(i).getFoundation(),
                devs.get(i).getBibliotecas(), devs.get(i).getFrameworks()
            };
            dados[i] = aux;
        }        
        return dados;
    }
}
