package view;

import controller.ProdutoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.LinguagemProgramacao;
import model.Produto;

public class ShowBibTxt {
    private static ArrayList<LinguagemProgramacao> devs;
    private static String cabecalho[] = {"Nome", "Data de Lançamento", 
        "Versão Estável", "Linguagem", "Tipo"};
    private static String[][] dadosTabela;
    
    public ShowBibTxt(String[][] conteudo){
        JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Bibliotecas");
                
        JButton salvar = new JButton("Salvar .txt");
        salvar.setVisible(true);
        salvar.setBounds(50,100,95,30);
        
        frame.getContentPane().add(salvar);
        
        salvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ProdutoDAO devDao = new ProdutoDAO(0);
                ArrayList<Produto> devs = new ArrayList<Produto>();
                
                //lê todas as linhas e verifica se o nome está vazio
                //se o nome estiver vazio, não grava o mesmo nem o resto das colunas no array
                for(int i = 0; i < EndTable.getRowCount(); i++){
                    if(!EndTable.getModel().getValueAt(i, 0).equals("")){
                        //caso o nome não esteja vazio, cria um objeto Desenvolvedora
                        //com as informações da linha
                        Produto dev = new Produto();
                        
                        dev.setName((String) EndTable.getModel().getValueAt(i,0));
                        dev.setRelease((String) EndTable.getModel().getValueAt(i,1));
                        dev.setStable((String) EndTable.getModel().getValueAt(i,2));
                        dev.setLanguages((String) EndTable.getModel().getValueAt(i,3));
                        dev.setTipo((String) EndTable.getModel().getValueAt(i,4));  
                        
                        //adiciona o objeto no arraylist devs
                        devs.add(dev);
                    }                    
                }
                
                //chama a DesenvolvedoraDAO para sobrescrever o arquivo original
                //com as informações no arraylist desta classe aqui
                try {
                    devDao.gravar(devs);
                } catch (IOException ex) {
                    Logger.getLogger(ShowDev.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                frame.dispose();
            }
        });        
        
        frame.getContentPane().add(new JScrollPane(EndTable));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);        
    }
}
