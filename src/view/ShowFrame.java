package view;

import controller.LinguagemProgramacaoDAO;
import controller.ProdutoDAO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Desenvolvedora;

public class ShowFrame {
    private static ArrayList<Desenvolvedora> devs;
    private static String cabecalho[] = {"Nome", "Data de Lançamento", 
        "Versão Estável", "Linguagens", "Tipo"};
    private static String[][] dadosTabela;
    
    int bibframe;
    
    public ShowFrame(String[][] conteudo, int bibframe){
        
        this.bibframe = bibframe;
        
        JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Frameworks/Bibliotecas");
        
        
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
                if(bibframe == 1){
                    new CadFramework().setVisible(true);
                } else {
                    new CadBIblioteca().setVisible(true); 
                }
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
                new ProdutoDAO(bibframe).createPDF();
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
                if(bibframe == 1){
                    new ReportLinguagem(3).setVisible(true);
                } else {
                    new ReportLinguagem(4).setVisible(true);
                }                
            }
        }); 
        
        //salvar alterações no BD
        JButton salvarBD = new JButton("Salvar");
        salvarBD.setVisible(true);
        salvarBD.setBounds(150, height + 50, 150, 50);
        frame.getContentPane().add(salvarBD);
        
        salvarBD.addActionListener(new ActionListener(){
            
            ProdutoDAO devDao = new ProdutoDAO(bibframe);
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
