package view;

import controller.DesenvolvedoraDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Desenvolvedora;

/**
 *
 * @author anken
 */
public class ShowDev {
    
    private static ArrayList<Desenvolvedora> devs;
    private static String cabecalho[] = {"Nome", "País de Origem", 
        "Data de Fundação", "Bibliotecas", "Frameworks"};
    private static String[][] dadosTabela;
    
    public ShowDev(Object[][] conteudo){
        
        JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Desenvolvedoras");
        
        JButton salvar = new JButton("Salvar");
        salvar.setVisible(true);
        salvar.setBounds(50,100,95,30);
        frame.getContentPane().add(salvar);
        salvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Caralho!");                
            }
        });
        
        frame.getContentPane().add(new JScrollPane(EndTable));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        
    }
    

    
    public static void main(String[] args) {
        
       
    }  
}
