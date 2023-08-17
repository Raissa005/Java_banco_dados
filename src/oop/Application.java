package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Application {

    public List<Account> listAccount;

    public void init() {
          System.out.println("Iniciando aplicação...");

        try{
            Connection conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/MyBank",
                    "postgres", "senai");
                    
                   PreparedStatement pstm = conexao.prepareStatement("SELECT count (*) as count_id FROM tb_account "); 
                   
                   ResultSet rs = pstm.executeQuery();
                   rs.next();
                   
                   if(rs.getInt("count_id")==0){
                       Statement st = conexao.createStatement();
                       st.execute("INSERT INTO tb_account VALUES (1, 'Filipe', 100201);");
                       st.execute("INSERT INTO tb_account VALUES (2, 'Thiago', 100202);");
                       st.execute("INSERT INTO tb_account VALUES (3, 'Eric', 100203);");
                       st.execute("INSERT INTO tb_account VALUES (4, 'Kauê', 100204);");
                       st.close();
                   }
                   
                   rs.close();
                   pstm.close();
                   conexao.close();
                   
        }catch(SQLException ex){
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      /*  listAccount = new ArrayList<>();
        

        {//populate listAccount
            Account acc = new Account("Filipe", 213);
//            1000
//            acc.numberAccount = 213;
//            acc.ownerName = "Filipe";
            listAccount.add(acc);
        }

        {//populate listAccount
            Account acc = new Account("Thiago", 214);

//            
//            acc.numberAccount = 214;
//            acc.ownerName = "Thiago";
            listAccount.add(acc);
        }

        {//populate listAccount
            Account acc = new Account("Erik", 215);

//            acc.numberAccount = 215;
//            acc.ownerName = "Erik";
            listAccount.add(acc);
        }

        {//populate listAccount
            Account acc = new Account("Kauê", 216);

//            acc.numberAccount = 216;
//            acc.ownerName = "Kauê";
            listAccount.add(acc);
        }*/

    }

    public void run() {

        boolean keepRuning = true;

        while (keepRuning) {

            String answer;

            answer = showMenu();

            switch (answer) {
                case "1":
                    //createNewAccount();
                    break;
                case "2":
                    //lookAccount();
                    break;
                case "3":
                    withdraw();
                    break;
                case "4":
                    deposit();
                    break;
                case "5":
                    keepRuning = false;
                    break;
                default:
                    showMessage("Opção inválida, tente novamente");
                    break;
            }

//        for (int i = 0; i < listAccount.size(); i++) {
//
////            System.out.println(listAccount.get(i).getOwnerName().equals("Thiago"));
//            if (listAccount.get(i).getOwnerName().equals("Thiago")) {
//
//                System.out.println(listAccount.get(i).getAccountNumber() + " // " + listAccount.get(i).getOwnerName());
//                
//                listAccount.get(i).deposit(1000000.0);
//                System.out.println(listAccount.get(i).getAccountBalance());
//             
//                Double newBalance = listAccount.get(i).withdraw(973000.0);
//                System.out.println(listAccount.get(i).getAccountBalance());
//                System.out.println(newBalance);
//                
//            }
//
//        }
        }

    }

    private void showMessage(String message) {

        JOptionPane.showMessageDialog(null, message);

    }

    private void deposit() {

        int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta"));

        Account acc = null;

        for (int i = 0; i < listAccount.size(); i++) {

            if (listAccount.get(i).getAccountNumber().equals(accountNumber)) {

                acc = listAccount.get(i);

            }

        }

        if (acc != null) {

            Double value = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor que você deseja depositar?"));

            acc.deposit(value);

        } else {

            showMessage("Erro: Conta não encontrada! Operação cancelada.");

        }

    }

    private void withdraw() {
        
        int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta"));
        String accountOwner = JOptionPane.showInputDialog("Digite o nome do titular da conta");

        Account acc = null;

        for (int i = 0; i < listAccount.size(); i++) {

            if (listAccount.get(i).getAccountNumber().equals(accountNumber) && listAccount.get(i).getOwnerName().equals(accountOwner)) {

                acc = listAccount.get(i);

            }

        }

        if (acc != null) {

            Double value = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor que você deseja sacar?"));

            Double newBalance = acc.withdraw(value);
            
            if(newBalance == -1.0){
            
                showMessage("Valor indisponível para saque!");
               
            }else{
                
                showMessage("Você ainda tem disponível o valor de: R$ "+newBalance);
                
            }

        } else {

            showMessage("Erro: Conta não encontrada! Operação cancelada.");

        }
        
    }

    private String showMenu() {

        String menu = "MyBank\n1 - Cria conta\n2 - Consultar conta\n3 - Sacar\n4 - Depositar\n5 - Sair";
        return JOptionPane.showInputDialog(menu);
        
    }

}
