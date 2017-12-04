package view;

import controller.DesenvolvedoraDAO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public ShowDev(String[][] conteudo){
        
        JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Desenvolvedoras");
        frame.setLayout(new BorderLayout());
        
        int height = EndTable.getRowHeight() * (EndTable.getRowCount() + 2);
        
        //botão para o cadastro de novas entradas
        JButton cadastro = new JButton("Novo cadastro");
        cadastro.setVisible(true);
        cadastro.setBounds(0, height, 150, 50);
        frame.getContentPane().add(cadastro);
        
        cadastro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CadDesenvolvedora().setVisible(true); 
            }
        });
        
        //botão para gerar um relatório geral
        JButton relatorioGeral = new JButton("Gerar Relatório Geral");
        relatorioGeral.setVisible(true);
        relatorioGeral.setBounds(150, height, 150, 50);
        frame.getContentPane().add(relatorioGeral);
        
        relatorioGeral.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new DesenvolvedoraDAO().createPDF();
            }
        });      
        
        //botão para gerar um relatório com procura por nome
        JButton relatorioNome = new JButton("Relatório por Nome");
        relatorioNome.setVisible(true);
        relatorioNome.setBounds(300, height, 150, 50);
        frame.getContentPane().add(relatorioNome);
        
        relatorioNome.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new ReportLinguagem(2).setVisible(true);
            }
        });  
        
        //botão que executa as ações para o BD
        JButton salvarBD = new JButton("Salvar");
        salvarBD.setVisible(true);
        salvarBD.setBounds(150, height + 50, 150, 50);
        frame.getContentPane().add(salvarBD);
        
        salvarBD.addActionListener(new ActionListener(){
            
            DesenvolvedoraDAO devDao = new DesenvolvedoraDAO();
            @Override
            public void actionPerformed(ActionEvent ae) {
                for(int i = 0; i < EndTable.getRowCount(); i++){
                    //caso tenha deletado o nome, apaga do BD
                    if(EndTable.getModel().getValueAt(i, 0).equals("")){
                        devDao.deletar(Integer.parseInt((String) conteudo[i][5]));
                    } else { //senão dá update ALWAYS
                        System.out.println(conteudo[i][0]);
                        devDao.atualizar(conteudo[i]);
                    }
                }
                    
            }
            
        });
        
        frame.getContentPane().add(new JScrollPane(EndTable));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);        
    }
    

    
    public static void main(String[] args) {
        
       
    }  
}
