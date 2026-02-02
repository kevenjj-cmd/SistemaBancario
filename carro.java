public class carro {
    // ATRIBUTOS
    String modelo;
    String cor;
    int ano;

    boolean estaLigado;
    int velocidade;

    // CONSTRUTOR
    carro(String m, String c, int a) {

        modelo = m;
        cor = c;
        ano = a;
    }

    // METODOS(oque faz)
    void ligar() {
        if (estaLigado == true) {
            System.out.println("O CARRO JA ESTA LIGADO!");
        } else {
            System.out.println("LIGANDO O MOTOR! ");
            estaLigado = true;
        }
    }

    void desligar() {
        if (estaLigado == true) {
            System.out.println("DESLIGANDO O MOTOR");
            estaLigado = false;
        } else {
            System.out.println("O MOTOR JA ESTA DESLIGADO! ");
        }
    }

    void acelerar(int valor) {
        if (estaLigado == true) {
            velocidade += valor;
            System.out.println("V: " + velocidade);

        } else {
            System.out.println("LIGUE O MOTOR PRIMEIRO!");
        }

    }

    void frear(int valor) {
        if (velocidade >= valor) {
            velocidade -= valor;
            System.out.println("V: " + velocidade);
        } else {
            velocidade = 0;
            System.out.println("V: " + velocidade);
        }
    }

    int valorVelocidade() {
        return velocidade;
    }

    public static void main(String[] args) {
        carro car1 = new carro("ferrai", "Vermelha", 2025);
        carro car2 = new carro("fusca", "azul", 1975);
        car2.ligar();
        car2.acelerar(50500);
        car2.frear(50450);
        car1.desligar();
        int velicidadeTotal = car2.valorVelocidade();
    }

}