
package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class FilmeDao 
{
    
    Statement stmt = null;
     Connection con = null;
      Filme filme = new Filme();
       
    private ArrayList<Filme> filmes;
    private DefaultTableModel model;

    
    
    

    
    

    
      
   
     
       
       
       
       
       
       
       
  //====================== ============ CONEXÃO COM O MYSQL=============================    
       
     public void conectar()
    {
        
        
          
              //CONEXÃO COM O BANCO DE DADOS//
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver" );
          System.out.println( "Driver JDBC carregado" );
          
        }
        catch(ClassNotFoundException cnfe)
                {
                     System.out.println( "Driver JDBC nao encontrado : " +
                               cnfe.getMessage() );
                }
            
        
        
        try
        {
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix","root","2960412032.");
            System.out.println( "Conexao com o banco de dados estabelecida." );
        }
        catch(SQLException sqle)
                {
                     System.out.println( "Erro na conexao ao Bando de Dados : " +
                                        sqle.getMessage() );
                }
        
       //======================================================================================================== 
        
        
       
                                        //STATEMENT
       
          Statement stmt = null;
       try
       {
          stmt = con.createStatement();
           System.out.println( "Pronto para execucao de comandos sql." );
       }
       catch(SQLException sqle)
               {
                    System.out.println( "Erro no acesso ao Bando de Dados : " +
                                        sqle.getMessage() );
               }
       
        }
    
    
     //====================================================================================   
     
     
     
     
     
     
     //==================================== MÉTODO DE CONSULTAR OS FILMES NO DB==================
     public void consulta( DefaultTableModel model,Filme filme)
     {
         
         if(filme.getCategoriaFil().isEmpty())
         {
             
         
         
         try
         {
           
            String sql = "SELECT*FROM filmes where id = " + filme.getId(); // Consulta SQL
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            
            
           // Adicionar dados à tabela
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String datalancamento = rs.getString("datalancamento");
                String categoria = rs.getString("categoria");
                 
                  // Adicionando os dados ao modelo
                 
                 
                model.addRow(new Object[]{id, nome,datalancamento,categoria});
                  
                  
            
            }
           
            
         }
         catch(SQLException sqle)
         {
              JOptionPane.showMessageDialog(null,"Ocorreu um erro na busca,tente novamente!");
         }
         }
          
        
         
         else
         {
              
         try
         {
           
            String sql = "SELECT*FROM filmes where id = " + filme.getId() + " && " + " categoria = " + "'" +filme.getCategoriaFil() + "'"; // Consulta SQL
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            
            
           // Adicionar dados à tabela
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String datalancamento = rs.getString("datalancamento");
                String categoria = rs.getString("categoria");
                 
                  // Adicionando os dados ao modelo
                 
                 
                model.addRow(new Object[]{id, nome,datalancamento,categoria});
                  
                  
            
            }
           
            
         }
         catch(SQLException sqle)
         {
              JOptionPane.showMessageDialog(null,"Ocorreu um erro na busca,tente novamente!");
         }
             
             
             
             
             
             
         }
         
         
         
         
         
     }
    
    
   //==========================================================================================  
     
     
     
     
     
     
    //=========================================MÉTODO DE ATUALIZAR O FILME========================== 
    public void atualizar(Filme filme)
    {
           
          Statement stmt = null;
       try
       {
          stmt = con.createStatement();
           System.out.println( "Pronto para execucao de comandos sql." );
       }
       catch(SQLException sqle)
               {
                    System.out.println( "Erro no acesso ao Bando de Dados : " +
                                        sqle.getMessage() );
               }
       
         
       String sql = "update filmes set nome =" + "'" + JOptionPane.showInputDialog("Novo nome:")+ "'" + " where id = "+ filme.getIdAt();
        try
        {
         
            
            
            try
            {
            if(JOptionPane.showInputDialog("Novo nome:").isEmpty())
            {
               JOptionPane.showMessageDialog(null, "O campo está vazio");
            }
            else
            {
            stmt.executeUpdate(sql);
           JOptionPane.showMessageDialog(null,"Nome do filme atualizado!");
            }
            }
            catch(NullPointerException ex)
            {
                 JOptionPane.showMessageDialog(null, "O campo está vazio");
            }
             
        }
        catch(SQLException sqle)
        {
    JOptionPane.showMessageDialog(null,"Erro na atualização!");
        }
        
    
    }
    //===========================================================================================
    
    
    
    
    
    
    
    
    
    
  //====================================DESCONECTA DO MYSQL==========================================  
    
    public void desconectar()
    {
        
           //FECHAMENTO  
try {
    con.close();
    System.out.println( "Conexão com o banco de dados fechada" );
} catch (SQLException sqle) {
    System.out.println( "Erro no fechamento da conexão : " + sqle.getMessage() );
}
    }
   //============================================================================================= 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //===========================================EXCLUIR FILME=================================================
    
    public void excluir()
    {
        
              
          Statement stmt = null;
       try
       {
          stmt = con.createStatement();
           System.out.println( "Pronto para execucao de comandos sql." );
       }
       catch(SQLException sqle)
               {
                    System.out.println( "Erro no acesso ao Bando de Dados : " +
                                        sqle.getMessage() );
               }
       
       
       
        try
        {
        String sql = "delete from filmes where id = " + filme.getIdEx();
        stmt.executeUpdate(sql);
        
          JOptionPane.showMessageDialog(null,"Filme excluído!");
        }
        catch(SQLException sqle)
        {
              JOptionPane.showMessageDialog(null,"Ocorreu um erro, tente novamente!");
        }
        
    }
    
    //=============================================================================================================
    
    
    
    
    
    
}
