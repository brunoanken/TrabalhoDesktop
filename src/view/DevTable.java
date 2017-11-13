package view;

import controller.DesenvolvedoraDAO;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DevTable {
    
    private static String cabecalho[] = {"Nome", "País de Origem", 
        "Data de Fundação", "Bibliotecas", "Frameworks"};
    private static String[][] dadosTabela;
    
    void ExibeTabela() throws SQLException {
        
        DesenvolvedoraDAO devDao = new DesenvolvedoraDAO();
        devDao.acessaBD();
        ResultSet rs = null;
        System.out.println(rs.getString(1));
        String aux[] = null;
        
        while(rs.next()){
            System.out.print(rs.getString(1));
            System.out.print(": ");
            System.out.println(rs.getString(2));
        }
        
        //JTable EndTable = new JTable(conteudo, cabecalho);
        JFrame frame = new JFrame("Desenvolvedoras");
                
        JButton salvar = new JButton("Salvar");
        salvar.setVisible(true);
        salvar.setBounds(50,100,95,30);
        frame.getContentPane().add(salvar);
        
    }     
}
