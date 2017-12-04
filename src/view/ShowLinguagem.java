package view;

import controller.DesenvolvedoraDAO;
import controller.LinguagemProgramacaoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Desenvolvedora;

public class ShowLinguagem {
    
    private static ArrayList<Desenvolvedora> devs;
    private static String cabecalho[] = {"Nome", "Data de Lançamento", "Versão Estável"};
    private static String[][] dadosTabela;
    
    public ShowLinguagem(String[][] conteudo){
        System.out.println(conteudo[0][4]);
        JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Linguagens de Programação");
        
        //botão para o cadastro de novas entradas
        JButton cadastro = new JButton("Novo cadastro");
        cadastro.setVisible(true);
        cadastro.setBounds(100, 100, 100, 100);
        frame.getContentPane().add(cadastro);
        
        cadastro.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                new CadLinguagem().setVisible(true); 
            }            
        });
        
        //botão para gerar um relatório geral
        JButton relatorioGeral = new JButton("Gerar Relatório Geral");
        relatorioGeral.setVisible(true);
        relatorioGeral.setBounds(200, 100, 100, 100);
        frame.getContentPane().add(relatorioGeral);
        
        relatorioGeral.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new DesenvolvedoraDAO().createPDF();
            }
        });    
        
        JButton salvarBD = new JButton("Salvar");
        salvarBD.setVisible(true);
        salvarBD.setBounds(100,200,190,60);
        frame.getContentPane().add(salvarBD);
        
        salvarBD.addActionListener(new ActionListener(){
            
            LinguagemProgramacaoDAO devDao = new LinguagemProgramacaoDAO();
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
    
}
