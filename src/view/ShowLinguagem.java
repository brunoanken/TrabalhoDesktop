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
    private static String cabecalho[] = {"Nome", "Data de Lançamento", 
        "Versão Estável", "Bibliotecas", "Frameworks"};
    private static String[][] dadosTabela;
    
    public ShowLinguagem(String[][] conteudo){
        System.out.println(conteudo[0][4]);
        JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Linguagens de Programação");
        
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
