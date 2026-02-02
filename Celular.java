import java.util.Scanner;
public class Celular {
    String modelo;
    private int bateria = 100;

    Celular(String m) {
        modelo = m;
    }

    void carregar(int valor) {
        if (valor + bateria >= 100) {
            bateria = 100;
            System.out.println("BATERIA RECARREGADA COMPLETAMENTE");
        } else if (valor + bateria < 100) {
            System.out.println((bateria += valor) + "% - -" + "CARREGANDO");
        }

    }

    void descarregando(int valor) {
        if (valor >= bateria) {
            bateria = 0;
            System.out.println("BATERIA DESCARREGADA COMPLETAMENTE");
        } else {
            bateria -= valor;
            System.out.println("RESTAM APENAS " + bateria + "%.");
        }
    }

     public int getbateria() {
        return bateria;
    }

  
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        // 1. CRIAMOS O OBJETO FORA DO LOOP
        // Se criarmos dentro, ele reseta a bateria toda vez que o menu volta!
        Celular meuIphone = new Celular("iPhone 15");

        System.out.println("--- INICIANDO SISTEMA " + meuIphone.modelo + " ---");

        // 2. O LOOP DO MENU (O Jogo começa aqui)
        while (true) {
            System.out.println("\nO que voce deseja fazer?");
            System.out.println("1 - Usar Instagram (Gasta 10%)");
            System.out.println("2 - Jogar Pesado (Gasta 50%)");
            System.out.println("3 - Carregar um pouco");
            System.out.println("4 - Ver Porcentagem");
            System.out.println("0 - Sair/Desligar");
            System.out.print("Escolha: ");
            
            int opcao = leitor.nextInt();

            // 3. A LÓGICA DE ESCOLHA
            if (opcao == 0) {
                System.out.println("Encerrando sistema...");
                break; // O comando 'break' quebra o loop infinito e sai.
            } 
            
            else if (opcao == 1) {
                // Chama o método de gastar
                meuIphone.descarregando(10);
            } 
            
            else if (opcao == 2) {
                // Chama o método de gastar muito
                meuIphone.descarregando(50);
            } 
            
            else if (opcao == 3) {
                System.out.println("Quanto quer carregar?");
                int carga = leitor.nextInt();
                meuIphone.carregar(carga);
            } 
            
            else if (opcao == 4) {
                // Aqui usamos o RETURN!
                int valorAtual = meuIphone.getbateria();
                System.out.println("STATUS: A bateria está em " + valorAtual + "%");
            } 
             if(meuIphone.bateria <= 0){
                System.out.println("Celular descarregado");
            break;
            }
            
            else {
                System.out.println("Opção inválida!");
            }

            
        }
    }
}


