import java.util.Scanner;

public class saldobanco {

    int conta = 500;

    void saque(int saque) {
        if (saque > conta) {
            System.out.println("Saldo insuficiente");
            System.out.println("valor em conta e " + conta);
        } else {
            System.out.println("SAQUE REALIZADO COM SUCESSO");
            conta -= saque;
            System.out.println("SALDO AINDA DISPONIVEL" + "(" + conta + " reais)");
        }

    }

    int consultarSaldo() {
        return conta;

    }

    public static void main(String[] args) {
        System.out.println("---BANCO ---");
        System.out.println("DIGITE A QUANTIDADE QUE DESEJA SACAR! ");
        Scanner leitor = new Scanner(System.in);
        saldobanco saque = new saldobanco();
        saque.saque(leitor.nextInt());

        leitor.close();
    }
}